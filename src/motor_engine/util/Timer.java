package motor_engine.util;
import motor_engine.MotorEngine;

/**
 * Timer utility class used to fire events. Can be repeating or a single shot.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Timer {

	/** Whether the timer should run more than once before stopping or run a single time.*/
	private boolean repeat;
	/** Whether the timer is currently running.*/
	private boolean running;
	/** The amount of time the timer should run for between firing events.*/
	private double duration;
	// The time this timer was last started
	private double startTime;
	/** The length if time the timer has been running.*/
	private double elapsed;
	// how many times the timer fired since the last update
	private int fires;

	/**
	 *
	 *
	 * @param	repeat		Whether the timer should  repeat or stop after it fires.
	 * @param	duration	The time between firing events in seconds.
	 */
	public Timer (boolean repeat, double duration) {
		this.repeat = repeat;
		this.duration = duration * 1000; // Convert to milliseconds
	}

	/**
	 * Starts the timer running.
	 */
	public void start() {
		running = true;
		reset();
	}

	/**
	 * Stops the timer running.
	 */
	public void stop() {
		running = false;
	}

	/**
	 * Resets the elapsed time of the timer.
	 */
	public void reset() {
		elapsed = 0;
		startTime = MotorEngine.getElapsedTime();
	}

	/**
	 * Updates the elapsed time and fires an event if the duration has been met.
	 */
	public void update () {
		fires = 0;
		if (running) {
			elapsed = MotorEngine.getElapsedTime() - startTime;

			while (elapsed >= duration && running) {
				fireEvent();
				elapsed -= duration;
				startTime += duration;

				if (!repeat) {
					stop();
				}
			}
		}
	}

	private void fireEvent () {
		fires++;
	}

	/**
	 * Check if the timer has been fired.
	 *
	 *	@return		If true this timer has fired since its last update, false otherwise.
	 */
	public boolean fired() {
		return fires > 0;
	}

	/**
	 * Check how wany times the event fired.
	 *
	 * @return		The number of times the Timer fired.
	 */
	public int timesFired() {
		return fires;
	}

	public String toString() {
		return elapsed + " / " + duration;
	}

}
