/**
 * Represents a point in 2D space with x, y coordinates.
 * This class provides methods to get and set the coordinates of the point.
 */
public class Point {
	private long x;
	private long y;
	
	/**
     * Constructs a new {@code Point} with the specified x, y coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */
	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}
	
	public long getX() {
		return this.x;
	}
	
	public void setX(long x) {
		this.x = x;
	}
	
	public long getY() {
		return this.y;
	}
	
	public void setY(long y) {
		this.y = y;
	}
}
