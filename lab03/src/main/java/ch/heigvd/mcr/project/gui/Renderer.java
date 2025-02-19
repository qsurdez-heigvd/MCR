package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.utils.Vector2;

import java.awt.*;

/**
 * The Renderer interface defines the methods that all renderer classes must implement.
 * It provides constants for sprite sizes and methods for displaying objects, getting object sizes,
 * and handling text and oval positioning.
 *
 * The Renderer interface is implemented by classes that are responsible for rendering different types of objects in the application.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public interface Renderer {

    int ANT_SPRITE_SIZE = 35;
    int SPIDER_SPRITE_SIZE = 45;
    int COLONY_SIZE = 100;
    int RESOURCE_SIZE = 50;
    int FONT_SIZE = 30;

    /**
     * Display the displayable object on the Graphics2D object
     *
     * @param g the Graphics2D object
     * @param d the displayable object to display
     */
    void display(Graphics2D g, Displayable d);

    /**
     * Get the coordinates of the top left corner of an oval centered at the given position
     * @param x the x position of the object
     * @param y the y position of the object
     * @param ovalWidth the wanted width of the oval
     * @param ovalHeight the wanted height of the oval
     * @return coordinates of the top left corner of an oval centered at the given position
     */
    default Vector2 getCenteredOvalPosition(int x, int y, int ovalWidth, int ovalHeight){
        int ovalX = x - ovalWidth / 2;
        int ovalY = y - ovalHeight / 2;
        return new Vector2(ovalX, ovalY);
    }


    /**
     * Print a centered text on the screen
     * @param g the Graphics2D object
     * @param text the text to print
     * @param x the x position of the text
     * @param y the y position of the text
     */
    default void printCenteredText(Graphics2D g, String text, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent() - fm.getDescent();
        int textX = x - textWidth / 2;
        int textY = y + textHeight / 2;
        g.drawString(text, textX, textY);
    }

    /**
     * Get the size of the displayable object
     * @return the size of the displayable object
     */
    int getSize();
}
