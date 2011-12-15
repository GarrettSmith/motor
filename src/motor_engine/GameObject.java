package motor_engine;

/**
 * A game object has a lifecycle, an active status and is owned by a state.
 * This is what rules and entities derive from.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public abstract class GameObject implements LifeCycle, Active, StateOwned {

	private boolean active;
	private State owner;

	// life cycle
	public abstract void begin();
	public abstract void end();
	public abstract void update(long deltaTime);

	// Active
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean b) {
		active = b;
	}

	// State owned
	public State getOwner() {
		return owner;
	}

	public void setOwner(State s) {
		owner = s;
	}

	public boolean isOwned() {
		return (owner != null);
	}

}
