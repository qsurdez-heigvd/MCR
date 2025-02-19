package ch.heig.mcr.labo.shape.circle;

import ch.heig.mcr.labo.renderer.EmptyRenderer;
import ch.heig.mcr.labo.renderer.Renderer;

import java.awt.*;
import java.util.Random;

/**
 * Class for empty circle shapes. It extends the Circle class.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class EmptyCircle extends Circle {

    /**
     * Constructor of the EmptyCircle class.
     *
     * @param random Random object to generate random numbers
     */
    public EmptyCircle(Random random) {
        super(random);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return EmptyRenderer.getInstance();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
