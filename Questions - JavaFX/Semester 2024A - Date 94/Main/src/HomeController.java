import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

public class HomeController {
	@FXML
	private Canvas canv;
	private GraphicsContext gc;
	private List<Ad> ads;
	
	public void initialize() {
		gc = canv.getGraphicsContext2D();
		ads = new ArrayList<Ad>();
	}
	
	@FXML
	public void canvasPressed(MouseEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		Optional<String> res = dialog.showAndWait();
		if (!res.isPresent()) {
			return;
		}
		String msg = res.get();
		Ad ad = new Ad(msg, event.getX(), event.getY());
		int index = findLocalAdIndex(ad);
		if (index >= 0) {
			ads.remove(index);
		}
		ads.add(ad);
		displayAds();
	}
	
	public void displayAds() {
		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		for (Ad ad : ads) {
			gc.strokeText(ad.getText(), ad.getX(), ad.getY());
		}
	}
	
	public int findLocalAdIndex(Ad ad) {
		final double MIN_DIST = 20.0;
		for (int i = 0; i < ads.size(); i++) {
			if (Math.abs(ads.get(i).getX() - ad.getX()) <= MIN_DIST
					|| Math.abs(ads.get(i).getY() - ad.getY()) <= MIN_DIST) {
				return i;
			}
		}
		return -1;
	}	
}
