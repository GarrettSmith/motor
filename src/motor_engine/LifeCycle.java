package motor_engine;

/**
 * The interface that gives a class the standard lifecyle commands.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public interface LifeCycle {
	/**
	 * Perform the actions needed to begin use.
	 */
	public void begin();

	/**
	 * Perform actions to end life.
	 */
	public void end();

	/**
	 * Update the object.
	 *
	 * @param	deltaTime	The time in milliseconds since the last update.
	 */
	public void update(long deltaTime);
}
