/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The ClockGUI class represents a graphical user interface component that displays a timer.
 * It extends JPanel and implements ClockObserver to observe changes in the associated Chrono object.
 */
package ch.heig.mcr.labo.GUI.watch;

import ch.heig.mcr.labo.clock.Chrono;
import ch.heig.mcr.labo.clock.ClockObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClockGUI extends JPanel implements ClockObserver {

    protected Chrono chrono;
    protected final static int WIDTH = 200;
    protected final static int HEIGHT = 200;
    /**
     * Constructs a ClockGUI with the specified Chrono object.
     * Attaches the ClockGUI as an observer to the Chrono.
     *
     * @param chrono The Chrono object to associate with this GUI component.
     */
    public ClockGUI(Chrono chrono) {
        this.chrono = chrono;
        chrono.attach(this);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        super.addMouseListener(new MouseEventListener());
    }
    /**
     * Detaches the ClockGUI from the associated Chrono.
     */
    public void erase() {
        chrono.detach(this);
    }
    /**
     * Gets the hours part of the elapsed time from the associated Chrono.
     *
     * @return The hours part of the elapsed time.
     */
    public long getHours() {
        return chrono.getDuration().toHours();
    }
    /**
     * Gets the minutes part of the elapsed time from the associated Chrono.
     *
     * @return The minutes part of the elapsed time.
     */
    public long getMinutes() {
        return chrono.getDuration().toMinutes();
    }
    /**
     * Gets the seconds part of the elapsed time from the associated Chrono.
     *
     * @return The seconds part of the elapsed time.
     */
    public long getSeconds() {
        return chrono.getDuration().toSecondsPart();
    }
    /**
     * Update method called when the state of the associated Chrono changes.
     * Repaints the GUI component to reflect the updated time.
     */
    @Override
    public void update() {
        repaint();
    }
    /**
     * MouseEventListener class to handle mouse events on the ClockGUI component.
     * Toggles the state of the associated Chrono (start/stop) when clicked.
     * Changes cursor to hand cursor when mouse enters the component area.
     */
    private class MouseEventListener implements MouseListener {
        /**
         * Toggles the state of the associated Chrono (start/stop) when clicked.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (chrono.isRunning()) {
                chrono.stop();
            } else {
                chrono.start();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // No action needed
            return;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // No action needed
            return;
        }
        /**
         * Changes cursor to hand cursor when mouse enters the component area.
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // No action needed
            return;
        }
    }
}
