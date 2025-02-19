/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The RomanClock class represents a clock dial with Roman numeral markers.
 * It extends ClockDial and provides specific configurations for a Roman-style clock.
 */
package ch.heig.mcr.labo.GUI.watch;

import ch.heig.mcr.labo.GUI.constant.ClockConstants;
import ch.heig.mcr.labo.clock.Chrono;

import java.awt.*;

public class RomanClock extends ClockDial {
    /**
     * Constructs a RomanClock instance with the provided Chrono object.
     *
     * @param chrono The Chrono object to observe.
     */
    public RomanClock(Chrono chrono) {
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
        return Color.GRAY;
    }

    @Override
    protected Color getSecondColor() {
        return Color.ORANGE;
    }

    @Override
    protected String getPathToImage() {
        return "./assets/cadran_chiffres_romains.jpg";
    }


}
