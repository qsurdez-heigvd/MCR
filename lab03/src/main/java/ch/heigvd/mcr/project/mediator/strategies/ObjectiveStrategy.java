package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.AbstractColleague;
import ch.heigvd.mcr.project.models.Ant;

import static ch.heigvd.mcr.project.utils.Vector2.getDirection;

/**
 * Abstract class representing an Objective strategy in the simulation.
 * An ObjectiveStrategy is a movement strategy where an ant moves towards an objective.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
abstract public class ObjectiveStrategy implements MovementStrategy {

    protected Ant ant; // The ant that is using this strategy

    /**
     * Constructor for the ObjectiveStrategy class.
     * @param ant The ant that will be using this strategy.
     */
    public ObjectiveStrategy(Ant ant) {
        this.ant = ant;
    }

    /**
     * Abstract method to get the range of the objective.
     * The range is defined in the subclasses of ObjectiveStrategy.
     * @return the range of the objective.
     */
    abstract public int getRange();

    /**
     * Executes the Objective strategy.
     * In this strategy, the ant moves towards an objective. If it is within range, it will call the arrivedAtObjective method.
     * The ant's position is updated, and it checks if the objective is within range and if there is a predator nearby.
     */
    @Override
    public void executeStrategy() {
        AbstractColleague objective = ant.getMediator().getObjective(ant);
        if (objective != null) {
            // In Mediator
            if (ant.getMediator().withinRange(ant, objective, getRange())) {
                ant.arrivedAtObjective();
            }
            ant.move(ant.getPosition().add(getDirection(ant.getPosition(), objective.getPosition())));
            ant.isPredatorNearby();
        }
    }
}
