package motor_engine.input;
import java.awt.event.*;
import java.util.*;
import org.lwjgl.input.Mouse;

/**
 * The mouse device class used to access information.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class MouseManager  {

	// The currently updated state
	private static MouseState currentState = new MouseState();
	// The state from the last update
	private static MouseState previousState = new MouseState();

	public static void update() {
		previousState.copy(currentState);
		currentState.update();
		pollInput();
	}

	/**
	 * Read input from the input buffer
	 */
	private static void pollInput() {
		// while there are Buttons left in the buffer
		while(Mouse.next()) {
			// check to make sure it's not the no button event
			if (Mouse.getEventButton() >= 0) {
				// if the Button was pressed
				if (Mouse.getEventButtonState()) {
					currentState.press(Mouse.getEventButton());
				}
				else {
					currentState.release(Mouse.getEventButton());
				}
			}
		}
		// get scroll
		if (Mouse.hasWheel()) {
			currentState.scroll(Mouse.getDWheel());
		}

		//get position
		currentState.move(Mouse.getX(), Mouse.getY(), Mouse.getDX(), Mouse.getDY());

		// enter / exit window
		if (Mouse.isInsideWindow()) {
			if (!previousState.inWindow()) {
				currentState.enter();
			}
		}
		else {
			if (previousState.inWindow()) {
				currentState.exit();
			}
		}

	}

	// Getters

	/**
	 * Check if any mouse button is being pressed.
	 *
	 * @return	True if any buttons are being pressed, false otherwise.
	 */
	public static boolean anyPressed() {
		return previousState.anyPressed();
	}


	/**
	 * Check if any mouse button has just been pressed this update.
	 *
	 * @return	True if any button was just pressed, false otherwise.
	 */
	public static boolean anyJustPressed() {
		return previousState.anyJustPressed();
	}

	/**
	 * Check if any mouse button was just released this update.
	 *
	 * @return	True if any button was just released, false otherwise.
	 */
	public static boolean anyJustReleased() {
		return previousState.anyJustReleased();
	}

	/**
	 * Check if the given mouse button is being pressed.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button is pressed, false otherwise.
	 */
	public static boolean pressed(int button) {
		return previousState.pressed(button);
	}

	/**
	 * Check if the given mouse button is released.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button is released, false otherwise.
	 */
	public static boolean released(int button) {
		return previousState.released(button);
	}

	/**
	 * Check if the given mouse button was just pressed this update.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button was just pressed, false otherwise.
	 */
	public static boolean justPressed(int button) {
		return previousState.justPressed(button);
	}

	/**
	 * Check if the given mouse button was just released.
	 *
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button was just released, false otherwise.
	 */
	public static boolean justReleased(int button) {
		return previousState.justReleased(button);
	}

	/**
	 *
	 */
	public static int getDeltaWheel() {
		return previousState.getDeltaWheel();
	}

	/**
	 *	Check if the mouse wheel has been scrolled this update.
	 *
	 * @return True if the mouse was scrolled, false otherwise.
	 */
	public static boolean isScrolled() {
		return previousState.isScrolled();
	}

	/**
	 * Check if the mouse has a scroll wheel.
	 *
	 * @return	true if the mouse has a scroll wheel, false if it does not.
	 */
	public static boolean hasWheel() {
		return Mouse.hasWheel();
	}

	/**
	 * Check if the mouse wheel was scrolled up this update.
	 *
	 * @return	True if the mouse was scrolled up, false otherwise.
	 */
	public static boolean isScrolledUp() {
		return previousState.isScrolledUp();
	}

	/**
	 * Check if the mouse wheel was scrolled down this update.
	 *
	 * @return	True if the mouse was scrolled down, false otherwise.
	 */
	public static boolean isScrolledDown() {
		return previousState.isScrolledDown();
	}

	/**
	 * Get the list of buttons clicked this update.
	 *
	 * @return	The array of integers, corresponding to mouse buttons,
	 * clicked this update in the order they occurred.
	 */
	public static int[] getButtonCache() {
		return previousState.getButtonCache();
	}

	/**
	 * Check if the mouse is currently moving.
	 *
	 * @return	True if the mouse is moving, false otherwise.
	 */
	public static boolean moving() {
		return previousState.moving();
	}

	/**
	 * Get the current x position of the mouse.
	 *
	 * @return	The x position of the mouse relative to the origin of the window.
	 */
	public static int getX() {
		return previousState.getX();
	}

	/**
	 * Get the current y position of the mouse.
	 *
	 * @return	The y position of the mouse relative to the origin of the window.
	 */
	public static int getY() {
		return previousState.getY();
	}

	/**
	 * Get the change in x position of the mouse.
	 *
	 * @return	The change in x position of the mouse relative.
	 */
	public static int getDeltaX() {
		return previousState.getDeltaX();
	}

	/**
	 * Get the change in y position of the mouse.
	 *
	 * @return	The change in y position of the mouse.
	 */
	public static int getDeltaY() {
		return previousState.getDeltaY();
	}

	/**
	 * Check whether the mouse is inside the window.
	 *
	 * @return	True if the mouse is inside the window, false otherwise.
	 */
	public static boolean inWindow() {
		return previousState.inWindow();
	}

	/**
	 * Check if the mouse has just entered the window this update.
	 *
	 * @return	True if the mouse has just entered the window this update, false otherwise.
	 */
	public static boolean justEntered() {
		return previousState.justEntered();
	}

	/**
	 * Check if the mouse has just exited the window this update.
	 *
	 * @return	True if the mouse has just exited the window this update, false otherwise.
	 */
	public static boolean justExited() {
		return previousState.justExited();
	}
}
