package ch.heigvd.mcr.project.models;

import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * Abstract class representing an ant in the simulation.
 * An ant is an entity that can hold resources and interact with predators via the mediator
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public abstract class Ant extends Entity {

    private boolean holding; // Flag indicating whether the ant is holding a resource

    /**
     * Constructor of the Ant class
     * @param mediator mediator
     * @param position starting position
     */
    public Ant(Mediator mediator, Vector2 position) {
        super(mediator, position);
        holding = false;
    }
    /**
     * Method to ask the mediator if there are resources nearby the scout
     */
    public void areThereResourcesNearby() {
        getMediator().checkForNearbyResources(this);
    }

    /**
     * Checks if the ant is holding a resource.
     * @return True if the ant is holding a resource, false otherwise.
     */
    public boolean isHolding() {
        return holding;
    }

    /**
     * Sets the holding status of the ant.
     * @param holding The new holding status.
     */
    public void setHolding(boolean holding) {
        this.holding = holding;
    }

    /**
     * Method to ask the mediator if there is a predator near the ant
     */
    public void isPredatorNearby() {
        getMediator().checkForNearbyPredators(this);
    }


    /**
     * Method to handle the entity arriving at its objective.
     */
    public void arrivedAtObjective() {
        getMediator().handleObjectiveReached(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void die() {
        getMediator().handleAntDeath(this);
    }


}
