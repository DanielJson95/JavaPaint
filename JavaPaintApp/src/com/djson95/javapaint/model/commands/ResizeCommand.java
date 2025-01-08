package com.djson95.javapaint.model.commands;

import com.djson95.javapaint.model.Drawing;

import se.kau.isgc08.lab4.model.DrawingShape;

public class ResizeCommand extends AbstractCommand {
	
	private int originalWidth, originalHeight, finalWidth, finalHeight;
	
	private DrawingShape targetShape;
	
	public ResizeCommand(
			Drawing drawing,
			DrawingShape targetShape,
			int originalWidth,
			int originalHeight,
			int finalWidth,
			int finalHeight) {
		super(drawing);
		
		this.targetShape = targetShape;
		this.originalWidth = originalWidth;
		this.originalHeight = originalHeight;
		this.finalWidth = finalWidth;
		this.finalHeight = finalHeight;
	}

	@Override
	public void undo() {
		// Gör om till orginalstorlek
		targetShape.setWidth(originalWidth);
		targetShape.setHeight(originalHeight);
	}

	@Override
	public void execute() {
		// Ändra til önskad storlek
		targetShape.setWidth(finalWidth);
		targetShape.setHeight(finalHeight);
	}
}
