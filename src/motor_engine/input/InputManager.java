package motor_engine.input;
import java.util.*;

/**
 * A container of InputDefinitions so that input actions can be easily defined, edited, and accessed.
 *
 * @author	Garrett Smith
 * @version 0.1
 */
public class InputManager {

	// the hash map of InputActions pointed to by their names.
	private static HashMap<String, InputDefinition> definitions = new HashMap<String, InputDefinition>();

	//SETTERS
	/**
	 * Defines a new input to be used by a game. This should be your primary means of checking and organizing user input.
	 *
	 * @param	name	The name of this input.
	 * @param	key		An int corresponding to a key to be asigned to this new input.
	 *
	 * @see InputDefinition
	 */
	public static void define(String name, int key) {
		definitions.put(name, new InputDefinition());
		definitions.get(name).addKey(key);
	}

	/**
	 * Releases an input definition.
	 *
	 * @param	name	The name of the input.
	 *
	 * @see InputDefinition
	 */
	public static void release(String name) {
		definitions.remove(name);
	}

	/**
	 * Defines a new empty input.
	 *
	 * @param	name	The name of this input.
	 */
	public static void define(String name) {
		definitions.put(name, new InputDefinition());
	}

	/**
	 * Add a key to the given input.
	 *
	 * @param	name	The name of the input to be added to.
	 * @param	key		The key to add to the input.
	 */
	public static void addKey(String name, int key) {
		definitions.get(name).addKey(key);
	}

	/**
	 * Remove a key from the given input.
	 *
	 * @param	name	The name of the input to be remmoved from.
	 * @param	key		The key to remove from the input.
	 */
	public static void removeKey(String name, int key) {
		definitions.get(name).removeKey(key);
	}

	/**
	 * Remove all keys from this input.
	 *
	 * @param	name	The name of the input to be added to.
	 */
	public static void clearKeys(String name) {
		definitions.get(name).clearKeys();
	}

	/**
	 * Add a mouse button to the given input.
	 *
	 * @param	name	The name of the input to be added to.
	 * @param	button	The button to add to the input.
	 */
	public static void addMouseButton(String name, int button) {
		definitions.get(name).addMouseButton(button);
	}

	/**
	 * Remove a mouse button from the given input.
	 *
	 * @param	name	The name of the input to be remmoved from.
	 * @param	button	The button to remove from the input.
	 */
	public static void removeMouseButton(String name, int button) {
		definitions.get(name).removeMouseButton(button);
	}

	/**
	 * Remove all mouse buttons from this input.
	 *
	 * @param	name	The name of the input to be added to.
	 */
	public static void clearMouseButtons(String name) {
		definitions.get(name).clearMouseButtons();
	}

	// Controller
	/**
	 * Add a controller button to the given input.
	 *
	 * @param	name	The name of the input to be added to.
	 * @param	index	The controller index.
	 * @param	button	The button to add to the input.
	 */
	public static void addControllerButton(String name, int index, int button) {
		definitions.get(name).addControllerButton(index,button);
	}

	/**
	 * Remove a controller buttonfrom the given input.
	 *
	 * @param	name	The name of the input to be remmoved from.
	 * @param	index	The controller index.
	 * @param	button	The button to remove from the input.
	 */
	public static void removeControllerButton(String name, int index, int button) {
		definitions.get(name).removeControllerButton(index,button);
	}

	/**
	 * Remove all controller buttons from this input.
	 *
	 * @param	name	The name of the input to be added to.
	 */
	public static void clearControllerButtons(String name, int index) {
		definitions.get(name).clearControllerButtons(index);
	}

	/**
	 * Removes all keys and buttons from the input.
	 *
	 * @param	name	The name of the input to be cleared.
	 */
	public static void clear(String name) {
		definitions.get(name).clearKeys();
		definitions.get(name).clearMouseButtons();
	}

	// GETTERS
	/**
	 * Check if any of the keys or buttons assigned to the named input are being pressed.
	 *
	 * @param	name	The name of the input to be checked.
	 * @return			True if atleast one of these inputs are being pressed, false otherwise.
	 */
	public static boolean pressed(String name) {
		return definitions.get(name).pressed();
	}

	/**
	 * Check if none of the keys or buttons assigned to the named input are being pressed.
	 *
	 * @param	name	The name of the input to be checked.
	 * @return			True if none of these inputs are being pressed, false otherwise.
	 */
	public static boolean released(String name) {
		return definitions.get(name).released();
	}

	/**
	 * Check if any of the keys or buttons assigned to the named input have just been pressed.
	 *
	 * @param	name	The name of the input to be checked.
	 * @return			True if any of the assigned inputs have just been pressed, false otherwise.
	 */
	public static boolean justPressed(String name) {
		return definitions.get(name).justPressed();
	}

	/**
	 * Check if any of the keys or buttons assigned to the named input have just been released.
	 *
	 * @param	name	The name of the input to be checked.
	 * @return			True if any of the assigned inputs have just been released, false otherwise.
	 */
	public static boolean justReleased(String name) {
		return definitions.get(name).justReleased();
	}

	/**
	 * Check if any inputs are pressed.
	 *
	 * @return	True if any inputs are being pressed, false otherwise.
	 */
	public static boolean anyPressed() {
		boolean anyPressed = false;
		for (InputDefinition i : definitions.values()) {
			if (i.pressed()) {
				anyPressed = true;
			}
		}
		return anyPressed;
	}

	/**
	 * Check if any inputs are released.
	 *
	 * @return	True if any inputs are released, false otherwise.
	 */
	public static boolean anyReleased() {
		boolean anyReleased = false;
		for (InputDefinition i : definitions.values()) {
			if (i.released()) {
				anyReleased = true;
			}
		}
		return anyReleased;
	}

	/**
	 * Check if any input have been just pressed.
	 *
	 * @return	True if any of the inputs have just been pressed, false otherwise.
	 */
	public static boolean anyJustPressed() {
		boolean anyJustPressed = false;
		for (InputDefinition i : definitions.values()) {
			if (i.justPressed()) {
				anyJustPressed = true;
			}
		}
		return anyJustPressed;
	}

	/**
	 * Check if any input have been just released.
	 *
	 * @return	True if any of the inputs have just been released, false otherwise.
	 */
	public static boolean anyJustReleased() {
		boolean anyJustReleased = false;
		for (InputDefinition i : definitions.values()) {
			if (i.justReleased()) {
				anyJustReleased = true;
			}
		}
		return anyJustReleased;
	}

}
