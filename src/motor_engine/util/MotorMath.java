package motor_engine.util;

/**
 * Additional helpful math operations to make life easier.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class MotorMath {

	/**
	 * Limit a double to a given range.
	 *
	 * @param	value	The value to constrain.
	 * @param	min		The minimum value to constrain to.
	 * @param	max		The maximum value to constrain to.
	 *
	 * @return			The value constrained to the given range.
	 */
	public static double constrain(double value, double min, double max) {
		if (value < min) {
			value = min;
		}
		else if (value > max) {
			value = max;
		}
		return value;
	}

	/**
	 * Limit a float to a given range.
	 *
	 * @param	value	The value to constrain.
	 * @param	min		The minimum value to constrain to.
	 * @param	max		The maximum value to constrain to.
	 *
	 * @return			The value constrained to the given range.
	 */
	public static float constrain(float value, float min, float max) {
		if (value < min) {
			value = min;
		}
		else if (value > max) {
			value = max;
		}
		return value;
	}

	/**
	 * Limit an integer to a given range.
	 *
	 * @param	value	The value to constrain.
	 * @param	min		The minimum value to constrain to.
	 * @param	max		The maximum value to constrain to.
	 *
	 * @return			The value constrained to the given range.
	 */
	public static int constrain(int value, int min, int max) {
		if (value < min) {
			value = min;
		}
		else if (value > max) {
			value = max;
		}
		return value;
	}

	/**
	 * Limit a short to a given range.
	 *
	 * @param	value	The value to constrain.
	 * @param	min		The minimum value to constrain to.
	 * @param	max		The maximum value to constrain to.
	 *
	 * @return			The value constrained to the given range.
	 */
	public static short constrain(short value, short min, short max) {
		if (value < min) {
			value = min;
		}
		else if (value > max) {
			value = max;
		}
		return value;
	}

	/**
	 * Limit a long to a given range.
	 *
	 * @param	value	The value to constrain.
	 * @param	min		The minimum value to constrain to.
	 * @param	max		The maximum value to constrain to.
	 *
	 * @return			The value constrained to the given range.
	 */
	public static long constrain(long value, long min, long max) {
		if (value < min) {
			value = min;
		}
		else if (value > max) {
			value = max;
		}
		return value;
	}

	/**
	 * Limit a byte to a given range.
	 *
	 * @param	value	The value to constrain.
	 * @param	min		The minimum value to constrain to.
	 * @param	max		The maximum value to constrain to.
	 *
	 * @return			The value constrained to the given range.
	 */
	public static byte constrain(byte value, byte min, byte max) {
		if (value < min) {
			value = min;
		}
		else if (value > max) {
			value = max;
		}
		return value;
	}
}
