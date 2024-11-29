/**
 * Enum representing different units of measurement used throughout the application.
 * This enum is designed to standardize the units that can be associated with various operations, such as distance, weight, and time.
 */
public enum UnitLabel {
	EMPTY(""),
	METERS("meters"),
	KILOMETERS("km"),
	HOURS("hours"),
	KILOGRAMS("kg"),
	MILLIGRAMS("mg");
	
	private String unit;
	
	/**
     * Constructor to initialize the unit string associated with the enum constant.
     *
     * @param unit the unit as a string.
     */
	UnitLabel(String unit) {
		this.unit = unit;
	}
	
	/**
     * Returns the unit associated with this enum constant.
     *
     * @return the unit as a string.
     */
	public String getUnit() {
		return this.unit;
	}
}
