import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HomeController extends Thread {
	@FXML
	private Canvas canv;
	private GraphicsContext gc;
	private List<Circle> circles;
	private final double SIZE = 10.0;
	private final double TIME = 200.0;

	public void initialize() {
		gc = canv.getGraphicsContext2D();
		circles = new ArrayList<Circle>();
		start();
	}

	private void displayCircles() {
		gc.clearRect(0, 0, canv.getWidth(), canv.getWidth());
		for (Circle c : circles) {
			gc.fillOval(c.getCenterX(), c.getCenterY(), SIZE, SIZE);
		}
	}

	@FXML
	private void canvasPressed(MouseEvent event) {
		circles.add(new Circle(event.getX(), event.getY(), SIZE / 2, Color.BLACK));
	}

	private void updateCircles() {
		Random rand = new Random();
		for (Circle c : circles) {
			c.setCenterX(canv.getWidth() * rand.nextDouble());
			c.setCenterY(canv.getHeight() * rand.nextDouble());
		}
	}

	public void run() {
		while (true) {
			try {
				displayCircles();
				sleep((long) TIME);
				updateCircles();
			} catch (InterruptedException exp) {

			}
		}
	}
}
