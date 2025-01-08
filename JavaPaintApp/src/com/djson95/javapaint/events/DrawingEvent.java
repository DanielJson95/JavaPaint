package com.djson95.javapaint.events;

import java.awt.Point;

import com.djson95.javapaint.model.enums.MouseAction;

/**
 * Den här klassen inkapslar en händelse på ritnignen med viktiga attribut.
 * Data i klassen används i kontroller för att uppdatera modellen (Drawing).
 * Det är en record class eftersom den endast inkapslar data: https://docs.oracle.com/en/java/javase/17/language/records.html
 *
 * @param action   Vilken händelse skedde?
 * @param position Muspekarens position vid händelsen
 */
public record DrawingEvent(MouseAction action, Point position) { }
