package motor_engine.example.bricks;
import motor_engine.*;
import motor_engine.util.*;
import motor_engine.input.*;
import motor_engine.components.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import motor_engine.util.vector.Vector2d;

public class TestRule extends Rule {

	private Timer second;
	private int updateNo = 0;
	private Entity camera;
	private Entity player;

	public TestRule (Entity e, Entity p) {
		camera = e;
		player = p;
	}

	public void update(long deltaTime) {
		if (KeyboardManager.pressed(Keyboard.KEY_Q) || MouseManager.isScrolledUp()) {
			double zoom = 1.1;
			if (MouseManager.getDeltaWheel() != 0) {
				zoom = 1 + (MouseManager.getDeltaWheel() / 1000.0);
			}
			camera.multiplyVector2d(Size.class, zoom);
		}
		if (KeyboardManager.pressed(Keyboard.KEY_E) || MouseManager.isScrolledDown()) {
			double zoom = 0.9;
			if (MouseManager.getDeltaWheel() != 0) {
				zoom = 1 + (MouseManager.getDeltaWheel() / 1000.0);
			}
			camera.multiplyVector2d(Size.class, zoom);
		}

		if (KeyboardManager.pressed(Keyboard.KEY_A)) {
			camera.addVector2d(Position.class, new Vector2d(-2, 0));
		}
		if (KeyboardManager.pressed(Keyboard.KEY_D)) {
			camera.addVector2d(Position.class, new Vector2d(2, 0));
		}
		if (KeyboardManager.pressed(Keyboard.KEY_S)) {
			camera.addVector2d(Position.class, new Vector2d(0, -2));
		}
		if (KeyboardManager.pressed(Keyboard.KEY_W)) {
			camera.addVector2d(Position.class, new Vector2d(0, 2));
		}

		if (KeyboardManager.pressed(Keyboard.KEY_RIGHT)) {
			camera.addVector2d(Velocity.class, new Vector2d(2, 0));
		}
		if (KeyboardManager.pressed(Keyboard.KEY_LEFT)) {
			camera.addVector2d(Velocity.class, new Vector2d(-2, 0));
		}
		if (KeyboardManager.pressed(Keyboard.KEY_DOWN)) {
			camera.addVector2d(Velocity.class, new Vector2d(0, -2));
		}
		if (KeyboardManager.pressed(Keyboard.KEY_UP)) {
			camera.addVector2d(Velocity.class, new Vector2d(0, 2));
		}

		if (KeyboardManager.pressed(Keyboard.KEY_Z)) {
			camera.addValue(Rotation.class, 2);
		}
		if (KeyboardManager.pressed(Keyboard.KEY_X)) {
			camera.addValue(Rotation.class, -2);
		}

		/*for (Entity e : owner.getPeers(player)) {
			if (e.getType().equalsIgnoreCase("key") && e != player && Collision.check(player, e)) {
				MotorEngine.console.log("MAn DOwn!");
			}
		}*/

		System.out.println(camera.get(Position.class));

	}

	public void begin() {
		MotorEngine.console.log("TestRule: Started.");
		setActive(true);
	}

	public void end() {
	}
}
