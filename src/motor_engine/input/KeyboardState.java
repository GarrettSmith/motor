package motor_engine.input;
import java.util.*;
import org.lwjgl.input.Keyboard;

/**
 * Stores the state of the keyboard.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class KeyboardState extends InputState {

	private boolean[] pressed;
	private boolean[] justPressed;
	private boolean[] justReleased;
	private ArrayList<Integer> keyCache;

	/**
	 * Creates a new empty state.
	 */
	public KeyboardState() {
		int keys = 256;
		pressed = new boolean[keys];
		clear(pressed);
		justPressed = new boolean[keys];
		clear(justPressed);
		justReleased = new boolean[keys];
		clear(justReleased);
		keyCache = new ArrayList<Integer>();
	}

	/**
	 * Creates a state that is a copy of the given state.
	 *
	 * @param	state	The KeybboardState to copy.
	 */
	public void copy(KeyboardState state) {
		for (int i = 0; i < pressed.length; i++) {
			pressed[i] = state.pressed[i];
			justPressed[i] = state.justPressed[i];
			justReleased[i] = state.justReleased[i];
		}
		keyCache.clear();
		keyCache.addAll(state.keyCache);
	}

	/**
	 * Clears time sensative information from this state.
	 */
	protected void update() {
		clear(justPressed);
		clear(justReleased);
		keyCache.clear();
	}

	//Getters
	/**
	 * Checks if any keys are pressed.
	 *
	 * @return	True if atleast one key is pressed, false if none are pressed.
	 */
	protected boolean anyPressed() {
		return containsTrue(pressed);
	}

	/**
	 * Check if any keys have just been pressed.
	 *
	 * @return	True if atleast one key is just pressed, false if none were.
	 */
	protected boolean anyJustPressed() {
		return containsTrue(justPressed);
	}

	/**
	 * Check if any keys were just released.
	 *
	 * @return	True if atleast one key was just released, false if none were.
	 */
	protected boolean anyJustReleased() {
		return containsTrue(justReleased);
	}

	/**
	 * Check if a key is being pressed
	 *
	 * @param	key	The key to check.
	 * @return	True if the given key is pressed, false otherwise.
	 */
	protected boolean pressed(int key) {
		return pressed[key];
	}

	/**
	 * Check if a key is released.
	 *
	 * @param	key	The key to check.
	 * @return	True if the given key is released, false otherwise.
	 */
	protected boolean released(int key) {
		return !pressed[key];
	}

	/**
	 * Check if a key was just pressed.
	 *
	 * @param	key	The key to check.
	 * @return	True if the key was jjust presses, false otherwise.
	 */
	protected boolean justPressed(int key) {
		return justPressed[key];
	}

	/**
	 * Check if a key was just released.
	 *
	 * @param	key	The key to check.
	 * @return	True if the given key was just released, false otherwise.
	 */
	protected boolean justReleased(int key) {
		return justReleased[key];
	}

	/**
	 * Get the keys just pressed this update.
	 *
	 * @return	An array of integer values each corresponding to a pressed key.
	 */
	protected int[] getKeyCache() {
		int[] ints = new int[keyCache.size()];
		for (int i = 0; i < keyCache.size(); i++) {
			ints[i] = keyCache.get(i).intValue();
		}
		return ints;
	}

	// Setters
	/**
	 * Sets the given key as pressed and just pressed.
	 *
	 * @param	key	The key that is pressed.
	 */
	protected void press(int key) {
		pressed[key] = true;
		justPressed[key] = true;
		keyCache.add(key);
	}

	/**
	 * Set the given key as released and just released.
	 *
	 * @param	key	The key that is released.
	 */
	protected void release(int key) {
		pressed[key] = false;
		justReleased[key] = true;
	}
}
