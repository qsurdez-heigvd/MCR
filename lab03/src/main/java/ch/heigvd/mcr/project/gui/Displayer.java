package ch.heigvd.mcr.project.gui;

import java.awt.*;

/**
 * The Displayer interface defines the methods that all displayer classes must implement.
 * It provides methods for getting the width and height of the displayer, getting the graphics of the displayer,
 * repainting the displayer, and setting the title of the displayer.
 *
 * The Displayer interface is implemented by classes that are responsible for displaying different types of objects in the application.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public interface Displayer {

    /**
     * Get the width of the displayer
     *
     * @return the width of the displayer
     */
    int getWidth();

    /**
     * Get the height of the displayer
     *
     * @return the height of the displayer
     */
    int getHeight();

    /**
     * Get the graphics of the displayer
     *
     * @return the graphics of the displayer
     */
    Graphics2D getGraphics();

    /**
     * Repaint the displayer
     */
    void repaint();

    /**
     * Set the title of the displayer
     *
     * @param title the new title of the displayer
     */
    void setTitle(String title);
}
