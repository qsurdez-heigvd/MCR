package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.Ant;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * Class representing a Scouting strategy in the simulation.
 * A ScoutingStrategy is a movement strategy where an ant explores the environment.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ScoutingStrategy implements MovementStrategy {

    private final Ant ant; // The ant that is using this strategy

    /**
     * Constructor for the ScoutingStrategy class.
     * @param ant The ant that will be using this strategy.
     */
    public ScoutingStrategy(Ant ant) {
        this.ant = ant;
    }

    /**
     * Executes the Scouting strategy.
     * In this strategy, the ant moves in a random direction to explore the environment every 50 steps.
     * The ant's position is updated, and it checks if there are resources or predators nearby.
     */
    @Override
    public void executeStrategy() {
        if (ant.getCount() == 0 || ant.getCount() == 50) {
            ant.setExploring(Vector2.getRandomNonNullVector());
            ant.resetCounter();
        }
        ant.move(ant.getPosition().add(ant.getExploring()));
        ant.areThereResourcesNearby();
        ant.incrementCounter();
        ant.isPredatorNearby();
    }
}
