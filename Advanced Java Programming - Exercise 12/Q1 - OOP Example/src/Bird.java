/**
 * Represents a Bird, a subclass of the {@code Animal} class.
 * This class defines a {@code fly()} method, which must be implemented by subclasses 
 * to specify how a bird flies between two points.
 */
public abstract class Bird extends Animal {
	/**
     * Defines the behavior of how the bird flies from one point to another.
     * Subclasses must provide the specific implementation of how the bird flies.
     * 
     * @param a the starting point of the bird's flight (represented as a {@code Point}).
     * @param b the destination point of the bird's flight (represented as a {@code Point}).
     */
	public abstract void fly(Point a, Point b);

}
