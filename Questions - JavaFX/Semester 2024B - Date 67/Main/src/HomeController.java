import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class HomeController {
	@FXML
	private Canvas canvUp, canvDown;
	private GraphicsContext gcUp, gcDown;
	private List<Rectangle> rectsUp, rectsDown;
	private int numOfMistakes = 0;
	private final double MIN_SIZE = 10.0, MAX_SIZE = 100.0;
	private final int NUM_OF_SQUARES = 10;

	public void initialize() {
		gcUp = canvUp.getGraphicsContext2D();
		gcDown = canvDown.getGraphicsContext2D();
		rectsUp = new ArrayList<Rectangle>();
		rectsDown = new ArrayList<Rectangle>();
		generateSquares();
		displaySquares();
	}

	private void generateSquares() {
		Random rand = new Random();
		for (int i = 0; i < NUM_OF_SQUARES; i++) {
			int size = rand.nextInt((int) (MAX_SIZE - MIN_SIZE + 1)) + (int) MIN_SIZE;
			rectsUp.add(new Rectangle(rand.nextDouble() * canvUp.getWidth(), rand.nextDouble() * canvUp.getHeight(),
					size, size));
		}
	}

	private void displaySquares() {
		gcUp.clearRect(0, 0, canvUp.getWidth(), canvUp.getHeight());
		gcDown.clearRect(0, 0, canvDown.getWidth(), canvDown.getHeight());

		for (Rectangle r : rectsUp) {
			gcUp.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		}
		
		for (Rectangle r : rectsDown) {
			gcDown.strokeRect(0, 0, r.getWidth(), r.getHeight());
		}
	}
	
	@FXML
	public void canvUpPressed(MouseEvent event) {
		Rectangle smallest = minSquare(rectsUp);
		Rectangle pressed = minPressed(event.getX(), event.getY());
		
		if (pressed == null || !smallest.equals(pressed)) {
			numOfMistakes += 1;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Wrong");
			alert.showAndWait();
		} else {
			rectsUp.remove(pressed);
			rectsDown.add(pressed);
			pressed.setX(0);
			pressed.setY(0);
			displaySquares();
		}
		if (rectsUp.size() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Mistakes:" + " " + numOfMistakes);
			alert.showAndWait();
		}
	}
	
	private Rectangle minSquare(List<Rectangle> rects) {
		Rectangle minSquare = rects.get(0);
		for (Rectangle r : rects) {
			if (r.getWidth() < minSquare.getWidth()) {
				minSquare = r;
			}
		}
		return minSquare;
	}
	
	private Rectangle minPressed(double x, double y) {
		List<Rectangle> pressed = new ArrayList<Rectangle>();
		for (Rectangle r : rectsUp) {
			if (r.contains(x, y)) {
				pressed.add(r);
			}
		}
		return minSquare(pressed);
	}
}
