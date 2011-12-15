package motor_engine.util.vector;

/**
 * This class contatins various methods to create new vectors from old ones.
 *
 * @author	Garrett Smith
 * @version	0.2
 */
public class Vectors {

	// 2 doubles
	/**
	 * Get a vector containing the sum of the two given vectors.
	 *
	 * @param	v1	The first vector.
	 * @param	v2	The second vector.
	 *
	 * @return		The vector containing the sum.
	 */
	public static ReadableVector2d add(ReadableVector2d v1, ReadableVector2d v2) {
		Vector2d tmp = new Vector2d(v1);
		tmp.add(v2);
		return tmp;
	}

	/**
	 * Get a vector containing the sum of the given vector and the given values.
	 *
	 * @param	v	The original vector.
	 * @param	x	The value to add to the x value.
	 * @param	y	The value to add to the y value.
	 *
	 * @return		The vector containing the sum.
	 */
	public static ReadableVector2d add(ReadableVector2d v, double x, double y) {
		Vector2d tmp = new Vector2d(v);
		tmp.add(x,y);
		return tmp;
	}

	/**
	 * Get a vector containing the difference of the two given vectors.
	 *
	 * @param	v1	The first vector.
	 * @param	v2	The second vector.
	 *
	 * @return		The vector containing the difference.
	 */
	public static ReadableVector2d subtract(ReadableVector2d v1, ReadableVector2d v2) {
		Vector2d tmp = new Vector2d(v1);
		tmp.subtract(v2);
		return tmp;
	}

	/**
	 * Get a vector containing the difference of the given vector and the given values.
	 *
	 * @param	v	The original vector.
	 * @param	x	The value to subtract from the x value.
	 * @param	y	The value to subtract from the y value.
	 *
	 * @return		The vector containing the difference.
	 */
	public static ReadableVector2d subtract(ReadableVector2d v, double x, double y) {
		Vector2d tmp = new Vector2d(v);
		tmp.subtract(x,y);
		return tmp;
	}

	/**
	 * Get a vector equal to the given vector multiplied by a scalar.
	 *
	 * @param	v	The original vector.
	 * @param	r	The scalar to multiply by.
	 *
	 * @return		The vector containing product.
	 */
	public static ReadableVector2d multiply(ReadableVector2d v, double r) {
		Vector2d tmp = new Vector2d(v);
		tmp.multiply(r);
		return tmp;
	}

	/**
	 * Get a vector equal to the given vector divided by a scalar.
	 *
	 * @param	v	The original vector.
	 * @param	r	The scalar to divide by.
	 *
	 * @return		The vector containing the quotient.
	 */
	public static ReadableVector2d divide(ReadableVector2d v, double r) {
		Vector2d tmp = new Vector2d(v);
		tmp.divide(r);
		return tmp;
	}

	/**
	 * Get a vector that is equal to the normal of the given vector.
	 *
	 * @param	v	The vector to normalize.
	 *
	 * @return		The normalized vector.
	 */
	public static ReadableVector2d normalize(ReadableVector2d v) {
		Vector2d tmp = new Vector2d(v);
		tmp.normalize();
		return tmp;
	}

	/**
	 * Get a vector that is equal to the negation of the given vector.
	 *
	 * @param	v	The vector to negate.
	 *
	 * @return		The negated vector.
	 */
	public static ReadableVector2d negate(ReadableVector2d v) {
		Vector2d tmp = new Vector2d(v);
		tmp.negate();
		return tmp;
	}

	/**
	 * Get a vector whose values are the absolute values of those from the given vector.
	 *
	 * @param	v	The vector to get the absolute values from.
	 *
	 * @return		The absolute vector.
	 */
	public static ReadableVector2d abs(ReadableVector2d v) {
		Vector2d tmp = new Vector2d(v);
		tmp.abs();
		return tmp;
	}

	/**
	 * CGet a vector whose magnitude is that of the given values and signs are of the given values.
	 *
	 * @param	v	The vector to get the magnitude from.
	 * @param	x			The sign for the x value.
	 * @param	y			The sign for the y value.
	 *
	 * @return				The sign adjusted vector.
	 */
	public static ReadableVector2d copySign(ReadableVector2d v, double x, double y) {
		Vector2d tmp = new Vector2d(v);
		tmp.copySign(x,y);
		return tmp;
	}

	/**
	 * Get a vector whose magnitude and signs are that of the given vectors.
	 *
	 * @param	magnitude	The vector to get the magnitude from.
	 * @param	signs		The vector to get the signs from.
	 *
	 * @return				The sign adjusted vector.
	 */
	public static ReadableVector2d copySign(ReadableVector2d magnitude, Vector2d signs) {
		Vector2d tmp = new Vector2d(magnitude);
		tmp.copySign(signs);
		return tmp;
	}

	/**
	 * Get a copy of the given vector whose values are constrained to the given range.
	 *
	 * @param	v		The original vector.
	 * @param	min		The minimum value.
	 * @param	max		The maximum value.
	 *
	 * @return			The constrained vector.
	 */
	public static ReadableVector2d constrain(ReadableVector2d v, double min, double max) {
		Vector2d tmp = new Vector2d(v);
		tmp.constrain(min,max);
		return tmp;
	}

	// 3 doubles

	/**
	 * Get a vector containing the sum of the two given vectors.
	 *
	 * @param	v1	The first vector.
	 * @param	v2	The second vector.
	 *
	 * @return		The vector containing the sum.
	 */
	public static ReadableVector3d add(ReadableVector3d v1, ReadableVector3d v2) {
		Vector3d tmp = new Vector3d(v1);
		tmp.add(v2);
		return tmp;
	}

	/**
	 * Get a vector containing the sum of the given vector and the given values.
	 *
	 * @param	v	The original vector.
	 * @param	x	The value to add to the x value.
	 * @param	y	The value to add to the y value.
	 * @param	z	The value to add to the z value.
	 *
	 * @return		The vector containing the sum.
	 */
	public static ReadableVector3d add(ReadableVector3d v, double x, double y, double z) {
		Vector3d tmp = new Vector3d(v);
		tmp.add(x,y,z);
		return tmp;
	}

	/**
	 * Get a vector containing the difference of the two given vectors.
	 *
	 * @param	v1	The first vector.
	 * @param	v2	The second vector.
	 *
	 * @return		The vector containing the difference.
	 */
	public static ReadableVector3d subtract(ReadableVector3d v1, ReadableVector3d v2) {
		Vector3d tmp = new Vector3d(v1);
		tmp.subtract(v2);
		return tmp;
	}

	/**
	 * Get a vector containing the difference of the given vector and the given values.
	 *
	 * @param	v	The original vector.
	 * @param	x	The value to subtract from the x value.
	 * @param	y	The value to subtract from the y value.
	 * @param	z	The value to subtract from the z value.
	 *
	 * @return		The vector containing the difference.
	 */
	public static ReadableVector3d subtract(ReadableVector3d v, double x, double y, double z) {
		Vector3d tmp = new Vector3d(v);
		tmp.subtract(x,y,z);
		return tmp;
	}

	/**
	 * Get a vector equal to the given vector multiplied by a scalar.
	 *
	 * @param	v	The original vector.
	 * @param	r	The scalar to multiply by.
	 *
	 * @return		The vector containing product.
	 */
	public static ReadableVector3d multiply(ReadableVector3d v, double r) {
		Vector3d tmp = new Vector3d(v);
		tmp.multiply(r);
		return tmp;
	}

	/**
	 * Get a vector equal to the given vector divided by a scalar.
	 *
	 * @param	v	The original vector.
	 * @param	r	The scalar to divide by.
	 *
	 * @return		The vector containing the quotient.
	 */
	public static ReadableVector3d divide(ReadableVector3d v, double r) {
		Vector3d tmp = new Vector3d(v);
		tmp.divide(r);
		return tmp;
	}

	/**
	 * Get a vector that is equal to the normal of the given vector.
	 *
	 * @param	v	The vector to normalize.
	 *
	 * @return		The normalized vector.
	 */
	public static ReadableVector3d normalize(ReadableVector3d v) {
		Vector3d tmp = new Vector3d(v);
		tmp.normalize();
		return tmp;
	}

	/**
	 * Get a vector that is equal to the negation of the given vector.
	 *
	 * @param	v	The vector to negate.
	 *
	 * @return		The negated vector.
	 */
	public static ReadableVector3d negate(ReadableVector3d v) {
		Vector3d tmp = new Vector3d(v);
		tmp.negate();
		return tmp;
	}

	/**
	 * Get a vector whose values are the absolute values of those from the given vector.
	 *
	 * @param	v	The vector to get the absolute values from.
	 *
	 * @return		The absolute vector.
	 */
	public static ReadableVector3d abs(ReadableVector3d v) {
		Vector3d tmp = new Vector3d(v);
		tmp.abs();
		return tmp;
	}

	/**
	 * CGet a vector whose magnitude is that of the given values and signs are of the given values.
	 *
	 * @param	v	The vector to get the magnitude from.
	 * @param	x			The sign for the x value.
	 * @param	y			The sign for the y value.
	 * @param	z			The sign for the z value.
	 *
	 * @return				The sign adjusted vector.
	 */
	public static ReadableVector3d copySign(ReadableVector3d v, double x, double y, double z) {
		Vector3d tmp = new Vector3d(v);
		tmp.copySign(x,y,z);
		return tmp;
	}

	/**
	 * Get a vector whose magnitude and signs are that of the given vectors.
	 *
	 * @param	magnitude	The vector to get the magnitude from.
	 * @param	signs		The vector to get the signs from.
	 *
	 * @return				The sign adjusted vector.
	 */
	public static ReadableVector3d copySign(ReadableVector3d magnitude, Vector3d signs) {
		Vector3d tmp = new Vector3d(magnitude);
		tmp.copySign(signs);
		return tmp;
	}

	/**
	 * Get a copy of the given vector whose values are constrained to the given range.
	 *
	 * @param	v		The original vector.
	 * @param	min		The minimum value.
	 * @param	max		The maximum value.
	 *
	 * @return			The constrained vector.
	 */
	public static ReadableVector3d constrain(ReadableVector3d v, double min, double max) {
		Vector3d tmp = new Vector3d(v);
		tmp.constrain(min,max);
		return tmp;
	}
}
