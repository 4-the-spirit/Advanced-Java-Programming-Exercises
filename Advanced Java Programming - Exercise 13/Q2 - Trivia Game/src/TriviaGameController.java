import java.io.File;
import java.util.Collections;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class TriviaGameController {

    @FXML
    private ToggleGroup options;
    
    @FXML
    private Button uploadButton;
    
    @FXML
    private Label questionLabel;
    
    private FileChooser fileChooser;
    
    private File selectedFile;
    
    private TriviaGame game;
    
    private final int NUM_OF_WORDS_PER_LINE = 8;
    
    private int currentQuestionIndex = 0;
    
    private double currentScore = 0;
    
    private List<Question> questions;
    
    public void initialize() {
    	fileChooser = new FileChooser();

    }

    @FXML
    void btnNewGamePressed(ActionEvent event) {
    	createNewGame();
    }

    @FXML
    void btnUploadWordsPressed(ActionEvent event) {
    	fileChooser.setInitialDirectory(new File("."));
    	selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
    	createNewGame();
    }
    
    @FXML
    void btnSubmitPressed(ActionEvent event) {
    	Toggle toggle = options.getSelectedToggle();
    	
    	if (selectedFile == null) {
    		displayAlert(AlertType.ERROR, "Select a File!", "Please select a file of questions.");
    		return;
    	}
    	
    	if (currentQuestionIndex >= questions.size()) {
			displayScore();
			return;
		}
    	
    	if (toggle instanceof RadioButton) {
    		RadioButton radioButton = (RadioButton) toggle;
    		Option selectedOption = (Option) radioButton.getUserData();
    		// Check if the selected answer is one of the question answers.
    		if (questions.get(currentQuestionIndex).getAnswers().contains(selectedOption)) {
    			updateScore(true);
    			displayAlert(AlertType.ERROR, "Correct Answer!", "Your answer was correct :)");
    		} else {
    			updateScore(false);
    			displayAlert(AlertType.INFORMATION, "Wrong Answer!", "Your answer was incorrect :(");
    		}
    		currentQuestionIndex += 1;
    		// As long as there are any questions, display them.
    		if (currentQuestionIndex < questions.size()) {
    			displayQuestion(questions.get(currentQuestionIndex));	
    		}
    		// If the user just answered the last question, display the score.
    		if (currentQuestionIndex == questions.size()) {
    			displayScore();
    			return;
    		}
    	}
    }
    
    /**
     * Initializes a new game session. Resets the score, shuffles the questions, and prepares the game for play.
     */
    private void createNewGame() {
    	currentQuestionIndex = 0;
    	currentScore = 0;
    	
		game = new TriviaGame(selectedFile);
		Collections.shuffle(game.getQuestions());
		// Shuffle the options of each question.
		for (Question question : game.getQuestions()) {
			Collections.shuffle(question.getOptions());
		}
		questions = game.getQuestions();
		
    	if (selectedFile == null) {
    		displayAlert(AlertType.ERROR, "Select a File!", "Please select a file of questions.");
    	} else {
    		
        	displayQuestionByIndex(currentQuestionIndex);
    	}
    }
    
    /**
     * Updates the score based on whether the answer is correct or incorrect.
     *
     * @param correctAnswer a boolean indicating if the selected answer is correct
     */
    private void updateScore(boolean correctAnswer) {
    	Question currentQuestion = questions.get(currentQuestionIndex);
    	if (correctAnswer) {
    		currentScore += currentQuestion.CORRECT_OPTION_SCORE;
    	} else {
    		currentScore += currentQuestion.INCORRECT_OPTION_SCORE;
    	}
    }
    
    /**
     * Displays an alert with the specified type, title, and content.
     *
     * @param type   the type of the alert (e.g., ERROR, INFORMATION)
     * @param title  the title of the alert
     * @param text   the content of the alert
     */
    private void displayAlert(AlertType type, String title, String text) {
    	Alert alert = new Alert(type);
    	alert.setHeaderText(title);
    	alert.setContentText(text);
    	alert.showAndWait();
    }
    
    /**
     * Displays the final score after all questions have been answered.
     */
    private void displayScore() {
    	displayAlert(AlertType.INFORMATION, "Your Score", "Your score is" + " " + currentScore + " " + "points.");
    }
    
    /**
     * Displays the current question with its options on the GUI.
     *
     * @param question the current question to be displayed
     */
    private void displayQuestion(Question question) {
    	questionLabel.setText(breakStringToLines(question.getQuestion(), NUM_OF_WORDS_PER_LINE));
    	
    	for (int i = 0; i < options.getToggles().size(); i++) {
    		Toggle toggle = options.getToggles().get(i);
    		if (toggle instanceof RadioButton) {
    			RadioButton radioButton = (RadioButton) toggle;
    			radioButton.setText("(" + (i + 1) + ")" + " " + question.getOptions().get(i).getText());
    			radioButton.setUserData(question.getOptions().get(i));
    		}
    	}
    }
    
    /**
     * Displays a question by its index in the list of questions.
     *
     * @param index the index of the question to be displayed
     */
    private void displayQuestionByIndex(int index) {
    	displayQuestion(questions.get(index));
    }
    
    /**
     * Breaks a string into lines of a specified length, ensuring no more than `breakEveryKWords` words per line.
     *
     * @param string           the string to be broken into lines
     * @param breakEveryKWords the number of words to be displayed per line
     * @return a string with line breaks inserted after the specified number of words
     */
    private String breakStringToLines(String string, int breakEveryKWords) {
    	StringBuilder sb = new StringBuilder();
    	String[] words = string.split(" ");
    	for (int i = 0; i < words.length; i++) {
    		if ((i % breakEveryKWords == 0) && (i != 0)) {
    			sb.append("\n");
    		}
    		sb.append(words[i]);
    		sb.append(" ");
    	}
    	return sb.toString();
    }

}
