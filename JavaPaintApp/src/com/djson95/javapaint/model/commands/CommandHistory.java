package com.djson95.javapaint.model.commands;

import java.util.Stack;

public class CommandHistory {
	
	private Stack<Command> undoStack;
	
	private Stack<Command> redoStack;
	
	public CommandHistory() {
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}

	public void storeCommand(Command command) {
		undoStack.add(command);
	}
	
	public void performUndo() {
		if (undoStack.isEmpty()) {
			return;
		}
		
		var undoCommand = undoStack.pop();
		undoCommand.undo();
		
		redoStack.add(undoCommand);
	}
	
	public void performRedo() {
		if (redoStack.isEmpty()) {
			return;
		}
		
		var redoCommand = redoStack.pop();
		redoCommand.redo();
		
		undoStack.add(redoCommand);
	}
}
