package motor_engine.rules;
import motor_engine.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import motor_engine.components.*;
import motor_engine.render.JBox2dDebugRenderer;

/**
 * A default physics rule using JBox2D.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Physics extends Rule {
	//debug renderer
	private DebugDraw debugRenderer;

	// the physics world
	private World physicsWorld;
	private int iterations = 2;

	/**
	 * Create the Box2D world.
	 */
	public void begin() {
		setActive(true);

		// get world dimensions
		Vec2 min = new Vec2((float)-getOwner().getWidth()/2, (float)-getOwner().getHeight()/2);
		Vec2 max = new Vec2((float)getOwner().getWidth()/2, (float)getOwner().getHeight()/2);
		AABB bounds = new AABB(min, max);

		// create world
		physicsWorld = new World(bounds, new Vec2(0,-1), true);

		// debug drawing
		if (debugRenderer != null) {
			System.out.println("DEBUG RENDERING");
			physicsWorld.setDebugDraw(debugRenderer);
		}

		createEntities();
	}

	/**
	 * Destroy the Box2D world.
	 */
	public void end() {
		physicsWorld = null;
	}

	/**
	 * Update the physics world.
	 */
	public void update(long deltaTime) {
		physicsWorld.step((deltaTime/1000f), iterations);
	}

	/**
	 * Add a debug renderer.
	 */
	public void setDebugDraw(DebugDraw d) {
		debugRenderer = d;
		debugRenderer.setFlags(0);
		debugRenderer.appendFlags(DebugDraw.e_shapeBit);
		debugRenderer.appendFlags(DebugDraw.e_jointBit);
		debugRenderer.appendFlags(DebugDraw.e_coreShapeBit);
		debugRenderer.appendFlags(DebugDraw.e_aabbBit);
		debugRenderer.appendFlags(DebugDraw.e_obbBit);
		debugRenderer.appendFlags(DebugDraw.e_pairBit);
		debugRenderer.appendFlags(DebugDraw.e_centerOfMassBit);
	}

	public void createEntities() {

		for (Entity e: getOwner().getEntities()) {
			if (e.has(Position.class)) {
				Position p = (Position) e.get(Position.class);
				if (e.has(Sprite.class)) {
					Sprite s = (Sprite) e.get(Sprite.class);

					PolygonDef poly = new PolygonDef();
					poly.setAsBox((float)s.getHalfWidth(), (float)s.getHalfHeight());

					BodyDef bd = new BodyDef();
					bd.position.set((float)p.getX(), (float)p.getY());

					physicsWorld.createBody(bd).createShape(poly);
				}
				else if (e.has(Size.class)) {
					Size s = (Size) e.get(Size.class);

					PolygonDef poly = new PolygonDef();
					poly.setAsBox((float)s.getHalfWidth(), (float)s.getHalfHeight());

					BodyDef bd = new BodyDef();
					bd.position.set((float)p.getX(), (float)p.getY());

					physicsWorld.createBody(bd).createShape(poly);
				}
			}
		}
	}
}
