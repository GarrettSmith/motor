package motor_engine.components;
import motor_engine.Component;

public class ZOrder extends DoubleComponent {
	public ZOrder() {
		super();
	}

	public ZOrder(double z) {
		super(z);
	}

	public ZOrder(DoubleComponent c) {
		super(c);
	}

	public Component clone() {
		return new ZOrder(this);
	}

	public void begin() {

	}

	public void end() {

	}

	public void update(long deltaTime) {

	}
}
