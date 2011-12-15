package motor_engine.util.shape;
import motor_engine.util.vector.*;
import java.io.Serializable;

public class Rectangle implements ReadableRectangle, WriteableRectangle, Serializable {
	private Dimension dimension;
	private Point point;

	public Rectangle() {
		point = new Point();
		dimension = new Dimension();
	}

	public Rectangle(double x, double y, double w, double h) {
		point = new Point(x,y);
		dimension = new Dimension(w,h);
	}

	public Rectangle(ReadablePoint p, ReadableDimension d) {
		point = new Point(p);
		dimension = new Dimension(d);
	}

	public Rectangle(ReadableRectangle r) {
		point = new Point(r.getPoint());
		dimension = new Dimension(r.getDimension());
	}

	// point
	public double getX() {
		return point.getX();
	}

	public double getY() {
		return point.getY();
	}

	public ReadablePoint getPoint() {
		return new Point(point);
	}

	public void setPoint(double x, double y) {
		point.setPoint(x,y);
	}

	public void setPoint(ReadablePoint p) {
		point.setPoint(p);
	}

	public void setX(double x) {
		point.setX(x);
	}

	public void setY(double y) {
		point.setY(y);
	}

	//dimension
	public double getWidth() {
		return dimension.getWidth();
	}

	public double getHeight() {
		return dimension.getHeight();
	}

	public double getHalfWidth() {
		return dimension.getWidth()/2.0;
	}

	public double getHalfHeight() {
		return dimension.getHeight()/2.0;
	}

	public ReadableDimension getDimension() {
		return new Dimension(dimension);
	}

	public void setDimension(double w, double h) {
		dimension.setDimension(w,h);
	}

	public void setDimension(ReadableDimension d) {
		dimension.setDimension(d);
	}

	public void setWidth(double w) {
		dimension.setWidth(w);
	}

	public void setHeight(double h) {
		dimension.setHeight(h);
	}

	//rectangle
	public ReadableRectangle getRectangle() {
		return new Rectangle(this);
	}

	public void setBounds(double x, double y, double w, double h) {
		point.setPoint(x,y);
		dimension.setDimension(w,h);
	}

	public void setBounds(ReadablePoint p, ReadableDimension d) {
		point.setPoint(p);
		dimension.setDimension(d);
	}

	public void setBounds(ReadableRectangle r) {
		point.setPoint(r.getPoint());
		dimension.setDimension(r.getDimension());
	}

	// collision
	public boolean contains(double x, double y) {
		double left = getX() - getHalfWidth();
		double right = getX() + getHalfWidth();
		double top = getY() + getHalfHeight();
		double bottom = getY() - getHalfHeight();
		return (left <= x && x <= right) && (bottom <= y && y <= top);
	}

	public boolean contains(ReadablePoint p) {
		return contains(p.getX(), p.getY());
	}

	public boolean contains(double x, double y, double w, double h) {
		boolean contains = false;
		// check two diagonal corners
		// if they're both contained the entire rectangle is contained
		if (contains(x-(w/2),y-(h/2))) {
			if (contains(x+(w/2),y+(h/2))) {
				contains = true;
			}
		}
		return contains;
	}

	public boolean contains(ReadableRectangle r) {
		return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public boolean intersects(double x, double y, double w, double h) {
		boolean intersects = false;
		// this rectangles bounds
		double left1 = getX() - getHalfWidth();
		double right1 = getX() + getHalfWidth();
		// the other rectangles bounds
		double left2 = x - (w/2);
		double right2 = x + (w/2);
		// check first dimension
		if ((left1	<	left2 	&& left2	< right1) ||
			(left1	<	right2 	&& right2	< right1) ||
			(left2	<=	left1 	&& right1	<= right2))
		{
			// this rectangles bounds
			double top1 = getY() + getHalfHeight();
			double bottom1 = getY() - getHalfHeight();
			// the other rectangles bounds
			double top2 = y + (h/2);
			double bottom2 = y - (h/2);
			// check second dimension
			if ((bottom1	<	bottom2 && bottom2 	< 	top1) ||
				(bottom1	<	top2 	&& top2		< 	top1) ||
				(bottom2 	<=	bottom1 && top1		<= 	top2))
			{
				intersects = true;
			}
		}
		return intersects;
	}

	public boolean intersects(ReadableRectangle r) {
		return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public boolean intersectsTop(double x, double y, double w, double h) {
		boolean intersects = false;
		// this rectangles bounds
		double top1 = getY() + getHalfHeight();
		double bottom1 = getY() - getHalfHeight();
		// the other rectangles bounds
		double top2 = y + (h/2);
		double bottom2 = y - (h/2);
		// check second dimension
		if (bottom2 < top1 && top1 < top2) {
			// this rectangles bounds
			double left1 = getX() - getHalfWidth();
			double right1 = getX() + getHalfWidth();
			// the other rectangles bounds
			double left2 = x - (w/2);
			double right2 = x + (w/2);
			// check first dimension
			if ((left1	<	left2 	&& left2	< right1) ||
				(left1	<	right2 	&& right2	< right1) ||
				(left2	<=	left1 	&& right1	<= right2))
			{
				intersects = true;
			}
		}

		return intersects;
	}

	public boolean intersectsTop(ReadableRectangle r) {
		return intersectsTop(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public boolean intersectsBottom(double x, double y, double w, double h) {
		boolean intersects = false;
		// this rectangles bounds
		double top1 = getY() + getHalfHeight();
		double bottom1 = getY() - getHalfHeight();
		// the other rectangles bounds
		double top2 = y + (h/2);
		double bottom2 = y - (h/2);
		// check second dimension
		if (bottom2 < bottom1 && bottom1 < top2) {
			// this rectangles bounds
			double left1 = getX() - getHalfWidth();
			double right1 = getX() + getHalfWidth();
			// the other rectangles bounds
			double left2 = x - (w/2);
			double right2 = x + (w/2);
			// check first dimension
			if ((left1	<	left2 	&& left2	< right1) ||
				(left1	<	right2 	&& right2	< right1) ||
				(left2	<=	left1 	&& right1	<= right2))
			{
				intersects = true;
			}
		}

		return intersects;
	}

	public boolean intersectsBottom(ReadableRectangle r) {
		return intersectsBottom(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public boolean intersectsLeft(double x, double y, double w, double h) {
		boolean intersects = false;
		// this rectangles bounds
		double left1 = getX() - getHalfWidth();
		double right1 = getX() + getHalfWidth();
		// the other rectangles bounds
		double left2 = x - (w/2);
		double right2 = x + (w/2);
		// check first dimension
		if (left2 < left1 && left1 < right2) {
			// this rectangles bounds
			double top1 = getY() + getHalfHeight();
			double bottom1 = getY() - getHalfHeight();
			// the other rectangles bounds
			double top2 = y + (h/2);
			double bottom2 = y - (h/2);
			// check second dimension
			if ((bottom1	<	bottom2 && bottom2 	< 	top1) ||
				(bottom1	<	top2 	&& top2		< 	top1) ||
				(bottom2 	<=	bottom1 && top1		<= 	top2))
			{
				intersects = true;
			}
		}
		return intersects;
	}

	public boolean intersectsLeft(ReadableRectangle r) {
		return intersectsLeft(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public boolean intersectsRight(double x, double y, double w, double h) {
		boolean intersects = false;
		// this rectangles bounds
		double left1 = getX() - getHalfWidth();
		double right1 = getX() + getHalfWidth();
		// the other rectangles bounds
		double left2 = x - (w/2);
		double right2 = x + (w/2);
		// check first dimension
		if (left2 < right1 && right1 < right2){
			// this rectangles bounds
			double top1 = getY() + getHalfHeight();
			double bottom1 = getY() - getHalfHeight();
			// the other rectangles bounds
			double top2 = y + (h/2);
			double bottom2 = y - (h/2);
			// check second dimension
			if ((bottom1	<	bottom2 && bottom2 	< 	top1) ||
				(bottom1	<	top2 	&& top2		< 	top1) ||
				(bottom2 	<=	bottom1 && top1		<= 	top2))
			{
				intersects = true;
			}
		}
		return intersects;
	}

	public boolean intersectsRight(ReadableRectangle r) {
		return intersectsRight(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	public ReadableVector2d intersection(double x, double y, double w, double h) {

		// this rectangles bounds
		double left1 = getX() - getHalfWidth();
		double right1 = getX() + getHalfWidth();
		// the other rectangles bounds
		double left2 = x - (w/2);
		double right2 = x + (w/2);

		double xDepth = 0;

		if (x <= point.getX()) {
			xDepth = (right2 - left1);
			if (xDepth < 0)
				xDepth = 0;
		}
		else if (x > point.getX()) {
			xDepth = (left2 - right1);
			if (xDepth > 0)
				xDepth = 0;
		}

		// this rectangles bounds
		double top1 = getY() + getHalfHeight();
		double bottom1 = getY() - getHalfHeight();
		// the other rectangles bounds
		double top2 = y + (h/2);
		double bottom2 = y - (h/2);

		double yDepth = 0;

		if (y <= point.getY()) {
			yDepth = (top2 - bottom1);
			if (yDepth < 0)
				yDepth = 0;
		}
		else if (y > point.getY()) {
			yDepth = (bottom2 - top1);
			if (yDepth > 0)
				yDepth = 0;
		}

		return new Vector2d(xDepth, yDepth);
	}

	public ReadableVector2d intersection(ReadableRectangle r) {
		return intersection(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
}
