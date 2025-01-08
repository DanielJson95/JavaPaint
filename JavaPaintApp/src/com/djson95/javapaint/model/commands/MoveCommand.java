package com.djson95.javapaint.model.commands;

import java.awt.Point;

import com.djson95.javapaint.model.Drawing;

import se.kau.isgc08.lab4.model.DrawingShape;

public class MoveCommand extends AbstractCommand {
	
	private Point originalPoint;
	
	private Point destinationPoint;
	
	private DrawingShape targetShape;

	public MoveCommand(Drawing drawing, DrawingShape targetShape, Point originalPoint, Point destinationPoint) {
		super(drawing);
		
		this.targetShape = targetShape;
		this.originalPoint = originalPoint;
		this.destinationPoint = destinationPoint;
	}

	@Override
	public void undo() {
		// Flytta tillbaka elementet till orginalposition
		targetShape.setX1(originalPoint.x);
		targetShape.setY1(originalPoint.y);
	}

	@Override
	public void execute() {
		// Flytta till destination point
		targetShape.setX1(destinationPoint.x);
		targetShape.setY1(destinationPoint.y);
	}

}
