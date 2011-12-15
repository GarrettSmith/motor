package motor_engine;
import java.util.TreeSet;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * The DisplayManager manages Views and the game window.
 *
 * @author Garrett Smith
 * @version 0.1
 */
public class DisplayManager {

	// The views attached to the window.
	private static TreeSet<View> views = new TreeSet<View>();

	// Update
	public static void begin(int width, int height) {
		// intialize gl
		try {
			// set size
			Display.setDisplayMode(new DisplayMode(width, height));
			//create window
			Display.create();
		}
		catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// clear color
		glClearColor(0f,0f,0f,0f);
	}

	/**
	 * Destroys the window. This is called automatically by MotorEngine.
	 */
	public static void end() {
		Display.destroy();
	}

	/**
	 * Renders all the views belonging to this window.
	 *
	 * @param	deltaTime	The time in milliseconds since the last update.
	 */
	public static void update(long deltaTime) {
		// update window title
		updateTitle();

		// clear buffers
		glClear(GL_COLOR_BUFFER_BIT);

		// render the views in z order
		for (View v : views) {
			v.update();
		}

		// Tell video card to finish
		glFlush();

		// update the windo
		Display.update();
	}

	public static void updateTitle() {
		// window title
		if (MotorEngine.GAME_NAME.isEmpty()) {
			Display.setTitle(MotorEngine.LIBRARY + ": " + MotorEngine.VERSION);
		}
		else {
			Display.setTitle(MotorEngine.GAME_NAME);
		}
	}

	// Views
	/**
	 *	Adds a view to the window.
	 *
	 * @param	v	A view to be added to this window.
	 */
	public static void add(View v) {
		views.add(v);
	}

	/**
	 * Remove a view from the window.
	 *
	 * @param	view	The view to be removed from the window.
	 */
	public static void removeView(View view) {
		views.remove(view);
	}

}
