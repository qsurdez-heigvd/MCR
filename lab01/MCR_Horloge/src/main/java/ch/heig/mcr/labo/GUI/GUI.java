/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The GUI class represents the main graphical user interface for managing multiple chronometers.
 * It extends JFrame and provides a control panel for managing chronometers and displaying various clock types.
 */
package ch.heig.mcr.labo.GUI;

import ch.heig.mcr.labo.clock.Chrono;
import ch.heig.mcr.labo.GUI.constant.ClockConstants;

import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JFrame {

    private final int numberOfChrono;
    private final ArrayList<Chrono> chronos = new ArrayList<>();
    /**
     * Constructs a GUI instance with the specified number of chronometers.
     *
     * @param numberOfChrono The number of chronometers to display.
     */
    public GUI(int numberOfChrono) {
        super("Panneau de contrôle");
        this.numberOfChrono = numberOfChrono;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        setContentPane(container);
        setResizable(false);
    }

    /**
     * Function to run the graphical interface and create all the chronos
     */
    public void run() {
        // Create and add ChronoGUI instances for each chronometer
        for (int i = 0; i < numberOfChrono; i++) {
            chronos.add(new Chrono());
            add(new ChronoGUI(chronos.get(i)));
        }

        add(new AllChronoGUI(chronos));

        pack();  // Size the frame to fit its contents
        setVisible(true);
    }

}
