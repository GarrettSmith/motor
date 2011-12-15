package motor_engine.components;
import motor_engine.*;

/**
 * A Component that allows the Entity to be tinted.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Tint extends Component {

	// the 3 different colour tints
	private double red;
	private double green;
	private double blue;

	/**
	 * Create a new tint Component without a tint.
	 */
	public Tint() {
		set(1,1,1);
		setPriority(0);
	}

	/**
	 * Create a new tint Component with the given tint.
	 *
	 * @param	r	The red tint value.
	 * @param	g	The green tint value.
	 * @param	b	The blue tint value.
	 */
	public Tint(double r, double g, double b) {
		this();
		set(r,g,b);
	}

	/**
	 * Create a new tint Component with the given component.
	 *
	 * @param	t	The component to copy.
	 */
	public Tint(Tint t) {
		this(t.getR(), t.getG(), t.getB());
	}

	public Component clone() {
		return	new Tint(this);
	}

	public void update(long deltaTime) {

	}

	public void begin() {

	}

	public void end() {

	}

	public String toString() {
		return "( " + red + ", " + green + ", " + blue + " )" ;
	}

	//Getters
	/**
	 * Get the current red tint.
	 *
	 * @return	The red tint.
	 */
	public double getR() {
		return red;
	}

	/**
	 * Get the current green tint.
	 *
	 * @return	The green tint.
	 */
	public double getG() {
		return green;
	}

	/**
	 * Get the current blue tint.
	 *
	 * @return	The blue tint.
	 */
	public double getB() {
		return blue;
	}

	//Setters
	/**
	 * Set the current tint.
	 *
	 * @param	r	The red tint.
	 * @param	g	The green tint.
	 * @param	b	The blue tint.
	 */
	public void set(double r, double g, double b) {
		setR(r);
		setG(g);
		setB(b);
	}

	/**
	 * Set the current red tint.
	 *
	 * @param	r	The red tint.
	 */
	public void setR(double r) {
		red = r;
	}

	/**
	 * Set the current green tint.
	 *
	 * @param	g	The green tint.
	 */
	public void setG(double g) {
		green = g;
	}

	/**
	 * Set the current blue tint.
	 *
	 * @param	b	The blue tint.
	 */
	public void setB(double b) {
		blue = b;
	}
}
