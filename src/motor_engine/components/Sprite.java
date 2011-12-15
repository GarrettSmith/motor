package motor_engine.components;
import motor_engine.*;
import java.io.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * A component that allows entities to have a sprite image.
 *
 * @author Garrett Smith
 * @version 0.1
 */
public class Sprite extends Component {

	// the path to the sprite
	private String path;

	/**
	 * Create an empty sprite.
	 */
	public Sprite() {
		setPriority(0);
	}

	/**
	 * Create a sprite loaded from the given file.
	 *
	 * @param	fileName	The path to the sprite image.
	 */
	public Sprite(String fileName) {
		this();
		setPath(fileName);
	}

	/**
	 * Create a sprite loaded from the given file.
	 *
	 * @param	s	The sprite component to copy.
	 */
	public Sprite(Sprite s) {
		this(s.getPath());
	}

	public Component clone() {
		return	new Sprite(this);
	}

	public void update(long deltaTime) {

	}

	public void begin() {
		if (!TextureManager.isLoaded(path)) {
			TextureManager.load(path);
		}
	}

	public void end() {

	}

	public String toString() {
		return (TextureManager.get(path).toString());
	}

	/**
	 * Get the path to this sprite's image.
	 *
	 * @return	The sprite image path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path to this sprite's image.
	 *
	 * @param	fileName	The sprite image path.
	 */
	public void setPath(String fileName) {
		path = fileName;
	}

	/**
	 * Reload the sprite image.
	 */
	public void reload() {
		TextureManager.load(path);
	}

	/**
	 *
	 */
	public Texture getSprite() {
		return TextureManager.get(path);
	}

	/**
	 *
	 */
	public boolean isLoaded() {
		return TextureManager.isLoaded(path);
	}

	/**
	 * Get the width of the sprite.
	 *
	 * @return	The width of the sprite, or 0 if there is no sprite loaded.
	 */
	public double getWidth() {
		double width = 0;
		if (isLoaded()) {
			width = TextureManager.get(path).getImageWidth();
		}
		return width;
	}

	/**
	 * Get the height of the sprite.
	 *
	 * @return	The height of the sprite, or 0 if there is no sprite loaded.
	 */
	public double getHeight() {
		double height = 0;
		if (isLoaded()) {
			height = TextureManager.get(path).getImageHeight();
		}
		return height;
	}

	/**
	 * Get the half-width of the sprite.
	 *
	 * @return	The half-width of the sprite, or 0 if there is no sprite loaded.
	 */
	public double getHalfWidth() {
		return getWidth()/2.0;
	}

	/**
	 * Get the half-height of the sprite.
	 *
	 * @return	The half-height of the sprite, or 0 if there is no sprite loaded.
	 */
	public double getHalfHeight() {
		return getHeight()/2.0;
	}

	/**
	 *
	 */
	public double getScaleX() {
		return (double) TextureManager.get(path).getImageWidth() / TextureManager.get(path).getTextureWidth();
	}

	/**
	 *
	 */
	public double getScaleY() {
		return (double) TextureManager.get(path).getImageHeight() / TextureManager.get(path).getTextureHeight();
	}
 }
