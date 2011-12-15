package motor_engine.render;
import motor_engine.Entity;

/**
 * Used by a View to render a camera.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public interface Renderer {

	/**
	 * Renders the state using the given camera.
	 *
	 * @param	camera	The camera Entity to be used to render.
	 */
	public void render(Entity camera);

}
