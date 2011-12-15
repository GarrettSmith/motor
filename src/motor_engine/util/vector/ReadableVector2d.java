package motor_engine.util.vector;

public interface ReadableVector2d extends ReadableVector {
	public abstract double getX();
	public abstract double getY();
	public abstract double angle(double x, double y);
	public abstract double angle(ReadableVector2d v);
	public abstract double dot(ReadableVector2d v);
	public abstract boolean equals(ReadableVector2d v);
}
