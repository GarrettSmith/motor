package motor_engine.util.shape;

public interface WriteableRectangle extends WriteablePoint, WriteableDimension {
	public void setBounds(double x, double y, double w, double h);
	public void setBounds(ReadablePoint position, ReadableDimension size);
	public void setBounds(ReadableRectangle r);
}
