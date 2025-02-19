package ch.heig.mcr.labo.renderer;

import ch.heig.mcr.labo.shape.Bounceable;

import java.awt.*;

/**
 * Interface for rendering bounceable object on a Graphics2D object.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public interface Renderer {

    /**
     * Display the bounceable object on the Graphics2D object.
     *
     * @param g the Graphics2D object
     * @param b the bounceable object to display
     */
    void display(Graphics2D g, Bounceable b);
}
