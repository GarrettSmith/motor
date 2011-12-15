package motor_engine;
import motor_engine.components.*;
import java.util.*;
import motor_engine.util.shape.Dimension;

/**
 * A State is a container of entities that can interact with one another.
 *
 * @author Garrett Smith
 * @version 0.1
 */
public class State extends Dimension implements LifeCycle {

	// Whether the State is currently active or not
	private boolean	active = false;

	// The list containing all the entities in the State.
	private HashSet<Group> groups;

	// The default Group
	private Group defaultGroup;

	// The set of rules
	private HashSet<Rule> rules;

	/**
	 * Default Constructor.
	 */
	public State() {
		super();
		groups = new HashSet<Group>();
		defaultGroup = new Group();
		groups.add(defaultGroup);
		rules = new HashSet<Rule>();
	}

	/**
	 *
	 */
	public State(int w, int h) {
		this();
		setDimension(w,h);
	}

	//ACTIVITY
	/**
	 *	Return whether the State is currently active or paused.
	 *
	 * @return	A boolean stating whether the State is currently active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 *	Set the State to active or paused.
	 *
	 * @param	active	The active state of the State.
	 */
	public void setActive (boolean active) {
		this.active = active;
	}

	//ENTITIES
	/**
	 *	Add an Entity to the default set.
	 *
	 * @param	e	The Entity to be added.
	 */
	public void add(Entity e) {
		e.setOwner(this);
		defaultGroup.add(e);
	}

	/**
	 *	Adds an array of Entities.
	 *
	 * @param	es	The array of Entities to add to the State.
	 */
	public void add(Entity[] es) {
		defaultGroup.add(es);
	}

	/**
	 *	Remove an Entity from the default set.
	 *
	 * @param	e	The Entity to be removed.
	 */
	public void remove(Entity e) {
		defaultGroup.remove(e);
	}

	/**
	 *	Removes an array of Entities from the State.
	 *
	 * @param	es	The array of Entities to remove from the State.
	 */
	public void remove(Entity[] es) {
		defaultGroup.remove(es);
	}

	//ENTITY SETS
	/**
	 *	Adds a Group to the State.
	 *
	 * @param	g	The Group to add to the State.
	 */
	public void add(Group g) {
		groups.add(g);
	}

	/**
	 *	Removes the Group from the State.
	 *
	 * @param	g	The Group to be removed.
	 */
	public void remove(Group g) {
		groups.remove(g);
	}

	//Entity Access
	/**
	 * Counts the number of Entities in this State.
	 *
	 * @return	The number of Entity objects in all groups belonging to this State.
	 */
	public int countEntities() {
		int count = 0;
		for (Group set : groups) {
			count += set.size();
		}
		return count;
	}

	/**
	 * Counts the number of Entities of a given type in this State.
	 *
	 * @param	type	The type of Entities.
	 * @return			The number of Entity objects in all groups of the given type.
	 */
	public int countEntities(String type) {
		int count = 0;
		for (Group set : groups) {
			count += set.count(type);
		}
		return count;
	}

	/**
	 *	Access all the Entities contained in this State.
	 *
	 *
	 * @return	An Entity Array containing every Entity in the groups of this State.
	 */
	public Entity[] getEntities() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (Group set : groups) {
			Collections.addAll(entities, set.getEntities());
		}
		return entities.toArray(new Entity[] {});
	}

	/**
	 *	Access all the Entities of a given type contained in this State.
	 *
	 * @param	type	The type of Entity
	 * @return			An Entity Array containing every Entity of the given type in the groups of this State.
	 */
	public Entity[] getEntities(String type) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (Group set : groups) {
			Collections.addAll(entities, set.getEntities(type));
		}
		return entities.toArray(new Entity[] {});
	}

	/**
	 * Get the Entity with the Matching name. (eg. mario#3)
	 *
	 * @param	name	The unique name of the Entity.
	 * @return			The matched Entity.
	 */
	public Entity getEntity(String name) {
		Entity matchedEntity = null;
		for (Group set : groups) {
			if (set.containsEntity(name)) {
				matchedEntity = set.getEntity(name);
			}
		}
		return matchedEntity;
	}

	// RULES
	/**
	 * Adds a rule to this State.
	 *
	 * @param	r	The rule to be added to the State.
	 */
	public void add(Rule r) {
		r.setOwner(this);
		rules.add(r);
	}

	/**
	 * Removes a rule form the State.
	 *
	 * @param	r	 The rule to be removed from the State.
	 */
	public void remove(Rule r) {
		rules.remove(r);
	}

	/**
	 *	Updates all Rules and Entites in the State. This will do nothing if the State is disabled.
	 *
	 * @param	elapsedTime	The time in milliseconds since the last update.
	 */
	public void update(long elapsedTime) {
		for (Rule r : rules) {
			if (r.isActive())
				r.update(elapsedTime);
		}
		for (Group g: groups) {
			g.update(elapsedTime);
		}
	}

	/**
	 *	Begins all Rules and groups within this State.
	 */
	public void begin() {
		for (Group es : groups) {
			es.begin();
		}
		for (Rule r : rules.toArray(new Rule[] {})) {
			r.begin();
		}
	}

	/**
	 *	Ends all Rules and groups in this State.
	 */
	public void end() {
		for (Rule r : rules.toArray(new Rule[] {})) {
			r.end();
		}
		for (Group e : groups) {
			e.end();
		}
		rules.clear();
		groups.clear();
	}
}
