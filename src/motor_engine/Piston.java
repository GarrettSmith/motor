package motor_engine;
import motor_engine.util.*;

/**
 * A piston is an abstract thread ran by the game engine. It can run at a variable or fixed framerate and can be paused.
 */
public class Piston extends Thread{

	/** If true, the timestep is fixed. If false, the timestep is dynamic.*/
	private boolean fixed;

	// Dynamic timestep variables
	/** The time in nanoseconds at the start of the step.*/
	private long beforeTime;
	/** The time in nanoseconds the step takes.*/
	private long deltaTime;
	/** The time in macroseconds to sleep.*/
	private long sleepTime;

	// Frame Timer
	/** The timer to keep track of the fps.*/
	private Timer fpsTimer;
	/** The number of steps taken this second.*/
	private int frames;
	/** The number of steps taken last second.*/
	private int fps;

	// Running booleans
	/** If true, the Piston stops running. If false, the Piston runs*/
	private boolean active;

	// Constructors

	/**
	 * Creates a Piston that you can set the timestep style and active state.
	 *
	 * @param	fixed	Whether the Piston should use a fixed timestep or not.
	 * @param	active	Wheter the Piston should start active or not.
	 * @param	frameRate	The number of steps per second this piston should run at.
	 */
	public Piston(boolean fixed, boolean active, int frameRate) {
		setFixed(fixed);
		setActive(active);
		setFrameRate(frameRate);
	}

	/**
	 * The main action of the piston, called by start. If the piston is paused no steps will occur.
	 */
	public void run() {
		// Set intial running boolean
		// set initial timer values
		beforeTime = System.currentTimeMillis();
		deltaTime = 0;
		// fps
		fpsTimer = new Timer(true, 1);
		fpsTimer.start();
		frames = 0;
		fps = 0;

		int i = 0;

		// game graphics loop
		while(active) {

			// execute the thread action
			owner.update(deltaTime);

			// Get the elapsed time
			deltaTime = (System.currentTimeMillis() - beforeTime);

			// get new start time
			beforeTime = System.currentTimeMillis();

			// calculate time to sleep
			sleepTime = (long)(1000 / frameRate);

			// set a minimum sleep value
			if (sleepTime < 0)
			sleepTime = 2;

			try {
				sleep(sleepTime);

			}
			catch (InterruptedException e) {
				exception(e);
			}

			// increase fps count
			frames++;
			// update fps timer
			fpsTimer.update();
			if (fpsTimer.fired()) {
				fps = frames;
				frames = 0;
				MotorEngine.console.logFPS(fps);
			}
		}
	}

	// The owner of this piston
	private LifeCycle owner;

	/**
	 * Sets the owner of this method.
	 *
	 * @param	owner	The object implementing PistonUpdate that is to be updated by this piston.
	 */
	public void setOwner(LifeCycle owner) {
		this.owner = owner;
	}

	/**
	 * Get the owner of this Piston.
	 *
	 * @return	Gets the owner object implementing PistonUpdate of this Piston.
	 */
	public LifeCycle getOwner() {
		return owner;
	}

	/**
	 * Run or pause the Piston.
	 *
	 * @param	active	A boolean stating if the Piston is active or not.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Check wheter the Piston is active or not.
	 *
	 * @return	A boolean stating if the Piston is active.
	 */
	public boolean getActive() {
		return active;
	}

	/**
	 * Set the Piston to run at a fixed or dynamic timestep.
	 *
	 * @param	fixed	If true the Piston is fixed if false the Piston is dynamic.
	 */
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	/**
	 * Check if the Piston is running at a fixed timestep.
	 *
	 * @return	A boolean stating if the Piston is running at a fixed timestep or not.
	 */
	public boolean getFixed() {
		return fixed;
	}

	// the frame rate for this piston to run at.
	private int frameRate;

	/**
	 *	Set the frame rate of this piston.
	 * 	<p>When the framerate is variable this is the goal not definate.
	 *
	 * @param	frameRate	The frames per second to run at.
	 */
	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}

	/**
	 * Get the framerate for this piston to run at.
	 *
	 *@return	The frames per second this Piston is running at.
	 */
	public int getFrameRate() {
		return frameRate;
	}

	/**
	 * The exception cathcher, this is currently just a placeholder that will print the exception to the terminal.
	 *
	 * @param	e	The interrupted exception created within the Piston.
	 */
	private void exception (InterruptedException e) {
		System.out.println(e);
	}

	/**
	 * Get the current fps this Piston is updating at.
	 *
	 * @return	The frames this Piston ran at in the last second.
	 */
	public int fps() {
		return fps;
	}
}
