package com.djson95.javapaint.model;

import java.awt.*;
import java.io.Serializable;

import se.kau.isgc08.lab4.model.*;
import se.kau.isgc08.lab4.view.*;

/**
 * <p>
 *     Det här är den huvudsakliga model-klassen.<br/>
 *     Den är som en facade mot DrawingAPI och ger metoder
 *     för att enkelt kunna placera figurer i en komposit.
 * </p>
 */
public class Drawing implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final DrawingComposite rootElement;
	
	private final DrawingUtilInterface drawingUtil;
	
	public Drawing() {
		rootElement = new DrawingContainer();
		
		// Skulle kunna använda egen implementerad drawing util här istället
		drawingUtil = new DrawingUtil();
	}
	
	/**
	 * Lägger till en linje i ritningen.
	 * 
	 * @param x1 Startpunkt - x-koordinat.
	 * @param y1 Startpunkt - y-koordinat.
	 * @param x2 Slutpunkt - x-koordinat.
	 * @param y2 Slutpunkt - y-koordinat.
	 * @param lineWidth Linjens tjocklek.
	 * @param color Linjens färg.
	 */
	public void addLine(int x1, int y1, int x2, int y2, int lineWidth, Color color) {
		rootElement.add(new Line(drawingUtil, x1, y1, x2, y2, lineWidth, color));
	}
	
	/**
	 * Lägger till en cirkel i ritningen.
	 * 
	 * @param x1 X-koordinat för cirkelns övre vänstra hörn.
	 * @param y1 Y-koordinat för cirkelns övre vänstra hörn.
	 * @param width Cirkelns bredd.
	 * @param height Cirkelns höjd.
	 * @param lineWidth Tjockleken på cirkeln kantlinje.
	 * @param lineColor Färgen på cirkelns kantlinje.
	 * @param fillColor Färgen på cirkelns fyllnad.
	 */
	public void addCircle(int x1, int y1, int width, int height, int lineWidth, Color lineColor, Color fillColor) {
		rootElement.add(new Circle(drawingUtil, x1, y1, width, height, lineWidth, lineColor, fillColor));
	}
	
	/**
	 * Lägger till en rektangel i ritningen.
	 * 
	 * @param x1 X-koordinat för rektangelns övre vänstra hörn.
	 * @param y1 Y-koordinat för rektangelns övre vänstra hörn.
	 * @param width Rektangelns bredd.
	 * @param height Rektangelns höjd.
	 * @param lineWidth Tjockleken på rektangelns kantlinje.
	 * @param lineColor Färgen på rektangelns kantlinje.
	 * @param fillColor Färgen på rektangelns fyllnad.
	 */
	public void addRectangle(int x1, int y1, int width, int height, int lineWidth, Color lineColor, Color fillColor) {
		rootElement.add(new Rect(drawingUtil, x1, y1, width, height, lineWidth, lineColor, fillColor));
	}
	
	/**
	 * Tar bort ett ritningselement från ritningen.
	 * 
	 * @param element Ritningselementet som ska tas bort.
	 */
	public void removeElement(DrawingComposite element) {
		rootElement.remove(element);
	}

	/**
	 * Hämtar root-elementet i ritningen som är en container i komposit-pattern.
	 * @return Rotelementet
	 */
	public DrawingComposite getRootElement() {
		return rootElement;
	}
}
