package motor_engine.util;
import java.util.*;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import motor_engine.*;

/**
 * The console used to access logged information about your game.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class Console {

	// The arraylist of messages
	private ArrayList<String> messages;

	//console fps counter
	private Timer second;
	private int frames;
	private int consoleFps;

	//Activity
	private boolean active;

	//Visibility
	private boolean visible;

	// if the console has been changed since the last update
	private boolean changed;

	//current fps
	private int fps;

	/**
	 * Create a new Console.
	 */
	public Console() {
		messages = new ArrayList<String>();
		second = new Timer(true, 1);
		second.start();
	}

	/**
	 * Updates the console and redraws it if any changes were made.
	 *
	 * @param	deltaTime	The time in milliseconds since the lastUpdate.
	 */
	public void update(long deltaTime) {
		if (isChanged()) {
			//  draw
			setChanged(false);
		}
	}

	/**
	 * Enable or disable the Console.
	 *
	 * @param	b	True enables the Console, false disables it.
	 */
	public void setActive(boolean b) {
		active = b;
	}

	/**
	 * Check whether the Console is enabled.
	 *
	 * @return	True if the Console is enabled, false otherwise.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Check whether the Console has been changed since the last update.
	 *
	 * @return	True if the Console has changed, false otherwise.
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * mark the console as changed.
	 */
	private void setChanged(boolean b) {
		changed = b;
	}

	//Visiblity
	/**
	 *
	 */
	public void setVisible(boolean vis) {
		visible = vis;
	}
	/**
	 * Toggle the visibility of the Console.
	 */
	public void toggle() {
		if (isActive())
			setVisible(!isVisible());
	}

	public boolean isVisible() {
		return visible;
	}

	//Logging
	/**
	 * Logs an event to the Console.
	 *
	 * @param	msg		The message to be logged to the console.
	 */
	public void log(String msg) {
		//messages.add(MotorEngine.LIBRARY + " @ " + MotorEngine.getElapsedTime() + ": " + msg);
		setChanged(true);
	}

	/**
	 * Logs an int value to the Console.
	 *
	 * @param	i	The int to log.
	 */
	public void log(int i) {
		log(Integer.toString(i));
	}

	/**
	 * Logs a boolean value to the Console.
	 *
	 * @param	b	The boolean to log.
	 */
	public void log(boolean b) {
		log(Boolean.toString(b));
	}

	/**
	 * Logs a long value to the Console.
	 *
	 * @param	l	The long to log.
	 */
	public void log(long l) {
		log(Long.toString(l));
	}

	/**
	 * Logs a double value to the Console.
	 *
	 * @param	d	The double to log.
	 */
	public void log(double d) {
		log(Double.toString(d));
	}

	/**
	 * Logs an empty line to the Console.
	 */
	public void log() {
		log("");
	}

	/**
	 * Logs an object to the Console.
	 *
	 * @param	o	The object to log.
	 */
	public void log(Object o) {
		log(o.toString());
	}

	/**
	 * Logs a Component to the Console.
	 *
	 * @param	c	The Component to log.
	 */
	public void log(Component c) {
		// get the owning entity name
		String eName = c.getParent().getName();
		// get the type of this component without the package info
		String cName = c.getClass().getName();
		cName = cName.substring(cName.lastIndexOf('.')+1);
		log(eName + "." + cName + ": " + c.toString());
	}

	/**
	 * Logs an fps value to the Console.
	 *
	 * @param	fps	The current FPS to log.
	 */
	public void logFPS(int fps) {
		this.fps = fps;
	}

}
