package se.kau.isgc08.lab4.model;

import java.awt.Color;
import java.awt.Graphics;

import se.kau.isgc08.lab4.view.DrawingUtilInterface;



public class Circle extends DrawingShape {
	
	
	public void draw(Graphics g) {
		di.drawCircle(this,g);
	}

	
	public Circle(DrawingUtilInterface di,int x1, int y1, int w, int h, int width, Color lineColor, Color area) {
		super(di);
		setX1(x1);
		setWidth(w);
		setY1(y1);
		setHeight(h);
		setLineWidth(width);
		setLineColor(lineColor);
		setAreaColor(area);
		}

}
