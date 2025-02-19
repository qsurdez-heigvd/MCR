/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The ClockDial class represents a clock dial GUI component that displays the clock face and hands.
 * It extends the ClockGUI class and provides functionality to draw clock hands based on the elapsed time.
 */
package ch.heig.mcr.labo.GUI.watch;

import ch.heig.mcr.labo.clock.Chrono;

import javax.swing.*;
import java.awt.*;

public abstract class ClockDial extends ClockGUI {

    private static final double HOUR_LENGTH = 0.4;
    private static final double MINUTE_LENGTH = 0.6;
    private static final double SECOND_LENGTH = 0.8;
    private final Image image;

    /**
     * Constructs a ClockDial object with the specified parameters.
     *
     * @param chrono       The Chrono instance associated with this clock dial.
     */
    public ClockDial(
            Chrono chrono
    ) {
        super(chrono);
        this.image = getImageFromFile(getPathToImage()).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    }

    /**
     * Retrieves an Image object from the specified file.
     *
     * @param filename The file path of the image.
     * @return The Image object.
     */
    public Image getImageFromFile(String filename) {
        return Toolkit.getDefaultToolkit().getImage(filename);
    }
    /**
     * Overrides the paint method to draw the clock face and hands as well as the overlay text
     *
     * @param g The Graphics object to paint on.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // paint the clock and hands
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        drawHands(g, getHourColor(), Math.PI * (getHours() + (getMinutes() / 60.0) + (getSeconds() / 3600.0) / 6.0), HOUR_LENGTH);
        drawHands(g, getMinuteColor(), Math.PI * (getMinutes() + (getSeconds() / 60.0) / 30.0), MINUTE_LENGTH);
        drawHands(g, getSecondColor(), Math.PI * getSeconds() / 30.0, SECOND_LENGTH);

        //paint the text overlay
        g.setColor(Color.GRAY);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(chrono.toString());
        int textHeight = fontMetrics.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int textX = centerX - textWidth / 2;
        int textY = centerY + 10 + textHeight / 2;
        g.drawString(chrono.toString(), textX, textY);
    }
    /**
     * Draws a clock hand on the dial.
     *
     * @param g      The Graphics object to draw on.
     * @param color  The color of the hand.
     * @param angle  The angle of the hand in radians.
     * @param length The length of the hand relative to the radius of the dial.
     */
    private void drawHands(Graphics g, Color color, double angle, double length) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int endX = (int) (centerX * (1 + Math.sin(angle) * length));
        int endY = (int) (centerY * (1 - Math.cos(angle) * length));
        Graphics2D graph = (Graphics2D) g;
        graph.setStroke(new BasicStroke(3));
        g.setColor(color);
        g.drawLine(centerX, centerY, endX, endY);
    }

    protected abstract Color getHourColor();
    protected abstract Color getMinuteColor();
    protected abstract Color getSecondColor();
    protected abstract String getPathToImage();

}
