package motor_engine.util.vector;
import motor_engine.util.MotorMath;

/**
 * A vector containing three doubles.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Vector3d extends Vector implements ReadableVector3d, WriteableVector3d {

	private double x;
	private double y;
	private double z;

	/**
	 * Create a new vector (0,0,0)
	 */
	public Vector3d() {
		set(0,0,0);
	}

	/**
	 * Create a new vector with the given values.
	 *
	 * @param	x	The x value.
	 * @param	y	The y value.
	 * @param	z	The z value.
	 */
	public Vector3d(double x, double y, double z) {
		set(x,y,z);
	}

	/**
	 * Create a copy of the given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public Vector3d(ReadableVector3d v) {
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
	 * Get the z value of this vector.
	 *
	 * @return	The z value.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Get a double array containing this vector's values.
	 *
	 * @return	The double array.
	 */
	public double[] toDoubleArray() {
		return new double[] {x,y,z};
	}

	public String toString() {
		return ("(" + x + ", " + y + ", " + z + ")");
	}

	/**
	 * Check this vector against another for equality.
	 *
	 * @param	v	The vector to compare to.
	 *
	 * @return		True if they have the same values, false otherwise.
	 */
	public boolean equals(ReadableVector3d v) {
		return x == v.getX() && y == v.getY() && z == v.getZ();
	}

	/**
	 * Calculate the dot product of this vector and a given vector.
	 *
	 * @param	v	The other vector.
	 *
	 * @return		The dot product.
	 */
	public double dot(ReadableVector3d v) {
		return x*v.getX() + y*v.getY() + z*v.getZ();
	}

	public double magnitude() {
		return x*x + y*y + z*z;
	}

	/**
	 * calculate the angle between this and a given vector.
	 *
	 * @param	x	The x value.
	 * @param	y	The y value.
	 * @param	z	The z value.
	 *
	 * @return		The angle between the two vectors.
	 */
	public double angle(double x, double y, double z) {
		Vector3d v = new Vector3d(x,y,z);
		return angle(v);
	}

	/**
	 * calculate the angle between this and a given vector.
	 *
	 * @param	v	The other vector.
	 *
	 * @return		The angle between the two vectors.
	 */
	public double angle(ReadableVector3d v) {
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
	 * @param	z	The z value.
	 */
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Set the values of this vector to that of a given vector.
	 *
	 * @param	v	The vector to copy.
	 */
	public void set(ReadableVector3d v) {
		x = v.getX();
		y = v.getY();
		z = v.getZ();
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
	 * Set the z value of this vector.
	 *
	 * @param	z	The new z value.
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Add the given values to this vector.
	 *
	 * @param	x	The value to add to the x value.
	 * @param	y	The value to add to the y value.
	 * @param	z	The value to add to the z value.
	 */
	public void add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	/**
	 * Add the given vector to this vector.
	 *
	 * @param	v	The vector to add.
	 */
	public void add(ReadableVector3d v) {
		x += v.getX();
		y += v.getY();
		z += v.getZ();
	}

	/**
	 * Subtract the given values from this vector.
	 *
	 * @param	x	The value to subtract to the x value.
	 * @param	y	The value to subtract to the y value.
	 * @param	z	The value to subtract to the z value.
	 */
	public void subtract(double x, double y, double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
	}

	/**
	 * Subtract the given vector from this vector.
	 *
	 * @param	v	The vector to subtract.
	 */
	public void subtract(ReadableVector3d v) {
		x -= v.getX();
		y -= v.getY();
		z -= v.getZ();
	}

	public void multiply(double r) {
		x *= r;
		y *= r;
		z *= r;
	}

	public void divide(double r) {
		x /= r;
		y /= r;
		z /= r;
	}

	/**
	 * Copy the signs of the given values.
	 *
	 * @param	x	The sign for the x value.
	 * @param	y	The sign for the y value.
	 * @param	z	The sign for the z value.
	 */
	public void copySign(double x, double y, double z) {
		this.x = Math.copySign(this.x, x);
		this.y = Math.copySign(this.y, y);
		this.z = Math.copySign(this.z, z);
	}

	/**
	 * Copy the signs of the given vector.
	 *
	 * @param	v	The vector to get the signs from.
	 */
	public void copySign(ReadableVector3d v) {
		this.x = Math.copySign(this.x, v.getX());
		this.y = Math.copySign(this.y, v.getY());
		this.z = Math.copySign(this.z, v.getZ());
	}

	public void normalize() {
		this.divide(length());
	}

	public void constrain(double min, double max) {
		x = MotorMath.constrain(x, min, max);
		y = MotorMath.constrain(y, min, max);
		z = MotorMath.constrain(z, min, max);
	}

	public void abs() {
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
	}

}
