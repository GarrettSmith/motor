package motor_engine.example.bricks;
import motor_engine.*;
import motor_engine.util.vector.*;
import motor_engine.util.Collision;
import motor_engine.components.*;

public class Lives extends Rule {

	private int lives = 3;

	public void begin() {
		setActive(true);

	}

	public void end() {

	}

	public void update(long deltaTime) {
		for (Entity ball : getOwner().getEntities("Ball")) {
			for (Entity cont : getOwner().getEntities("Container")) {
				if (Collision.checkBottom(cont, ball)) {
					ball.setVector2d(Position.class, new Vector2d(0,0));
					ball.setVector2d(Velocity.class, new Vector2d(Math.random()*50-25, -50));
					lives--;
					if (lives <= 0) {
						System.out.println("GAME OVER");
						MotorEngine.exit();
					}
				}
			}
		}
	}
}
