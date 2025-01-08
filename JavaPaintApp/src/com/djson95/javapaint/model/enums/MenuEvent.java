package com.djson95.javapaint.model.enums;

// TODO: Se över vilka typer av händelser som faktiskt kommer användas

/**
 * <p>
 *     Representerar ett menyvalsevent.<br/>
 *	   Enumen innefattar alla val en användare kan göra i menyen.
 * </p>
 */
public enum MenuEvent {
	SAVE,
	SAVE_AS,
	OPEN,
	EXIT,
	UNDO,	// TODO: Command pattern?
	REDO,	// TODO: Command pattern?
	SET_THIN_LINE,
	SET_MEDIUM_LINE,
	SET_THICK_LINE,
	CHOOSE_FILL_COLOR,
	CHOOSE_LINE_COLOR,
	BRING_TO_FRONT,	// TODO: Lagerfunktionalitet?
	SEND_TO_BACK,	// TODO: Lagerfunktionalitet?
	BRING_FORWARD,	// TODO: Lagerfunktionalitet?
	SEND_BACKWARD	// TODO: Lagerfunktionalitet?
}
