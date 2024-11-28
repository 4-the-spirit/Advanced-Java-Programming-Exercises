import java.util.ArrayList;
import java.util.List;

public enum LifeRule {
	BIRTH(ConwayCellsArray.BIRTH_VALUES, false, true),
	LONELINESS_DEATH(ConwayCellsArray.LONELINESS_DEATH_VALUES, true, false),
	OVERPOPULATION_DEATH(ConwayCellsArray.OVERPOPULATION_DEATH_VALUES, true, false),
	EXISTENCE(ConwayCellsArray.EXISTENCE_VALUES, true, true),
	OTHER(ConwayCellsArray.OTHER_VALUES, false, false);
	
	private final int minLivingNeighbors;
	private final int maxLivingNeighbors;
	private final boolean alive;
	private final boolean willLive;
	
	private final List<Range> values;
	
	/**
	 * Constructs a {@code LifeRule} with a single range, the current alive state, and the future alive state.
	 *
	 * @param range a {@link Range} object defining the range of living neighbors applicable for this rule
	 * @param alive {@code true} if this rule applies to cells currently alive, {@code false} otherwise
	 * @param willLive {@code true} if the cell will live under this rule, {@code false} otherwise
	 */
	LifeRule(Range range, boolean alive, boolean willLive) {
		this.minLivingNeighbors = range.getStart();
		this.maxLivingNeighbors = range.getEnd();
		this.alive = alive;
		this.willLive = willLive;
		
		this.values = new ArrayList<Range>();
		this.values.add(new Range(getMinLivingNeighbors(), getMaxLivingNeighbors()));
	}
	
	/**
	 * Constructs a {@code LifeRule} with a list of ranges, the current alive state, and the future alive state.
	 *
	 * @param values a {@link List} of {@link Range} objects defining the ranges of living neighbors applicable for this rule
	 * @param alive {@code true} if this rule applies to cells currently alive, {@code false} otherwise
	 * @param willLive {@code true} if the cell will live under this rule, {@code false} otherwise
	 */
	LifeRule(List<Range> values, boolean alive, boolean willLive) {
		this.values = values;
		this.minLivingNeighbors = getMinValue(values);
		this.maxLivingNeighbors = getMaxValue(values);
		this.alive = alive;
		this.willLive = willLive;
	}
	
	/**
	 * Determines whether the specified cell and the number of living neighbors satisfy this rule.
	 * The rule checks if the cell's current state and the number of its living neighbors match 
	 * the conditions defined by this rule's ranges.
	 *
	 * @param cell the {@link Cell} to check against this rule
	 * @param numOfLivingNeighbors the number of living neighbors around the cell
	 * @return {@code true} if the cell satisfies this rule, {@code false} otherwise
	 */
	public boolean satisfiesRule(Cell cell, int numOfLivingNeighbors) {
		return this.isValue(numOfLivingNeighbors) && (cell.isAlive() == this.isAlive()); 
	}
	
	/**
	 * Checks whether the given value falls within any of the ranges defined by this rule.
	 *
	 * @param value the value to check (e.g., the number of living neighbors)
	 * @return {@code true} if the value is within one of the rule's ranges, {@code false} otherwise
	 */
	public boolean isValue(int value) {
		for (Range range : getValues()) {
			if (value >= range.getStart() && value <= range.getEnd()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves the minimum number of living neighbors required for this rule to apply.
	 *
	 * @return the minimum number of living neighbors defined by this rule
	 */
	public int getMinLivingNeighbors() {
		return this.minLivingNeighbors;
	}
	
	/**
	 * Retrieves the maximum number of living neighbors allowed for this rule to apply.
	 *
	 * @return the maximum number of living neighbors defined by this rule
	 */
	public int getMaxLivingNeighbors() {
		return this.maxLivingNeighbors;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public boolean willLive() {
		return this.willLive;
	}
	
	/**
	 * Retrieves the list of ranges associated with this rule.
	 * Each {@link Range} specifies a range of living neighbors that this rule applies to.
	 *
	 * @return a {@link List} of {@link Range} objects defining the valid neighbor ranges for this rule
	 */
	public List<Range> getValues() {
		return this.values;
	}
	
	/**
	 * Determines the minimum start value from a list of ranges.
	 *
	 * @param values a {@link List} of {@link Range} objects
	 * @return the smallest start value among the provided ranges
	 */
	private int getMinValue(List<Range> values) {
		int tempMin = values.get(0).getStart();
		for (Range range : values) {
			if (range.getStart() < tempMin) {
				tempMin = range.getStart();
			}
		}
		return tempMin;
	}
	
	/**
	 * Determines the maximum end value from a list of ranges.
	 *
	 * @param values a {@link List} of {@link Range} objects
	 * @return the largest end value among the provided ranges
	 */
	private int getMaxValue(List<Range> values) {
		int tempMax = values.get(0).getEnd();
		for (Range range : values) {
			if (range.getEnd() < tempMax) {
				tempMax = range.getEnd();
			}
		}
		return tempMax;
	}
}
