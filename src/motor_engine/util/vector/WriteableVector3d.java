package motor_engine.util.vector;

public interface WriteableVector3d extends WriteableVector {
	public abstract void set(double x, double y, double z);
	public abstract void set(ReadableVector3d v);
	public abstract void setX(double x);
	public abstract void setY(double y);
	public abstract void setZ(double z);
	public abstract void add(double x, double y, double z);
	public abstract void add(ReadableVector3d v);
	public abstract void subtract(double x, double y, double z);
	public abstract void subtract(ReadableVector3d v);
	public abstract void copySign(double x, double y, double z);
	public abstract void copySign(ReadableVector3d v);
	public abstract void constrain(double min, double max);
}
