package ch.heig.mcr.labo.shape;

import java.awt.*;

/**
 * Interface for bounceable objects.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public interface Bounceable {

    /**
     * Method to draw the object.
     */
    void draw();

    /**
     * Method to move the object within the bounds.
     */
    void move();

    /**
     * Method to get the color of the object.
     *
     * @return the color of the object
     */
    Color getColor();

    /**
     * Method to get the shape of the object.
     *
     * @return the shape of the object
     */
    Shape getShape();
}
