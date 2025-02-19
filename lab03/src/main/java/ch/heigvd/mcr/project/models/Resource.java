package ch.heigvd.mcr.project.models;


import ch.heigvd.mcr.project.gui.Renderer;
import ch.heigvd.mcr.project.gui.ResourceRenderer;
import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * Class representing a Resource in the simulation.
 * A Resource is an entity that can be collected by ants via the mediator
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Resource extends AbstractColleague {

    private int nbResources; // The number of resources available in this resource entity

    /**
     * Constructor for the Resource class.
     * @param mediator The mediator for the Resource.
     * @param position The initial position of the Resource.
     * @param nbResources The initial number of resources in the Resource.
     */
    public Resource(Mediator mediator, Vector2 position, int nbResources) {
        super(mediator, position);
        this.nbResources = nbResources;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        // Do nothing ?
        if (nbResources == 0) {
            getMediator().handleResourceDepleted(this);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return ResourceRenderer.getInstance();
    }

    /**
     * Gets the number of resources in the Resource.
     * @return The number of resources in the Resource.
     */
    public int getNbResources() {
        return nbResources;
    }

    /**
     * Decrements the number of resources in the Resource by 1.
     * If the Resource is depleted, it will notify the mediator.
     */
    public void decrementResource() {
        if (nbResources != 0) {
            nbResources--;
        } else {
            getMediator().handleResourceDepleted(this);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String name() {
        return "Resource";
    }

}
