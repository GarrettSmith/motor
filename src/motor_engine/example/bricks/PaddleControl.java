package motor_engine.example.bricks;
import motor_engine.Rule;
import motor_engine.Entity;
import motor_engine.rules.*;
import motor_engine.components.*;
import motor_engine.input.*;
import org.lwjgl.input.Keyboard;
import motor_engine.util.Timer;
import motor_engine.util.vector.*;

public class PaddleControl extends Rule {

	private Entity paddle;

	private final double PADDLE_SPEED = 1000;
	private final double MAX_PADDLE_SPEED = 250;

	private Timer coolTimer;
	private Timer jumpTimer;
	private Timer fallTimer;
	private final double COOL_DOWN = .25;
	private final double JUMP_TIME = 0.1;
	private final double JUMP_SPEED = 200;
	private boolean jumpReady = true;

	public PaddleControl(Entity e) {
		paddle = e;
		setActive(true);
		coolTimer = new Timer(false, COOL_DOWN);
		jumpTimer = new Timer(false, JUMP_TIME);
		fallTimer = new Timer(false, JUMP_TIME);
	}

	public void begin() {
		InputManager.define("left", Keyboard.KEY_LEFT);
		InputManager.addControllerButton("left",0,3);
		InputManager.define("right", Keyboard.KEY_RIGHT);
		InputManager.addControllerButton("right",0,1);
		InputManager.define("jump", Keyboard.KEY_SPACE);
		InputManager.addControllerButton("jump",0,2);
	}

	public void end() {
		InputManager.release("left");
		InputManager.release("right");
		InputManager.release("jump");

	}

	public void update(long deltaTime) {

		//jumping
		if (InputManager.justPressed("jump") && jumpReady) {
			jumpPaddle();
		}

		if (coolTimer.fired()) {
			jumpReady = true;
		}

		if (jumpTimer.fired()) {
			fallPaddle();
		}

		if (fallTimer.fired()) {
			stopFall();
		}

		// accelerate paddle
		if (paddle.has(Acceleration.class)) {
			Acceleration a = (Acceleration) paddle.get(Acceleration.class);
			Velocity v = (Velocity) paddle.get(Velocity.class);

			a.setX(0);

			//a.add((ControllerManager.getAxisValue(0,5) * PADDLE_SPEED), 0);

			if (InputManager.pressed("left")) {
				a.add(-PADDLE_SPEED, 0);
			}

			if (InputManager.pressed("right")) {
				a.add(PADDLE_SPEED, 0);
			}

			if (((InputManager.released("right") && InputManager.released("left")) ||
				(InputManager.pressed("right") && InputManager.pressed("left"))) 
				//&& ControllerManager.getAxisValue(0,5) == 0 
				) {
				Velocity tmp = new Velocity(v);
				tmp.abs();
				tmp.subtract(15, 0);
				tmp.constrain(0, Double.MAX_VALUE);
				tmp.copySign(v);
				v.set(tmp);
			}
		}

		// limit velocity
		if (paddle.has(Velocity.class)) {
			Velocity v = (Velocity) paddle.get(Velocity.class);
			v.constrain(-MAX_PADDLE_SPEED, MAX_PADDLE_SPEED);
		}

		jumpTimer.update();
		coolTimer.update();
		fallTimer.update();
	}

	private void jumpPaddle() {
		paddle.addVector2d(Velocity.class, new Vector2d(0,JUMP_SPEED));
		jumpTimer.start();
		coolTimer.start();
		jumpReady = false;
	}

	private void fallPaddle() {
		paddle.addVector2d(Velocity.class, new Vector2d(0,JUMP_SPEED*-2));
		fallTimer.start();
	}

	private void stopFall() {
		Vector2d v = new Vector2d(paddle.getVector2d(Velocity.class));
		v.setY(0);
		paddle.setVector2d(Velocity.class, v);
	}

}
