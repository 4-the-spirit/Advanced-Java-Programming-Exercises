import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("HangmanGame.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Hangman Game");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);		
	}
}

