package com.djson95.javapaint.model.commands;

import com.djson95.javapaint.model.Drawing;

import se.kau.isgc08.lab4.model.DrawingComposite;

public class RemoveCommand extends AbstractCommand {
		
	private DrawingComposite targetElement;
	
	public RemoveCommand(Drawing drawing, DrawingComposite targetElement) {
		super(drawing);
		
		this.targetElement = targetElement;
	}

	@Override
	public void undo() {
		// Lägg till elementet igen?
		drawing.getRootElement().add(targetElement);
	}
	
	@Override
	public void execute() {
		drawing.getRootElement().remove(targetElement);
	}
	
}
