package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.Simulation;
import ch.heigvd.mcr.project.utils.Vector2;

import java.awt.*;

/**
 * The SoldierRenderer class implements the Renderer interface and is responsible for rendering the soldier in the application.
 * It contains a Sprite object which is the image representation of the soldier.
 * The class provides a method to display the spider and a method to get the size of the spider.
 * It also provides a method to get the unique instance of the SoldierRenderer class, following the Singleton design pattern.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class SoldierRenderer implements Renderer{

    private static final Sprite sprite = new Sprite(SoldierRenderer.class.getResource("/assets/soldier.png"), ANT_SPRITE_SIZE, ANT_SPRITE_SIZE);


    /**
     * Default Constructor
     */
    private SoldierRenderer(){}

    /**
     * Display the soldier
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
            g.setFont(new Font("default", Font.BOLD, 30));
            printCenteredText(g, "F", x, y);
        }
    }

    public int getSize(){
        return ANT_SPRITE_SIZE;
    }

    /**
     * Get the unique instance of the SoldierRenderer
     * @return the unique instance of the SoldierRenderer
     */
    public static SoldierRenderer getInstance(){
        return Instance.instance;
    }


    /**
     * Inner class that hold the unique instance of SoldierRenderer
     */
    public static class Instance{
        static final SoldierRenderer instance = new SoldierRenderer();
    }
}
