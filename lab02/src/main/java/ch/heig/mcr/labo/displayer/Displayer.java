package ch.heig.mcr.labo.displayer;

import java.awt.*;
import java.awt.event.KeyAdapter;

/**
 * Interface that has all the methods
 * needed for the class that implements Displayer.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public interface Displayer {
    /**
     * Method to get the width of the window
     *
     * @return current width of the window
     */
    int getWidth();

    /**
     * Method to get the height of the window
     *
     * @return current height of the window
     */
    int getHeight();

    /**
     * Method that will get the Graphics linked to
     * the window
     *
     * @return a graphic object to draw on
     */
    Graphics2D getGraphics();

    /**
     * Method to repaint the window.
     */
    void repaint();

    /**
     * Method to set the title of the window
     *
     * @param name new title of the window
     */
    void setTitle(String name);

    /**
     * Method to add a KeyListener to the window
     *
     * @param ka the KeyAdapter that needs to be followed by the window
     */
    void addKeyListener(KeyAdapter ka);

}
