package ch.heigvd.mcr.project.mediator.strategies;

/**
 * Interface representing a movement strategy in the simulation.
 * A MovementStrategy can be implemented by any entity that needs to move in the simulation.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public interface MovementStrategy {

    /**
     * Executes the movement strategy.
     * The specific implementation of this method will depend on the entity implementing the strategy.
     */
    void executeStrategy();
}
