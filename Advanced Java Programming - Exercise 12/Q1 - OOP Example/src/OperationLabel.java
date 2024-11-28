/**
 * Enum representing various operations that animals can perform, such as eating, sleeping, barking, hunting, and climbing.
 * Each enum constant is associated with a string describing the specific operation.
 */
public enum OperationLabel {
	EAT("Eating"),
	SLEEP("Sleeping"),
	BARK("Barking"),
	HUNT("Hunting"),
	CLIMB("Climbing");
	
	private String operation;
	
	/**
     * Constructs an {@code OperationLabel} with the specified operation string.
     *
     * @param operation the string describing the operation.
     */
	OperationLabel(String operation) {
		this.operation = operation;
	}
	
	/**
     * Retrieves the string representing the operation for this enum constant.
     *
     * @return the string describing the operation.
     */
	public String getOperation() {
		return this.operation;
	}
}
