package motor_engine;
import motor_engine.components.Size;
import motor_engine.render.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * The component that the graphics are drawn to from a camera entity.
 *
 * @author	Garrett Smith
 * @version	0.1
 *
 */
public class View implements Comparable {

	// The camera linked to this View.
	private Entity camera;

	// The renderer useb by the View.
	private Renderer renderer;

	// Position
	private int x;
	private int y;
	private int z;

	// Size
	private int width;
	private int height;

	//visibility
	private boolean visible;

	/**
	 * Create a new View the size of the window.
	 * This also sets the renderer to a default renderer.
	 */
	public View() {
		setVisible(true);
		setSize(Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		setRenderer(new DefaultRenderer());

	}

	/**
	 * Creates a new View of the given size.
	 *
	 * @param	width	The width of the View.
	 * @param	height	The height of the View.
	 */
	public View(int width, int height) {
		this();
		setSize(width, height);
		centerLocation();

	}

	/**
	 * Creates a new View at the given coordinates and with the given size.
	 *
	 * @param	x		The x position of this View.
	 * @param	y		The y position of this View.
	 * @param	width	The width of the View.
	 * @param	height	The height of the View.
	 */
	public View(int x, int y, int width, int height) {
		this(width, height);
		setLocation(x, y);
	}

	/**
	 * Creates a new View at the given coordinates and with the given size.
	 *
	 * @param	x		The x position of this View.
	 * @param	y		The y position of this View.
	 * @param	z		The z-order of the View.
	 * @param	width	The width of the View.
	 * @param	height	The height of the View.
	 */
	public View(int x, int y, int z, int width, int height) {
		this(x, y, width, height);
		setZ(z);
	}

	/**
	 * Get the x position of this View.
	 *
	 * @return	The current x position of this View relative to the bottom left corner of the window.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y position of this View.
	 *
	 * @return	The current y position of this View relative to the bottom left corner of the window.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the z-order of this View. This is used to layer the Views.
	 *
	 * @return	The current z-order of this View.
	 */
	public int getZ() {
		return z;
	}

	// compare views by zorder
	public int compareTo(Object anotherView) throws ClassCastException {
		if (!(anotherView instanceof View))
			throw new ClassCastException("A View object expected.");
		View v = (View) anotherView;
		return -(new Integer(this.getZ()).compareTo(new Integer(v.getZ())));
	}

	/**
	 * Set the location of this View.
	 *
	 * @param	x		The x position of this View.
	 * @param	y		The y position of this View.
	 */
	public void setLocation(int x, int y) {
		setX(x);
		setY(y);
	}

	/**
	 * Set the location and z-order of this View.
	 *
	 * @param	x		The x position of this View.
	 * @param	y		The y position of this View.
	 * @param	z		The z-order of this View.
	 */
	public void setLocation(int x, int y, int z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * Center the View in the window.
	 */
	public void centerLocation() {
		setLocation(Display.getDisplayMode().getWidth()/2 - width/2, Display.getDisplayMode().getHeight()/2 - height/2);
	}

	/**
	 * set the x position of this View.
	 *
	 * @param	x		The x position of this View.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * set the y position of this View.
	 *
	 * @param	y		The y position of this View.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Set the z-order of this View. Use this to order the layering of Views.
	 *
	 * @param	z	The z-order of this View.
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * Get the width of this View.
	 *
	 * @return	The width of the View.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of this View.
	 *
	 * @return	The height of the View.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the size of this View.
	 *
	 * @param	w	The width of the View.
	 * @param	h	The height of the View.
	 */
	public void setSize(int w, int h) {
		setWidth(w);
		setHeight(h);
	}

	/**
	 * Set the width of this View.
	 *
	 * @param	w	The width of the View.
	 */
	public void setWidth(int w) {
		width = w;
	}

	/**
	 * Set the height of this View.
	 *
	 * @param	h	The height of the View.
	 */
	public void setHeight(int h) {
		height = h;
	}

	/**
	 * Check if the View is currently visible.
	 *
	 * @return	True if the View is visible, flase otherwise.
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Set the current visibility of this View.
	 *
	 * @param	v	Whether this View should be visible or not.
	 */
	public void setVisible(boolean v) {
		visible = v;
	}

	/**
	 * Returns the camera Entity linked to this View.
	 *
	 * @return	The linked camera Entity.
	 */
	public Entity getCamera () {
		return camera;
	}

	/**
	 * Sets the camera to the given Entity.
	 *
	 * @param	e	The camera Entity to link to this View.
	 */
	public void setCamera (Entity e) {
		camera = e;
	}

	/**
	 * Get the RenderRule used by this View.
	 *
	 * @return	The Renderer used by this View.
	 */
	public Renderer getRenderer() {
		return renderer;
	}

	/**
	 * Set the Renderer to be used by this View.
	 *
	 * @param	r	The RenderRule to be used.
	 */
	public void setRenderer(Renderer r) {
		renderer = r;
	}

	/**
	 * Renders and resizes this View.
	 */
	public void update() {
		updateViewportSize();
		updateGLSize();
		renderer.render(camera);
	}

	// update the gl coordinates
	private void updateGLSize() {
		if (camera.has(Size.class)) {
			//modify to check for change
			Size cs = (Size) camera.get(Size.class);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, cs.getX(), 0, cs.getY(), -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
		}
	}

	// update the viewport size
	private void updateViewportSize() {
		glViewport(x, y, width, height);
	}

}
