package motor_engine.input;
import java.awt.event.*;
import java.util.*;
import org.lwjgl.input.Keyboard;

/**
 * The keyboard device class used to access information.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class KeyboardManager {

	private static KeyboardState currentState = new KeyboardState();
	private static KeyboardState previousState = new KeyboardState();

	public static void update() {
		previousState.copy(currentState);
		currentState.update();
		pollInput();
	}

	/**
	 * Read input from the input buffer
	 */
	private static void pollInput() {
		// while there are keys left in the buffer
		while(Keyboard.next()) {
			// if the key was pressed
			if (Keyboard.getEventKeyState()) {
				currentState.press(Keyboard.getEventKey());
			}
			else {
				currentState.release(Keyboard.getEventKey());
			}
		}
	}

	//Getters

	/**
	 * Check if any key is being pressed.
	 *
	 * @return	True if any keys are being pressed, false otherwise.
	 */
	public static boolean anyPressed() {
		return previousState.anyPressed();
	}

	/**
	 * Check if any key has just been pressed this update.
	 *
	 * @return	True if any key was just pressed, false otherwise.
	 */
	public static boolean anyJustPressed() {
		return previousState.anyJustPressed();
	}

	/**
	 * Check if any key was just released this update.
	 *
	 * @return	True if any key was just released, false otherwise.
	 */
	public static boolean anyJustReleased() {
		return previousState.anyJustReleased();
	}

	/**
	 * Check if the given key is being pressed.
	 *
	 * @param	key	The integer value of the key, defined as a constant.
	 * @return			True if the given key is pressed, false otherwise.
	 */
	public static boolean pressed(int key) {
		return previousState.pressed(key);
	}

	/**
	 * Check if the given key is released.
	 *
	 * @param	key	The integer value of the key, defined as a constant.
	 * @return			True if the given key is released, false otherwise.
	 */
	public static boolean released(int key) {
		return previousState.released(key);
	}

	/**
	 * Check if the given key was just pressed this update.
	 *
	 * @param	key	The integer value of the key, defined as a constant.
	 * @return			True if the given key was just pressed, false otherwise.
	 */
	public static boolean justPressed(int key) {
		return previousState.justPressed(key);
	}

	/**
	 * Check if the given key was just released.
	 *
	 * @param	key	The integer value of the key, defined as a constant.
	 * @return			True if the given key was just released, false otherwise.
	 */
	public static boolean justReleased(int key) {
		return previousState.justReleased(key);
	}

	/**
	 * Get the list of keys typed this update.
	 *
	 * @return	The array of integers, corresponding to the keys,
	 * typed this update in the order they occurred.
	 */
	public static int[] getKeyCache() {
		return previousState.getKeyCache();
	}
}
