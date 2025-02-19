/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The ArabicClock class represents a clock dial with Arabic numeral markers.
 * It extends ClockDial and provides specific configurations for an Arabic-style clock.
 */
package ch.heig.mcr.labo.GUI.watch;

import ch.heig.mcr.labo.GUI.constant.ClockConstants;
import ch.heig.mcr.labo.clock.Chrono;

import java.awt.*;

public class ArabicClock extends ClockDial {
    /**
     * Constructs an ArabicClock instance with the provided Chrono object.
     *
     * @param chrono The Chrono object to observe.
     */
    public ArabicClock(Chrono chrono) {
        super(
                chrono
        );
    }


    @Override
    protected Color getHourColor() {
        return Color.BLACK;
    }

    @Override
    protected Color getMinuteColor() {
        return Color.BLUE;
    }

    @Override
    protected Color getSecondColor() {
        return Color.RED;
    }

    @Override
    protected String getPathToImage() {
        return "./assets/cadran_chiffres_arabes.jpg";
    }
}

