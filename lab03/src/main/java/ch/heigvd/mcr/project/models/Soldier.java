package ch.heigvd.mcr.project.models;

import ch.heigvd.mcr.project.gui.Renderer;
import ch.heigvd.mcr.project.gui.SoldierRenderer;
import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * Class representing a Soldier ant in the simulation.
 * A Soldier is an ant that has a higher strength and can interact with predators.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Soldier extends Ant {

    /**
     * Constructor for the Soldier class.
     * @param mediator The mediator for the Soldier.
     * @param position The initial position of the Soldier.
     */
    public Soldier(Mediator mediator, Vector2 position) {
        super(mediator, position);
        pv = 20;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getStrength(){
        return 4;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return SoldierRenderer.getInstance();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        getMediator().handleUpdateSoldier(this);
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
        return "Soldier";
    }

}
