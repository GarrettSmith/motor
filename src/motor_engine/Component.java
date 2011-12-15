package motor_engine;
import java.util.*;

/**
 *	The base component class that all components extend.
 *
 * @author Garrett Smith
 * @version 0.1
 */

public abstract class Component implements EntityOwned, LifeCycle {

	// All components have an update priority
	private double priority;

	// All components have an Entity as an parent
	// The owning Entity of the component. This is used to access the other components through.
	private Entity parent;

	/**
	 * Set the update priority of this component
	 *
	 * @param	p	The priority for this component to update at.
	 */
	public void setPriority(double p) {
		priority = p;
	}

	/**
	 * Get the update priority of this component.
	 *
	 * @return	The priority of this component to update.
	 */
	public double getPriority() {
		return priority;
	}

	// Component Parentship

	public Entity getParent() {
		return parent;
	}


	public void setParent(Entity parent) {
		this.parent = parent;
	}

	public boolean hasParent() {
		return (parent != null);
	}

	// Component Actions
	/** All components do something to affect game logic in an update method.
	 * 	@param	deltaTime	The time in milliseconds since the last update.
	 */
	public abstract void update(long deltaTime);

	/** All components have an initialization method that is ran when their Entity is begun.*/
	public abstract void begin();

	/** All Entities can be removed from the game world. When they are removed,
	  they call the end() method. The end() method calls all of the end() methods
	  on every component, where cleanup can be performed, or special events can
	  be triggered*/
	public abstract void end();

	/**
	 * Every Component can be expected to be logged (polled) for its current state.
	 */
	public abstract String toString();

	/**
	 * Every component can be cloned.
	 */
	public abstract Component clone();
}
