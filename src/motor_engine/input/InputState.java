package motor_engine.input;
import java.util.*;

/**
 * An abstract state of an InputDevice.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public abstract class InputState {

	/** Clear time sensative values */
	protected abstract void update();

	/**
	 * Sets all values of an array, in this case of keys or buttons, to false.
	 *
	 * @param	a	The array to be filled.
	 */
	protected static void clear(boolean[] a) {
		Arrays.fill(a, false);
	}

	/**
	 * Sets all values of an array, in this case of keys or buttons, to false.
	 *
	 * @param	a	The array to be filled.
	 */
	protected static void clear(float[] a) {
		Arrays.fill(a, 0);
	}

	/**
	 *  A helper method that iterates through an array, in this case of keys or buttons, and checks if any are true.
	 *
	 * @param	a	The array to be checked for a true value.
	 * @return		True if atleast one true is found, false otherwise.
	 */
	protected static boolean containsTrue(boolean[] a) {
		boolean found = false;
		for (boolean i : a) {
			if (i) {
				found = true;
			}
		}
		return found;
	}

}
