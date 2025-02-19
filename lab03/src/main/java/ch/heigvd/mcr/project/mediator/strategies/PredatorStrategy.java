package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.Predator;
import ch.heigvd.mcr.project.utils.Vector2;

import static ch.heigvd.mcr.project.utils.Vector2.getDirection;

/**
 * Class representing a Predator strategy in the simulation.
 * A PredatorStrategy is a movement strategy where a predator moves in a random direction.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class PredatorStrategy implements MovementStrategy {

    private final Predator predator; // The predator that is using this strategy

    /**
     * Constructor for the PredatorStrategy class.
     * @param predator The predator that will be using this strategy.
     */
    public PredatorStrategy(Predator predator) {
        this.predator = predator;
    }

    /**
     * Executes the Predator strategy.
     * In this strategy, the predator moves in a random direction every 20 steps.
     * The predator's position is updated, and it checks if there are preys nearby.
     */
    @Override
    public void executeStrategy() {
        if (predator.getCount() == 0 || predator.getCount() == 20) {
            predator.setExploring(new Vector2(predator.getRandom().nextInt(), predator.getRandom().nextInt()));
            predator.resetCounter();
        }
        predator.move(predator.getPosition().add(getDirection(predator.getPosition(), predator.getExploring())));
        predator.incrementCounter();
        predator.areTherePreysNearby();
    }
}
