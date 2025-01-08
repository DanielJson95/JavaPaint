package com.djson95.javapaint.model.chainOfResponsibility;

import com.djson95.javapaint.events.DrawingEvent;
import se.kau.isgc08.lab4.model.DrawingComposite;

/**
 * En abstrakt föräldraklass som definier en handler i en chain of responsibility för
 * operationer på former. Subklasser implementerar specifik hanteringslogik för operationerna.
 */
public abstract class AbstractShapeHandler {
	protected AbstractShapeHandler next;
	// Behövs när vi raderar ett element
	protected DrawingComposite rootElement;

	public void setNext(AbstractShapeHandler next) {
		this.next = next;
	}

	abstract public void handle(DrawingEvent event, DrawingComposite selectedShape);
}
