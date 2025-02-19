package ch.heig.mcr.labo.displayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * This class is used to display the shapes in the window. It implements the Displayer interface.
 * It is a singleton, which means that only one instance of this class can be created.
 *
 * @author REDACTED
 * @author Quentin Surdez
 */
public class DisplayWindow implements Displayer {

    private final GPanel container;

    private final JFrame frame;


    /**
     * This class is used to create the unique instance of the DisplayWindow class.
     */
    private static class Instance {
        static private final DisplayWindow instance = new DisplayWindow();
    }

    /**
     * Constructor of the DisplayWindow class. It creates a new JFrame and sets its properties.
     * It creates a new GPanel and adds it to the JFrame.
     */
    private DisplayWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        container = new GPanel();
        frame.add(container);
        container.setVisible(true);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Get the unique instance of the DisplayWindow class.
     *
     * @return the unique instance of the DisplayWindow class
     */
    public static DisplayWindow getInstance() {
        return Instance.instance;
    }

    /**
     * @inheritDoc
     */
    public int getWidth() {
        return container.getWidth();
    }

    /**
     * @inheritDoc
     */
    public int getHeight() {
        return container.getHeight();
    }

    /**
     * @inheritDoc
     */
    public Graphics2D getGraphics() {
        return (Graphics2D) container.image.getGraphics();
    }

    /**
     * @inheritDoc
     */
    public void repaint() {
        frame.repaint();
    }

    /**
     * @inheritDoc
     */
    public void setTitle(String name) {
        frame.setTitle(name);
    }

    /**
     * @inheritDoc
     */
    public void addKeyListener(KeyAdapter ka) {
        frame.addKeyListener(ka);
    }

    /**
     * This inner class is used to create a JPanel that will be added to the JFrame.
     * It is used to display the shapes in the window.
     */
    private static class GPanel extends JPanel {

        private BufferedImage image;

        /**
         * Default constructor of the GPanel class
         */
        GPanel() {
            setSize(500, 500);
            setPreferredSize(getSize());
            setBackground(Color.WHITE);

            updateImage();
        }

        /**
         * This method is used to paint the image on which the shapes are drawn.
         *
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.drawImage(image, 0, 0,  getWidth(), getHeight(), this);
            setVisible(true);
            updateImage();
        }

        /**
         * Utilitary method to create a new image.
         */
        private void updateImage() {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
    }
}
