package com.djson95.javapaint.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.djson95.javapaint.controller.Controller;
import com.djson95.javapaint.events.DrawingEvent;
import com.djson95.javapaint.factory.SelectionRectFactory;
import com.djson95.javapaint.model.enums.MouseAction;

import se.kau.isgc08.lab4.model.DrawingComposite;
import se.kau.isgc08.lab4.view.DrawingPanel;

import javax.swing.*;

/**
 * <p>
 *     Den här klassen ärver DrawingPanel i DrawingAPI och ger den extra
 *     funktionalitet. Den är en JPanel i grunden och den ansvarar för att
 *     lyssna efter mushändelser och delegera vidare till Controllern, samt
 *     att rita upp en röd fyrkant runt ett markerat element.
 * </p>
 */
public class DrawingPanelAdapter extends DrawingPanel {
	private final Controller controller;
	private DrawingComposite selectedElement;
	private boolean isDragging = false;

	public DrawingPanelAdapter(DrawingComposite dc, Controller controller) {
		super(dc);
		this.controller = controller;
		setupListeners();
	}

	private void setupListeners() {
		// Mus-lyssnare
		addMouseListener(new MouseAdapter() {
			// Lägg till händelser här...
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Kolla om höger (för radering) eller vänster (för markering) musknapp tryckts
				MouseAction click = SwingUtilities.isLeftMouseButton(e)
						? MouseAction.LEFT_CLICK
						: MouseAction.RIGHT_CLICK;
				var event = new DrawingEvent(click, new Point(e.getX(), e.getY()));
				controller.handleDrawingEvent(event);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// Om man inte drar, sätt startpunkt
				if (!isDragging) {
					var event = new DrawingEvent(MouseAction.PRESSED, new Point(e.getX(), e.getY()));
					controller.handleDrawingEvent(event);
				}
				isDragging = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isDragging = false;
				var event = new DrawingEvent(MouseAction.RELEASED, new Point(e.getX(), e.getY()));
				controller.handleDrawingEvent(event);
			}
		});
		
		// Musrörelse-lyssnare
		addMouseMotionListener(new MouseMotionAdapter() {
			// Lägg till händelser här...

			@Override
			public void mouseDragged(MouseEvent e) {
				var event = new DrawingEvent(MouseAction.DRAGGED, new Point(e.getX(), e.getY()));
				controller.handleDrawingEvent(event);
			}
		});
	}

	public void setSelectedElement(DrawingComposite selectedElement) {
		this.selectedElement = selectedElement;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Här målar vi en röd fyrkant runt den markerade figuren
		if (selectedElement == null) {
			return;
		}

		Rectangle selectionRect = SelectionRectFactory.createRectangle(selectedElement);
		g.setColor(Color.RED);
		g.drawRect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);
		// TODO: Behövs det återstålla till tidigare vald färg?
		g.setColor(Color.BLACK);
	}
}
