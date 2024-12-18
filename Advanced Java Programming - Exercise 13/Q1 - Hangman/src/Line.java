import javafx.scene.paint.Color;

/**
 * Represents a line defined by a start point, an end point, width, and color.
 */
public class Line {
	private Point startPoint;
	private Point endPoint;
	private double width = 1.0;
	private Color color = Color.BLACK;
	
	/**
     * Constructs a line with the specified start point and end point, 
     * using default values for width and color.
     *
     * @param startPoint the starting point of the line
     * @param endPoint the ending point of the line
     */
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	/**
     * Constructs a line with the specified start point, end point, width, and color.
     *
     * @param startPoint the starting point of the line
     * @param endPoint the ending point of the line
     * @param width the width of the line
     * @param color the color of the line
     */
	public Line(Point startPoint, Point endPoint, double width, Color color) {
		this(startPoint, endPoint);
		this.width = width;
		this.color = color;
	}
	
	/**
     * Returns the starting point of the line.
     *
     * @return the starting point
     */
	public Point getStartPoint() {
		return startPoint;
	}

	/**
     * Sets the starting point of the line.
     *
     * @param startPoint the new starting point
     */
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	/**
     * Returns the ending point of the line.
     *
     * @return the ending point
     */
	public Point getEndPoint() {
		return endPoint;
	}

	/**
     * Sets the ending point of the line.
     *
     * @param endPoint the new ending point
     */
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
