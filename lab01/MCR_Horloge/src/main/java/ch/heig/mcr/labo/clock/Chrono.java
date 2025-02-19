/**
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 * @since 2024-02-22
 *
 * Chrono class representing a simple stopwatch or timer functionality. It implements the ChronoSubject Class.
 * It provides methods to start, stop, reset, and get the duration of the timer.
 */
package ch.heig.mcr.labo.clock;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import static java.time.Duration.ofMillis;

public class Chrono implements ChronoSubject {
    private static final int TIMER_INTERVAL = 1000;
    private final Timer timer;
    private static int countId = 0;
    private final int id;
    private long elapsedTime;
    private TaskPerformer taskPerformer;
    /**
     * Constructs a Chrono object with its id.
     * Initializes the timer and sets the initial time to 0.
     */
    public Chrono() {
        id = ++countId;
        timer = new Timer("Chrono#" + id);
        elapsedTime = 0;
    }
    /**
     * Starts the timer if it's not already running.
     * Prints a message indicating the timer has started.
     */
    public void start() {
        if (isRunning()) {
            return;
        }

        System.out.println(this + " has been started");
        taskPerformer = new TaskPerformer();
        timer.scheduleAtFixedRate(
                taskPerformer,
                TIMER_INTERVAL,
                TIMER_INTERVAL
        );
    }
    /**
     * Stops the timer if it's running.
     * Prints a message indicating the timer is being stopped.
     */
    public void stop(){
        // Check if is not runngin ?
        if (!isRunning()) {
            return;
        }
        System.out.println(this + " is being stopped");
        taskPerformer.cancel();
        taskPerformer = null;
    }
    /**
     * Resets the timer by setting the elapsed time to 0.
     * Prints a message indicating the timer has been reset.
     * Notifies observers of the reset.
     */
    public void reinitialize() {
        elapsedTime = 0;
        System.out.println(this + " has been reset");
        signal();
    }
    /**
     * Attaches a ClockObserver to this Chrono instance.
     *
     * @param o The ClockObserver to attach.
     */
    @Override
    public void attach(ClockObserver o) {
        clockObservers.add(o);
    }
    /**
     * Detaches a ClockObserver from this Chrono instance.
     *
     * @param o The ClockObserver to detach.
     */
    @Override
    public void detach(ClockObserver o) {
        clockObservers.remove(o);
    }
    /**
     * Signals all attached observers that the state of the Chrono has changed.
     */
    @Override
    public void signal() {
        for (ClockObserver co : clockObservers) {
            co.update();
        }
    }
    /**
     * Returns the duration of the timer.
     *
     * @return The duration of the timer.
     */
    public Duration getDuration() {
        return ofMillis(elapsedTime);
    }
    /**
     * Checks if the timer is running.
     *
     * @return True if the timer is running, false otherwise.
     */
    public boolean isRunning() {
        return taskPerformer != null;
    }
    /**
     * Returns a string representation of the Chrono object.
     *
     * @return A string representation of the Chrono object.
     */
    @Override
    public String toString() {
        return "Chrono #" + id;
    }
    /**
     * TaskPerformer is a TimerTask implementation that updates the elapsed time.
     */
    private class TaskPerformer extends TimerTask {
        /**
         * Updates the elapsed time by adding the timer interval.
         * Notifies observers of the updated time.
         */
        @Override
        public void run() {
            elapsedTime+=1000;
            signal();
        }
    }

}
