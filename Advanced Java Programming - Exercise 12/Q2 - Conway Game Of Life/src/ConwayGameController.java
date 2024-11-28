import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ConwayGameController {

    @FXML
    private Canvas canvas;
    
    @FXML
    private ComboBox<String> comboBox;
    
    private GraphicsContext gc;
    
    public static final Color ALIVE_COLOR = Color.rgb(65, 105, 225);
    public static final Color DEATH_COLOR = Color.WHITE;
    
    public static final double LINE_WIDTH = 7;
    
    public static final double X_SPACE_SHIFTING_FACTOR = 150;
    public static final double X_SPACE_SCALING_FACTOR = 5;
    public static final double Y_SPACE_SCALING_FACTOR = 5;
    
    public static final int BOARD_SIZE = 10;
    public static final int BOARD_SIZE_START = 2;
    public static final int BOARD_SIZE_END = 20;
    
    public static final double RECTANGLE_WIDTH = 20;
    public static final double RECTANGLE_HEIGHT = 20;
    
    private ConwayCellsArray cellsArray = null;
    
    
    public void initialize() {
    	gc = canvas.getGraphicsContext2D();
    	initComboBox();
    }
    
    @FXML
    void btnNewGamePressed(ActionEvent event) {
    	cellsArray = new ConwayCellsArray(BOARD_SIZE, BOARD_SIZE);
    	displayRectangles(transformRectangles(getRectangles()));
    }

    @FXML
    void btnNextPressed(ActionEvent event) {
    	if (cellsArray == null) {
    		cellsArray = new ConwayCellsArray(BOARD_SIZE, BOARD_SIZE);
    	}
    	displayNextGeneration();
    }

    @FXML
    void btnPreviousPressed(ActionEvent event) {

    }
    
    private void initComboBox() {
    	for (int i = BOARD_SIZE_START; i <= BOARD_SIZE_END; i++) {
    		comboBox.getItems().add(Integer.toString(i));
    	}
    }
    
    /**
     * Updates the cells array to the next generation and then displays the updated grid of cells.
     * <p>
     * This method first calculates the next generation of cells based on the current state of the cells array.
     * It then transforms the array of rectangles representing the cells (such as adding spacing and shifting),
     * and finally, it visually displays the transformed array of rectangles.
     * </p>
     */
    private void displayNextGeneration() {
    	cellsArray.setCellsArray(cellsArray.getNextGeneration());
    	Rectangle[][] rects = transformRectangles(getRectangles());
    	displayRectangles(rects);
    }
    
    /**
     * Transforms the given array of rectangles representing the cells in the grid.
     * <p>
     * This method adds vertical and horizontal spacing, as well as vertical shifting, to adjust
     * the layout of the rectangles. It is used to improve the visual appearance of the grid of cells
     * in the user interface.
     * </p>
     *
     * @param rectangles the two-dimensional array of rectangles representing the cells
     * @return the transformed two-dimensional array of rectangles with the added spacing and shifting
     */
    private Rectangle[][] transformRectangles(Rectangle[][] rectangles) {
    	return addVerticalShifting(addHorizontalSpacing(addVerticalSpacing(rectangles, Y_SPACE_SCALING_FACTOR), X_SPACE_SCALING_FACTOR), X_SPACE_SHIFTING_FACTOR);
    }
    
    /**
     * Retrieves the current 2D array of rectangles.
     *
     * @return a 2D array of {@link Rectangle} objects
     */
    private Rectangle[][] getRectangles() {
    	Rectangle[][] rects = new Rectangle[cellsArray.NUMBER_OF_ROWS][cellsArray.NUMBER_OF_COLUMNS];
    	Rectangle tempRect;
    	LifeState cellState;
    	
    	for (int i = 0; i < cellsArray.NUMBER_OF_ROWS; i++) {
    		for (int j = 0; j < cellsArray.NUMBER_OF_COLUMNS; j++) {
    			tempRect = new Rectangle();
    			tempRect.setX(j * RECTANGLE_WIDTH);
    			tempRect.setY(i * RECTANGLE_HEIGHT);
    			tempRect.setWidth(RECTANGLE_WIDTH);
    			tempRect.setHeight(RECTANGLE_HEIGHT);

    			cellState = cellsArray.getCellLifeState(i, j);
    			if (cellState == LifeState.BIRTH || cellState == LifeState.EXISTENCE) {
    				tempRect.setFill(ALIVE_COLOR);
    			} else if (cellState == LifeState.DEATH) {
    				tempRect.setFill(DEATH_COLOR);
    			}
    			rects[i][j] = tempRect;
    		}
    	}
    	return rects;
    }
    
    /**
     * Adds vertical spacing between columns of rectangles in the given 2D array.
     *
     * @param rectangles a 2D array of {@link Rectangle} objects
     * @param space the amount of vertical space to add between columns
     * @return a new 2D array of {@link Rectangle} objects with adjusted vertical spacing
     */
    private Rectangle[][] addVerticalSpacing(Rectangle[][] rectangles, double space) {
    	Rectangle tempRect;
    	for (int j = 1; j < cellsArray.NUMBER_OF_COLUMNS; j++) {
    		for (int i = 0; i < cellsArray.NUMBER_OF_ROWS; i++) {
    			tempRect = rectangles[i][j];
    			tempRect.setX(tempRect.getX() + j * space);
    		}
    	}
    	return rectangles;
    }
    
    /**
     * Adds horizontal spacing between rows of rectangles in the given 2D array.
     *
     * @param rectangles a 2D array of {@link Rectangle} objects
     * @param space the amount of horizontal space to add between rows
     * @return a new 2D array of {@link Rectangle} objects with adjusted horizontal spacing
     */
    private Rectangle[][] addHorizontalSpacing(Rectangle[][] rectangles, double space) {
    	Rectangle tempRect;
    	for (int i = 0; i < cellsArray.NUMBER_OF_ROWS; i++) {
    		for (int j = 0; j < cellsArray.NUMBER_OF_COLUMNS; j++) {
    			tempRect = rectangles[i][j];
    			tempRect.setY(tempRect.getY() + i * space);
    		}
    	}
    	return rectangles;
    }
    
    /**
     * Applies a vertical shift to all rows of rectangles in the given 2D array.
     *
     * @param rectangles a 2D array of {@link Rectangle} objects
     * @param shift the amount by which to shift all rows vertically
     * @return a new 2D array of {@link Rectangle} objects with adjusted vertical positions
     */
    private Rectangle[][] addVerticalShifting(Rectangle[][] rectangles, double shift) {
    	Rectangle tempRect;
    	for (int i = 0; i < cellsArray.NUMBER_OF_ROWS; i++) {
    		for (int j = 0; j < cellsArray.NUMBER_OF_COLUMNS; j++) {
    			tempRect = rectangles[i][j];
    			tempRect.setX(tempRect.getX() + shift);
    		}
    	}
    	return rectangles;
    }
    
    /**
     * Displays the given 2D array of rectangles.
     * This method is responsible for rendering the rectangles visually, such as on a canvas or a graphical interface.
     *
     * @param rectangles a 2D array of {@link Rectangle} objects to display
     */
    private void displayRectangles(Rectangle[][] rectangles) {
    	Rectangle tempRect;
    	for (int i = 0; i < rectangles.length; i++) {
    		for (int j = 0; j < rectangles[i].length; j++) {
    			tempRect = rectangles[i][j];
    			gc.setFill(tempRect.getFill());
    			gc.fillRect(tempRect.getX(), tempRect.getY(), tempRect.getWidth(), tempRect.getHeight());
    		}
    	}
    }
    

}
