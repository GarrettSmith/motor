package motor_engine;
import motor_engine.components.*;
import motor_engine.util.vector.*;

public interface WriteableEntity {

	// Component management
	public void add(Component c);
	public void remove(Class<? extends Component> componentClass);

	//single value components
	public void setValue(Class<? extends DoubleComponent> componentClass, double x);
	public void addValue(Class<? extends DoubleComponent> componentClass, double x);
	public void subtractValue(Class<? extends DoubleComponent> componentClass, double x);
	public void multiplyValue(Class<? extends DoubleComponent> componentClass, double x);
	public void divideValue(Class<? extends DoubleComponent> componentClass, double x);

	//Vector components
	public void setVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v);
	public void addVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v);
	public void subtractVector2d(Class<? extends Vector2dComponent> componentClass, ReadableVector2d v);
	public void multiplyVector2d(Class<? extends Vector2dComponent> componentClass, double r);
	public void divideVector2d(Class<? extends Vector2dComponent> componentClass, double r);
}
