package motor_engine.input;
import java.util.*;
import org.lwjgl.input.Mouse;

/**
 * Stores the information about the mouse.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class MouseState extends InputState {

	// mouse location
	private int x;
	private int y;
	private int deltaX;
	private int deltaY;
	private boolean inWindow;
	private boolean justEntered;
	private boolean justExited;

	// Mouse buttons
	private boolean[] pressed;
	private boolean[] justPressed;
	private boolean[] justReleased;
	private int deltaWheel;
	private ArrayList<Integer> buttonCache;

	/**
	 * Creates a new empty state.
	 */
	public MouseState() {
		int buttons = Mouse.getButtonCount();
		x = 0;
		y = 0;
		deltaX = 0;
		deltaY = 0;
		deltaWheel = 0;
		inWindow = false;
		justEntered = false;
		justExited = false;
		pressed = new boolean[buttons];
		clear(pressed);
		justPressed = new boolean[buttons];
		clear(justPressed);
		justReleased = new boolean[buttons];
		clear(justReleased);
		buttonCache = new ArrayList<Integer>();
	}

	/**
	 * Creates a new state that is a csopy of the given state.
	 *
	 * @param	state	The state to copy from.
	 */
	public void copy(MouseState state) {
		x = state.x;
		y = state.y;
		deltaX = state.deltaX;
		deltaY = state.deltaY;
		deltaWheel = state.deltaWheel;
		inWindow = state.inWindow;
		justEntered = state.justEntered;
		justExited = state.justExited;
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
		deltaX = 0;
		deltaY = 0;
		deltaWheel = 0;
		justEntered = false;
		justExited = false;
		clear(justPressed);
		clear(justReleased);
		buttonCache.clear();
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
	 *
	 */
	protected int getDeltaWheel() {
		return deltaWheel;
	}

	/**
	 * Check if the mouse wheel was scrolled.
	 *
	 * @return	True if the mouse wheel scrolled, false otherwise.
	 */
	protected boolean isScrolled() {
		return (deltaWheel != 0);
	}

	/**
	 * Check if the mouse wheel was scrolled up.
	 *
	 * @return	True if the mouse wheel scrolled up, false otherwise.
	 */
	protected boolean isScrolledUp() {
		return (deltaWheel > 0);
	}

	/**
	 * Check if the mouse wheel was scrolled down.
	 *
	 * @return	True if the mouse wheel scrolled down, false otherwise.
	 */
	protected boolean isScrolledDown() {
		return (deltaWheel < 0);
	}

	/**
	 * Check if the mouse is moving.
	 *
	 * @return	True if the mouse is moving, false otherwise.
	 */
	protected boolean moving() {
		return (deltaX != 0 || deltaY != 0);
	}

	/**
	 * Get the x position of the mouse relative to the window.
	 *
	 * @return	The mouse x position relative to the window.
	 */
	protected int getX() {
		return x;
	}

	/**
	 * Get the y position of the mouse relative to the window.
	 *
	 * @return	The mouse y position relative to the window.
	 */
	protected int getY() {
		return y;
	}

	/**
	 * Get the change in x position of the mouse.
	 *
	 * @return	Thec hange in  mouse x position.
	 */
	protected int getDeltaX() {
		return deltaX;
	}

	/**
	 * Get thechange in y position of the mouse.
	 *
	 * @return	Thechange in mouse y position.
	 */
	protected int getDeltaY() {
		return deltaY;
	}

	/**
	 * Check if the mouse is currently in the window.
	 *
	 * @return	True if the mouse is in the window, false otherwise.
	 */
	protected boolean inWindow() {
		return inWindow;
	}

	/**
	 * Check if the mouse just entered the window.
	 *
	 * @return	True if the mouse just entered the window, false otherwise.
	 */
	protected boolean justEntered() {
		return justEntered;
	}

	/**
	 * Check if the mouse just exited the window.
	 *
	 * @return	True if the mouse just exited the window, false otherwise.
	 */
	protected boolean justExited() {
		return justExited;
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
	 * Scrolls the mouse wheel.
	 * A positive value means the mouse was scrolled down, a negative value means up.
	 *
	 * @param	scroll	The value the mouse is scrolled.
	 */
	protected void scroll(int scroll) {
		deltaWheel = scroll;
	}

	/**
	 * Move the mouse position.
	 *
	 * @param	x	The new x position of the mouse.
	 * @param	y	The new y position of the mouse.
	 * @param	dx	The change in x position of the mouse.
	 * @param	dy	The change in y position of the mouse.
	 */
	protected void move(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		deltaX = dx;
		deltaY = dy;
	}

	/**
	 * Enter the mouse into the window.
	 */
	protected void enter() {
		inWindow = true;
		justEntered = true;
	}

	/**
	 * Exit the mouse from the window.
	 */
	protected void exit() {
		inWindow = false;
		justExited = true;
	}
}
