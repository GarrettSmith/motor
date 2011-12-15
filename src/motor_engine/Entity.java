package motor_engine;
import motor_engine.components.Vector2dComponent;
import motor_engine.components.DoubleComponent;
import motor_engine.util.vector.ReadableVector2d;
import motor_engine.util.vector.Vector2d;
import java.util.HashMap;

/**
 * An Entity is a container of components that ineract with one another.
 *
 * @author Garrett Smith
 * @version 0.1
 */
public class Entity extends GameObject implements ReadableEntity, WriteableEntity {

	// All entites have a type and unique identifier number
	private String type;
	private int identifier;

	// A hasmap keeping track of the current unique identifier counters
	private static HashMap<String, Integer> identifierCounter = new HashMap<String, Integer>();

	// A hashMap containing references to all the components this entity has with the keys corresponding to the class
	private HashMap<Class<? extends Component>, Component> componentMap;

	// this entity's parent null if it doesn't have one
	private Entity parent;

	/**
	 * Create a new Entity of the specified type.
	 *
	 * @param	type	The type of Entity this is.
	 */
	public Entity(String type) {
		componentMap = new HashMap<Class<? extends Component>, Component>();
		this.type = type;
		setupIdentifier();
		MotorEngine.console.log(getName() + " was created.");
		setActive(false);
	}

	/**
	 * Get the type of this Entity.
	 *
	 * @return	A String contianing the type of this Entity.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Get the unique identifier number of this Entity.
	 *
	 * @return	The unique identifier of this Entity.
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * Get the unique name of this Entity.
	 *
	 * @return	A String containing the type of this Entity with the Entity identifier appenedded to it.
	 */
	public String getName() {
		return type + "#" + identifier;
	}

	/**
	 * Gives each entity a unique number within entities of the same type.
	 */
	private void setupIdentifier() {
		// increment counter or start new counter
		if (identifierCounter.containsKey(type)) {
			identifierCounter.put(type, (new Integer(identifierCounter.get(type).intValue() + 1)));
		}
		else {
			identifierCounter.put(type, new Integer(0));
		}
		//set this Entities unique identifier
		identifier = identifierCounter.get(type).intValue();
	}

	/**
	 * Get the string representation of this Entity.
	 *
	 * @return	The string of the Entity's name and all it's components.
	 */
	public String toString() {
		String str = getName();
		for (Component c : componentMap.values()) {
			str += "\n" + c.toString();
		}
		return str;
	}

	// Parenting

	public Entity getParent() {
		return parent;
	}

	public void setParent(Entity e) {
		parent = e;
	}

	public boolean hasParent() {
		return (parent != null);
	}

	// components

	/**
	 * Search the Entity's Components and return wheter it has the desired Component or not.
	 *
	 * @param	componentClass	The class of the Component to search for.
	 * @return					True if the hash for this class references a matching Compoent object, false if it references null.
	 *
	 */
	public boolean has(Class<? extends Component> componentClass) {
		return componentMap.containsKey(componentClass);
	}

	/**
	 * Access the Component object given the Component class.
	 *
	 * @param	componentClass	The class of the Component to search for.
	 * @return	component		The Compeont object.
	 */
	public Component get(Class<? extends Component> componentClass) {
		final Component c = componentMap.get(componentClass);

		if (c != null) {
			return c;
		}
		else {
			return null;
		}
	}

	/**
	 * Adds a Component to the Entity.
	 *
	 * @param	c	The Component to be added or replaced.
	 */
	public void add(Component c) {
		// set parentship
		c.setParent(this);
		// add to the entity
		componentMap.put(c.getClass(), c);
	}

	/**
	 * Removes a Component from the Entity.
	 *
	 * @param	componentClass	The class of the Component to remove.
	 */
	public void remove(Class<? extends Component> componentClass) {
		get(componentClass).end();
		componentMap.remove(componentClass);
	}

	/**
	 * Update all Components belonging to this Entity. This will do nothing if the Entity is disabled.
	 *
	 * @param	deltaTime	The elapsed game time, in milliseconds, since the last update.
	 */
	public void update(long deltaTime) {
		if (isActive()) {
			for (Component c : componentMap.values() ) {
				if (c != null) {
					c.update(deltaTime);
				}
			}
		}
	}

	/**
	 * Begin all Components belonging to this Entity.
	 */
	public void begin() {
		for (Component c : componentMap.values() ) {
			if (c != null)
				c.begin();
		}
	}

	/**
	 *	End all Components belonging to this Entity.
	 */
	public void end() {
		for (Component c : componentMap.values() ) {
			if (c != null)
				c.end();
		}
		componentMap.clear();
	}

	//double component setting shortcuts
	/**
	 * Get the value of a Double Component.
	 * If the component does not exist zero is returned so make sure to test for the component before you try to get its value.
	 *
	 * @param	componentClass	The class of the component to get.
	 */
	public double getValue(Class<? extends DoubleComponent> componentClass) {
		DoubleComponent c = (DoubleComponent) componentMap.get(componentClass);
		if (c != null) {
			return c.get();
		}
		else {
			return 0;
		}
	}

	/**
	 * Set the value of a Double Component.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @param	x				The value to set.
	 */
	public void setValue(Class<? extends DoubleComponent> componentClass, double x) {
		DoubleComponent c = (DoubleComponent) componentMap.get(componentClass);
		if (c != null) {
			c.set(x);
		}
	}

	/**
	 * Add to the value of a Double Component.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @param	x				The value to add.
	 */
	public void addValue(Class<? extends DoubleComponent> componentClass, double x) {
		DoubleComponent c = (DoubleComponent) componentMap.get(componentClass);
		if (c != null) {
			c.add(x);
		}
	}

	/**
	 * Subtract from the value of a Double Component.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @param	x				The value to subtract.
	 */
	public void subtractValue(Class<? extends DoubleComponent> componentClass, double x) {
		DoubleComponent c = (DoubleComponent) componentMap.get(componentClass);
		if (c != null) {
			c.subtract(x);
		}
	}

	/**
	 * Multiply the value of a Double Component.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @param	x				The value to multiply.
	 */
	public void multiplyValue(Class<? extends DoubleComponent> componentClass, double x) {
		DoubleComponent c = (DoubleComponent) componentMap.get(componentClass);
		if (c != null) {
			c.subtract(x);
		}
	}

	/**
	 * Divide the value of a Double Component.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @param	x				The value to divide.
	 */
	public void divideValue(Class<? extends DoubleComponent> componentClass, double x) {
		DoubleComponent c = (DoubleComponent) componentMap.get(componentClass);
		if (c != null) {
			c.divide(x);
		}
	}

	// vector component setting shortcuts

	/**
	 * Set the value of a Vector2dComponent.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @return					A readable vector with the vlues of the component.
	 */
	public ReadableVector2d getVector2d(Class<? extends Vector2dComponent> componentClass) {
		Vector2dComponent c = (Vector2dComponent) componentMap.get(componentClass);
		if (c != null) {
			return new Vector2d(c);
		}
		else {
			return null;
		}
	}

	/**
	 * Set the value of a Vector2dComponent.
	 *
	 * @param	componentClass	The class of the component to set.
	 * @param	v				The vector containtaing the desired values.
	 */
	public void setVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v) {
		Vector2dComponent c = (Vector2dComponent) componentMap.get(componentClass);
		if (c != null) {
			c.set(v);
		}
	}

	/**
	 * Helper method to add to Vector components.
	 *
	 * @param	componentClass	The class of the component to add to.
	 * @param	v				The vector to add to the component.
	 */
	public void addVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v) {
		Vector2dComponent c = (Vector2dComponent) componentMap.get(componentClass);
		if (c != null) {
			c.add(v);
		}
	}

	/**
	 * Helper method to subtract from Vector components.
	 *
	 * @param	componentClass	The class of the component to subtract from.
	 * @param	v				The vector to subtract from the component.
	 */
	public void subtractVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v) {
		Vector2dComponent c = (Vector2dComponent) componentMap.get(componentClass);
		if (c != null) {
			c.subtract(v);
		}
	}

	/**
	 * Helper method to multiply Vector components.
	 *
	 * @param	componentClass	The class of the component to subtract from.
	 * @param	r				The amount to multiply the component by.
	 */
	public void multiplyVector2d(Class<? extends Vector2dComponent> componentClass, double r) {
		Vector2dComponent c = (Vector2dComponent) componentMap.get(componentClass);
		if (c != null) {
			c.multiply(r);
		}
	}

	/**
	 * Helper method to divide Vector components.
	 *
	 * @param	componentClass	The class of the component to subtract from.
	 * @param	r				The amount to divide the component by.
	 */
	public void divideVector2d(Class<? extends Vector2dComponent> componentClass, double r) {
		Vector2dComponent c = (Vector2dComponent) componentMap.get(componentClass);
		if (c != null) {
			c.divide(r);
		}
	}

}
