package motor_engine.components;
import motor_engine.*;
import motor_engine.util.vector.*;

/**
 * A component that allows entities to have a position within a world.
 *
 * @author Garrett Smith
 * @version 0.1
 */

public class Position extends Vector2dComponent {

	/**
	 * Sets the position to (0, 0),
	 */
	public Position() {
		super();
	}

	/**
	 * Sets the position to the given one.
	 *
	 * @param	x	The x of the position in game units.
	 * @param	y	The y of the position in game units.
	 */
	public Position(double x, double y) {
		super(x,y);
	}

	/**
	 *	Sets the position to the given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public Position (ReadableVector2d v) {
		super(v);
	}

	public Component clone() {
		return	new Position(this);
	}

	// Default Component methods
	/** Set priority and default values.*/
	public void begin() {

	}

	/** Position does not update it is just a container of values. */
	public void update(long deltaTime) {

	}

	/** Delete the vector. */
	public void end() {

	}

}
