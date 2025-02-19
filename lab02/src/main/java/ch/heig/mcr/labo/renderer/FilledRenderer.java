package ch.heig.mcr.labo.renderer;

import ch.heig.mcr.labo.shape.Bounceable;

import java.awt.*;

/**
 * Renderer for filled shapes.
 * It is a singleton, which means that only one instance of this class can be created.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class FilledRenderer implements Renderer {

    /**
     * This class is used to create the unique instance of the FilledRenderer class.
     */
    private static class Instance {
        static final private FilledRenderer instance = new FilledRenderer();
    }

    /**
     * Get the unique instance of the FilledRenderer class.
     *
     * @return the unique instance of the FilledRenderer class
     */
    public static FilledRenderer getInstance() { return Instance.instance; }

    /**
     * Default constructor
     */
    private FilledRenderer() {}

    /**
     * @inheritDoc
     */
    @Override
    public void display(Graphics2D g, Bounceable b) {
        g.setColor(b.getColor());
        g.fill(b.getShape());
    }
}
