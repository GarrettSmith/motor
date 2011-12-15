package motor_engine;
import motor_engine.input.*;
import motor_engine.util.Timer;
import motor_engine.util.Console;
import java.util.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;

/**
 * Instantiates all game systems, links them together, and provides global variables for the game.
 *
 * @author Garrett Smith
 * @version 0.1
 */
public abstract class MotorEngine implements LifeCycle {

	/** Version constant. */
	public static final String VERSION = "SUPER DUPER ALPHA!";
	/** Library name. */
	public static final String LIBRARY = "Motor Engine";

	// Game Constants
	/** The name of the game.*/
	public static String GAME_NAME = "";

	// Console
	/** The console.*/
	public static Console console;

	// Elapsed game time
	public final static long startTime = System.currentTimeMillis();
	private static long currentTime;
	private static long elapsedTime;

	// the boolean set to exit the program
	private static boolean exit;

	/**
	 * Create a new game.
	 *
	 * @param	width	The width of the game window.
	 * @param	height	The height of the game window.
	 */
	public MotorEngine(int width, int height) {

		// Window
		DisplayManager.begin(width, height);

		// Input
		setupInput();

		//Console
		setupConsole();

		//begin game
		begin();

		while(true) {
			Display.sync(60);
			update(10);
		}
	}

	//Setup Console
	private void setupConsole() {
		//Create Console
		console = new Console();

		//Hide Console
		console.setVisible(false);

		// Size console
		//console.setSize(Display.getDisplayMode().getWidth(), (Display.getDisplayMode().getHeight() / 3));

		//add console to window
		//window.add(MotorEngine.console);
	}

	//Setup Input
	private void setupInput() {

		// disable key repeat
		Keyboard.enableRepeatEvents(false);

		// grab mouse
		//Mouse.setGrabbed(true);

		// Begin controller manager
		ControllerManager.begin();

		// Create console toggle input
		InputManager.define("Console", Keyboard.KEY_GRAVE);
		// Full screen toggle
		InputManager.define("Full Screen", Keyboard.KEY_F11);
	}

	/**
	 * Get the current game time.
	 *
	 * @return The current time in milliseconds.
	 */
	public static long getCurrentTime() {
		return currentTime;
	}

	/**
	 * Get the elapsed game time.
	 *
	 * @return The elapsed time in milliseconds.
	 */
	public static long getElapsedTime() {
		return elapsedTime;
	}

	// updates the game time
	private void updateGameTime() {
		currentTime = System.currentTimeMillis();
		elapsedTime = currentTime - startTime;
	}

	/**
	 * Updates the the world map and the input devices.
	 *
	 * @param	deltaTime	The time in nanoseconds since the last update.
	 */
	public void update(long deltaTime) {

		// Check if the window is closed for exit requested
		if (Display.isCloseRequested() || exit) {
			end();
			DisplayManager.end();
			System.exit(0);
		}
		else {
			updateGameTime();
			MouseManager.update();
			KeyboardManager.update();
			ControllerManager.update();
			StateManager.update(deltaTime);
			console.update(deltaTime);
			DisplayManager.update(deltaTime);

			//Console input
			if (InputManager.justPressed("Console")) {
				console.toggle();
			}

			//Fullscreen
			if(InputManager.justPressed("Full Screen")) {
				System.out.println("Hey look fullscreen was toggled!");
			}
		}
	}

	public static void exit() {
		exit = true;
	}

	/**
	 * Override this to perform all neccessary starting tasks.
	 */
	public abstract void begin();

	/**
	 * 	Override this to perform all ending taks.
	 */
	public abstract void end();

}
