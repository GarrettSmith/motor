package motor_engine.input;
import motor_engine.*;
import java.util.*;

/**
 * The definition of an input action. For example a "Jump" action might have the space bar and up key mapped to it.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class InputDefinition {

	// The int values defined in Keyboard of the keys assigned
	private HashSet<Integer> keys;

	// The int values defined in Mouse for mouse buttons
	private HashSet<Integer> mouseButtons;

	private HashMap<Integer, HashSet<Integer>> controllerButtons;

	/**
	 * Creates a new InputDefinition.
	 *
	 * @param	keys	A HashSet of integers each corresponding to a key.
	 */
	public InputDefinition(HashSet<Integer> keys,
							HashSet<Integer> mouseButtons,
							HashMap<Integer, HashSet<Integer>> controllerButtons){
		setKeys(keys);
		setMouseButtons(mouseButtons);
		setControllerButtons(controllerButtons);
	}

	/**
	 * Creates a new empty InputDefinition.
	 */
	public InputDefinition() {
		keys = new HashSet<Integer>();
		mouseButtons = new HashSet<Integer>();
		controllerButtons = new HashMap<Integer, HashSet<Integer>>();
	}

	// Keyboard
	/**
	 * Get the keys assigned to this InputDefinition. The values corresponding to keys can be found in Keyboard.
	 *
	 * @return	An HashSet of integers whose values correspond to a key.
	 */
	public HashSet<Integer> getKeys() {
		return keys;
	}

	/**
	 * Assign keys to this InputDefinition. The values corresponding to keys can be found in Keyboard.
	 *
	 * @param	keys	A HashSet of integers each corresponding to a key.
	 */
	public void setKeys(HashSet<Integer> keys) {
		this.keys = keys;
	}

	/**
	 * Add a key to this InputDefinition.
	 *
	 * @param	key	The int value corresponding to the key to add.
	 */
	public void addKey(int key) {
		keys.add(key);
	}

	/**
	 * Remove a key from this InputDefinition.
	 *
	 * @param	key	The int value corresponding to the key to be removed.
	 */
	public void removeKey(int key) {
		keys.remove(key);
	}

	/**
	 * Removes all the keys from this InputDefinition.
	 */
	public void clearKeys() {
		keys.clear();
	}

	// Mouse

	/**
	 * Assign mouse buttons to this InputDefinition. The values corresponding to buttons can be found in Mouse.
	 *
	 * @param	buttons	A HashSet of integers whose values correspond to a mouse buttons.
	 */
	public void setMouseButtons(HashSet<Integer> buttons) {
		mouseButtons = buttons;
	}

	/**
	 * Get the mouse buttons assigned to this InputDefinition. The values corresponding to buttons can be found in Mouse.
	 *
	 * @return	A HashSet of integers whose values correspond to a mouse buttons.
	 */
	public HashSet<Integer> getButtons() {
		return mouseButtons;
	}

	/**
	 * Adds a mouse button to this InputDefinition.
	 *
	 * @param	button	The int value corresponding to the mouse button to be removed.
	 */
	public void addMouseButton(int button) {
		mouseButtons.add(button);
	}

	/**
	 * Removes a mouse button from this InputDefinition.
	 *
	 * @param	button	The int value corresponding to the button to be removed.
	 */
	public void removeMouseButton(int button) {
		mouseButtons.remove(button);
	}

	/**
	 * Removes all mouse buttons from this InputDefinition.
	 */
	public void clearMouseButtons() {
		mouseButtons.clear();
	}

	// Controller

	/**
	 * Assign controller buttons to this InputDefinition. The values corresponding to buttons can be found in Controller.
	 *
	 * @param	buttons	A HashSet of integers whose values correspond to a controller buttons.
	 */
	public void setControllerButtons(HashMap<Integer, HashSet<Integer>> buttons) {
		controllerButtons = buttons;
	}

	/**
	 * Adds a controller button to this InputDefinition.
	 *
	 * @param	button	The int value corresponding to the controller button to be removed.
	 */
	public void addControllerButton(int index, int button) {
		if (!controllerButtons.containsKey(index)) {
			controllerButtons.put(index, new HashSet<Integer>());
		}
		controllerButtons.get(index).add(button);
	}

	/**
	 * Removes a controller button from this InputDefinition.
	 *
	 * @param	button	The int value corresponding to the button to be removed.
	 */
	public void removeControllerButton(int index, int button) {
		if (controllerButtons.containsKey(index)) {
			controllerButtons.get(index).remove(button);
		}
	}

	/**
	 * Removes all controller buttons from this InputDefinition.
	 */
	public void clearControllerButtons(int index) {
		if (controllerButtons.containsKey(index)) {
			controllerButtons.get(index).clear();
		}
	}

	// checking
	/**
	 * Check if any of the keys or buttons assigned to this InputDefinition are being pressed.
	 *
	 * @return	True if atleast one of these inputs are being pressed, false otherwise.
	 */
	public boolean pressed() {
		boolean pressed = false;
		for (int k : keys) {
			if (KeyboardManager.pressed(k)) {
				pressed = true;
			}
		}
		for (int b : mouseButtons) {
			if (MouseManager.pressed(b)) {
				pressed = true;
			}
		}
		for (Integer i : controllerButtons.keySet()) {
			for (int b : controllerButtons.get(i)) {
				if (ControllerManager.pressed(i,b)) {
					pressed = true;
				}
			}
		}
		return pressed;
	}

	/**
	 * Check if none of the keys or buttons assigned to this InputDefinition are released.
	 *
	 * @return	True if none of these inputs are being pressed, false otherwise.
	 */
	public boolean released() {
		return !pressed();
	}

	/**
	 * Check if any of the keys or buttons assigned to this InputDefinition have just been pressed.
	 *
	 * @return	True if any of the assigned inputs have just been pressed, false otherwise.
	 */
	public boolean justPressed() {
		boolean justPressed = false;
		for (int k : keys) {
			if (KeyboardManager.justPressed(k)) {
				justPressed = true;
			}
		}
		for (int b : mouseButtons) {
			if (MouseManager.justPressed(b)) {
				justPressed = true;
			}
		}
		for (Integer i : controllerButtons.keySet()) {
			for (int b : controllerButtons.get(i)) {
				if (ControllerManager.justPressed(i,b)) {
					justPressed = true;
				}
			}
		}
		return justPressed;
	}

	/**
	 * Check if any of the keys or buttons assigned to this InputDefinition have just been released.
	 *
	 * @return	True if any of the assigned inputs have just been released, false otherwise.
	 */
	public boolean justReleased() {
		boolean justReleased = false;
		for (int k : keys) {
			if (KeyboardManager.justReleased(k)) {
				justReleased = true;
			}
		}
		for (int b : mouseButtons) {
			if (MouseManager.justReleased(b)) {
				justReleased = true;
			}
		}
		for (Integer i : controllerButtons.keySet()) {
			for (int b : controllerButtons.get(i)) {
				if (ControllerManager.justReleased(i,b)) {
					justReleased = true;
				}
			}
		}
		return justReleased;
	}

}
