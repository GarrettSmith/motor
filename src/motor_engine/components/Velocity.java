package motor_engine.components;
import motor_engine.*;
import motor_engine.util.vector.*;

/**
 *A component that allows entities to move.
 *
 * @author Garrett Smith
 * @version 0.1
 */

public class Velocity extends Vector2dComponent {

	/**
	 * Sets the velocity to (0 ,0).
	 */
	public Velocity () {
		super();
		setPriority(0);
	}

	/**
	 *	Sets the velocity to the given values.
	 *
	 * @param	x	The number of game units per second the Entity is moving horizontally.
	 * @param	y	The number of game units per second the Entity is moving vertically.
	 */
	public Velocity (double x, double y) {
		super(x,y);
	}

	/**
	 *	Sets the velocity to the given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public Velocity (ReadableVector2d v) {
		super(v);
	}

	public Component clone() {
		return	new Velocity(this);
	}

	/** Set priority and default values.*/
	public void begin() {

	}

	/** If the entity has a position component change the position by the current velocity. */
	public void update(long deltaTime) {
		getParent().addVector2d(Position.class, Vectors.multiply(this, (deltaTime / 1000.0) ));
	}

	/** Movement does not have a end method. */
	public void end() {

	}

}
