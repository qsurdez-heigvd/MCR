package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.models.Predator;
import ch.heigvd.mcr.project.utils.Vector2;

import java.awt.*;

/**
 * The SpiderRenderer class implements the Renderer interface and is responsible for rendering the spider in the application.
 * It contains a Sprite object which is the image representation of the spider.
 * The class provides a method to display the spider and a method to get the size of the spider.
 * It also provides a method to get the unique instance of the SpiderRenderer class, following the Singleton design pattern.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class SpiderRenderer implements Renderer {

    private static final Sprite sprite = new Sprite(SpiderRenderer.class.getResource("/assets/spider.png"), SPIDER_SPRITE_SIZE, SPIDER_SPRITE_SIZE);

    /**
     * Default constructor
     */
    private SpiderRenderer(){}

    /**
     * Display the spider
     * @param g the Graphics2D object
     * @param d the displayable object to display
     */
    @Override
    public void display(Graphics2D g, Displayable d) {
        Predator predator = (Predator) d;
        int x = d.getX();
        int y = d.getY();
        Vector2 imageCenter = getCenteredOvalPosition(x, y, sprite.getWidth(), sprite.getHeight());
        g.drawImage(sprite.getImage(), imageCenter.getX(), imageCenter.getY(), null);

        String nbPV = String.valueOf(predator.getPv());
        g.setFont(new Font("default", Font.BOLD, 30));
        printCenteredText(g, nbPV, x, y);
    }

    /**
     * Get the size of the predator
     * @return the size of the predator
     */
    public int getSize(){
        return SPIDER_SPRITE_SIZE;
    }

    /**
     * Get the unique instance of the SpiderRenderer
     * @return the unique instance of the SpiderRenderer
     */
    public static SpiderRenderer getInstance(){
        return Instance.instance;
    }

    /**
     * The inner class that holds the unique instance of the SpiderRenderer
     */
    public static class Instance{
        static final SpiderRenderer instance = new SpiderRenderer();
    }
}
