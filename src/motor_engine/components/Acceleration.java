package motor_engine.components;
import motor_engine.*;
import motor_engine.util.vector.*;

/**
 *A component that allows entities accelerate.
 *
 * @author Garrett Smith
 * @version 0.1
 */

public class Acceleration extends Vector2dComponent {

	/**
	 * Sets the acceleration to (0 ,0).
	 */
	public Acceleration () {
		super();
	}

	/**
	 *	Sets the acceleration to the given values.
	 *
	 * @param	x	The number of game units per second.
	 * @param	y	The number of game units per second.
	 */
	public Acceleration (double x, double y) {
		super(x,y);
	}

	/**
	 *	Sets the acceleration to the given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public Acceleration (ReadableVector2d v) {
		super(v);
	}

	public Component clone() {
		return new Acceleration(this);
	}

	/** Set priority and default values.*/
	public void begin() {

	}

	/** If the entity has a velocity component change the velocity by the current acceleration. */
	public void update(long deltaTime) {
		if (getParent().has(Velocity.class)) {
			Velocity v = (Velocity)getParent().get(Velocity.class);
			v.add(Vectors.multiply(this, (deltaTime / 1000.0) ));
		}
	}

	/** Movement does not have a end method. */
	public void end() {

	}

}
