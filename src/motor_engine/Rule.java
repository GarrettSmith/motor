package motor_engine;

/**
 * A Rule is used to define custom logic for a game.
 *
 * @author	Garrett Smith
 * @version	0.1
 */
public abstract class Rule extends GameObject {

	/**
	 *
	 */
	public Rule() {
		//RuleManager.add(this);
	}

	/**
	 * A Rule can preform actions every update.
	 *
	 * @param	deltaTime	The time in milliseconds since the last update.
	 */
	public abstract void update(long deltaTime);

	/**
	 * A Rule can begin and setup when their owning state is begun.
	 */
	public abstract void begin();

	/**
	 * A rule can end and can perform cleanup and shutdown tasks.
	 */
	public abstract void end();
}
