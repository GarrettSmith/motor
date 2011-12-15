package motor_engine.util;
import motor_engine.Entity;
import motor_engine.components.Position;
import motor_engine.components.Size;
import motor_engine.components.Sprite;
import motor_engine.util.vector.*;
import motor_engine.util.shape.*;

/**
 * Utility class provides helper methods for checking collisions.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Collision {

	/**
	 * Check whether two Entities have collided. To preform this check both Entities must have a Position and either a Size or a Sprite.
	 *
	 * @param	e1	The first Entity to be checked.
	 * @param	e2	The second Entity to be checked.
	 *
	 * @return		True if the two Entities collide, false otherwise.
	 */
	public static boolean check(Entity e1, Entity e2) {
		return createRectangle(e1).intersects(createRectangle(e2));
	}

	public static ReadableVector2d getVector(Entity e1, Entity e2) {
		return createRectangle(e1).intersection(createRectangle(e2));
	}

	public static Boolean checkTop(Entity e1, Entity e2) {
		return createRectangle(e1).intersectsTop(createRectangle(e2));
	}

	public static Boolean checkBottom(Entity e1, Entity e2) {
		return createRectangle(e1).intersectsBottom(createRectangle(e2));
	}

	public static Boolean checkLeft(Entity e1, Entity e2) {
		return createRectangle(e1).intersectsLeft(createRectangle(e2));
	}

	public static Boolean checkRight(Entity e1, Entity e2) {
		return createRectangle(e1).intersectsRight(createRectangle(e2));
	}

	public static Rectangle createRectangle(Entity e) {
		Rectangle r = new Rectangle();
		if (e.has(Position.class)) {
			if (e.has(Size.class)) {
				Position p = (Position) e.get(Position.class);
				Size s = (Size) e.get(Size.class);
				r.setBounds(p.getX(), p.getY(), s.getX(), s.getY());
			}
			else if (e.has(Sprite.class)) {
				Position p = (Position) e.get(Position.class);
				Sprite s = (Sprite) e.get(Sprite.class);
				r.setBounds(p.getX(), p.getY(), s.getWidth(), s.getHeight());
			}
		}
		return r;
	}
}
