package se.kau.isgc08.lab4.model;

import java.awt.*;

import se.kau.isgc08.lab4.view.DrawingUtilInterface;



// TODO: Auto-generated Javadoc
/**
 * The Class DrawingShape.You need to use this class as a superclass for all you're
 * shapes. The DrawingAPI (which is placed in the View package) relies on DrawingShape as input to drawing the different shapes on screen.
 */
public class DrawingShape implements DrawingComposite {
	
	/** The di. */
	DrawingUtilInterface di;
	
	/** The width. */
	int width;
	
	/** The x1. */
	int x1;
	
	/** The y1. */
	int y1;
	
	/** The height. */
	int height;
	
	/** The lineWidth. */
	int lineWidth;
	
	/** The line color. */
	Color lineColor;
	
	/** The area color. */
	Color areaColor;
	
	/**
	 * Instantiates a new drawing shape.
	 *
	 * @param di the di
	 */
	public DrawingShape(DrawingUtilInterface di) {
		this.di=di;
	}
	

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#setDrawingAPI(se.kau.isgc08.lab4.view.DrawingUtilInterface)
	 */
	public void setDrawingAPI (DrawingUtilInterface di) {
		this.di=di;
	}
	
	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getX1()
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Sets the x1.
	 *
	 * @param x1 the new x1
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param w the new width
	 */
	public void setWidth(int w) {
		this.width = w;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getY1()
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Sets the y1.
	 *
	 * @param y1 the new y1
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getHeight()
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the line width.
	 *
	 * @param w the new line width needs to be an even number to make shapefills right.
	 */
	public void setLineWidth(int w) {
		lineWidth=w;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getLineWidth()
	 */
	public int getLineWidth() {
		return lineWidth;
	}

	/**
	 * Sets the line color.
	 *
	 * @param c the new line color
	 */
	public void setLineColor(Color c) {
		lineColor=c;
	}

	/**
	 * Sets the area color.
	 *
	 * @param c the new area color
	 */
	public void setAreaColor(Color c) {
		areaColor=c;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getLineColor()
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#getAreaColor()
	 */
	public Color getAreaColor() {
		return areaColor;
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#add(se.kau.isgc08.lab4.model.DrawingComposite)
	 */
	public void add(DrawingComposite d) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#remove(se.kau.isgc08.lab4.model.DrawingComposite)
	 */
	public void remove(DrawingComposite d) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see se.kau.isgc08.lab4.model.DrawingComposite#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DrawingComposite findShapeAtPoint(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

	/* TILLAGDA METODER & ATTRIBUT */

	// Håller koll på om figuren är marker
	private boolean selected = false;
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public void moveTo(int x, int y) {
		setX1(x);
		setY1(y);
	}

	// Den här versionen flyttar en linje
	public void moveTo(int x1, int y1, int x2, int y2) {
		setX1(x1);
		setY1(y1);
		setWidth(x2);
		setHeight(y2);
	}

	@Override
	public void resize(int newWidth, int newHeight) {
		// Se till att formen är minst några pixlar bred/hög
		setWidth(newWidth);
		setHeight(newHeight);
	}

	@Override
	public boolean isSelected() {
		return selected;
	}
}