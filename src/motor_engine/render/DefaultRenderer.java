package motor_engine.render;
import motor_engine.*;
import motor_engine.components.*;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;

/**
 * Default RenderingRule.
 * Pretty standard no pre or post processing just Renders Entities that are within the camera.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class DefaultRenderer implements Renderer {

	public void render (Entity camera) {

		// set openGL settings
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		// check if the camera is valid
		if (cameraIsValid(camera)) {

			// order Entities by z position
			TreeMap<Double, ArrayList<Entity>> valid = new TreeMap<Double, ArrayList<Entity>>();
			// Check against each entity in the state
			for (Entity e : camera.getOwner().getEntities()) {
				// check if the entity  is a valid candidate to render
				if (entityIsValid(e)) {
					ZOrder z = (ZOrder) e.get(ZOrder.class);
					if (z != null) {
						// create a new list if this is the first Entity with this z-order
						if (!valid.containsKey(z.get())) {
							valid.put(z.get(), new ArrayList<Entity>());
						}
						//add entity to list
						valid.get(z.get()).add(e);
					}
					else {
						// create a new list if this is the first Entity with this z-order
						if (!valid.containsKey(0.0)) {
							valid.put(0.0, new ArrayList<Entity>());
						}
						//add entity to list
						valid.get(0.0).add(e);
					}
				}
			}

			//calculate camera transform
			transformCamera(camera);

			// create a display list of the current scene
			int renderList = glGenLists(valid.size());
			glNewList(renderList, GL_COMPILE);
				for (Double key : valid.descendingKeySet()) {
					for (Entity e : valid.get(key)) {
						// save the camera matrix
						glPushMatrix();
						// calculate the entity
						renderEntity(camera, e);
						//restore the camera matrix
						glPopMatrix();
					}
				}
			glEndList();

			// call the list
			glCallList(renderList);

			//delete the list
			glDeleteLists(renderList, 1);
		}

		//unset gl
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
	}

	/**
	 * Check if the given Entity is a valid camera.
	 * A valid camera has a Position, Size, and is active.
	 *
	 * @param	e	The Entity to check.
	 * @return		True if the Entity is valid, false if it is not.
	 */
	protected static boolean cameraIsValid(Entity e) {
		return ( e.isActive() && e.has(Position.class) && e.has(Size.class) && e.isOwned() );
	}

	/**
	 * Check if the given entity can be rendered.
	 * An Entity can be rendered if it has a Position, a loaded Sprite, and is active.
	 *
	 * @param	e	The Entity to check.
	 * @return		True if the Entity is valid, false if it is not.
	 */
	protected static boolean entityIsValid(Entity e) {
		boolean valid = false;
		if (e.has(Sprite.class)) {
			Sprite s = (Sprite) e.get(Sprite.class);
			valid = ( e.isActive() && e.has(Position.class) && s.isLoaded());
		}
		return valid;
	}

	/**
	 *
	 */
	public void renderEntity(Entity camera, Entity e) {
		Position cp = (Position) camera.get(Position.class);
		Size cs = (Size) camera.get(Size.class);
		Position ep = (Position) e.get(Position.class);
		Sprite es = (Sprite) e.get(Sprite.class);

		// translate
		double x = ep.getX() - (cp.getX() - cs.getHalfWidth());
		double y = ep.getY() - (cp.getY() - cs.getHalfHeight());

		glTranslated(x,y,0);

		// get sprite sizes
		double halfWidth = es.getHalfWidth();
		double halfHeight = es.getHalfHeight();
		double scaleX = es.getScaleX();
		double scaleY = es.getScaleY();

		// Rotate
		if (e.has(Rotation.class)) {
			Rotation r = (Rotation)e.get(Rotation.class);
			glRotatef((float)r.get(), 0.0f, 0.0f, 1.0f);
		}

		// get scale from size
		if (e.has(Size.class)) {
			Size s = (Size) e.get(Size.class);
			glScaled(s.getHalfWidth()/halfWidth, s.getHalfHeight()/halfHeight, 0);
		}

		double[] tint = {1, 1, 1, 1};

		//tint
		if (e.has(Tint.class)) {
			Tint t = (Tint) e.get(Tint.class);
			tint[0] = t.getR();
			tint[1] = t.getG();
			tint[2] = t.getB();
		}

		//opacity
		if (e.has(Transparency.class)) {
			Transparency t = (Transparency) e.get(Transparency.class);
			tint[3] = t.get();
		}

		// sent tint and alpha
		glColor4d(tint[0], tint[1], tint[2], tint[3]);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// bind sprite texture
		es.getSprite().bind();

		//draw quad
		glBegin(GL_QUADS);
			// bottom left
			glTexCoord2d(scaleX, 0);
			glVertex2d(halfWidth, halfHeight);
			// bottom right
			glTexCoord2d(0, 0);
			glVertex2d(-halfWidth, halfHeight);
			// top right
			glTexCoord2d(0, scaleY);
			glVertex2d(-halfWidth, -halfHeight);
			// top left
			glTexCoord2d(scaleX, scaleY);
			glVertex2d(halfWidth, -halfHeight);
		glEnd();

	}

	protected void transformCamera(Entity camera) {
		Position cp = (Position) camera.get(Position.class);
		Size cs = (Size) camera.get(Size.class);

		// Reset view
		glLoadIdentity();

		// rotate camera
		if (camera.has(Rotation.class)) {
			Rotation r = (Rotation) camera.get(Rotation.class);
			glTranslated(cs.getHalfWidth(), cs.getHalfHeight(), 0.0);
			glRotatef((float) r.get(), 0, 0, 1);
			glTranslated(-cs.getHalfWidth(), -cs.getHalfHeight(), 0.0);
		}
	}

}
