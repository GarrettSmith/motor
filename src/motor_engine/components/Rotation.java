package motor_engine.components;
import motor_engine.*;

/**
 * A component that allows entities to be rotated.
 *
 * @author Garrett Smith
 * @version 0.1
 */
public class Rotation extends DoubleComponent {

	/**
	 *
	 */
	public Rotation() {
		super(0);
	}

	/**
	 *
	 */
	public Rotation(double r) {
		super(r);
	}

	/**
	 *
	 */
	public Rotation(Rotation r) {
		super(r);
	}


	public Component clone() {
		return	new Rotation(this);
	}

	/**
	 * Remove complete rotations of the Entity so that a value is between 0 and 360.
	 */
	public void removeCompleteRotations() {
		set(get() % 360);
	}

	public void update (long deltaTime) {

	}

	public void begin() {

	}

	public void end() {

	}

}
