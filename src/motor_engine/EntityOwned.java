package motor_engine;

/**
 * An object implementing this interface can be owned by an Entity.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public interface EntityOwned {

	/**
	 * Get the parent entity of this object.
	 *
	 * @return	The parent of this object.
	 */
	public Entity getParent();

	/**
	 * Set the parent entity of this object.
	 *
	 * @param	e	The parent entity.
	 */
	public void setParent(Entity e);

	/**
	 * Check if this object has a parent.
	 *
	 * @return	True if this objects parent is not null.
	 */
	public boolean hasParent();

}
