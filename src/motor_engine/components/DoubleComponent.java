package motor_engine.components;
import motor_engine.Component;
import motor_engine.util.MotorMath;

/**
 * A component with a single double as its field.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public abstract class DoubleComponent extends Component {

	// Component methods
	public abstract void begin();
	public abstract void end();
	public abstract void update(long deltaTime);

	public String toString() {
		return "(" + x + ")";
	}

	private double x;

	public DoubleComponent() {
		set(0);
	}

	public DoubleComponent(double x) {
		set(x);
	}

	public DoubleComponent(DoubleComponent c) {
		set(c);
	}

	public abstract Component clone();

	public double get() {
		return x;
	}

	public void set(double x) {
		this.x = x;
	}

	public void set(DoubleComponent c) {
		this.x = c.get();
	}

	public void add(double x) {
		this.x += x;
	}

	public void add(DoubleComponent c) {
		this.x += c.get();
	}

	public void subtract(double x) {
		this.x -= x;
	}

	public void subtract(DoubleComponent c) {
		this.x -= c.get();
	}

	public void multiply(double x) {
		this.x *= x;
	}

	public void multiply(DoubleComponent c) {
		this.x *= c.get();
	}

	public void divide(double x) {
		this.x /= x;
	}

	public void divide(DoubleComponent c) {
		this.x /= c.get();
	}

	public boolean equals(double x) {
		return this.x == x;
	}

	public boolean equals(DoubleComponent c) {
		return this.x == c.get();
	}

	public void copySign(double sign) {
		x = Math.copySign(x, sign);
	}

	public void copySign(DoubleComponent c) {
		x = Math.copySign(x, c.get());
	}

	public void constrain(double min, double max) {
		x = MotorMath.constrain(x,min,max);
	}

	public void negate() {
		multiply(-1);
	}

	public void abs() {
		x = Math.abs(x);
	}
}
