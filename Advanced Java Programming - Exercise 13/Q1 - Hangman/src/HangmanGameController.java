
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class HangmanGameController {

    @FXML
    private Canvas canvas;

    @FXML
    private ComboBox<Character> charactersComboBox;

    @FXML
    private Label secretWordText;
    
    @FXML
    private Button uploadBtn;
    
    private GraphicsContext gc;
    
    private FileChooser fileChooser;
    
    private File selectedFile = null;
        
    private HangmanGame game;
    
    private final double HANGING_LINE_BASE_X = 20;
    private final double HANGING_LINE_BASE_WIDTH = 150;
    private final double HANGING_LINE_BASE_HEIGHT = 50;
    
    private final double HANGING_LINE_VERTICAL_X = HANGING_LINE_BASE_X + 0.25 * HANGING_LINE_BASE_WIDTH;
    private final double HANGING_LINE_VERTICAL_WIDTH = 20;
    
    private final double HANGING_LINE_HORIZONTAL_X = HANGING_LINE_VERTICAL_X;
    private final double HANGING_LINE_HORIZONTAL_WIDTH = HANGING_LINE_BASE_WIDTH + 50;
    private final double HANGING_LINE_HORIZONTAL_HEIGHT = HANGING_LINE_VERTICAL_WIDTH;
    
    private final double LINE_WIDTH = 5;
    
    private final double BODY_CENTER_X = HANGING_LINE_HORIZONTAL_X + HANGING_LINE_HORIZONTAL_WIDTH;
    private final double BODY_CENTER_Y = HANGING_LINE_HORIZONTAL_HEIGHT;
    
    private final double MOUTH_WIDTH_SCALING_FACTOR = 2;
    
    private final Circle head = new Circle(BODY_CENTER_X, BODY_CENTER_Y, 100);
    private final Circle leftEye = new Circle(BODY_CENTER_X + 15, BODY_CENTER_Y + 20, 40);
    private final Circle rightEye = new Circle(BODY_CENTER_X + 45, BODY_CENTER_Y + 20, 40);
    private final Circle mouth = new Circle(BODY_CENTER_X + 30, BODY_CENTER_Y + 70, 20);
    private final Circle leftPupil = new Circle(BODY_CENTER_X + 20, BODY_CENTER_Y + 18, 20);
    private final Circle rightPupil = new Circle(BODY_CENTER_X + 50, BODY_CENTER_Y + 18, 20);
    private final Circle firstBodyTriangle = new Circle(BODY_CENTER_X + 30, BODY_CENTER_Y + 100, 0);
    
    private final Circle[] hangingmanParts = new Circle[] { 
    		head, leftEye, rightEye, 
    		leftPupil, rightPupil, mouth,
    		firstBodyTriangle
    };
    
    private final Color[] colors = new Color[] {Color.GRAY, Color.rgb(65, 105, 225), Color.AZURE };
    
    private int hangingmanPartIndex = 0;
    
    private final int MAX_NUM_OF_GUESSES = 12;
    
    public void initialize() {
    	gc = canvas.getGraphicsContext2D();
    	fileChooser = new FileChooser();
    	
    	initializeComboBox();
    }
    
    /**
     * Retrieves a random word from the provided file.
     *
     * @param file the file containing words
     * @return a randomly selected word from the file
     */
    private String getRandomWord(File file) {
    	List<String> words = new ArrayList<String>();
    	try {
    		Scanner scanner = new Scanner(file);
    		while (scanner.hasNext()) {
        		words.add(scanner.next());
        	}
    		scanner.close();
    	} catch (IOException e) {
    		System.out.println("Error");
    	}
    	Random rand = new Random();
    	return words.get(rand.nextInt(words.size()));
    }
    
    /**
     * Initializes the graphical components of the canvas.
     */
    private void initializeCanvasGraphics() {
    	displayHangingLine(gc, Color.BLACK);
    }
    
    /**
     * Populates the ComboBox with alphabet letters.
     */
    private void initializeComboBox() {
    	final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    	for (int i = 0; i < ALPHABET.length(); i++) {
    		charactersComboBox.getItems().add(ALPHABET.charAt(i));
    	}
    }
    
    /**
     * Initializes a new Hangman game with a random word from the selected file.
     *
     * @param file            the file containing the words
     * @param maxNumOfGuesses the maximum number of guesses allowed
     */
    private void initializeNewGame(File file, int maxNumOfGuesses) {
    	game = new HangmanGame(getRandomWord(file), maxNumOfGuesses);
    	hangingmanPartIndex = 0;
    	clearCanvas();
    	initializeCanvasGraphics();
    	displayGuessedCharacters();
    }
    
    /**
     * Draws the hanging line on the canvas.
     *
     * @param gc    the graphics context of the canvas
     * @param color the color of the hanging line
     */
    private void displayHangingLine(GraphicsContext gc, Color color) {
    	gc.fillRect(HANGING_LINE_BASE_X, canvas.getHeight() - HANGING_LINE_BASE_HEIGHT, HANGING_LINE_BASE_WIDTH, HANGING_LINE_BASE_HEIGHT);
    	gc.fillRect(HANGING_LINE_VERTICAL_X, 0, HANGING_LINE_VERTICAL_WIDTH, canvas.getHeight());
    	gc.fillRect(HANGING_LINE_HORIZONTAL_X, 0, HANGING_LINE_HORIZONTAL_WIDTH, HANGING_LINE_HORIZONTAL_HEIGHT);
    }

    /**
     * Displays the guessed characters in the secret word.
     */
    private void displayGuessedCharacters() {
    	String currentGuessedWord = "";
    	final char[] arr = game.getDetectedCharactersArray();
    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] == '\u0000') {
    			currentGuessedWord += " _ ";
    		} else {
    			currentGuessedWord += " " + Character.toString(arr[i]) + " ";
    		}
    	}
    	secretWordText.setText(currentGuessedWord);
    }
    
    /**
     * Validates whether a file has been chosen.
     *
     * @param displayAlertDialog whether to display an alert if no file is chosen
     * @return true if a file is chosen, false otherwise
     */
    private boolean validateFileChosen(boolean displayAlertDialog) {
    	if (selectedFile == null) {
    		if (displayAlertDialog) {
        		displayAlert(AlertType.ERROR, "Choose Words File!", "A file of words must be chosen.");
    		}
    		return false;
    	}
    	return true;
    }
    
    /**
     * Displays an alert dialog with the specified parameters.
     *
     * @param alertType the type of alert
     * @param title     the title of the alert
     * @param content   the content of the alert
     */
    private void displayAlert(AlertType alertType, String title, String content) {
    	Alert alert = new Alert(alertType);
    	alert.setHeaderText(title);
    	alert.setContentText(content);
    	alert.showAndWait();
    }
    
    /**
     * Displays the next part of the hanging man on the canvas.
     */
    private void displayNextHangingmanPart() {
    	Circle currentPart = null;
    	
    	if (hangingmanPartIndex < hangingmanParts.length) {
    		currentPart = hangingmanParts[hangingmanPartIndex];
    	}
    
    	// Might have been better to implement a concrete class for each
    	// of the body parts, that will implement an interface BodyPart
    	// and implement its display() method that will display the body part
    	// on the canvas, differently for each body part.
    	
    	if (hangingmanPartIndex >= 0 &&
    			hangingmanPartIndex <= 2) {
    		// draw the head and the eyes.
    		gc.setStroke(Color.BLACK);
    		gc.setLineWidth(LINE_WIDTH);
    		gc.strokeOval(currentPart.getCenterX(), currentPart.getCenterY(), currentPart.getRadius(), currentPart.getRadius());
    	} else if (hangingmanPartIndex >= 3 &&
    			hangingmanPartIndex <= 4) {
    		// draw the pupils.
    		gc.setFill(Color.BLACK);
    		gc.fillOval(currentPart.getCenterX(), currentPart.getCenterY(), currentPart.getRadius(), currentPart.getRadius());
    	} else if (hangingmanPartIndex == 5) {
    		// draw the mouth.
    		gc.setFill(Color.BLACK);
    		gc.fillOval(currentPart.getCenterX(), currentPart.getCenterY(), currentPart.getRadius() * MOUTH_WIDTH_SCALING_FACTOR, currentPart.getRadius());
    	} else if (hangingmanPartIndex == 6) {
    		// draw the body.
    		final double DELTA = 30;
    		
    		for (int i = 0; i < 8; i++) {
    			gc.setFill(colors[new Random().nextInt(colors.length)]);
        		gc.fillRect(currentPart.getCenterX() - DELTA, currentPart.getCenterY() + i * 20, 100, 20);
    		}
    		
    		gc.setFill(Color.BLACK);
    		gc.fillRect(BODY_CENTER_X + 40, BODY_CENTER_Y + 100, 20, 130);
    		
    	} else if (hangingmanPartIndex >= 7 &&
				hangingmanPartIndex <= 8) {
    		// draw the hands.
    		gc.setFill(Color.BLACK);
    		if (hangingmanPartIndex == 7) {
    			gc.fillRect(BODY_CENTER_X - 20, BODY_CENTER_Y + 150, 70, 10);
    		} else if (hangingmanPartIndex == 8) {
    			gc.fillRect(BODY_CENTER_X + 60, BODY_CENTER_Y + 150, 70, 10);
    		}
		} else if (hangingmanPartIndex >= 9 &&
				hangingmanPartIndex <= 10) {
			// draw the legs.
			gc.setFill(Color.BLACK);
			gc.setLineWidth(10);
			
			if (hangingmanPartIndex == 9) {
				gc.strokeLine(BODY_CENTER_X + 45, BODY_CENTER_Y + 230, BODY_CENTER_X + 10, BODY_CENTER_Y + 300);
			} else if (hangingmanPartIndex == 10) {
				gc.strokeLine(BODY_CENTER_X + 55, BODY_CENTER_Y + 230, BODY_CENTER_X + 100, BODY_CENTER_Y + 300);
			}
		} else {
			// hang the hanging man.
    		gc.setStroke(Color.BLACK);
    		gc.strokeLine(BODY_CENTER_X + 50, BODY_CENTER_Y + 140, 70, 30);
		}
    	hangingmanPartIndex += 1;
    }
    
    @FXML
    void btnGuessPressed(ActionEvent event) {
    	boolean fileChosen = validateFileChosen(true);
    	Character guessObject = charactersComboBox.getValue();
    	// No words file was chosen.
    	if (!fileChosen) {
    		return;
    	}
    	// No character guess was chosen.    	
    	if (guessObject == null) {
    		displayAlert(AlertType.ERROR, "Select a Guess!", "Please select your guessed character.");
    		return;
    	}
    	char guessChar = guessObject;
    	if (game.getNumOfUniqueGuesses() < game.getMaxNumOfGuesses()) {
    		// It is only counted as a guess if it was not in the unique guesses set.
        	if (!game.getGuessesSet().contains(charactersComboBox.getValue())) {
        		displayNextHangingmanPart();
        		game.addGuess(guessChar);
        		displayGuessedCharacters();
        	} else {
        		displayAlert(AlertType.INFORMATION, "Enter a Unique Guess!", "Please enter a guess that you haven't made before.");
        	}
        	
    		if (game.getNumOfCorrectGuesses() == game.getSecretWord().length()) {
    			displayAlert(AlertType.INFORMATION, "You Won!", "Great job, you guessed correctly.");
    			return;
    		}
    	} else {
    		displayAlert(AlertType.INFORMATION, "You Lost!", "You lost the game. Please play again.");
    		return;
    	}
    	
    }
    
    public void clearCanvas() {
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    void btnNewGamePressed(ActionEvent event) {
    	validateFileChosen(true);
    	initializeNewGame(selectedFile, MAX_NUM_OF_GUESSES);
    }

    @FXML
    void btnUploadWordsPressed(ActionEvent event) {
    	fileChooser.setInitialDirectory(new File("."));
    	selectedFile = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());
    	validateFileChosen(true);
    	
    	initializeNewGame(selectedFile, MAX_NUM_OF_GUESSES);
    }

}
