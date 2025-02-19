package ch.heigvd.mcr.project.gui;


/**
 * The Displayable interface defines the methods that all displayable classes must implement.
 * It provides methods for drawing and updating the object, getting the shape of the object,
 * and getting the x and y coordinates of the object.
 *
 * The Displayable interface is implemented by classes that are responsible for displaying different types of objects in the application.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public interface Displayable {

    /**
     * Draw the object
     */
    void draw();

    /**
     * Move the object
     */
    void update();


    /**
     * Get the x coordinate of the object
     * @return the x coordinate of the object
     */
    int getX();

    /**
     * Get the y coordinate of the object
     * @return the y coordinate of the object
     */
    int getY();

}
