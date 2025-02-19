package ch.heig.mcr.labo.factories;

import ch.heig.mcr.labo.shape.Bounceable;
import ch.heig.mcr.labo.shape.circle.EmptyCircle;
import ch.heig.mcr.labo.shape.square.EmptySquare;

import java.util.Random;

/**
 * Concrete factory for creating empty shapes.
 * It is a singleton, which means that only one instance of this class can be created.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class ConcreteFactoryEmpty extends AbstractFactory {

    /**
     * This class is used to create the unique instance of the ConcreteFactoryEmpty class.
     */
    private static class Instance {
        static private final ConcreteFactoryEmpty instance = new ConcreteFactoryEmpty();
    }

    /**
     * Get the unique instance of the ConcreteFactoryEmpty class.
     *
     * @return the unique instance of the ConcreteFactoryEmpty class
     */
    static public ConcreteFactoryEmpty getInstance() { return Instance.instance; }

    /**
     * Default constructor
     */
    private ConcreteFactoryEmpty() {}

    /**
     * @inheritDoc
     */
    public Bounceable createCircle(Random random) {
        return new EmptyCircle(random);
    }

    /**
     * @inheritDoc
     */
    public Bounceable createSquare(Random random) {
        return new EmptySquare(random);
    }

}