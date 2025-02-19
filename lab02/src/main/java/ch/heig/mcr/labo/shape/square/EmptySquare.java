package ch.heig.mcr.labo.shape.square;

import ch.heig.mcr.labo.renderer.EmptyRenderer;
import ch.heig.mcr.labo.renderer.Renderer;

import java.awt.*;
import java.util.Random;

/**
 * Class for empty square shapes. It extends the Square class.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class EmptySquare extends Square {


    /**
     * Constructor of the EmptySquare class.
     *
     * @param random Random object to generate random numbers
     */
    public EmptySquare(Random random) {
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
        return Color.RED;
    }
}
