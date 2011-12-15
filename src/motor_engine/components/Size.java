package motor_engine.components;
import motor_engine.*;

/**
 * A component that allows entities to occupy an area in a world.
 *
 * @author Garrett Smith
 * @version 0.1
 */

public class Size extends Vector2dComponent {

	/**
	 * Sets the size to 0x0.
	 */
	public Size() {
		super();
	}

	/**
	 * Sets the size to the given values.
	 *
	 * @param	w	The width in game units of the Entity.
	 * @param	h	The heigh in game units of the Entity.
	 */
	public Size(double w, double h) {
		super(w,h);
	}

	/**
	 * Sets the size to the given values.
	 *
	 * @param	v	The vector containing the dimensions.
	 */
	public Size(Vector2dComponent v) {
		super(v);
	}

	public Component clone() {
		return	new Size(this);
	}

	/**
	 * Get the current half height of the Entity.
	 *
	 * @return	The current half height of the Entity in game units.
	 */
	public double getHalfHeight() {
		return this.getY()/2.0;
	}

	/**
	 * Get the current half width of the Entity.
	 *
	 * @return	The current half width of the Entity in game units.
	 */
	public double getHalfWidth() {
		return this.getX()/2.0;
	}

	public void begin() {

	}

	public void update(long deltaTime) {
		;
	}

	public void end() {
		;
	}

}
