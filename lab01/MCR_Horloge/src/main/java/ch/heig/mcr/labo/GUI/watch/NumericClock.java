/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The NumericClock class represents a digital clock display in a graphical user interface.
 * It extends ClockGUI and updates its display whenever notified of changes in the observed Chrono object.
 */
package ch.heig.mcr.labo.GUI.watch;

import ch.heig.mcr.labo.clock.Chrono;

import javax.swing.*;
import java.awt.*;

public class NumericClock extends ClockGUI {

    private JLabel label;
    /**
     * Constructs a NumericClock instance with the provided Chrono object.
     *
     * @param chrono The Chrono object to observe.
     */
    public NumericClock(Chrono chrono) {
        super(chrono);

        setLayout(new BorderLayout());

        label = new JLabel(
                getFormattedChrono(),
                SwingConstants.CENTER
        );

        add(label, BorderLayout.CENTER);
    }
    /**
     * Updates the clock display with the current time from the Chrono object.
     */
    @Override
    public void update() {
        label.setText(getFormattedChrono());
    }
    /**
     * Formats the chrono time as a string for display in the clock.
     *
     * @return A formatted string representing the chrono time.
     */
    private String getFormattedChrono() {
        return "%s: %2dh %2dm %2ds".formatted(
                chrono.toString(),
                getHours(),
                getMinutes(),
                getSeconds()
        );
    }
}
