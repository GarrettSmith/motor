package motor_engine;

/**
 * A class implementing this is owned by a state.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public interface StateOwned {
	/**
	 * Get the owning state of this object.
	 *
	 * @return	A reference to the state owning this object.
	 */
	public State getOwner();

	/**
	 * Set the owning state of this object.
	 *
	 * @param	s	The owning state.
	 */
	public void setOwner(State s);

	/**
	 * Check if the object has an owning state.
	 *
	 * @return	True if the object has an owner, false otherwise.
	 */
	public boolean isOwned();
}
