package ch.heig.mcr.labo.factories;

import ch.heig.mcr.labo.shape.Bounceable;
import ch.heig.mcr.labo.shape.circle.FilledCircle;
import ch.heig.mcr.labo.shape.square.FilledSquare;

import java.util.Random;

/**
 * Concrete factory for creating filled shapes.
 * It is a singleton, which means that only one instance of this class can be created.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class ConcreteFactoryFilled extends AbstractFactory {

    /**
     * This class is used to create the unique instance of the ConcreteFactoryFilled class.
     */
    private static class Instance {
        static private final ConcreteFactoryFilled instance = new ConcreteFactoryFilled();
    }

    /**
     * Get the unique instance of the ConcreteFactoryFilled class.
     *
     * @return the unique instance of the ConcreteFactoryFilled class
     */
    static public ConcreteFactoryFilled getInstance() { return Instance.instance; }

    /**
     * Default constructor
     */
    private ConcreteFactoryFilled() {}

    /**
     * @inheritDoc
     */
    public Bounceable createCircle(Random random) {
        return new FilledCircle(random);
    }

    /**
     * @inheritDoc
     */
    public Bounceable createSquare(Random random) {
        return new FilledSquare(random);
    }
}
