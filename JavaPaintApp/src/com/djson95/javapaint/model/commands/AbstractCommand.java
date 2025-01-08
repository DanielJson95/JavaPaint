package com.djson95.javapaint.model.commands;

import com.djson95.javapaint.model.Drawing;

public abstract class AbstractCommand implements Command {
	protected Drawing drawing;
	
	protected AbstractCommand(Drawing drawing) {
		this.drawing = drawing;
	}
	
	// Default-beteende för redo är att utföra kommandot igen
	@Override
	public void redo() {
		execute();
	}
}
