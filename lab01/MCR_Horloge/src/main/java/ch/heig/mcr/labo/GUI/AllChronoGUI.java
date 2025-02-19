/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The AllChronoGUI class represents a graphical user interface for displaying multiple clocks.
 * It allows the user to choose between different types of clocks (Roman, Arabic, Numeric) to display all chronos.
 */
package ch.heig.mcr.labo.GUI;

import ch.heig.mcr.labo.GUI.constant.ClockConstants;
import ch.heig.mcr.labo.GUI.watch.*;
import ch.heig.mcr.labo.clock.Chrono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

import static java.awt.FlowLayout.RIGHT;

public class AllChronoGUI extends JPanel {

    private final ArrayList<Chrono> chronos;
    /**
     * Constructs an AllChronoGUI instance with the provided list of chronos.
     *
     * @param chronos The list of chronos to display.
     */
    public AllChronoGUI(ArrayList<Chrono> chronos) {
            super(new FlowLayout(RIGHT));
            this.chronos = chronos;

            JLabel label = new JLabel("Tous les chronos");

            var roman = createButton(ClockConstants.ROMAN, RomanClock::new);
            var arabic = createButton(ClockConstants.ARABIC, ArabicClock::new);
            var numeric = createButton(ClockConstants.NUMERIC, NumericClock::new);

            add(label);
            add(roman);
            add(arabic);
            add(numeric);
        }
    /**
     * Displays the specified visualizer panel.
     *
     * @param visualizer The visualizer panel to display.
     */
    private void showPanel(Visualizer visualizer) {
        visualizer.pack();
        visualizer.setVisible(true);
    }

    /**
     * Private function to create a button with the correct actionPerformer
     * @param name name of the button
     * @param clockGUIConstructor Function that will tell which kind of Clock we want to display
     * @return the newly created JButton
     */
    private JButton createButton(String name, Function<Chrono, ClockGUI> clockGUIConstructor) {
            JButton b = new JButton(name);
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                ArrayList<ClockGUI> clocks = new ArrayList<>();
                for (Chrono chrono : chronos) {
                    clocks.add(clockGUIConstructor.apply(chrono));
                }
                showPanel(new Visualizer(clocks));
            }
            });
            return b;
    }


}
