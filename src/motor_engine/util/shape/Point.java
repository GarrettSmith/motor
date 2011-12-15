package motor_engine.util.shape;
import java.io.Serializable;

public class Point implements ReadablePoint, WriteablePoint, Serializable {
	private double x;
	private double y;

	public Point() {
		setPoint(0,0);
	}

	public Point(double x, double y) {
		setPoint(x,y);
	}

	public Point(ReadablePoint p) {
		setPoint(p);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public ReadablePoint getPoint() {
		return this;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setPoint(double x, double y) {
		setX(x);
		setY(y);
	}

	public void setPoint(ReadablePoint p) {
		setX(p.getX());
		setY(p.getY());
	}
}
