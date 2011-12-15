package motor_engine.components;
import motor_engine.*;

/**
 * A Component that allows the Entity to have transparency.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Transparency extends DoubleComponent {

	// The alpha value of the Entity (between 0 and 1)
	private double alpha;

	/**
	 * Creates a new opaque transparency Component.
	 */
	public Transparency() {
		super(0);
	}

	/**
	 * Create a new transparency Component at the specified alpha value.
	 *
	 * @param	alpha	The alpha value to set at.
	 */
	public Transparency(double alpha) {
		super(alpha);
	}

	/**
	 * Create a new transparency Component at the specified alpha value.
	 *
	 * @param	t	The transparency component to copy.
	 */
	public Transparency(Transparency t) {
		super(t);
	}


	public Component clone() {
		return new Transparency(this);
	}

	public void update(long deltaTime) {

	}

	public void begin() {

	}

	public void end() {

	}
}
