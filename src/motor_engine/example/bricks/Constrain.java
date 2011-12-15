package motor_engine.example.bricks;
import motor_engine.Rule;
import motor_engine.Entity;
import motor_engine.components.*;
import motor_engine.util.Collision;
import motor_engine.util.vector.*;

public class Constrain extends Rule {

	private Entity container;

	public Constrain(Entity e) {
		container = e;
	}

	public void begin() {
		setActive(true);
	}

	public void end() {

	}

	public void update(long deltaTime) {

		for (Entity paddle : getOwner().getEntities("Paddle")) {
			if (paddle.has(Position.class) && paddle.has(Sprite.class)) {
				Position p = (Position) paddle.get(Position.class);
				Sprite s = (Sprite) paddle.get(Sprite.class);

				if (!Collision.check(paddle, container)) {
					p.setX(-p.getX());
				}

			}
		}

	}

}
