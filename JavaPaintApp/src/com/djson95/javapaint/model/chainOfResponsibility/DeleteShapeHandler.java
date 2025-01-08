package com.djson95.javapaint.model.chainOfResponsibility;

import com.djson95.javapaint.events.DrawingEvent;
import com.djson95.javapaint.model.enums.MouseAction;
import se.kau.isgc08.lab4.model.DrawingComposite;

/**
 * <p>
 * Den här klassen ansvarar för att hantera när en form raderas från ritningen.
 * Den är en del i en chain of responsibility.
 *</p>
 * <p>
 * Klassen kollar efter ett högerklicks-event på en form för att göra raderingen.
 * Om händelsen hanteras tas formen bort från rootElement-kompositen.
 * Om klassen inte kan hantera händelsen skickas förfrågan vidare till nästa handler.
 * </p>
 */
public class DeleteShapeHandler extends AbstractShapeHandler {

    public DeleteShapeHandler(DrawingComposite rootElement) {
        this.rootElement = rootElement;
    }

    @Override
    public void handle(DrawingEvent event, DrawingComposite selectedShape) {
        System.out.println("DeleteShapeHandler: handle");

        if (event.action() == MouseAction.RIGHT_CLICK) {
            System.out.println("DeleteShapeHandler: handle: RIGHT_CLICK");
            // Ta bort formen om man högerklickat på en form
            if (selectedShape != null) {
                rootElement.remove(selectedShape);
                return;
            }
        }

        if (next != null) {
            next.handle(event, selectedShape);
        }
    }
}
