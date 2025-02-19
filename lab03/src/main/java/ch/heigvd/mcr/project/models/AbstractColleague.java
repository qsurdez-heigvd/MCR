package ch.heigvd.mcr.project.models;

import ch.heigvd.mcr.project.gui.Displayable;
import ch.heigvd.mcr.project.gui.Renderer;
import ch.heigvd.mcr.project.gui.SimulationDisplayer;
import ch.heigvd.mcr.project.utils.Vector2;
import ch.heigvd.mcr.project.mediator.Mediator;

/**
 * Abstract class representing a colleague in the simulation.
 * A colleague is a participant in the simulation and can interact with other colleagues only
 * via the Mediator
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public abstract class AbstractColleague implements Displayable {

    private Mediator mediator; // The mediator for the colleague
    protected Vector2 position; // The position of the colleague
    private final int id; // The unique identifier for the colleague
    static private int idCounter = 0; // Counter to generate unique identifiers for colleagues


    /**
     * Constructor for the AbstractColleague class.
     * @param mediator The mediator for the colleague.
     * @param position The initial position of the colleague.
     */
    public AbstractColleague(Mediator mediator, Vector2 position) {
        this.mediator = mediator;
        this.position = position;
        id = idCounter++;
    }

    /**
     * Gets the mediator of the colleague.
     * @return The mediator of the colleague.
     */
    public Mediator getMediator() {
        return mediator;
    }

    /**
     * Sets the mediator of the colleague.
     * @param newMediator The new mediator for the colleague.
     */
    public void setMediator(Mediator newMediator) {
        this.mediator = newMediator;
    }

    /**
     * Getter for the position
     * @return The position of the colleague
     */
    public Vector2 getPosition() {
        return position;
    }


    /**
     * Method to update each of the Colleagues
     * Will be called at each turn by the Mediator
     */
    public abstract void update();

    /**
     * Getter for the x position
     * @return The x-coordinate of the colleague's position
     */
    public int getX(){
        return position.getX();
    }

    /**
     * Getter for the y position
     * @return The y-coordinate of the colleague's position
     */
    public int getY(){
        return position.getY();
    }

    /**
     * Getter for the renderer of the entity
     * @return The renderer of the entity
     */
    protected abstract Renderer getRenderer();

    /**
     * Method to draw the entity
     */
    public void draw() {
        getRenderer().display(SimulationDisplayer.getInstance().getGraphics(), this);
    }

    /**
     * Method to get the name of the colleague
     * @return The name of the colleague
     */
    public abstract String name();

    /**
     * Method to get the size of the colleague
     * @return The size of the colleague
     */
    public int getSize() {
        return getRenderer().getSize();
    }

    /**
     * Method to get a string representation of the colleague
     * @return A string representation of the colleague
     */
    @Override
    public String toString() {
        return name() + " #" + id + " at " + position;
    }
}
