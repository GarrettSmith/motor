package motor_engine.input;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class ControllerDevice {
	// the device index
	private Controller controller;

	// The currently updated state
	private ControllerState currentState;
	// The state from the last update
	private ControllerState previousState;

	public ControllerDevice(int i) {
		controller = Controllers.getController(i);
		currentState = new ControllerState(controller.getAxisCount(), controller.getButtonCount());
		previousState = new ControllerState(controller.getAxisCount(), controller.getButtonCount());
		update();
	}

	public void update() {
		previousState.copy(currentState);
		currentState.update();
		pollInput();
	}

	public void pollInput() {
		//poll buttons
		for (int i = 0; i < controller.getButtonCount(); i++) {
			if (controller.isButtonPressed(i) && released(i)) {
				currentState.press(i);
			}
			else if (!controller.isButtonPressed(i) && pressed(i)) {
				currentState.release(i);
			}
		}
		//poll axes
		for (int i = 0; i < controller.getAxisCount(); i++) {
			currentState.move(i, controller.getAxisValue(i));
		}
	}

	// Getters

	/**
	 * Check if any controller button is being pressed.
	 *
	 * @return	True if any buttons are being pressed, false otherwise.
	 */
	public boolean anyPressed() {
		return previousState.anyPressed();
	}


	/**
	 * Check if any controller button has just been pressed this update.
	 *
	 * @return	True if any button was just pressed, false otherwise.
	 */
	public boolean anyJustPressed() {
		return previousState.anyJustPressed();
	}

	/**
	 * Check if any controller button was just released this update.
	 *
	 * @return	True if any button was just released, false otherwise.
	 */
	public boolean anyJustReleased() {
		return previousState.anyJustReleased();
	}

	/**
	 * Check if the given controller button is being pressed.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button is pressed, false otherwise.
	 */
	public boolean pressed(int button) {
		return previousState.pressed(button);
	}

	/**
	 * Check if the given controller button is released.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button is released, false otherwise.
	 */
	public boolean released(int button) {
		return previousState.released(button);
	}

	/**
	 * Check if the given controller button was just pressed this update.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button was just pressed, false otherwise.
	 */
	public boolean justPressed(int button) {
		return previousState.justPressed(button);
	}

	/**
	 * Check if the given controller button was just released.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button was just released, false otherwise.
	 */
	public boolean justReleased(int button) {
		return previousState.justReleased(button);
	}

	/**
	 * Check if any axis were moved.
	 *
	 * @return	True if any of the axis were moved, false otherwise.
	 */
	public boolean anyMoved() {
		return previousState.anyMoved();
	}

	/**
	 * Check if a given axis is moved.
	 *
	 * @param	axis	Which axis to check.
	 * @return			True if the axis delta is not zero.
	 */
	public boolean isMoved(int axis) {
		return previousState.isMoved(axis);
	}

	/**
	 * Get the current position of the given axis.
	 *
	 * @param	axis	Which axis to check.
	 * @return			The current position.
	 */
	public float getAxisValue(int axis) {
		return previousState.getAxisValue(axis);
	}

	/**
	 * Get the current position of the given axis.
	 *
	 * @param	axis	Which axis to check.
	 * @return			The current delta.
	 */
	public float getAxisDelta(int axis) {
		System.out.println(previousState.getAxisDelta(axis));
		return previousState.getAxisDelta(axis);
	}

	/**
	 * Check if the given axis is moved positively.
	 *
	 * @param	axis	The axis to check.
	 * @return			True if the axis position is positive.
	 */
	public boolean isAxisPositive(int axis) {
		return previousState.isAxisPositive(axis);
	}

	/**
	 * Check if the given axis is moved negatively.
	 *
	 * @param	axis	The axis to check.
	 * @return			True if the axis position is negative.
	 */
	public boolean isAxisNegative(int axis) {
		return previousState.isAxisNegative(axis);
	}

}
