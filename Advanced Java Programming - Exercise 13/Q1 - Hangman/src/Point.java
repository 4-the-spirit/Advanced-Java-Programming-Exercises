/**
 * Represents a point in a 2D coordinate system with x and y coordinates.
 */
public class Point {
	private double x;
	private double y;
	
	/**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object other) {
		Point otherPoint = (Point) other;
		return (getX() == otherPoint.getX()) && (getY() == otherPoint.getY());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
