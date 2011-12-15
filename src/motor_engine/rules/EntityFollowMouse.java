package motor_engine.rules;
import motor_engine.*;
import motor_engine.input.*;
import motor_engine.components.*;
import java.awt.Cursor;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Use to set the position of an Entity to that of the mouse crusor.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class EntityFollowMouse extends Rule {

	// the entity to follow with
	private Entity follower;

	public EntityFollowMouse(Entity e) {
		setFollower(e);
	}

	/**
	 * Get the Entity that is currently following the mouse.
	 *
	 * @return	The Entity that is following the mouse.
	 */
	public Entity getFollower() {
		return follower;
	}

	/**
	 * Set the Entity to follow the mouse.
	 *
	 * @param	e	The Entity to follow the mouse.
	 */
	public void setFollower(Entity e) {
		follower = e;
	}

	public void begin() {
		if (follower.has(Position.class)) {
			Position p = (Position) follower.get(Position.class);
			p.set( (double) MouseManager.getX(), (double) MouseManager.getY());
		}
	}

	public void end() {

	}

	/**
	 * Moves the Following Entity to the position of the mouse.
	 */
	public void update(long deltaTime) {
		if (follower.has(Position.class)) {
			Position p = (Position) follower.get(Position.class);
			p.setX(MouseManager.getX() - Display.getDisplayMode().getWidth()/2);
			p.setY(MouseManager.getY() - Display.getDisplayMode().getHeight()/2);
		}
	}
}
