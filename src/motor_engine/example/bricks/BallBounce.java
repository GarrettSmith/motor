package motor_engine.example.bricks;
import motor_engine.Rule;
import motor_engine.Entity;
import motor_engine.components.Velocity;
import motor_engine.util.Collision;
import motor_engine.util.vector.*;

public class BallBounce extends Rule {

	public void begin() {
		setActive(true);
	}

	public void end() {

	}

	public void update(long deltaTime) {

		for (Entity ball : getOwner().getEntities("ball")) {
			if (ball.has(Velocity.class)) {
				Velocity v = (Velocity) ball.get(Velocity.class);

				for (Entity paddle : getOwner().getEntities("paddle")) {
					if (Collision.checkTop(paddle, ball)) {
						v.setY(-v.getY());
						ball.multiplyVector2d(Velocity.class, 1.01);
						// add half of the paddle velocity
						if (paddle.has(Velocity.class)) {
							ball.addVector2d(Velocity.class, (Vectors.divide(paddle.getVector2d(Velocity.class), 4)));
						}
					}
					if (Collision.checkLeft(paddle, ball)) {
						v.setX(-Math.abs(v.getX()));
					}
					else if (Collision.checkRight(paddle, ball)) {
						v.setX(Math.abs(v.getX()));
					}
				}

				for (Entity block : getOwner().getEntities("block")) {
					if (Collision.check(ball, block) && block.isActive()) {

						if (Collision.checkTop(block, ball)) {
							v.setY(Math.abs(v.getY()));
						}
						else if (Collision.checkBottom(block, ball)) {
							v.setY(-Math.abs(v.getY()));
						}
						if (Collision.checkLeft(block, ball)) {
							v.setX(-Math.abs(v.getX()));
						}
						else if (Collision.checkRight(block, ball)) {
							v.setX(Math.abs(v.getX()));
						}

						ball.multiplyVector2d(Velocity.class, 1.01);
						block.setActive(false);
					}
				}

				for (Entity container : getOwner().getEntities("Container")) {
					if (Collision.checkTop(container, ball)) {
						v.setY(-v.getY());
					}

					if (Collision.checkLeft(container, ball) || Collision.checkRight(container, ball)) {
						v.setX(-v.getX());
					}
				}
			}
		}

	}

}
