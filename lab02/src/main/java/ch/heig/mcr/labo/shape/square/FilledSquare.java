package ch.heig.mcr.labo.shape.square;

import ch.heig.mcr.labo.renderer.FilledRenderer;
import ch.heig.mcr.labo.renderer.Renderer;

import java.awt.*;
import java.util.Random;

/**
 * Class for filled square shapes. It extends the Square class.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class FilledSquare extends Square {

    /**
     * Constructor of the FilledSquare class.
     *
     * @param random Random object to generate random numbers
     */
    public FilledSquare(Random random) {
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
        return Color.ORANGE;
    }
}
