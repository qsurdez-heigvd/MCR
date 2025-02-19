package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.utils.Vector2;
import ch.heigvd.mcr.project.Simulation;

import java.awt.*;

/**
 * The GathererRenderer class implements the Renderer interface and is responsible for rendering the gatherer in the application.
 * It contains a Sprite object which is the image representation of the gatherer.
 * The class provides a method to display the spider and a method to get the size of the spider.
 * It also provides a method to get the unique instance of the GathererRenderer class, following the Singleton design pattern.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class GathererRenderer implements Renderer{

    private static final Sprite sprite = new Sprite(GathererRenderer.class.getResource("/assets/gatherer.png"), ANT_SPRITE_SIZE, ANT_SPRITE_SIZE);

    /**
     * Default constructor
     */
    private GathererRenderer(){}

    /**
     * Display the gatherer on the screen
     * @param g the Graphics2D object
     * @param d the displayable object to display
     */
    @Override
    public void display(Graphics2D g, Displayable d) {
        int x = d.getX();
        int y = d.getY();
        Vector2 imageCenter = getCenteredOvalPosition(x, y, sprite.getWidth(), sprite.getHeight());
        g.drawImage(sprite.getImage(), imageCenter.getX(), imageCenter.getY(), null);

        if(Simulation.getInstance().isColorblindMode()) {
            g.setFont(new Font("default", Font.BOLD, FONT_SIZE));
            printCenteredText(g, "G", x, y);
        }
    }

    /**
     * Get the size of the gatherer
     * @return the size of the gatherer
     */
    public int getSize(){
        return ANT_SPRITE_SIZE;
    }

    /**
     * Get the unique instance of the GathererRenderer type
     * @return the unique instance of the GathererRenderer type
     */
    public static GathererRenderer getInstance(){
        return Instance.instance;
    }

    /**
     * The inner class that holds the unique instance of the GathererRenderer type
     */
    public static class Instance{
        static final GathererRenderer instance = new GathererRenderer();
    }
}
