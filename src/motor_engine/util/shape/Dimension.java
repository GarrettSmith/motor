package motor_engine.util.shape;
import java.io.Serializable;

public class Dimension implements ReadableDimension, WriteableDimension, Serializable {
	private double width;
	private double height;

	public Dimension() {
		setDimension(0,0);
	}

	public Dimension(double w, double h) {
		setDimension(w,h);
	}

	public Dimension(ReadableDimension d) {
		setDimension(d);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public ReadableDimension getDimension() {
		return this;
	}

	public void setDimension(double w, double h) {
		setWidth(w);
		setHeight(h);
	}

	public void setDimension(ReadableDimension d) {
		setWidth(d.getWidth());
		setHeight(d.getHeight());
	}

	public void setWidth(double w) {
		width = w;
	}

	public void setHeight(double h) {
		height = h;
	}

}
