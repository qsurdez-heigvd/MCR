/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The ClockObserver interface defines the contract for objects that observe changes in a clock or timer.
 * Implementing classes must provide functionality to update their state when notified of changes.
 */
package ch.heig.mcr.labo.clock;

public interface ClockObserver {
    /**
     * Updates the state of the observer based on changes in the observed clock or timer.
     */
    public void update();

}
