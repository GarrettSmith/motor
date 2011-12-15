package motor_engine.util.vector;
import java.io.Serializable;

/**
 * The base vector class.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public abstract class Vector implements Serializable, ReadableVector, WriteableVector {

	/**
	 * Get the length of the Vector.
	 *
	 * @return	The length of the vector.
	 */
	public final double length() {
		return Math.sqrt(magnitude());
	}

	/**
	 * get the magnitude of the vector.
	 *
	 * @return	The magnitude of the vector.
	 */
	public abstract double magnitude();

	/**
	 * Multiply the vector by a scalar.
	 *
	 * @param	r	The scalar to multiply by.
	 */
	public abstract void multiply(double r);

	/**
	 * Divide the vector by a scalar.
	 *
	 * @param	r	The scalar to divide by.
	 */
	public abstract void divide(double r);

	/**
	 * Negate this vector.
	 * Note you will most likely want to use the static version of this method as this method permanantly modifies the vector.
	 */
	public final void negate() {
		multiply(-1);
	}

	/**
	 * Get the array containing this vectors values.
	 *
	 * @return	The array containing this vector's values.
	 */
	public abstract double[] toDoubleArray();

	/**
	 * Replace all values of this vector with their absolute values.
	 * Note you will most likely want to use the static version of this method as this method permanantly modifies the vector.
	 */
	public abstract void abs();

	/**
	 * Normalize this vector.
	 * Note you will most likely want to use the static version of this method as this method permanantly modifies the vector.
	 */
	public abstract void normalize();

	/**
	 * Limit the values of the vector to the given range.
	 *
	 * @param	min		The minimum value.
	 * @param	max		The maximum value.
	 */
	public abstract void constrain(double min, double max);

}
