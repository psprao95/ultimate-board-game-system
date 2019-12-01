package main.java.model.utility;

/**
 * A point representing a location in {@code (x,y)} coordinate space,
 * specified in integer
 */
public class Point {
	public int x;
	public int y;

	public Point() {
		this(0, 0);
	}

	public Point(Point p) {
		this(p.x, p.y);
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	private void setLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	public void setLocation(Point p) {
		setLocation(p.x, p.y);
	}



	public String toString() {
		return getClass().getSimpleName() + "[x=" + x + ",y=" + y + "]";
	}
}

