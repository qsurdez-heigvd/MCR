package ch.heig.mcr.labo.shape.circle;

import ch.heig.mcr.labo.shape.AbstractShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * Abstract class for circle shapes. It extends the AbstractShape class.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
abstract class Circle extends AbstractShape {

    /**
     * Constructor of the Circle class.
     *
     * @param random the random object to generate random numbers
     */
    public Circle(Random random) {
        super(random);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return new Ellipse2D.Double(getPosition().getX(), getPosition().getY(), getSize(), getSize());
    }
}
