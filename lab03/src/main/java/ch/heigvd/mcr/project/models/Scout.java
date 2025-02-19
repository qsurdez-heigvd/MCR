package ch.heigvd.mcr.project.models;

import ch.heigvd.mcr.project.gui.Renderer;
import ch.heigvd.mcr.project.gui.ScoutRenderer;
import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * Class representing a Scout ant in the simulation.
 * A Scout is an ant that has a larger vision range and can interact with predators via the mediator
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Scout extends Ant {

    /**
     * Constructor for the Scout class.
     * @param mediator The mediator for the Scout.
     * @param position The initial position of the Scout.
     */
    public Scout(Mediator mediator, Vector2 position) {
        super(mediator, position);
        pv = 10;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getStrength(){
        return 2;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return ScoutRenderer.getInstance();
    }


    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        getMediator().handleUpdateScout(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getVisionRange() {
        return 20;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String name() {
        return "Scout";
    }
}
