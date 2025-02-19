package ch.heigvd.mcr.project.models;


import ch.heigvd.mcr.project.gui.GathererRenderer;
import ch.heigvd.mcr.project.gui.Renderer;
import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * Class representing a Gatherer ant in the simulation.
 * A Gatherer is an ant that can gather resources and interact with predators via the mediator.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Gatherer extends Ant {

    /**
     * Constructor for the Gatherer class.
     * @param mediator The mediator for the Gatherer.
     * @param position The initial position of the Gatherer.
     */
    public Gatherer(Mediator mediator, Vector2 position) {
        super(mediator, position);
        fleeing = null;
        pv = 5;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getStrength(){
        return 1;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return GathererRenderer.getInstance();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        getMediator().handleUpdateGatherer(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getVisionRange() {
        return 5;
    }


    /**
     * @inheritDoc
     */
    @Override
    public String name() {
        return "Gatherer";
    }

}
