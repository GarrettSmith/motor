package motor_engine.input;
import java.util.*;
import org.lwjgl.input.Controllers;
import java.util.ArrayList;

/**
 * Stores the information about a controller.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class ControllerState extends InputState {

	private float[] axis;
	private float[] axisDelta;
	private boolean[] pressed;
	private boolean[] justPressed;
	private boolean[] justReleased;
	private ArrayList<Integer> buttonCache;

	/**
	 * Creates a new empty state.
	 */
	public ControllerState(int axis, int buttons) {
		this.axis = new float[axis];
		this.axisDelta = new float[axis];
		this.pressed = new boolean[buttons];
		this.justPressed = new boolean[buttons];
		this.justReleased = new boolean[buttons];
		buttonCache = new ArrayList<Integer>();
	}

	/**
	 * Copies the given state.
	 *
	 * @param	state	The state to copy from.
	 */
	public void copy(ControllerState state) {
		for (int i = 0; i < axis.length; i++) {
			axis[i] = state.axis[i];
			axisDelta[i] = state.axisDelta[i];
		}
		for (int i = 0; i < pressed.length; i++) {
			pressed[i] = state.pressed[i];
			justPressed[i] = state.justPressed[i];
			justReleased[i] = state.justReleased[i];
		}
		buttonCache.clear();
		buttonCache.addAll(state.buttonCache);
	}

	/**
	 * Clears time sensitive values.
	 */
	protected void update() {
		clear(axisDelta);
		clear(justPressed);
		clear(justReleased);
		buttonCache.clear();
	}

	// Setters
	/**
	 * Sets the given button as pressed and just pressed.
	 *
	 * @param	button	The button that is pressed.
	 */
	protected void press(int button) {
		pressed[button] = true;
		justPressed[button] = true;
		buttonCache.add(button);
	}

	/**
	 * Set the given button as released and just released.
	 *
	 * @param	button	The button that is released.
	 */
	protected void release(int button) {
		pressed[button] = false;
		justReleased[button] = true;
	}

	/**
	 * Sets the specified axis to the specified position.
	 *
	 * @param	index		The axis number.
	 * @param	position	The axis position.
	 */
	protected void move(int index, float position) {
		axisDelta[index] = position - axis[index] ;
		axis[index] = position;
	}

	//Getters

	/**
	 * Checks if any buttons are pressed.
	 *
	 * @return	True if atleast one button is pressed, false if none are pressed.
	 */
	protected boolean anyPressed() {
		return containsTrue(pressed);
	}

	/**
	 * Check if any buttons have just been pressed.
	 *
	 * @return	True if atleast one button is just pressed, false if none were.
	 */
	protected boolean anyJustPressed() {
		return containsTrue(justPressed);
	}

	/**
	 * Check if any buttons were just released.
	 *
	 * @return	True if atleast one button was just released, false if none were.
	 */
	protected boolean anyJustReleased() {
		return containsTrue(justReleased);
	}

	/**
	 * Check if a button is being pressed
	 *
	 * @param	button	The button to check.
	 * @return	True if the given button is pressed, false otherwise.
	 */

	protected boolean pressed(int button) {
		return pressed[button];
	}

	/**
	 * Check if a button is released.
	 *
	 * @param	button	The button to check.
	 * @return	True if the given button is released, false otherwise.
	 */
	protected boolean released(int button) {
		return !pressed[button];
	}

	/**
	 * Check if a button was just pressed.
	 *
	 * @param	button	The button to check.
	 * @return	True if the button was jjust presses, false otherwise.
	 */
	protected boolean justPressed(int button) {
		return justPressed[button];
	}

	/**
	 * Check if a button was just released.
	 *
	 * @param	button	The button to check.
	 * @return	True if the given button was just released, false otherwise.
	 */
	protected boolean justReleased(int button) {
		return justReleased[button];
	}

	/**
	 * Get the buttons just pressed this update.
	 *
	 * @return	An array of integer values each corresponding to a pressed button.
	 */
	protected int[] getButtonCache() {
		int[] ints = new int[buttonCache.size()];
		for (int i = 0; i < buttonCache.size(); i++) {
			ints[i] = buttonCache.get(i).intValue();
		}
		return ints;
	}

	/**
	 * Check if any axis were moved.
	 *
	 * @return	True if any of the axis were moved, false otherwise.
	 */
	protected boolean anyMoved() {
		boolean moved = false;
		for (float f : axisDelta) {
			if (f != 0)
				moved = true;
		}
		return moved;
	}

	/**
	 * Check if a given axis is moved.
	 *
	 * @param	index	Which axis to check.
	 * @return			True if the axis delta is not zero.
	 */
	protected boolean isMoved(int index) {
		return (axisDelta[index] != 0);
	}

	/**
	 * Get the current position of the given axis.
	 *
	 * @param	index	Which axis to check.
	 * @return			The current position.
	 */
	protected float getAxisValue(int index) {
		return axis[index];
	}

	/**
	 * Get the current position of the given axis.
	 *
	 * @param	index	Which axis to check.
	 * @return			The current delta.
	 */
	protected float getAxisDelta(int index) {
		return axisDelta[index];
	}

	/**
	 * Check if the given axis is moved positively.
	 *
	 * @param	index	The axis to check.
	 * @return			True if the axis position is positive.
	 */
	protected boolean isAxisPositive(int index) {
		return (axis[index] > 0);
	}

	/**
	 * Check if the given axis is moved negatively.
	 *
	 * @param	index	The axis to check.
	 * @return			True if the axis position is negative.
	 */
	protected boolean isAxisNegative(int index) {
		return (axis[index] < 0);
	}
}
