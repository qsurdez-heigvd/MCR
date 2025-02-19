package ch.heigvd.mcr.project.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * The Sprite class extends JFrame and is used to handle image sprites in the application.
 * It contains an Image object, and the width and height of the sprite.
 * It also provides getter methods for the image, width, and height.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Sprite extends JFrame {

    private Image image;
    private final int width;
    private final int height;

    /**
     * Sprite Constructor
     * @param file the path of the image file
     */
    public Sprite(URL file, int width, int height) {
        this.width = width;
        this.height = height;
        try {
            this.image = ImageIO.read(file).getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the image
     * @return the image of the sprite
     */
    public Image getImage(){
        return image;
    }

    /**
     * Get the width of the sprite
     * @return the width of the sprite
     */
    public int getWidth(){
        return width;
    }

    /**
     * Get the height of the sprite
     * @return the height of the sprite
     */
    public int getHeight(){
        return height;
    }
}
