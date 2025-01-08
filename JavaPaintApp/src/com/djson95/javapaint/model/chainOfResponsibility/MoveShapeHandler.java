package com.djson95.javapaint.model.chainOfResponsibility;

import com.djson95.javapaint.events.DrawingEvent;
import com.djson95.javapaint.model.enums.MouseAction;
import se.kau.isgc08.lab4.model.DrawingComposite;
import se.kau.isgc08.lab4.model.Line;

/**
 * <p>
 * Den här klassen ansvarar för att hantera att flytta former i en ritning.
 * Den är en del i en chain of responsibility och används för att svara på mushändelser som
 * trycka, dra och släppa för att flytta på former.
 * </p>
 * <p>
 * Den här handlern hanterar flyttandet av linjer annorlunda då båda punkter (x1, y1 och x2, y2)
 * behöver flyttas. Om handlern inte kan bearbeta händelsen skickar den vidare händelsen
 * till nästa handler i kedjan.
 * </p>
 */
public class MoveShapeHandler extends AbstractShapeHandler {
    // Jag tog inspiration för lösningen från den här tråden:
    // https://stackoverflow.com/questions/29108021/dragging-a-shape-in-java
    private int offsetX1, offsetY1, offsetX2, offsetY2; // x2 och y2 är för linjer
    private boolean isMoving = false;

    @Override
    public void handle(DrawingEvent event, DrawingComposite selectedShape) {
        System.out.println("MoveShapeHandler: handle");

        if (event.action() == MouseAction.PRESSED && selectedShape != null) {
            System.out.println("MoveShapeHandler: handle: PRESSED");
            isMoving = true;

            if (selectedShape instanceof Line) {
                // Vi behöver hålla koll på två punkter om det är en linje
                offsetX2 = event.position().x - selectedShape.getWidth();
                offsetY2 = event.position().y - selectedShape.getHeight();
            }
            offsetX1 = event.position().x - selectedShape.getX1();
            offsetY1 = event.position().y - selectedShape.getY1();
            return;
        }
        if (event.action() == MouseAction.DRAGGED && selectedShape != null) {
            System.out.println("MoveShapeHandler: handle: DRAGGED");
            if (selectedShape instanceof Line) {
                selectedShape.moveTo(
                        event.position().x - offsetX1,
                        event.position().y - offsetY1,
                        event.position().x - offsetX2,
                        event.position().y - offsetY2
                );
            } else {
                selectedShape.moveTo(
                        event.position().x - offsetX1,
                        event.position().y - offsetY1
                );
            }
            return;
        }

        if (event.action() == MouseAction.RELEASED) {
            System.out.println("MoveShapeHandler: handle: RELEASED");
            isMoving = false;
        }

        if (next != null && !isMoving) {
            next.handle(event, selectedShape);
        }
    }
}
