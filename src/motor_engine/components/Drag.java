package motor_engine.components;
import motor_engine.*;
import motor_engine.util.vector.*;

/**
 *A component that allows entities to have drag.
 *
 * @author Garrett Smith
 * @version 0.1
 */

public class Drag extends Vector2dComponent {

	private Vector2d delta;

	/**
	 * Sets the Drag to (0 ,0).
	 */
	public Drag () {
		super();
	}

	/**
	 *	Sets the Drag to the given values.
	 *
	 * @param	x	The number of game units per second.
	 * @param	y	The number of game units per second.
	 */
	public Drag (double x, double y) {
		super(x,y);
	}

	/**
	 *	Sets the Drag to the given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public Drag (ReadableVector2d v) {
		super(v);
	}

	public Component clone() {
		return new Drag(this);
	}

	public void begin() {

	}

	public void end() {

	}

	/** If the entity has a velocity component change the velocity by the current Drag. */
	public void update(long deltaTime) {
		if (getParent().has(Velocity.class)) {
			Velocity v = (Velocity)getParent().get(Velocity.class);
			Vector2d tmp = new Vector2d(v);
			tmp.abs();
			tmp.subtract(Vectors.abs(Vectors.multiply(this,(deltaTime / 1000.0))));
			tmp.constrain(0, Double.MAX_VALUE);
			tmp.copySign(v);
			v.set(tmp);
		}
	}

}
