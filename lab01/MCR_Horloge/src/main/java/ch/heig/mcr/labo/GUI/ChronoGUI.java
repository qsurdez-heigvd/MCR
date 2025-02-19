/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The ChronoGUI class represents a graphical user interface for controlling a Chrono object.
 * It provides buttons for starting, stopping, and reinitializing the Chrono, as well as options
 * to display the Chrono in various clock styles.
 */
package ch.heig.mcr.labo.GUI;

import ch.heig.mcr.labo.GUI.constant.ClockConstants;
import ch.heig.mcr.labo.GUI.watch.ArabicClock;
import ch.heig.mcr.labo.GUI.watch.ClockGUI;
import ch.heig.mcr.labo.GUI.watch.NumericClock;
import ch.heig.mcr.labo.GUI.watch.RomanClock;
import ch.heig.mcr.labo.clock.Chrono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

import static java.awt.FlowLayout.LEFT;

public class ChronoGUI extends JPanel {

    private final Chrono chrono;

    private final String name;
    /**
     * Constructs a ChronoGUI instance for the given Chrono object.
     *
     * @param chrono The Chrono object to display/control.
     */
    public ChronoGUI(Chrono chrono) {
        super(new FlowLayout(LEFT));
        this.chrono = chrono;
        this.name = chrono.toString();

        JLabel label = new JLabel(name);
        //create buttons
        var start = createButton("Démarrer", e -> chrono.start());
        var stop = createButton("Arrêter", e -> chrono.stop());
        var reinitialize = createButton("Réinitialiser", e -> chrono.reinitialize());
        var roman = createButton(ClockConstants.ROMAN, e -> showPanel(new Visualizer(new RomanClock(chrono))));
        var arabic = createButton(ClockConstants.ARABIC, e -> showPanel(new Visualizer(new ArabicClock(chrono))));
        var numeric = createButton(ClockConstants.NUMERIC, e -> showPanel(new Visualizer(new NumericClock(chrono))));
        //add components to the panel
        add(label);
        add(start);
        add(stop);
        add(reinitialize);
        add(roman);
        add(arabic);
        add(numeric);
    }

    /**
     * Helper method to create a JButton with the given name and ActionListener.
     *
     * @param name   The name (label) of the button.
     * @param action The ActionListener for the button.
     * @return The created JButton.
     */
    private JButton createButton(String name, ActionListener action) {
        JButton b = new JButton(name);
        b.addActionListener(action);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }
    /**
     * Retrieves the name of the Chrono associated with this GUI.
     *
     * @return The name of the Chrono.
     */
    public String getName() {
        return name;
    }

    /**
     * Displays the given Visualizer in a dialog.
     *
     * @param visualizer The Visualizer to display.
     */
    private void showPanel(Visualizer visualizer) {
        visualizer.pack();
        visualizer.setVisible(true);
    }
}
