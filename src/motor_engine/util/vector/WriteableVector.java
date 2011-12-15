package motor_engine.util.vector;

public interface WriteableVector {
	public abstract void multiply(double r);
	public abstract void divide(double r);
	public abstract void abs();
	public abstract void negate();
	public abstract void normalize();
}
