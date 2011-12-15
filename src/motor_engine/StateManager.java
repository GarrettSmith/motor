package motor_engine;
import java.util.*;

/**
 * A map of Names corresponding to States.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public class StateManager {

	// A hashmap of States corresponding to their names
	private static HashMap<String, State> States = new HashMap<String, State>();

	/**
	 *	Update all active States in the StateManager.
	 *
	 * @param	deltaTime	The elapsed time in nanoseconds since the last update.
	 *
	 * @see	State
	 */
	public static void update (long deltaTime) {
		for (State s : States.values()) {
			if (s.isActive()) {
				s.update(deltaTime);
			}
		}
	}

	/**
	 * Add a State to the StateManager and exectues its begin method.
	 *
	 * @param	name	The name of the State to add.
	 * @param	s		The State to add to the list of States.
	 */
	public static void add (String name, State s) {
		States.put(name, s);
		s.begin();
	}

	/**
	 * Remove a State from the StateManager and exectues its end method.
	 *
	 * @param	name	The State to be removed from the list of States.
	 */
	public static void remove (String name) {
		States.get(name).end();
		States.remove(name);
	}

	/**
	 * Access a State from the StateManager.
	 *
	 * @param	name	The name of the State to access.
	 */
	public static State get(String name) {
		return States.get(name);
	}

	/**
	 * Disable the given State.
	 *
	 * @param	name	The name of the State to disable.
	 */
	public static void disable(String name) {
		States.get(name).setActive(false);
	}

	/**
	 * Enable the given State.
	 *
	 * @param	name	The name of the State to enable.
	 */
	public static void enable(String name) {
		States.get(name).setActive(true);
	}

}
