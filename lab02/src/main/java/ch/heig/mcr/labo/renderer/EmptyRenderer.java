package ch.heig.mcr.labo.renderer;

import ch.heig.mcr.labo.shape.Bounceable;

import java.awt.*;

/**
 * Renderer for empty shapes.
 * It is a singleton, which means that only one instance of this class can be created.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class EmptyRenderer implements Renderer {

    /**
     * This class is used to create the unique instance of the EmptyRenderer class.
     */
    private static class Instance {
        static final private EmptyRenderer instance = new EmptyRenderer();
    }

    /**
     * Get the unique instance of the EmptyRenderer class.
     *
     * @return the unique instance of the EmptyRenderer class
     */
    public static EmptyRenderer getInstance() { return Instance.instance; }

    /**
     * Default constructor
     */
    private EmptyRenderer() {}

    /**
     * @inheritDoc
     */
    @Override
    public void display(Graphics2D g, Bounceable b) {
        g.setStroke(new BasicStroke(2));
        g.setColor(b.getColor());
        g.draw(b.getShape());
    }
}
