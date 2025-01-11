import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HomeController {
	@FXML
	private Canvas canv;
	private GraphicsContext gc;
	private Circle circle;
	private double MOVE = 10.0;
	private double SIZE = 20.0;

	public void initialize() {
		gc = canv.getGraphicsContext2D();
		circle = new Circle(canv.getWidth() / 2, canv.getHeight() / 2, SIZE / 2);
	}

	public void displayCircle() {
		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		gc.fillOval(circle.getCenterX(), circle.getCenterY(), SIZE, SIZE);
	}

	private void centerCircle() {
		if (circle.getCenterX() > canv.getWidth() || circle.getCenterY() > canv.getHeight()) {
			circle.setCenterX(canv.getWidth() / 2);
			circle.setCenterY(canv.getHeight() / 2);
		}
	}

	@FXML
	public void leftBtnPressed(MouseEvent event) {
		circle.setCenterX(circle.getCenterX() - MOVE);
		centerCircle();
		displayCircle();
	}

	@FXML
	public void rightBtnPressed(MouseEvent event) {
		circle.setCenterX(circle.getCenterX() + MOVE);
		centerCircle();
		displayCircle();
	}

	@FXML
	public void upBtnPressed(MouseEvent event) {
		circle.setCenterY(circle.getCenterY() - MOVE);
		centerCircle();
		displayCircle();
	}

	@FXML
	public void downBtnPressed(MouseEvent event) {
		circle.setCenterY(circle.getCenterY() + MOVE);
		centerCircle();
		displayCircle();
	}
}
