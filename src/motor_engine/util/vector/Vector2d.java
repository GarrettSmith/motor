package motor_engine.util.vector;
import motor_engine.util.MotorMath;

/**
 * A vector containing two doubles.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Vector2d extends Vector implements ReadableVector2d, WriteableVector2d {

	private double x;
	private double y;

	/**
	 * Create a new vector (0,0)
	 */
	public Vector2d() {
		set(0,0);
	}

	/**
	 * Create a new vector with the given values.
	 *
	 * @param	x	The x value.
	 * @param	y	The y value.
	 */
	public Vector2d(double x, double y) {
		set(x,y);
	}

	/**
	 * Create a copy of the given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public Vector2d(ReadableVector2d v) {
		set(v);
	}

	// getters
	/**
	 * Get the x value of this vector.
	 *
	 * @return	The x value.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y value of this vector.
	 *
	 * @return	The y value.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get a double array containing this vector's values.
	 *
	 * @return	The double array.
	 */
	public double[] toDoubleArray() {
		return new double[] {x,y};
	}

	public String toString() {
		return ("(" + x + ", " + y + ")");
	}

	/**
	 * Check this vector against another for equality.
	 *
	 * @param	v	The vector to compare to.
	 *
	 * @return		True if they have the same values, false otherwise.
	 */
	public boolean equals(ReadableVector2d v) {
		return x == v.getX() && y == v.getY();
	}

	/**
	 * Calculate the dot product of this vector and a given vector.
	 *
	 * @param	v	The other vector.
	 *
	 * @return		The dot product.
	 */
	public double dot(ReadableVector2d v) {
		return x*v.getX() + y*v.getY();
	}

	public double magnitude() {
		return x*x + y*y;
	}

	/**
	 * calculate the angle between this and a given vector.
	 *
	 * @param	x	The x value of the vector to compare to.
	 * @param	y	The y value of the vector to compare to.
	 *
	 * @return		The angle between the two vectors.
	 */
	public double angle(double x, double y) {
		Vector2d v = new Vector2d(x,y);
		return angle(v);
	}

	/**
	 * calculate the angle between this and a given vector.
	 *
	 * @param	v	The other vector.
	 *
	 * @return		The angle between the two vectors.
	 */
	public double angle(ReadableVector2d v) {
		double tmp = dot(v) / (this.length() * v.length());
		if (tmp < -1f)
			tmp = -1f;
		else if (tmp > 1.0f)
			tmp = 1.0f;
		return Math.acos(tmp);
	}

	//setters

	/**
	 * Set the values of this vector to the given values.
	 *
	 * @param	x	The x value.
	 * @param	y	The y value.
	 */
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Set the values of this vector to that of a given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public void set(ReadableVector2d v) {
		x = v.getX();
		y = v.getY();
	}

	/**
	 * Set the x value of this vector.
	 *
	 * @param	x	The new x value.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the y value of this vector.
	 *
	 * @param	y	The new y value.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Add the given values to this vector.
	 *
	 * @param	x	The value to add to the x value.
	 * @param	y	The value to add to the y value.
	 */
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Add the given vector to this vector.
	 *
	 * @param	v	The vector to add.
	 */
	public void add(ReadableVector2d v) {
		x += v.getX();
		y += v.getY();
	}

	/**
	 * Subtract the given values from this vector.
	 *
	 * @param	x	The value to subtractfrom the x value.
	 * @param	y	The value to subtract from the y value.
	 */
	public void subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
	}

	/**
	 * Subtract the given vector from this vector.
	 *
	 * @param	v	The vector to subtract.
	 */
	public void subtract(ReadableVector2d v) {
		x -= v.getX();
		y -= v.getY();
	}

	/**
	 * Copy the signs of the given values.
	 *
	 * @param	x	The sign for the x value.
	 * @param	y	The sign for the y value.
	 */
	public void copySign(double x, double y) {
		this.x = Math.copySign(this.x, x);
		this.y = Math.copySign(this.y, y);
	}

	/**
	 * Copy the signs of the given vector.
	 *
	 * @param	v	The vector to get the signs from.
	 */
	public void copySign(ReadableVector2d v) {
		this.x = Math.copySign(this.x, v.getX());
		this.y = Math.copySign(this.y, v.getY());
	}

	public void multiply(double r) {
		x *= r;
		y *= r;
	}

	public void divide(double r) {
		x /= r;
		y /= r;
	}

	public void normalize() {
		this.divide(length());
	}

	public void constrain(double min, double max) {
		x = MotorMath.constrain(x, min, max);
		y = MotorMath.constrain(y, min, max);
	}

	public void abs() {
		x = Math.abs(x);
		y = Math.abs(y);
	}

}
