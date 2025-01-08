package com.djson95.javapaint.factory;

import se.kau.isgc08.lab4.model.DrawingComposite;
import se.kau.isgc08.lab4.model.Line;

import java.awt.*;

/**
 * En fabriksklass som ansvarar för att skapa "markeringsrektanglar" för angivna former.
 * Markeringsrektangeln beräknas efter positionen, dimensionerna och
 * linjetjockleken på kompositen. I nuläget behandlas linjer lite annorlunda från
 * andra former.
 */
public class SelectionRectFactory {
    public static Rectangle createRectangle(DrawingComposite shape) {
        // Är formen en linje?
        if (shape instanceof Line) {
            // Punkten som rektangeln ska börja på är den minsta av linjens punkter
            int x = Math.min(shape.getX1(), shape.getWidth());
            int y = Math.min(shape.getY1(), shape.getHeight());
            int width = Math.abs(shape.getX1() - shape.getWidth());
            int height = Math.abs(shape.getY1() - shape.getHeight());

            // Justera för linjetjockleken
            // (inte perfekt, men jag kunde inte få det bättre)
            x = x - (shape.getLineWidth() / 2) - 2;
            y = y - (shape.getLineWidth() / 2) - 2;
            width = width + shape.getLineWidth() + 4;
            height = height + shape.getLineWidth() + 4;

            return new Rectangle(x, y, width, height);
        } else {
            // Anta att en giltig form skickats in som inte är en linje
            int x = shape.getX1() - 1;
            int y = shape.getY1() - 1;
            int height = shape.getHeight() + 2;
            int width = shape.getWidth() + 2;

            return new Rectangle(x, y, width, height);
        }
    }
}
