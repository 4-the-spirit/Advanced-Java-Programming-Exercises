import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Trivia Game where players answer questions and score points.
 * The game can be initialized with a list of questions or read questions from a file.
 * It supports a fixed number of options per question and checks for correct answers.
 */
public class TriviaGame {
	private final static int NUM_OF_QUESTION_OPTIONS = 4;
	private final static int NUM_OF_QUESTION_ANSWERS = 1;

	private final List<Question> questions;
	private double totalScore = 0;

	/**
     * Constructs a TriviaGame with a given list of questions.
     * 
     * @param questions the list of questions to be used in the game
     */
	public TriviaGame(List<Question> questions) {
		this.questions = questions;
	}

	/**
     * Constructs a TriviaGame by reading questions from a file and selecting a specific number of them.
     * 
     * @param file the file containing trivia questions
     * @param numOfQuestions the number of questions to select from the file
     */
	public TriviaGame(File file, int numOfQuestions) {
		this.questions = chooseQuestions(readQuestions(file), numOfQuestions);
	}
	
	/**
     * Constructs a TriviaGame by reading questions from a file.
     * 
     * @param file the file containing trivia questions
     */
	public TriviaGame(File file) {
		this.questions = readQuestions(file);
	}

	/**
     * Reads trivia questions from a file. The file format expects each question
     * to be followed by a list of options, with one correct answer.
     * 
     * @param file the file containing trivia questions
     * @return a list of questions read from the file
     */
	public List<Question> readQuestions(File file) {
		BufferedReader br = null;
		
		final int NUM_OF_QUESTION_LINES = TriviaGame.NUM_OF_QUESTION_OPTIONS + 1;
		boolean foundNullLine = false;
		List<String> questionLines;
		
		List<Question> questions = new ArrayList<Question>();
		
		try {
			br = new BufferedReader(new FileReader(file));
			while (true) {
				questionLines = new ArrayList<String>();
				String line;
				// Scan question lines.
				for (int i = 0; i < NUM_OF_QUESTION_LINES; i++) {
					try {
						if ((line = br.readLine()) == null) {
							foundNullLine = true;
							break;
						} else {
							questionLines.add(line);
						}
					} catch (IOException exp) {
						System.out.println(exp.getMessage());
					}
				}
				// Check if we couldn't scan a question successfully.
				if (foundNullLine) {
					break;
				}
				
				Question question;
				String title = questionLines.get(0);
				List<Option> options = new ArrayList<Option>();
				List<Option> answers = new ArrayList<Option>();
				
				questionLines.remove(0);
				
				for (String questionLine : questionLines) {
					options.add(new Option(questionLine));
				}
				
				for (int i = 0; i < TriviaGame.NUM_OF_QUESTION_ANSWERS; i++) {
					answers.add(new Option(questionLines.get(i)));
				}

				question = new Question(title, options, answers);
				questions.add(question);
			}
			br.close();
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
		}
		return questions;
	}
	
	/**
     * Randomly selects a specified number of questions from a list.
     * 
     * @param questions the list of all questions
     * @param numOfQuestions the number of questions to select
     * @return a list of randomly selected questions
     */
	private List<Question> chooseQuestions(List<Question> questions, int numOfQuestions) {
		List<Question> allQuestions = new ArrayList<Question>(questions);
		List<Question> chosenQuestions = new ArrayList<Question>();
		Random random = new Random();
		int index = 0;
		while (numOfQuestions > 0) {
			index = random.nextInt(allQuestions.size());
			chosenQuestions.add(allQuestions.get(index));
			allQuestions.remove(index);
			numOfQuestions -= 1;
		}
		return chosenQuestions;
	}

	/**
     * Returns the list of questions used in the game.
     * 
     * @return the list of trivia questions
     */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
     * Returns the total score accumulated by the player.
     * 
     * @return the total score of the player
     */
	public double getTotalScore() {
		return totalScore;
	}

	/**
     * Sets the total score for the player.
     * 
     * @param totalScore the total score to set
     */
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
}
