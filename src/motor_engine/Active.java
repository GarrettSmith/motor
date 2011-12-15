package motor_engine;

/**
 * A class implementing this can be enabled and disabled.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public interface Active {
	/**
	 * Check if the object is active.
	 *
	 * @return	True if the object is active, false otherwise.
	 */
	public boolean isActive();

	/**
	 * Set the current active status of the object.
	 *
	 * @param	b	The active status.
	 */
	public void setActive(boolean b);
}
