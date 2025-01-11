
public class Point implements Swappable<Point> {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void swap(Point other) {
		Point tmp = new Point(this.getX(), this.getY());
		this.setX(other.getX());
		this.setY(other.getY());
		other.setX(tmp.getX());
		other.setY(tmp.getY());
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
