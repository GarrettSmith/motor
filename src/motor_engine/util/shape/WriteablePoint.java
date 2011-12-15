package motor_engine.util.shape;

public interface WriteablePoint {
	public void setX(double x);
	public void setY(double y);
	public void setPoint(double x, double y);
	public void setPoint(ReadablePoint p);
}
