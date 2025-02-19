package ch.heigvd.mcr.project.models;

import ch.heigvd.mcr.project.mediator.Mediator;
import ch.heigvd.mcr.project.mediator.strategies.*;
import ch.heigvd.mcr.project.utils.Vector2;


import java.util.Random;

import static ch.heigvd.mcr.project.utils.Vector2.getDirection;

/**
 * Abstract class representing an entity in the simulation.
 * An entity is a participant in the simulation that can move, have objectives, and interact with other entities.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public abstract class Entity extends AbstractColleague {

    static private final Random random = new Random(); // Random object for generating random values
    protected Vector2 fleeing; // Direction in which the entity is fleeing
    protected int pv; // Vitality points of the entity
    protected Vector2 exploring; // Direction in which the entity is exploring
    private int count; // Counter for the exploring entity


    /**
     * Constructor for the Entity class.
     * @param mediator The mediator for the entity.
     * @param position The initial position of the entity.
     */
    public Entity(Mediator mediator, Vector2 position) {
        super(mediator, position);
        this.count = 0;
    }

    /**
     * Abstract method to get the strength of the entity.
     * @return The strength of the entity.
     */
    abstract public int getStrength();

    /**
     * Gets the count of the entity.
     * @return The count of the entity.
     */
    public int getCount() {
        return count;
    }

    /**
     * Resets the counter of the entity.
     */
    public void resetCounter() {
        count = 0;
    }

    /**
     * Increments the counter of the entity.
     */
    public void incrementCounter(){
        this.count++;
    }

    /**
     * Makes the entity flee from a given position.
     * @param predatorPosition The position the entity is fleeing from.
     */
    public void flee(Vector2 predatorPosition) {
        fleeing = getDirection(predatorPosition, position);
    }

    /**
     * Makes the entity stop fleeing.
     */
    public void stopFleeing() {
        fleeing = null;
    }

    /**
     * Checks if the entity is currently fleeing.
     * @return True if the entity is fleeing, false otherwise.
     */
    public boolean isFleeing() {
        return fleeing != null;
    }

    /**
     * Checks if the entity is currently fleeing.
     * @return The direction the entity is fleeing in, null if not fleeing.
     */
    public Vector2 getFleeing() {
        return fleeing;
    }

    /**
     * Gets the direction the entity is exploring in.
     * @return The direction the entity is exploring in.
     */
    public Vector2 getExploring() {
        return exploring;
    }

    /**
     * Sets the direction the entity is exploring in.
     * @param newExploring The new direction for the entity to explore in.
     */
    public void setExploring(Vector2 newExploring) {
        this.exploring = newExploring;
    }

    /**
     * Gets the vitality points of the entity.
     * @return The vitality points of the entity.
     */
    public int getPv() {
        return pv;
    }

    /**
     * Decrements the vitality points of the entity by a given amount.
     * If the vitality points reach 0, the entity dies.
     * @param amount The amount to decrement the vitality points by.
     */
    public void decrementPv(int amount) {
        pv -= amount;
        if (pv <= 0) {
            this.die();
        }
    }

    /**
     * Abstract method to handle the death of the entity.
     */
    public abstract void die();

    /**
     * Moves the entity to a new position.
     * @param newPosition The new position for the entity to move to.
     */
    public void move(Vector2 newPosition) {
        boolean foundPos = false;
        Vector2 newDir = Vector2.getDirection(position, newPosition);
        if (getMediator().checkCollision(newPosition, this)) {
            newPosition = position.add(Vector2.getOrthogonalDirection(newDir));
            if (getMediator().checkCollision(newPosition, this)) {
                for (var dir : Vector2.POSSIBLE_DIRECTIONS.values()) {
                    newPosition = position.add(dir.getDirection());
                    if (!getMediator().checkCollision(newPosition, this)) {
                        foundPos = true;
                        break;
                    }
                }
            } else {
                foundPos = true;
            }
        } else {
            foundPos = true;
        }
        if (foundPos) {
            position = newPosition;
        }
    }

    /**
     * Gets the random object for the entity.
     * @return The random object for the entity.
     */
    public Random getRandom() {
        return random;
    }


    /**
     * Makes the entity attack.
     */
    public void attack() {
        getMediator().entityAttacked(this);
    }

    /**
     * Abstract method to get the vision range of the entity.
     * @return The vision range of the entity.
     */
    public abstract int getVisionRange();
}
