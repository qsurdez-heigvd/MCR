package ch.heig.mcr.labo.factories;


import ch.heig.mcr.labo.shape.Bounceable;

import java.util.Random;

/**
 * Abstract factory for creating shapes. It follows the factory design pattern.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public abstract class AbstractFactory {

    /**
     * Create a circle.
     *
     * @param random Random object to generate random values
     * @return an object implementing the Bounceable interface
     */
    public abstract Bounceable createCircle(Random random);

    /**
     * Create a square.
     *
     * @param random Random object to generate random values
     * @return an object implementing the Bounceable interface
     */
    public abstract Bounceable createSquare(Random random);

}