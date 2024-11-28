/**
 * Represents a point in 3D space with x, y, and z coordinates.
 * This class provides methods to get and set the coordinates of the point.
 */
public class Point {
	private long x;
	private long y;
	private long z;
	
	/**
     * Constructs a new {@code Point} with the specified x, y, and z coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param z the z-coordinate of the point.
     */
	public Point(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	public long getZ() {
		return this.z;
	}
	
	public void setZ(long z) {
		this.z = z;
	}
}
