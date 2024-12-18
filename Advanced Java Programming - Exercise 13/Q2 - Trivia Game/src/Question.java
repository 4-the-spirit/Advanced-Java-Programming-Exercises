import java.util.List;

/**
 * Represents a trivia question with a set of options and correct answers.
 * The class also contains scoring logic for correct and incorrect answers.
 */
public class Question {
	private final String question;
	private final List<Option> options;
	private final List<Option> answers;
	
	public final double CORRECT_OPTION_SCORE = 10.0;
	public final double INCORRECT_OPTION_SCORE = -5.0; 
	
	/**
     * Constructs a Question with a question title, a list of options, and a list of correct answers.
     * It also validates that the answers provided are part of the options.
     * 
     * @param question the text of the question
     * @param options the list of possible options for the question
     * @param answers the list of correct answers
     * @throws IllegalArgumentException if any of the answers is not a valid option
     */
	public Question(String question, List<Option> options, List<Option> answers) {
		this.question = question;
		this.options = options;
		this.answers = answers;
		
		validateAnswers();
	}
	
	/**
     * Validates that each answer provided is part of the list of options.
     * If any answer is not a valid option, throws an IllegalArgumentException.
     */
	private void validateAnswers() {
		for (Option answer : getAnswers()) {
			if (!getOptions().contains(answer)) {
				throw new IllegalArgumentException("Please provide a list of answers that each is an existing option!");
			}
		}
	}

	/**
     * Returns the list of options for this question.
     * 
     * @return a list of options
     */
	public List<Option> getOptions() {
		return options;
	}

	/**
     * Returns the list of correct answers for this question.
     * 
     * @return a list of correct answers
     */
	public List<Option> getAnswers() {
		return answers;
	}

	/**
     * Returns the text of the question.
     * 
     * @return the question text
     */
	public String getQuestion() {
		return question;
	}
	
	
	
}
