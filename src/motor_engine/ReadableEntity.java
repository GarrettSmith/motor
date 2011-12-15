package motor_engine;
import motor_engine.util.vector.ReadableVector2d;
import motor_engine.components.*;

public interface ReadableEntity {

	// identification
	public String getName();
	public String getType();
	public int getIdentifier();

	// component checking
	public boolean has(Class<? extends Component> componentClass);
	public Component get(Class<? extends Component> componentClass);

	//single value componenets
	public double getValue(Class<? extends DoubleComponent> componentClass);

	// vector components
	public ReadableVector2d getVector2d(Class<? extends Vector2dComponent> componentClass);
}
