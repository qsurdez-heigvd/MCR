package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.models.Colony;
import ch.heigvd.mcr.project.utils.Vector2;

import java.awt.*;

/**
 * The ColonyRenderer class implements the Renderer interface and is responsible for rendering the colony in the application.
 * It follows the Singleton design pattern, meaning only one instance of this class can exist.
 * The class provides a method to display the colony, a method to get the size of the colony,
 * and a method to get the unique instance of the ColonyRenderer class.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ColonyRenderer implements Renderer{

    /**
     * Private constructor
     */
    private ColonyRenderer(){}

    /**
     * Display the colony on the screen
     * @param g the Graphics2D object
     * @param d the displayable object to display
     */
    @Override
    public void display(Graphics2D g, Displayable d) {
        Colony colony = (Colony) d;
        int x = colony.getX();
        int y = colony.getY();
        Vector2 ovalCenter = getCenteredOvalPosition(x, y, COLONY_SIZE, COLONY_SIZE);

        g.setColor(Color.red);
        g.fillOval(ovalCenter.getX(), ovalCenter.getY(), COLONY_SIZE, COLONY_SIZE);

        g.setColor(Color.white);
        g.setFont(new Font("default", Font.BOLD, 30));
        String nbFood = Integer.toString(colony.getNbFood());
        printCenteredText(g, nbFood, x, y);
    }

    /**
     * Get the size of the colony
     * @return the size of the colony
     */
    public int getSize(){
        return COLONY_SIZE;
    }

    /**
     * Get the unique instance of the ColonyRenderer
     * @return the unique instance of the ColonyRenderer
     */
    public static ColonyRenderer getInstance(){
        return Instance.instance;
    }

    /**
     * The inner class that holds the unique instance of the ColonyRenderer
     */
    public static class Instance{
        static final ColonyRenderer instance = new ColonyRenderer();
    }
}
