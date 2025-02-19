package ch.heig.mcr.labo.utils;

import java.util.Random;

/**
 * Utility class to represent a vector in the two dimensional plane
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class Vec2D {

    private final double x;
    private final double y;

    /**
     * Constructor of the Vec2D class
     *
     * @param x the coordinate on the x-axis
     * @param y the coordinate on the y-axis
     */
    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of the Vec2D class. It will generate random coordinates for the
     * vector within the given bounds.
     *
     * @param random  the random object to generate random numbers
     * @param maxWidth the maximum width of the plane (x-axis)
     * @param maxHeight the maximum height of the plane (y-axis)
     */
    public Vec2D(Random random, int maxWidth, int maxHeight) {
        x = random.nextDouble() * maxWidth;
        y = random.nextDouble() * maxHeight;
    }

    /**
     * Constructor of the Vec2D class. It will generate random coordinates for the
     * vector within [-bound, bound).
     *
     * @param random the random object to generate random numbers
     * @param bound the bound of the coordinates
     */
    public Vec2D(Random random, double bound) {
        x = -bound + 2 * bound * random.nextDouble();
        y = -bound + 2 * bound * random.nextDouble();
    }

    /**
     * Method to add two vectors together.
     *
     * @param v the second operand of the operation
     * @return a new vector which is the sum of the two vectors
     */
    public Vec2D add(Vec2D v) {
        return new Vec2D(x + v.x, y + v.y);
    }

    /**
     * Getter for the x value
     *
     * @return the x value of the vector
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the y value
     *
     * @return the y value of the vector
     */
    public double getY() {
        return y;
    }

}
