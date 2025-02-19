package ch.heigvd.mcr.project.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * The class that displays the simulation
 *
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class SimulationDisplayer implements Displayer {

    public static final int INITIAL_WIDTH = 800;
    public static final int INITIAL_HEIGHT = 700;
    private final JFrame jFrame = new JFrame();
    private Image image = new BufferedImage(INITIAL_WIDTH, INITIAL_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private Image backgroundImage;

    /**
     * Constructor
     */
    private SimulationDisplayer() {
        try {
            Image originalBackgroundImage = ImageIO.read(Objects.requireNonNull(SimulationDisplayer.class.getResource(("/assets/background.jpg"))));
            Image iconImage = ImageIO.read(Objects.requireNonNull(SimulationDisplayer.class.getResource("/assets/icon.png")));
            backgroundImage = makeImageTranslucent(originalBackgroundImage, 0.65f);
            jFrame.setIconImage(iconImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        setTitle("Ant Simulation");
        jFrame.setLayout(new BorderLayout());

        // Create the simulation panel
        JPanel simulationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this);
                g.drawImage(image, 0, 0, null);
                setVisible(true);
                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            }
        };

        // Create the control panel with the buttons to change the mediator and the colorblind mode
        ControlPanel controlPanel = new ControlPanel();

        // Create and add the title screen
        JPanel titleScreen = new TitleScreenPanel(jFrame, simulationPanel, controlPanel, image, backgroundImage);
        jFrame.add(titleScreen, BorderLayout.CENTER);

        // jFrame settings
        jFrame.setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    /**
     * Make an image translucent
     *
     * @param image the image to make translucent
     * @param alpha the alpha value of the image (how much translucent it is)
     * @return the translucent image
     */
    private static Image makeImageTranslucent(Image image, float alpha) {
        BufferedImage translucentImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = translucentImage.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return translucentImage;
    }

    /**
     * Get the unique instance of the SimulationDisplayer
     *
     * @return the unique instance of the SimulationDisplayer
     */
    public static SimulationDisplayer getInstance() {
        return Instance.instance;
    }

    /**
     * Get the width of the simulation window
     *
     * @return the width of the simulation window
     */
    @Override
    public int getWidth() {
        return jFrame.getContentPane().getWidth();
    }

    /**
     * Get the height of the simulation window
     *
     * @return the height of the simulation window
     */
    @Override
    public int getHeight() {
        return jFrame.getContentPane().getHeight();
    }

    /**
     * Get the graphics of the simulation window
     *
     * @return the graphics of the simulation window
     */
    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) image.getGraphics();
    }

    /**
     * Repaint the simulation window
     */
    @Override
    public void repaint() {
        jFrame.repaint();
    }

    /**
     * Set the title of the displayer
     *
     * @param title the new title of the displayer
     */
    @Override
    public void setTitle(String title) {
        jFrame.setTitle(title);
    }

    /**
     * Inner class that hold the unique instance of the SimulationDisplayer
     */
    private static class Instance {
        static final SimulationDisplayer instance = new SimulationDisplayer();
    }
}
