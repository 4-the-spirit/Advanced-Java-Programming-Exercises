/**
 * A custom exception that is thrown when the number of guesses in the Hangman game
 * exceeds the allowed maximum.
 */
public class NumberOfGuessesExceededException extends RuntimeException {
	/**
     * Constructs a new NumberOfGuessesExceededException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
	public NumberOfGuessesExceededException(String message) {
		super(message);
	}

}
