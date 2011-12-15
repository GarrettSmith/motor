package motor_engine.util.vector;

public interface ReadableVector3d extends ReadableVector {
	public abstract double getX();
	public abstract double getY();
	public abstract double getZ();
	public abstract double angle(double x, double y, double z);
	public abstract double angle(ReadableVector3d v);
	public abstract double dot(ReadableVector3d v);
	public abstract boolean equals(ReadableVector3d v);
}
