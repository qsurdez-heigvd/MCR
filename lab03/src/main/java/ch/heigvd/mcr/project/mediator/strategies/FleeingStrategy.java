package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.Ant;

/**
 * Class representing a Fleeing strategy in the simulation.
 * A FleeingStrategy is a movement strategy where an entity moves away from a threat.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class FleeingStrategy implements MovementStrategy {

    private final Ant ant; // The ant that is using this strategy

    /**
     * Constructor for the FleeingStrategy class.
     * @param ant The ant that will be using this strategy.
     */
    public FleeingStrategy(Ant ant) {
        this.ant = ant;
    }

    /**
     * Executes the Fleeing strategy.
     * In this strategy, the ant moves away from a threat if it is fleeing.
     * The ant's position is updated, and it checks if the predator is still nearby.
     */
    @Override
    public void executeStrategy() {
        if (ant.isFleeing()) {
            System.out.println(ant + " is fleeing");
            ant.move(ant.getPosition().add(ant.getFleeing()));
            ant.getMediator().predatorStillNearby(ant);
            ant.isPredatorNearby();
        }
    }
}
