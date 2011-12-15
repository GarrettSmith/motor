package motor_engine.render;
import motor_engine.Entity;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import static org.lwjgl.opengl.GL11.*;
import motor_engine.*;
import motor_engine.components.*;
import java.util.ArrayList;

/**
 * Used by a View to debug render a camera with jbox2d.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class JBox2dDebugRenderer extends DebugDraw implements Renderer {

	private ArrayList<Integer> renderLists = new  ArrayList<Integer>();

	/**
	 * Renders the state using the given camera.
	 *
	 * @param	camera	The camera Entity to be used to render.
	 */
	public void render(Entity camera) {

		// transform camera
		if (cameraIsValid(camera)) {
			transformCamera(camera);
		}

		// render everything
		glPushAttrib(GL_POLYGON_BIT);
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		for (Integer i : renderLists) {
			glCallList(i);
		}
		glPopAttrib();

		//delete the list
		glDeleteLists(renderLists.get(0), renderLists.size());

		// clear render list
		renderLists.clear();

	}

	protected void transformCamera(Entity camera) {
		Position cp = (Position) camera.get(Position.class);
		Size cs = (Size) camera.get(Size.class);

		// Reset view
		glLoadIdentity();

		//translate
		glTranslated(-cp.getX()+cs.getHalfWidth(), -cp.getY()+cs.getHalfHeight(), 0);

		// rotate camera
		if (camera.has(Rotation.class)) {
			Rotation r = (Rotation) camera.get(Rotation.class);
			glTranslated(cs.getHalfWidth(), cs.getHalfHeight(), 0.0);
			glRotatef((float) r.get(), 0, 0, 1);
			glTranslated(-cs.getHalfWidth(), -cs.getHalfHeight(), 0.0);
		}
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

	// debug draw methods
	public void drawCircle(Vec2 center, float radius, Color3f color) {

		setGlColor(color);
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_TRIANGLE_FAN);
			glVertex2f(center.x, center.y);
			glVertex2f(center.x + radius, center.y);
			glVertex2f(center.x, center.y - radius);
			glVertex2f(center.x - radius, center.y);
			glVertex2f(center.x, center.y + radius);
		glEnd();
	}

	public void drawPoint(Vec2 position, float f, Color3f color) {
		glPushAttrib(GL_POLYGON_BIT);
		setGlColor(color);
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_POINTS);
			glVertex2f(position.x, position.y);
		glEnd();
		glPopAttrib();
	}

	public void drawPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
		// get the list index
		int list = glGenLists(1);
		// create a renderlist for this draw command
		glNewList(list, GL_COMPILE);
			setGlColor(color);
			glBegin(GL_POLYGON);
				for (int i = 0; i < vertices.length; i++) {
					glVertex2f(vertices[i].x, vertices[i].y);
				}
			glEnd();
		glEndList();
		// keep track of the lists
		renderLists.add(list);
	}

	public void drawSegment(Vec2 p1, Vec2 p2, Color3f color) {
		glPushAttrib(GL_POLYGON_BIT);
		setGlColor(color);
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_LINES);
			glVertex2f(p1.x, p1.y);
			glVertex2f(p2.x, p2.y);
		glEnd();
		glPopAttrib();
	}

	public void drawSolidCircle(Vec2 center, float radius, Vec2 axis, Color3f color) {
		glPushAttrib(GL_POLYGON_BIT);
		System.out.println("draw solid circle");
		setGlColor(color);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		glBegin(GL_TRIANGLE_FAN);
			glVertex2f(center.x, center.y);
			glVertex2f(center.x + radius, center.y);
			glVertex2f(center.x, center.y - radius);
			glVertex2f(center.x - radius, center.y);
			glVertex2f(center.x, center.y + radius);
		glEnd();
		glPopAttrib();
	}

	public void drawSolidPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
		drawPolygon(vertices, vertexCount, color);
	}

	public void drawString(float x, float y, java.lang.String s, Color3f color) {
		System.out.println(s);
	}

	public void drawXForm(XForm xf) {
		//glLoadIdentity();
		Vec2 position = xf.position;
		//glTranslatef(position.x, position.y,0);
		//Mat22 r = xf.R;

	}

	public void setCamera(float x, float y, float scale) {
		glTranslatef(x,y,0);
		glScalef(scale,scale,0);
	}

	public void setGlColor(Color3f color) {
		glColor3f(color.x, color.y, color.z);
	}
}
