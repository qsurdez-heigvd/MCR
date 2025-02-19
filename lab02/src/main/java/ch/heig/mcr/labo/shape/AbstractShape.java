package ch.heig.mcr.labo.shape;

import ch.heig.mcr.labo.displayer.DisplayWindow;
import ch.heig.mcr.labo.renderer.Renderer;
import ch.heig.mcr.labo.utils.Vec2D;


import java.util.Random;

/**
 * Abstract class for shapes. It implements the Bounceable interface. It uses the Vector2D class from the Apache Commons
 * to represent the position as well as the velocity of the shape.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public abstract class AbstractShape implements Bounceable {

    private final int size;
    private Vec2D position;
    private Vec2D velocity;

    private final static int MAX_SIZE = 30;
    private final static int MIN_SIZE = 5;
    private final static double BOUND = 2.0;

    /**
     * Constructor of the class. It will initialize the position, the velocity and the size of the shape
     * with random values.
     *
     * @param random Random object to generate random values
     */
    public AbstractShape(Random random) {

        // Chosen size between 5 and 30
        size = random.nextInt(MAX_SIZE-MIN_SIZE) + MIN_SIZE;

        position = new Vec2D(random, getMaxPositionXAxis(size), getMaxPositionYAxis(size));

        // Chosen bound of 2.0 for the velocity
        velocity = new Vec2D(random, BOUND);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void draw() {
        getRenderer().display(DisplayWindow.getInstance().getGraphics(), this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void move() {
        DisplayWindow displayWindow = DisplayWindow.getInstance();
        position = position.add(velocity);
        // Check if it is within bounds and update the velocity if it is not
        if (!isWithinBounds()) {
            velocityBouncing();
            position = position.add(velocity);
        }
    }

    /**
     * Get the specific renderer of the shape
     *
     * @return the renderer of the shape
     */
    abstract protected Renderer getRenderer();

    /**
     * Check if the shape is within the bounds of the window.
     *
     * @return true if the shape is within the bounds of the window, false otherwise
     */
    private boolean isWithinBounds() {
        return position.getX() >= 0 && position.getX() <= getMaxPositionXAxis(size) && position.getY() >= 0 && position.getY() <= getMaxPositionYAxis(size);
    }

    /**
     * Update the velocity vector if the shape is not within the bounds of the window and needs to bounce.
     * It will also update the position of the shape after a resizing of the window.
     */
    private void velocityBouncing() {
        if (position.getX() <= 0 || position.getX() >= getMaxPositionXAxis(size)) {
            velocity = new Vec2D(-velocity.getX(), velocity.getY());
        }
        if (position.getY() <= 0 || position.getY() >= getMaxPositionYAxis(size)) {
            velocity = new Vec2D(velocity.getX(), -velocity.getY());
        }
        position = new Vec2D(Math.min(position.getX(), getMaxPositionXAxis(size)), Math.min(position.getY(), getMaxPositionYAxis(size)));
    }

    /**
     * Get the maximum position on the x-axis for the shape with its specific size
     *
     * @param size the size of the shape
     * @return the maximum position on the x-axis
     */
    private int getMaxPositionXAxis(int size) {
        return DisplayWindow.getInstance().getWidth() - size;
    }

    /**
     * Get the maximum position on the y-axis for the shape with its specific size
     *
     * @param size the size of the shape
     * @return the maximum position on the y-axis
     */
    private int getMaxPositionYAxis(int size) {
        return DisplayWindow.getInstance().getHeight() - size;
    }

    /**
     * Getter for the position of the shape. Only used in children classes
     *
     * @return the position of the shape
     */
    protected Vec2D getPosition() {
        return position;
    }

    /**
     * Getter for the size of the shape. Only used in children classes
     *
     * @return the size of the shape
     */
    protected int getSize() {
        return size;
    }
}
