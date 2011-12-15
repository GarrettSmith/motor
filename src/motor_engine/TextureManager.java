package motor_engine;
import motor_engine.components.Sprite;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import java.util.HashMap;
import java.io.*;

/**
 * Texture managing system.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class TextureManager {
	// texture hash map
	private static HashMap<String, Texture> textureMap = new HashMap<String, Texture>();

	/**
	 *
	 */
	public static Texture get(String path) {
		return textureMap.get(path);
	}

	/**
	 *
	 */
	public static boolean has(String path) {
		return textureMap.containsKey(path);
	}

	/**
	 *
	 */
	public static void add(String path) {
		textureMap.put(path, null);
	}

	/**
	 *
	 */
	public static void remove(String path) {
		textureMap.get(path).release();
		textureMap.remove(path);
	}

	/**
	 *
	 */
	public static boolean isLoaded(String path) {
		return (textureMap.get(path) != null);
	}

	/**
	 *
	 */
	public static void load(String path) {
		try {
			textureMap.put(path, TextureLoader.getTexture("PNG", new FileInputStream(path)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
