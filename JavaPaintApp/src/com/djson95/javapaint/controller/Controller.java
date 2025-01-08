package com.djson95.javapaint.controller;

import java.awt.Color;

import com.djson95.javapaint.events.DrawingEvent;
import com.djson95.javapaint.model.Drawing;
import com.djson95.javapaint.model.chainOfResponsibility.*;
import com.djson95.javapaint.model.enums.MenuEvent;
import com.djson95.javapaint.model.enums.MouseAction;
import com.djson95.javapaint.view.DrawingPanelAdapter;
import com.djson95.javapaint.view.PaintFrame;
import se.kau.isgc08.lab4.model.DrawingComposite;
import se.kau.isgc08.lab4.model.DrawingShape;
import se.kau.isgc08.lab4.model.Line;

/**
 * Den här controller-klassen ansvarar för att sköta interaktionen mellan
 * logiken i ritningen och användargränssnittet.<br/>
 * Controllern initialiserar ritningen, sätter upp gränssnittet och hanterar
 * användarinteraktioner som mushändelser och menyhändelser.
 */
public class Controller {
	
	private final Drawing drawing;
	private final DrawingPanelAdapter drawingPanel;
	private final PaintFrame paintFrame;
	private DrawingComposite selectedShape;
	private final AbstractShapeHandler responsibilityChain;
	
	public Controller() {
		drawing = new Drawing();

		// Sätt upp lite testformer
		drawing.addCircle(20, 20, 50, 50, 2, Color.BLACK, Color.WHITE);

		drawing.addRectangle(100, 100, 100, 100, 2, Color.BLACK, Color.WHITE);

		drawing.addLine(100, 50, 200, 50, 5, Color.BLUE);
		
		drawingPanel = new DrawingPanelAdapter(drawing.getRootElement(), this);
		paintFrame = new PaintFrame(this, drawingPanel);

		// Sätt upp chain of responsibility
		AbstractShapeHandler resizeHandler = new ResizeShapeHandler();
		AbstractShapeHandler moveHandler = new MoveShapeHandler();
		AbstractShapeHandler deleteHandler = new DeleteShapeHandler(drawing.getRootElement());

		// Länka ihop delarna
		resizeHandler.setNext(moveHandler);
		moveHandler.setNext(deleteHandler);
		responsibilityChain = resizeHandler;
	}

	/**
	 * Den här metoden tar emot händelser som har att göra med musen i DrawingPanel
	 * och delegarar vidare till chain of responsibility om man vill förändra en markerad figur.
	 * @param event Händelsen som utfördes
	 */
	public void handleDrawingEvent(DrawingEvent event) {
		// TODO: Här vill vi nog sköta placering av element också

		// Ta reda på vilken form som valts om man gjort ett tryck (eller vill radera med högerklick)
		if (event.action() == MouseAction.PRESSED) {
			selectedShape = drawing.getRootElement().findShapeAtPoint(event.position());
		}
		// Markera figur
		if (event.action() == MouseAction.PRESSED && selectedShape != null) {
			drawingPanel.setSelectedElement(selectedShape);
			// Nu kan man formattera den valda figuren
			paintFrame.setFormatMenuEnabled(true);
			paintFrame.setLayerMenuEnabled(true);
		}
		// Avmarkera figur
		if (event.action() == MouseAction.RIGHT_CLICK
				|| (event.action() == MouseAction.PRESSED && selectedShape == null)) {
			drawingPanel.setSelectedElement(null);
			// Finns ingen vald figur att formattera
			paintFrame.setFormatMenuEnabled(false);
			paintFrame.setLayerMenuEnabled(false);
		}

		// Är selected shape null här borde PlaceShapeHandler placera ut en figur
		responsibilityChain.handle(event, selectedShape);

		paintFrame.repaint();
	}

	/**
	 * Den här metoden hanterar händelser som sker i menyer.
	 * @param menuEvent Händelsen som skedde
	 */
	public void handleMenuEvent(MenuEvent menuEvent) {
		switch (menuEvent) {
			case SAVE: {
				// TODO: Fixa sparning av fil
				break;
			}
				
			case SAVE_AS: {
				// TODO: Fixa sparning av ny fil
				break;
			}
				
			case OPEN: {
				// TODO: Fixa öppning av fil
				break;
			}
				
			case EXIT: {
				// TODO: Kolla efter ändringar och stäng ned program
				break;
			}

			case CHOOSE_FILL_COLOR: {
				Color chosenFillColor = paintFrame.showChooseColorDialog(true);
				if (chosenFillColor == null || selectedShape == null) {
					return;
				}
				// Ingen fyllfärg för linjer!
				if (selectedShape instanceof Line) {
					return;
				}

				if (selectedShape instanceof DrawingShape shape) {
					shape.setAreaColor(chosenFillColor);
				}

				break;
			}

			case CHOOSE_LINE_COLOR: {
				Color chosenLineColor = paintFrame.showChooseColorDialog(false);
				if (chosenLineColor == null || selectedShape == null) {
					return;
				}

				if (selectedShape instanceof DrawingShape shape) {
					shape.setLineColor(chosenLineColor);
				}

				break;
			}

			case SET_THIN_LINE: {
				int thickness = 1;
				if (selectedShape == null) {
					return;
				}

				if (selectedShape instanceof DrawingShape shape) {
					shape.setLineWidth(thickness);
				}

				break;
			}

			case SET_MEDIUM_LINE: {
				int thickness = 3;
				if (selectedShape == null) {
					return;
				}

				if (selectedShape instanceof DrawingShape shape) {
					shape.setLineWidth(thickness);
				}

				break;
			}

			case SET_THICK_LINE: {
				int thickness = 5;
				if (selectedShape == null) {
					return;
				}

				if (selectedShape instanceof DrawingShape shape) {
					shape.setLineWidth(thickness);
				}

				break;
			}

			default: {
				throw new IllegalArgumentException("Error: Unknown MenuEvent! " + menuEvent);
			}
		}

		paintFrame.repaint();
	}
}
