package motor_engine;
import motor_engine.components.*;
import motor_engine.util.vector.*;
import java.util.*;

/**
 * A Group of Entities.
 *
 * @author	Garrett Smith
 * @version 0.1
 */
public class Group implements WriteableEntity {

	// The list containing all the entities in the Group.
	private HashSet<Entity> entities;

	/**
	 * Default Constructor.
	 */
	public Group() {
		entities = new HashSet<Entity>();
	}

	/**
	 * Counts the number of Entities in this Group of the specified type.
	 *
	 * @param	type	The type of Entity.
	 * @return			The number of the specified type of Entities in this Group.
	 */
	public int count(String type) {
		int count = 0;
		for (Entity e : entities) {
			if (e.getType().equalsIgnoreCase(type)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Get all Entities in this Group.
	 *
	 * @return	An Entity array containing all the entities in this Group.
	 */
	public Entity[] getEntities() {
		return entities.toArray(new Entity[] {});
	}

	/**
	 * Get all Entities of the given type in this Group.
	 *
	 * @param	type	The type of Entity.
	 * @return			An array of Entities of the given type.
	 */
	public Entity[] getEntities(String type) {
		Entity[] es = new Entity[count(type)];
		int i = 0;
		for (Entity e : entities) {
			if (e.getType().equalsIgnoreCase(type)) {
				es[i] = e;
				i++;
			}
		}
		return es;
	}

	/**
	 * Get the Entity with the Matching name. (eg. mario#3)
	 *
	 * @param	name	The unique name of the Entity.
	 * @return			The matched Entity.
	 */
	public Entity getEntity(String name) {
		Entity matchedEntity = null;
		for (Entity e : entities) {
			if (e.getName().equals(name))
				matchedEntity = e;
		}
		return matchedEntity;
	}

	/**
	 * Check if the Group contains the given Entity.
	 *
	 * @param	search	The Entity to check for.
	 */
	public boolean containsEntity(Entity search) {
		return containsEntity(search.getName());
	}

	/**
	 * Check if the Group contains the named Entity.
	 *
	 * @param	name	The name of the Entity to check for.
	 */
	public boolean containsEntity(String name) {
		boolean found = false;
		for (Entity e : entities) {
			if (e.getName().equalsIgnoreCase(name)) {
				found = true;
			}
		}
		return found;
	}

	/**
	 * Check if the Group contains the given type
	 *
	 * @param	type	The type of Entity to check for.
	 */
	public boolean containsType(String type) {
		boolean found = false;
		for (Entity e : entities) {
			if (e.getType().equalsIgnoreCase(type)) {
				found = true;
			}
		}
		return found;
	}

	/**
	 *	Adds an Entity to the Group.
	 *
	 * @param	e	The Entity to add to the State.
	 */
	public void add(Entity e) {
		entities.add(e);
	}

	/**
	 *	Adds an array of Entities to the Group.
	 *
	 * @param	es	The array of Entities to add to the Group.
	 */
	public void add(Entity[] es) {
		for (Entity e  : es) {
			if (e != null)
				add(e);
		}
	}

	/**
	 *	Removes the Entity and ends the Entity.
	 *
	 * @param	e	The entity to be removed.
	 */
	public void remove(Entity e) {
		entities.remove(e);
	}

	/**
	 *	Removes aa array of Entities from the Group.
	 *
	 * @param	es	The array of Entities to remove from the Group.
	 */
	public void remove(Entity[] es) {
		for (Entity e  : es) {
			remove(e);
		}
	}

	/**
	 *	Remove all the entities from this Group.
	 */
	public void clear() {
		for (Entity e : entities) {
			remove(e);
		}
	}

	/**
	 * Transfers all entities from a Group to this Group.
	 *
	 * @param	es	The Group to be transfered to this Group.
	 */
	public void transer(Group es) {
		add(es.getEntities());
		es.clear();
	}

	/**
	 * Check if the Group is empty.
	 *
	 * @return	True if the Group contains no Entities, false otherwise.
	 */
	public boolean isEmpty() {
		return entities.isEmpty();
	}

	/**
	 * Get the number of Entities in this Group.
	 *
	 * @return	The number of Entities in this Group.
	 */
	public int size() {
		return entities.size();
	}

	/**
	 *	Updates all Entites in the Group.
	 *
	 * @param	elapsedTime	The time in milliseconds since the last update.
	 */
	public void update(long elapsedTime) {
		for (Entity e: entities) {
			if (e.isActive())
				e.update(elapsedTime);
		}
	}

	/**
	 * Begins all Entities within this Group.
	 */
	public void begin() {
		for (Entity e : entities) {
			e.begin();
		}
	}

	/**
	 * Ends all Entities within this Group.
	 */
	public void end() {
		for (Entity e : entities) {
			e.end();
		}
		clear();
	}

	// group operations
	// Component management
	public void add(Component c) {
		for (Entity e : entities) {
			e.add(c.clone());
		}
	}

	public void remove(Class<? extends Component> componentClass) {
		for (Entity e : entities) {
			e.remove(componentClass);
		}
	}

	//single value components
	public void setValue(Class<? extends DoubleComponent> componentClass, double x) {
		for (Entity e : entities) {
			e.setValue(componentClass, x);
		}
	}

	public void addValue(Class<? extends DoubleComponent> componentClass, double x) {
		for (Entity e : entities) {
			e.addValue(componentClass, x);
		}
	}

	public void subtractValue(Class<? extends DoubleComponent> componentClass, double x) {
		for (Entity e : entities) {
			e.subtractValue(componentClass, x);
		}
	}

	public void multiplyValue(Class<? extends DoubleComponent> componentClass, double x) {
		for (Entity e : entities) {
			e.multiplyValue(componentClass, x);
		}
	}

	public void divideValue(Class<? extends DoubleComponent> componentClass, double x) {
		for (Entity e : entities) {
			e.divideValue(componentClass, x);
		}
	}

	//Vector components
	public void setVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v) {
		for (Entity e : entities) {
			e.setVector2d(componentClass, v);
		}
	}

	public void addVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v) {
		for (Entity e : entities) {
			e.addVector2d(componentClass, v);
		}
	}

	public void subtractVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v) {
		for (Entity e : entities) {
			e.subtractVector2d(componentClass, v);
		}
	}

	public void multiplyVector2d(Class<? extends Vector2dComponent> componentClass, double r) {
		for (Entity e : entities) {
			e.multiplyVector2d(componentClass, r);
		}
	}

	public void divideVector2d(Class<? extends Vector2dComponent> componentClass, double r) {
		for (Entity e : entities) {
			e.divideVector2d(componentClass, r);
		}
	}

}
