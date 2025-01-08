package com.djson95.javapaint.model.commands;

public interface Command {
	void undo();
	void redo();
	void execute();
}
