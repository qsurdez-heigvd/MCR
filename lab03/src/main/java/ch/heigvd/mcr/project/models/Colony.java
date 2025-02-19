package ch.heigvd.mcr.project.models;


import ch.heigvd.mcr.project.gui.ColonyRenderer;
import ch.heigvd.mcr.project.gui.Renderer;
import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.utils.Vector2;



/**
 * Class representing a Colony in the simulation.
 * A Colony is an entity that can generate ants and manage food resources via the mediator
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Colony extends AbstractColleague {

    private int nbFood; // The amount of food the colony has

    /**
     * Constructor for the Colony class.
     * @param mediator The mediator for the Colony.
     * @param position The initial position of the Colony.
     */
    public Colony(Mediator mediator, Vector2 position) {
        super(mediator, position);
        nbFood = 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        getMediator().addAnt();
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Renderer getRenderer() {
        return ColonyRenderer.getInstance();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String name() {
        return "Colony";
    }

    /**
     * Getter for the nbFood attribute
     * @return The amount of food the Colony has.
     */
    public int getNbFood() {
        return nbFood;
    }


    /**
     * Increments the amount of food the Colony has by 1.
     */
    public void incrementFood() {
        nbFood++;
    }

    /**
     * Decrements the amount of food the Colony has by a specified amount.
     * @param nbFoodUsed The amount of food to be used.
     */
    public void decrementFood(int nbFoodUsed) {
        nbFood -= nbFoodUsed;
    }


}
