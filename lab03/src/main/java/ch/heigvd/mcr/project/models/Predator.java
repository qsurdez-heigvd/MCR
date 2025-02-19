package ch.heigvd.mcr.project.models;

import ch.heigvd.mcr.project.gui.SpiderRenderer;
import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;
import ch.heigvd.mcr.project.gui.Renderer;


/**
 * Class representing a Predator in the simulation.
 * A Predator is an entity that can attack preys and interact with other entities via the mediator
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Predator extends Entity {

    /**
     * Constructor
     * @param mediator The mediator to which the predator is linked
     * @param position The position of the predator
     */
    public Predator(Mediator mediator, Vector2 position) {
        super(mediator, position);
        pv = 80;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getStrength(){
        return 5;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return SpiderRenderer.getInstance();
    }

    /**
     * Method to ask the mediator if there are preys nearby the predator.
     */
    public void areTherePreysNearby() {
        getMediator().checkForNearbyPreys(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        getMediator().handleUpdatePredator(this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String name() {
        return "Predator";
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getVisionRange() {
        return 10;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void die() {
        System.out.println(this + " is dead");
        getMediator().handlePredatorDeath(this);
    }
}
