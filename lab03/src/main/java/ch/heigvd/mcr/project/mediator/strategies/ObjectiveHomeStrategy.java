package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.Ant;

/**
 * Executes the Objective strategy.
 * In this strategy, the ant moves towards its hub
 * The ant's position is updated, and it checks if the objective is within range and if there is a predator nearby.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ObjectiveHomeStrategy extends ObjectiveStrategy {

    /**
     * Constructor for the ObjectiveHomeStrategy class.
     * @param ant The ant that will be using this strategy.
     */
    public ObjectiveHomeStrategy(Ant ant) {
        super(ant);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRange() {
        return 0;
    }
}
