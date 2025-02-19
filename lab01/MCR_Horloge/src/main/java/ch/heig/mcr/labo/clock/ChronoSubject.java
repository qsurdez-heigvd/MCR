/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * The ChronoSubject interface defines the contract for objects that can be observed by ClockObservers.
 * Implementing classes must provide functionality to attach, detach, and signal observers.
 */
package ch.heig.mcr.labo.clock;

import java.util.LinkedList;

public interface ChronoSubject {
    final LinkedList<ClockObserver> clockObservers = new LinkedList<>();

    /**
     * Attaches a ClockObserver to this ChronoSubject.
     *
     * @param o The ClockObserver to attach.
     */
    public void attach(ClockObserver o);

    /**
     * Detaches a ClockObserver from this ChronoSubject.
     *
     * @param o The ClockObserver to detach.
     */
    public void detach(ClockObserver o);

    /**
     * Signals all attached observers that the state of the ChronoSubject has changed.
     */
    public void signal();
}
