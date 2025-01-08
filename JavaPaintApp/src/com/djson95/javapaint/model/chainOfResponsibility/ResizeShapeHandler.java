package com.djson95.javapaint.model.chainOfResponsibility;

import com.djson95.javapaint.events.DrawingEvent;
import com.djson95.javapaint.factory.SelectionRectFactory;
import com.djson95.javapaint.model.enums.Corner;
import com.djson95.javapaint.model.enums.MouseAction;
import se.kau.isgc08.lab4.model.DrawingComposite;
import se.kau.isgc08.lab4.model.Line;

import java.awt.*;

/**
 * <p>
 *     Den här klassen ansvarar för att ändra storleken på former genom att
 *     hantera olika mushändelser. Den bestämmer vilket hörne som klickats,
 *     ser över hur formens storlek behöver ändras medan musen dras, och utför
 *     resizing beroende på hur musen dras och vilket hörne som dras i.
 * </p>
 */
public class ResizeShapeHandler extends AbstractShapeHandler {

    private final int CORNER_SIZE = 15; // Ändra för annan känslighet för hörntryck
    private final int MIN_SIZE = 5; // Minsta tillåtna bredd/höjd på en form
    private int originalX, originalY, originalWidth, originalHeight;
    private Point startPoint;
    private boolean isResizing = false;
    private Corner currentCorner = Corner.NONE;

    @Override
    public void handle(DrawingEvent event, DrawingComposite selectedShape) {
        System.out.println("ResizeShapeHandler: handle");

        if (event.action() == MouseAction.PRESSED
                && selectedShape != null
                && isNearCorner(event.position(), SelectionRectFactory.createRectangle(selectedShape))) {
            System.out.println("ResizeShapeHandler: handle: PRESSED");

            isResizing = true;

            // Spara orginalvärden vid mustryck, vilket behövs när vi ska förstora senare
            originalX = selectedShape.getX1();
            originalY = selectedShape.getY1();
            originalWidth = selectedShape.getWidth();
            originalHeight = selectedShape.getHeight();
            startPoint = event.position();

            // SPara vilket hörne som klickats
            if (isNearUpperLeftCorner(event.position(), SelectionRectFactory.createRectangle(selectedShape))) {
                currentCorner = Corner.TOP_LEFT;
            } else if (isNearUpperRightCorner(event.position(), SelectionRectFactory.createRectangle(selectedShape))) {
                currentCorner = Corner.TOP_RIGHT;
            } else if (isNearLowerLeftCorner(event.position(), SelectionRectFactory.createRectangle(selectedShape))) {
                currentCorner = Corner.BOTTOM_LEFT;
            } else {
                // Måste vara nedre högra hörnet
                currentCorner = Corner.BOTTOM_RIGHT;
            }

            return;
        }

        if (event.action() == MouseAction.DRAGGED
                && isResizing
                && selectedShape != null
                && currentCorner != Corner.NONE) {
            System.out.println("ResizeShapeHandler: handle: DRAGGED");

            // Hämta ut hur långt som dragits
            int offsetX = event.position().x - startPoint.x;
            int offsetY = event.position().y - startPoint.y;

            if (selectedShape instanceof Line) {
                if (!handleLineResize(selectedShape, offsetX, offsetY)) {
                    // MoveShapeHandler kommer ta hand om request istället
                    isResizing = false;
                    currentCorner = Corner.NONE;
                }
            } else {
                handleNonLineResize(selectedShape, offsetX, offsetY);
            }

            return;
        }

        if (event.action() == MouseAction.RELEASED) {
            System.out.println("ResizeShapeHandler: handle: RELEASED");
            isResizing = false;
            currentCorner = Corner.NONE;

            // Byt plats på linjens punkter om x1 är till höger om x2
            if (selectedShape instanceof Line && selectedShape.getX1() > selectedShape.getWidth()) {
                selectedShape.moveTo(
                        selectedShape.getWidth(),
                        selectedShape.getHeight(),
                        selectedShape.getX1(),
                        selectedShape.getY1()
                );
            }
        }

        if (next != null && !isResizing) {
            next.handle(event, selectedShape);
        }
    }

    private void handleNonLineResize(DrawingComposite selectedShape, int offsetX, int offsetY) {
        // Ta reda på den nya bredden och höjden
        switch (currentCorner) {
            case TOP_LEFT: {
                System.out.println("ResizeShapeHandler: handle: UPPER LEFT");
                // X- och y-position förändras
                // Bredd och höjd behöver justeras men deras slutpunkt (offset) ska vara samma som innan resize
                int newWidth = originalWidth - offsetX;
                int newHeight = originalHeight - offsetY;

                if (newWidth >= MIN_SIZE && newHeight >= MIN_SIZE) {
                    selectedShape.moveTo(originalX + offsetX, originalY + offsetY);
                    selectedShape.resize(newWidth, newHeight);
                }
                break;
            }
            case TOP_RIGHT: {
                System.out.println( "ResizeShapeHandler: handle: UPPER RIGHT");
                // Här kan y-position förändras samt bredd och höjd
                int newWidth = originalWidth + offsetX;
                int newHeight = originalHeight - offsetY;

                if (newWidth >= MIN_SIZE && newHeight >= MIN_SIZE) {
                    selectedShape.moveTo(originalX, originalY + offsetY);
                    selectedShape.resize(newWidth, newHeight);
                }
                break;
            }
            case BOTTOM_LEFT: {
                System.out.println("ResizeShapeHandler: handle: LOWER LEFT");
                // X-position kan förändras här samt bredd och höjd
                int newWidth = originalWidth - offsetX;
                int newHeight = originalHeight + offsetY;

                if (newWidth >= MIN_SIZE && newHeight >= MIN_SIZE) {
                    selectedShape.moveTo(originalX + offsetX, originalY);
                    selectedShape.resize(newWidth, newHeight);
                }
                break;
            }
            case BOTTOM_RIGHT: {
                System.out.println("ResizeShapeHandler: handle: LOWER RIGHT");
                // Måste vara nedre högra hörnet
                // Bredd och höjd kan ändras här
                int newWidth = originalWidth + offsetX;
                int newHeight = originalHeight + offsetY;

                if (newWidth >= MIN_SIZE && newHeight >= MIN_SIZE) {
                    selectedShape.resize(newWidth, newHeight);
                }
                break;
            }
        }
    }

    private boolean handleLineResize(DrawingComposite selectedShape, int offsetX, int offsetY) {
        // Beteendet för att förstora en linje får vara att
        // flytta på punkten närmast hörnet man drar ifrån

        if (currentCorner == Corner.TOP_LEFT || currentCorner == Corner.BOTTOM_LEFT) {
            System.out.println( "ResizeShapeHandler: handle (line): UPPER LEFT||LOWER LEFT");

            int newX = originalX + offsetX;
            int newY = originalY + offsetY;

            // Man ska bara kunna dra i det hörne linjens punkt befinner sig vid
            if ((currentCorner == Corner.TOP_LEFT && originalY <= originalHeight)
                || (currentCorner == Corner.BOTTOM_LEFT && originalY >= originalHeight)) {
                selectedShape.moveTo(
                        newX,
                        newY,
                        originalWidth,
                        originalHeight);
                return true;
            }  else {
                return false;
            }
        }
        else if (currentCorner == Corner.TOP_RIGHT || currentCorner == Corner.BOTTOM_RIGHT) {
            System.out.println( "ResizeShapeHandler: handle (line): UPPER RIGHT||LOWER RIGHT");

            int newX = originalWidth + offsetX;
            int newY = originalHeight + offsetY;

            // Man ska bara kunna dra i det hörne linjens punkt befinner sig vid
            if ((currentCorner == Corner.TOP_RIGHT && originalY >= originalHeight)
                    || (currentCorner == Corner.BOTTOM_RIGHT && originalY <= originalHeight)) {
                selectedShape.moveTo(
                        originalX,
                        originalY,
                        newX,
                        newY);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean isNearCorner(Point point, Rectangle selectionRect) {
        return isNearLowerRightCorner(point, selectionRect)
                || isNearUpperLeftCorner(point, selectionRect)
                || isNearUpperRightCorner(point, selectionRect)
                || isNearLowerLeftCorner(point, selectionRect);
    }

    private boolean isNearLowerRightCorner(Point point, Rectangle rectangle) {
        int rectRight = rectangle.x + rectangle.width;
        int rectBottom = rectangle.y + rectangle.height;
        return Math.abs(point.x - rectRight) < CORNER_SIZE
                &&  Math.abs(point.y - rectBottom) < CORNER_SIZE;
    }

    private boolean isNearUpperLeftCorner(Point point, Rectangle rectangle) {
        return Math.abs(point.x - rectangle.x) < CORNER_SIZE
                &&  Math.abs(point.y - rectangle.y) < CORNER_SIZE;
    }

    private boolean isNearUpperRightCorner(Point point, Rectangle rectangle) {
        int rectRight = rectangle.x + rectangle.width;
        return Math.abs(point.x - rectRight) < CORNER_SIZE
                &&  Math.abs(point.y - rectangle.y) < CORNER_SIZE;
    }

    private boolean isNearLowerLeftCorner(Point point, Rectangle rectangle) {
        int rectBottom = rectangle.y + rectangle.height;
        return Math.abs(point.x - rectangle.x) < CORNER_SIZE
                &&  Math.abs(point.y - rectBottom) < CORNER_SIZE;
    }
}
