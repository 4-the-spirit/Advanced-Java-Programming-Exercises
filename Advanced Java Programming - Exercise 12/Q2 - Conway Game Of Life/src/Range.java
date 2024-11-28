
/**
 * Represents an immutable range of integers, defined by a start and end value (inclusive).
 * Provides utility methods to access the start and end points of the range.
 */
public class Range {
	private final int start;
	private final int end;
	
	/**
     * Constructs a new {@code Range} with the specified start and end values.
     *
     * @param start the starting value of the range (inclusive)
     * @param end the ending value of the range (inclusive)
     */
	public Range(int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException("Start must not be greater than end!");
		}
		this.start = start;
		this.end = end;
	}
	
	/**
     * Retrieves the starting value of the range.
     *
     * @return the start value of the range
     */
	public int getStart() {
		return this.start;
	}
	
	/**
     * Retrieves the ending value of the range.
     *
     * @return the end value of the range
     */
	public int getEnd() {
		return this.end;
	}
}
