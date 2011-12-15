package motor_engine.util.shape;

public interface WriteableDimension {
	public void setWidth(double w);
	public void setHeight(double h);
	public void setDimension(double w, double h);
	public void setDimension(ReadableDimension d);
}
