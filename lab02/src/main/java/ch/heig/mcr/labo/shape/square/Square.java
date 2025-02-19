package ch.heig.mcr.labo.shape.square;

import ch.heig.mcr.labo.shape.AbstractShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Abstract class for square shapes. It extends the AbstractShape class.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
abstract class Square extends AbstractShape {

    /**
     * Constructor of the Square class.
     *
     * @param random the random object to generate random numbers
     */
    public Square(Random random) {
        super(random);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return new Rectangle2D.Double(getPosition().getX(), getPosition().getY(), getSize(), getSize());
    }
}
