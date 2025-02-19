package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.Ant;

/**
 * Executes the Objective strategy.
 * In this strategy, the ant moves towards a resource
 * The ant's position is updated, and it checks if the objective is within range and if there is a predator nearby.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ObjectiveResourceStrategy extends ObjectiveStrategy {

    /**
     * Executes the Objective strategy.
     * In this strategy, the ant moves towards an objective if it is within range.
     * The ant's position is updated, and it checks if the objective is within range and if there is a predator nearby.
     */
    public ObjectiveResourceStrategy(Ant ant) {
        super(ant);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRange() {
        return ant.getSize()/2  +  ant.getMediator().getObjective(ant).getSize()/2;
    }
}
