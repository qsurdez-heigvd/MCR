package ch.heigvd.mcr.project.gui;

import java.awt.*;
import ch.heigvd.mcr.project.models.Resource;
import ch.heigvd.mcr.project.utils.Vector2;

/**
 * The ResourceRenderer class implements the Renderer interface and is responsible for rendering resources in the application.
 * It follows the Singleton design pattern, meaning only one instance of this class can exist.
 * The class provides a method to display the resource and a method to get the size of the resource.
 * It also provides a method to get the unique instance of the ResourceRenderer class.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ResourceRenderer implements Renderer {

    /**
     * Constructor
     */
    private ResourceRenderer(){}

    @Override
    public void display(Graphics2D g, Displayable d) {
        Resource resource = (Resource) d;
        int x = resource.getX();
        int y = resource.getY();
        Vector2 ovalCenter = getCenteredOvalPosition(x, y, RESOURCE_SIZE, RESOURCE_SIZE);

        // Draw the circle
        g.setColor(Color.blue);
        g.fillOval(ovalCenter.getX(), ovalCenter.getY(), RESOURCE_SIZE, RESOURCE_SIZE);

        // Draw the nbResources text
        g.setColor(Color.white);
        g.setFont(new Font("default", Font.BOLD, FONT_SIZE));
        String nbResourcesText = String.valueOf(resource.getNbResources());
        printCenteredText(g, nbResourcesText, x, y);
    }

    /**
     * Get the size of the resource
     * @return the size of the resource
     */
    public int getSize(){
        return ANT_SPRITE_SIZE;
    }

    /**
     * Get the unique instance of the ResourceRenderer type
     * @return the unique instance of the ResourceRenderer type
     */
    public static ResourceRenderer getInstance(){
        return Instance.instance;
    }

    /**
     * The inner class that holds the unique instance of the ResourceRenderer type
     */
    public static class Instance{
        static final ResourceRenderer instance = new ResourceRenderer();
    }
}
