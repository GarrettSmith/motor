package motor_engine.util.vector;

public interface WriteableVector2d extends WriteableVector {
	public abstract void set(double x, double y);
	public abstract void set(ReadableVector2d v);
	public abstract void setX(double x);
	public abstract void setY(double y);
	public abstract void add(double x, double y);
	public abstract void add(ReadableVector2d v);
	public abstract void subtract(double x, double y);
	public abstract void subtract(ReadableVector2d v);
	public abstract void copySign(double x, double y);
	public abstract void copySign(ReadableVector2d v);
	public abstract void constrain(double min, double max);
}
