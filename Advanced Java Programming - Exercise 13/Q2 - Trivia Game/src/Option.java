
/**
 * Represents an option for a trivia question, encapsulating the option's text.
 * This class also overrides the `toString` and `equals` methods to provide customized string representation 
 * and equality comparison for options.
 */
public class Option {
	private String text;
	
	/**
     * Constructs an Option with the provided text.
     * 
     * @param text the text of the option
     */
	public Option(String text) {
		this.text = text;
	}
	
	/**
     * Returns a string representation of the option (the option's text).
     * 
     * @return the text of the option
     */
	@Override
	public String toString() {
		return getText();
	}
	
	/**
     * Compares this option to another object for equality.
     * Two options are considered equal if their texts are the same.
     * 
     * @param other the object to compare this option to
     * @return true if the options have the same text, false otherwise
     */
	@Override
	public boolean equals(Object other) {
		return this.getText().equals(((Option) other).getText()); 
	}
	
	/**
     * Returns the text of the option.
     * 
     * @return the text of the option
     */
	public String getText() {
		return text;
	}
	
	/**
     * Sets the text of the option.
     * 
     * @param text the new text for the option
     */
	public void setText(String text) {
		this.text = text;
	}
}
