import java.util.HashSet;
import java.util.Set;

/**
 * The HangmanGame class manages the core logic of the Hangman game.
 * It tracks the secret word, guessed letters, and the player's progress.
 */
public class HangmanGame {
	// Constants we don't want to provide access for.
	private final String SECRET_WORD;
	private final Set<Character> guessesSet = new HashSet<Character>();
	
	private final int maxNumOfGuesses;
	private final char[] detectedCharactersArray;
	
	private int numOfUniqueGuesses = 0;
	
	/**
     * Constructs a HangmanGame instance with the given secret word and maximum number of guesses.
     *
     * @param secretWord      the word to be guessed (case-insensitive)
     * @param maxNumOfGuesses the maximum number of unique guesses allowed
     */
	public HangmanGame(
			String secretWord, 
			int maxNumOfGuesses) {
		this.SECRET_WORD = secretWord.toLowerCase();
		this.maxNumOfGuesses = maxNumOfGuesses;
		this.detectedCharactersArray = new char[this.SECRET_WORD.length()];
	}

	/**
     * Adds a character guess to the game.
     * If the guess is correct, the character is revealed in the secret word.
     * Throws an exception if the number of unique guesses exceeds the maximum allowed.
     *
     * @param character the character to guess
     * @throws NumberOfGuessesExceededException if the number of guesses exceeds the limit
     */
	public void addGuess(char character) {
		character = Character.toLowerCase(character);
		if (getNumOfUniqueGuesses() >= getMaxNumOfGuesses()) {
			throw new NumberOfGuessesExceededException("The total number of guesses must be lower than the maximum number of guesses!");
		}
		if (!guessesSet.contains(character)) {
			guessesSet.add(character);
			setNumOfUniqueGuesses(getNumOfUniqueGuesses() + 1);
			makeCharacterVisible(character);
		}
	}
	
	/**
     * Reveals all occurrences of the guessed character in the detectedCharactersArray.
     *
     * @param character the character to reveal
     */
	private void makeCharacterVisible(char character) {
		for (int i = 0; i < SECRET_WORD.length(); i++) {
			if (SECRET_WORD.charAt(i) == character) {
				getDetectedCharactersArray()[i] = character;
			}
		}
	}

	/**
     * Gets the number of unique guesses made by the player.
     *
     * @return the number of unique guesses
     */
	public int getNumOfUniqueGuesses() {
		return numOfUniqueGuesses;
	}

	/**
     * Sets the number of unique guesses made by the player.
     *
     * @param numOfUniqueGuesses the new number of unique guesses
     */
	public void setNumOfUniqueGuesses(int numOfUniqueGuesses) {
		this.numOfUniqueGuesses = numOfUniqueGuesses;
	}

	/**
     * Gets the maximum number of unique guesses allowed.
     *
     * @return the maximum number of guesses
     */
	public int getMaxNumOfGuesses() {
		return maxNumOfGuesses;
	}

	/**
     * Gets the array representing the characters revealed in the secret word.
     *
     * @return the detected characters array
     */
	public char[] getDetectedCharactersArray() {
		return detectedCharactersArray;
	}
	
	/**
     * Gets the secret word.
     *
     * @return the secret word
     */
	public String getSecretWord() {
		return SECRET_WORD;
	}
	
	/**
     * Gets the number of correctly guessed characters in the secret word.
     *
     * @return the number of correct guesses
     */
	public int getNumOfCorrectGuesses() {
		int num = 0;
		char[] arr = getDetectedCharactersArray();
		for (int i = 0; i < arr.length; i++) {
			num += (arr[i] == '\u0000' ? 0 : 1);
		}
		return num;
	}

	/**
     * Gets the set of unique guessed characters.
     *
     * @return the set of guessed characters
     */
	public Set<Character> getGuessesSet() {
		return guessesSet;
	}
	
	
	
}
