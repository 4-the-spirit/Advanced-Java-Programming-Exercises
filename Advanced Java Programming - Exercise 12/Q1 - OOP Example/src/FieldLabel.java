/**
 * Enum representing different field labels used to describe an animal's attributes and operations.
 * Each field label corresponds to a specific attribute or operation that can be displayed in an animal's information.
 */
public enum FieldLabel {
	TYPE("Type"), 
	NAME("Name"), 
	AGE("Age"), 
	OPERATION("Operation"), 
	ADDITIONAL_INFORMATION("Additional Information");
	
	private String fieldName;
	
	/**
     * Constructs a FieldLabel with the specified name.
     *
     * @param fieldName the name of the field label (e.g., "Type", "Name", etc.).
     */
	FieldLabel(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
     * Retrieves the field name associated with this label.
     *
     * @return the string representing the field name (e.g., "Type", "Name", etc.).
     */
	public String getFieldName() {
		return this.fieldName;
	}
}
