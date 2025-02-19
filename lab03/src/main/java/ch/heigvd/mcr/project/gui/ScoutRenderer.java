package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.Simulation;
import ch.heigvd.mcr.project.utils.Vector2;

import java.awt.*;

/**
 * The ScoutRenderer class implements the Renderer interface and is responsible for rendering the scout in the application.
 * It contains a Sprite object which is the image representation of the scout.
 * The class provides a method to display the spider and a method to get the size of the spider.
 * It also provides a method to get the unique instance of the ScoutRenderer class, following the Singleton design pattern.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ScoutRenderer implements Renderer{

    private static final Sprite sprite = new Sprite(ScoutRenderer.class.getResource("/assets/scout.png"), ANT_SPRITE_SIZE, ANT_SPRITE_SIZE);


    /**
     * Constructor
     */
    private ScoutRenderer(){}

    @Override
    public void display(Graphics2D g, Displayable d) {
        int x = d.getX();
        int y = d.getY();
        Vector2 imageCenter = getCenteredOvalPosition(x, y, sprite.getWidth(), sprite.getHeight());
        g.drawImage(sprite.getImage(), imageCenter.getX(), imageCenter.getY(), null);

        if(Simulation.getInstance().isColorblindMode()) {
            g.setFont(new Font("default", Font.BOLD, 30));
            printCenteredText(g, "S", x, y);
        }
    }

    /**
     * Get the size of the scout
     * @return the size of the scout
     */
    public int getSize(){
        return ANT_SPRITE_SIZE;
    }

    /**
     * Get the unique instance of the ScoutRenderer type
     * @return the unique instance of the ScoutRenderer type
     */
    public static ScoutRenderer getInstance(){
        return Instance.instance;
    }

    /**
     * The inner class that holds the unique instance of the ScoutRenderer type
     */
    public static class Instance{
        static final ScoutRenderer instance = new ScoutRenderer();
    }
}
