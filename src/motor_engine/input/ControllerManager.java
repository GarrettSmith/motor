package motor_engine.input;
import java.awt.event.*;
import java.util.*;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Controller;
import org.lwjgl.LWJGLException;

/**
 * The controllerList device class used to access information.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class ControllerManager {

	private static ControllerDevice[] controllerList;

	public static void begin() {
		try {
			Controllers.create();
			if (Controllers.getControllerCount() > 0) {
				controllerList = new ControllerDevice[Controllers.getControllerCount()];
				for (int i = 0; i < controllerList.length; i++) {
					controllerList[i] = new ControllerDevice(i);
				}
			}
			else {
				controllerList = null;
			}
		}
		catch (LWJGLException e) {
			System.err.println("Couldn't initialize Controllers: "+e.getMessage());
		}
	}

	public static void update() {
		//update all the controllers
		// add a check so only controllers that created events are updated.
		if (controllerList != null) {
			for (ControllerDevice c : controllerList) {
				c.update();
			}
		}
	}

	// Getters

	/**
	 * Check if any mouse button is being pressed.
	 *
	 * @param	index	The index of the controller to check.
	 * @return			True if any buttons are being pressed, false otherwise.
	 */
	public static boolean anyPressed(int index) {
		return controllerList[index].anyPressed();
	}


	/**
	 * Check if any mouse button has just been pressed this update.
	 *
	 * @param	index	The index of the controller to check.
	 * @return			True if any button was just pressed, false otherwise.
	 */
	public static boolean anyJustPressed(int index) {
		return controllerList[index].anyJustPressed();
	}

	/**
	 * Check if any mouse button was just released this update.
	 *
	 * @param	index	The index of the controller to check.
	 * @return			True if any button was just released, false otherwise.
	 */
	public static boolean anyJustReleased(int index) {
		return controllerList[index].anyJustReleased();
	}

	/**
	 * Check if the given mouse button is being pressed.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button is pressed, false otherwise.
	 */
	public static boolean pressed(int index, int button) {
		return controllerList[index].pressed(button);
	}

	/**
	 * Check if the given mouse button is released.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button is released, false otherwise.
	 */
	public static boolean released(int index, int button) {
		return controllerList[index].released(button);
	}

	/**
	 * Check if the given mouse button was just pressed this update.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button was just pressed, false otherwise.
	 */
	public static boolean justPressed(int index, int button) {
		return controllerList[index].justPressed(button);
	}

	/**
	 * Check if the given mouse button was just released.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	button	The integer value of the button, defined as a constant.
	 * @return			True if the given button was just released, false otherwise.
	 */
	public static boolean justReleased(int index, int button) {
		return controllerList[index].justReleased(button);
	}

	/**
	 * Check if any axis were moved.
	 *
	 * @return	True if any of the axis were moved, false otherwise.
	 */
	public static boolean anyMoved(int index) {
		return controllerList[index].anyMoved();
	}

	/**
	 * Check if a given axis is moved.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	axis	Which axis to check.
	 * @return			True if the axis delta is not zero.
	 */
	public static boolean isMoved(int index, int axis) {
		return controllerList[index].isMoved(axis);
	}

	/**
	 * Get the current position of the given axis.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	axis	Which axis to check.
	 * @return			The current position.
	 */
	public static float getAxisValue(int index, int axis) {
		return controllerList[index].getAxisValue(axis);
	}

	/**
	 * Get the current position of the given axis.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	axis	Which axis to check.
	 * @return			The current delta.
	 */
	public static float getAxisDelta(int index, int axis) {
		return controllerList[index].getAxisDelta(axis);
	}

	/**
	 * Check if the given axis is moved positively.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	axis	The axis to check.
	 * @return			True if the axis position is positive.
	 */
	public static boolean isAxisPositive(int index, int axis) {
		return controllerList[index].isAxisPositive(axis);
	}

	/**
	 * Check if the given axis is moved negatively.
	 *
	 * @param	index	The index of the controller to check.
	 * @param	axis	The axis to check.
	 * @return			True if the axis position is negative.
	 */
	public static boolean isAxisNegative(int index, int axis) {
		return controllerList[index].isAxisNegative(axis);
	}
}
