package motor_engine.example.bricks;
import motor_engine.*;
import motor_engine.input.*;
import motor_engine.components.*;
import motor_engine.rules.*;
import motor_engine.util.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import motor_engine.render.*;

public class ExampleGame extends MotorEngine {

	public ExampleGame() {
		super (750, 900);
	}

	public void begin() {
		console.setActive(true);
		//console.setSize(window.getWidth(), window.getHeight()/2);

		//Entities
		Entity paddle = new Entity("Paddle");
		paddle.add(new Position(0, -65));
		paddle.add(new Velocity());
		paddle.add(new Acceleration());
		paddle.add(new Sprite("motor_engine/example/bricks/assets/paddle.png"));
		paddle.setActive(true);

		Entity ball = new Entity("Ball");
		ball.add(new Position());
		ball.add(new Velocity(Math.random()*50-25, -50));
		ball.add(new Sprite("motor_engine/example/bricks/assets/ball.png"));
		ball.setActive(true);

		Group blocks = new Group();

		for (int i = 0; i < 20; i++) {
			Entity e  = new Entity("block");
			e.add(new Position(Math.random()*240-120, Math.random()*60));
			e.add(new Tint(Math.random()+0.25,Math.random()+0.25,Math.random()+0.25));
			e.setActive(true);
			blocks.add(e);
		}

		blocks.add(new Sprite("motor_engine/example/bricks/assets/block.png"));
		blocks.add(new Velocity(0,-0.25));

		Entity bg = new Entity("background");
		bg.add(new Position(0,0));
		bg.add(new ZOrder(1));
		bg.add(new Sprite("motor_engine/example/bricks/assets/bg.png"));
		bg.add(new Tint(Math.random()+0.25,Math.random()+0.25,Math.random()+0.25));
		bg.setActive(true);

		Entity fg = new Entity("foreground");
		fg.add(new Position(0,0));
		fg.add(new ZOrder(0.99));
		fg.add(new Sprite("motor_engine/example/bricks/assets/fg.png"));
		fg.add(new Tint(Math.random(),Math.random(),Math.random()));
		fg.add(new Transparency(0.3));
		fg.setActive(true);

		Entity frame = new Entity("frame");
		frame.add(new Position(0,0));
		frame.add(new ZOrder(-1));
		frame.add(new Sprite("motor_engine/example/bricks/assets/frame.png"));
		frame.add(new Tint(Math.random()+0.5,Math.random()+0.5,Math.random()+0.5));
		frame.setActive(true);

		Entity cont = new Entity("Container");
		cont.add(new Size(236, 155));
		cont.add(new Position(0,-10));
		cont.setActive(true);

		// camera
		Entity camera = new Entity("Test Camera");
		camera.add(new Position(0,0));
		camera.add(new Size(250, 150));
		camera.setActive(true);

		// ball camera
		Entity bcamera = new Entity("Test Camera");
		bcamera.add(ball.get(Position.class));
		bcamera.add(new Size(250, 150));
		bcamera.setActive(true);

		View v = new View(0,450,0,750,450);
		v.setCamera(camera);
		DisplayManager.add(v);

		View v2 = new View(0,0,-1,750,450);
		v2.setCamera(camera);
		DisplayManager.add(v2);
		JBox2dDebugRenderer jdb = new JBox2dDebugRenderer();
		v2.setRenderer(jdb);

		//physics rule
		Physics p = new Physics();
		p.setDebugDraw(jdb);

		State testState = new State(250,150);
		testState.add(paddle);
		testState.add(ball);
		testState.add(blocks);
		testState.add(bg);
		testState.add(fg);
		testState.add(frame);
		testState.add(cont);
		testState.add(camera);
		testState.add(bcamera);
		testState.add(new PaddleControl(paddle));
		testState.add(new Constrain(cont));
		testState.add(new BallBounce());
		testState.add(new Lives());
		testState.add(p);

		StateManager.add("Test World", testState);
		StateManager.enable("Test World");
	}

	public void end() {
		console.log("Exiting!");
		System.out.println("Exiting!");
	}

	public static void main (String[] args) {
		new ExampleGame();
	}

}
