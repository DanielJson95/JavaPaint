package com.djson95.javapaint.model.commands;

import com.djson95.javapaint.model.Drawing;

import se.kau.isgc08.lab4.model.DrawingComposite;

public class AddCommand extends AbstractCommand {
	
	private DrawingComposite elementToAdd;

	public AddCommand(Drawing drawing, DrawingComposite elementToAdd) {
		super(drawing);
		
		this.elementToAdd = elementToAdd;
	}

	@Override
	public void undo() {
		// Ta bort elementet?
		drawing.getRootElement().remove(elementToAdd);
	}

	@Override
	public void execute() {
		drawing.getRootElement().add(elementToAdd);
	}

}
