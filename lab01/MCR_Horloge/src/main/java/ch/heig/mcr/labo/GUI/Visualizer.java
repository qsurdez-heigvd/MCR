/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The Visualizer class represents a JFrame for visualizing clock GUIs.
 * It provides constructors to display a single ClockGUI or multiple ClockGUIs.
 */
package ch.heig.mcr.labo.GUI;

import ch.heig.mcr.labo.GUI.watch.ClockGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Visualizer extends JFrame {
    private JPanel content;
    private ArrayList<ClockGUI> clockGUIS = new ArrayList<>();

    /**
     * Private helper method to factor constructors, creates a panel and adds the clock(s)
     */
    private void createPanels(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        content = new JPanel();
        setContentPane(content);

        for (ClockGUI clockGUI : clockGUIS) {
            add(clockGUI);
        }
    }
    /**
     * Constructs a Visualizer instance to display a single ClockGUI.
     *
     * @param clockGUI The ClockGUI instance to display.
     */
    public Visualizer(ClockGUI clockGUI) {
        this.clockGUIS.add(clockGUI);
        createPanels();
    }
    /**
     * Constructs a Visualizer instance to display multiple ClockGUIs.
     *
     * @param clockGUIS The list of ClockGUI instances to display.
     */
    public Visualizer(ArrayList<ClockGUI> clockGUIS) {
        this.clockGUIS = clockGUIS;
        createPanels();
    }
    /**
     * Overrides the dispose method to properly clean up clock GUI resources upon closing the frame.
     */
    @Override
    public void dispose() {
        if (clockGUIS != null) {
            for (ClockGUI clockGUI : clockGUIS) {
                clockGUI.erase();
            }
        }
        super.dispose();
    }
}
