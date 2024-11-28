/**
 * Represents a reptile, which is a subclass of {@link Animal}.
 * This class provides the abstract method for crawling, which is unique to reptiles.
 */
public abstract class Reptile extends Animal {
	/**
     * Makes the reptile crawl from point A to point B.
     * The specific implementation of how a reptile crawls should be defined in the concrete subclasses.
     *
     * @param a the starting point of the crawl.
     * @param b the destination point of the crawl.
     */
	public abstract void crawl(Point a, Point b);
}
