package ch.heig.mcr.labo.shape.circle;

import ch.heig.mcr.labo.renderer.FilledRenderer;
import ch.heig.mcr.labo.renderer.Renderer;

import java.awt.*;
import java.util.Random;

/**
 * Class for filled circle shapes. It extends the Circle class.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class FilledCircle extends Circle {

    /**
     * Constructor of the FilledCircle class.
     *
     * @param random Random object to generate random numbers
     */
    public FilledCircle(Random random) {
        super(random);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return FilledRenderer.getInstance();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
