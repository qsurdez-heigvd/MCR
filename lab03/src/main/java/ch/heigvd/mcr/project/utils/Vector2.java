package ch.heigvd.mcr.project.utils;

import java.util.Random;

/**
 * This class represents a 2D vector with integer coordinates.
 * It provides various utility methods for vector operations.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Vector2 {

    /**
     * Enum representing the 8 possible directions for a vector.
     */
    public enum POSSIBLE_DIRECTIONS {

        UP(new Vector2(0, 1)),
        DOWN(new Vector2(0, -1)),
        LEFT(new Vector2(-1, 0)),
        RIGHT(new Vector2(1, 0)),
        UP_LEFT(new Vector2(-1, 1)),
        UP_RIGHT(new Vector2(1, 1)),
        DOWN_LEFT(new Vector2(-1, -1)),
        DOWN_RIGHT(new Vector2(1, -1));

        private final Vector2 vector;

        /**
         * Constructor for the enum.
         * @param vector The vector representing the direction.
         */
        POSSIBLE_DIRECTIONS(Vector2 vector) {
            this.vector = vector;
        }

        /**
         * Constructor for the enum.
         * @return The vector representing the direction.
         */
        public Vector2 getDirection() {
            return vector;
        }

    }

    static public Random random = new Random(); // Random object for generating random values
    private final int x; // x-coordinate of the Vector
    private final int y; // y-coordinate of the Vector

    /**
     * Constructor for the Vector2 class.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor for the Vector2 class.
     * @param other The vector to copy.
     */
    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Constructor for the Vector2 class that generates random coordinates.
     * @param random The Random object to generate random numbers.
     * @param maxWidth The maximum possible x-coordinate.
     * @param maxHeight The maximum possible y-coordinate.
     */
    public Vector2(Random random, int maxWidth, int maxHeight) {
        x = (int) (random.nextDouble() * maxWidth);
        y = (int) (random.nextDouble() * maxHeight);
    }

    /**
     * Constructor for the Vector2 class that generates random coordinates within specified bounds.
     * @param random The Random object to generate random numbers.
     * @param minWidth The minimum possible x-coordinate.
     * @param minHeight The minimum possible y-coordinate.
     * @param maxWidth The maximum possible x-coordinate.
     * @param maxHeight The maximum possible y-coordinate.
     */
    public Vector2(Random random, int minWidth, int minHeight, int maxWidth, int maxHeight) {
        x = (int) (random.nextDouble() * (maxWidth-minWidth) + minWidth);
        y = (int) (random.nextDouble() * (maxHeight-minHeight)+ minHeight);
    }


    /**
     * Constructor for the Vector2 class that generates random coordinates within a bound.
     * @param random The Random object to generate random numbers.
     * @param bound The bound for the coordinates.
     */
    public Vector2(Random random, int bound) {
        x = (int) (-bound + 2 * bound * random.nextDouble());
        y = (int) (-bound + 2 * bound * random.nextDouble());
    }

    /**
     * Method to get the direction from one vector to another.
     * @param from The starting vector.
     * @param to The ending vector.
     * @return A vector representing the direction from 'from' to 'to'.
     */
    public static Vector2 getDirection(Vector2 from, Vector2 to) {
        int _x = to.x - from.x;
        int _y = to.y - from.y;

        return new Vector2(
                _x == 0 ? 0 : _x / Math.abs(_x),
                _y == 0 ? 0 : _y / Math.abs(_y)
        );
    }

    /**
     * Method to check if a vector is within a certain range of another vector.
     * @param from The starting vector.
     * @param to The ending vector.
     * @param range The range to check within.
     * @return True if 'to' is within 'range' of 'from', false otherwise.
     */
    public static boolean isWithinRange(Vector2 from, Vector2 to, int range) {
        return from.dist(to) <= range;
    }

    /**
     * Method to get a random non-null vector from the possible directions
     * @return A random non-null vector.
     */
    public static Vector2 getRandomNonNullVector() {
        int dir = random.nextInt(0, POSSIBLE_DIRECTIONS.values().length);
        return POSSIBLE_DIRECTIONS.values()[dir].getDirection();
    }

    /**
     * Method to add two vectors.
     * @param v The vector to add.
     * @return A new vector that is the sum of this vector and 'v'.
     */
    public Vector2 add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Getter for the x-coordinate.
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate.
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Method to get the distance between this vector and another vector.
     * @param v The other vector.
     * @return The distance between this vector and 'v'.
     */
    public double dist(Vector2 v) {
        return  Math.sqrt((Math.pow(v.x - x, 2) + Math.pow(v.y - y, 2)));
    }


    /**
     * Method to get a string representation of this vector.
     * @return A string representation of this vector.
     */
    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }

    /**
     * Method to get the orthogonal direction to a given direction.
     * @param collisionDirection The given direction.
     * @return The orthogonal direction to 'collisionDirection'.
     */
    public static Vector2 getOrthogonalDirection(Vector2 collisionDirection) {
        return new Vector2(collisionDirection.getY(), -collisionDirection.getX());
    }

    /**
     * Method to check if a vector is colliding with another vector.
     * @param previousPos The previous position of the vector.
     * @param newPos The new position of the vector.
     * @param otherPos The position of the other vector.
     * @param radiusFirst The radius of the first vector.
     * @param radiusSecond The radius of the second vector.
     * @return True if the vectors are colliding, false otherwise.
     */
    public static boolean isColliding(Vector2 previousPos, Vector2 newPos, Vector2 otherPos, double radiusFirst, double radiusSecond) {
   if(newPos.dist(otherPos) < radiusFirst + radiusSecond && previousPos.dist(otherPos) > newPos.dist(otherPos)) {
       return true;
   }
   return false;
    }

    /**
     * Method to check if a vector is going out of bounds.
     * @param previousPos The previous position of the vector.
     * @param newPosition The new position of the vector.
     * @param entityRadius The radius of the entity.
     * @param mapWidth The width of the map.
     * @param mapHeight The height of the map.
     * @return True if the vector is going out of bounds, false otherwise.
     */
    public static boolean outOfBounds(Vector2 previousPos, Vector2 newPosition, double entityRadius, int mapWidth, int mapHeight) {
        if(newPosition.getX() < entityRadius || newPosition.getX() > mapWidth -entityRadius || newPosition.getY() < entityRadius|| newPosition.getY() > mapHeight - entityRadius) {
            if(minToBound(previousPos, entityRadius, mapWidth, mapHeight) > minToBound(newPosition, entityRadius, mapWidth, mapHeight)) {
                return true;
            }
        }
       return false;
    }

    /**
     * Method to get the minimum distance to the boundary of the map.
     * @param position The position of the vector.
     * @param entityRadius The radius of the entity.
     * @param mapWidth The width of the map.
     * @param mapHeight The height of the map.
     * @return The minimum distance to the boundary of the map.
     */
    public static double minToBound(Vector2 position, double entityRadius, int mapWidth, int mapHeight) {
        return Math.min(Math.min((position.getX()-entityRadius), (mapWidth - (position.getX()+entityRadius))), Math.min(mapHeight - (position.getY()+entityRadius), (position.getY()-entityRadius)));
    }

}
