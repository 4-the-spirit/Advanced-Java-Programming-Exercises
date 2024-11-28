import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A class representing a two-dimensional array of cells for use in Conway's Game of Life.
 * This class manages the state of each cell in the grid, updates the cells for each generation,
 * and applies the rules of the game to determine whether a cell survives, is born, or dies.
 * It provides methods for getting, setting, and manipulating cells, as well as tracking the history
 * of cell states for visualization or debugging purposes.
 */
public class ConwayCellsArray {
	public static final int MIN_NUMBER_OF_NEIGHBORS = 3;
	public static final int MAX_NUMBER_OF_NEIGHBORS = 8;

	public static final int MIN_LIVING_NEIGHBORS = 0;
	public static final int MAX_LIVING_NEIGHBORS = MAX_NUMBER_OF_NEIGHBORS;

	private static final int BIRTH_LIVING_NEIGHBORS_START = 3;
	private static final int BIRTH_LIVING_NEIGHBORS_END = 3;

	private static final int LONELINESS_LIVING_NEIGHBORS_START = 0;
	private static final int LONELINESS_LIVING_NEIGHBORS_END = 1;

	private static final int OVERPOPULATION_LIVING_NEIGHBORS_START = 4;
	private static final int OVERPOPULATION_LIVING_NEIGHBORS_END = MAX_LIVING_NEIGHBORS;

	private static final int EXISTENCE_LIVING_NEIGHBORS_START = 2;
	private static final int EXISTENCE_LIVING_NEIGHBORS_END = 3;

	public static final Range BIRTH_VALUES = new Range(BIRTH_LIVING_NEIGHBORS_START, BIRTH_LIVING_NEIGHBORS_END);
	public static final Range LONELINESS_DEATH_VALUES = new Range(LONELINESS_LIVING_NEIGHBORS_START,
			LONELINESS_LIVING_NEIGHBORS_END);
	public static final Range OVERPOPULATION_DEATH_VALUES = new Range(OVERPOPULATION_LIVING_NEIGHBORS_START,
			OVERPOPULATION_LIVING_NEIGHBORS_END);
	public static final Range EXISTENCE_VALUES = new Range(EXISTENCE_LIVING_NEIGHBORS_START,
			EXISTENCE_LIVING_NEIGHBORS_END);
	public static final List<Range> OTHER_VALUES = new ArrayList<Range>();

	public final int NUMBER_OF_ROWS;
	public final int NUMBER_OF_COLUMNS;
	
	/*
	 * Design the cells array as an array of Cell objects to make it scalable to
	 * adding properties to the cells and conditioning the game of life rules based
	 * on it.
	 */
	private Cell[][] cellsArray;
	private List<Cell[][]> cellsArrayHistory;
	private int cellsArrayHistoryIndex = 0;

	{
		OTHER_VALUES.add(new Range(MIN_LIVING_NEIGHBORS, BIRTH_LIVING_NEIGHBORS_END - 1));
		OTHER_VALUES.add(new Range(BIRTH_LIVING_NEIGHBORS_END + 1, MAX_LIVING_NEIGHBORS));
	}

	/**
	 * Initializes a new ConwayCellsArray object with the specified number of rows and columns.
	 * 
	 * @param NUMBER_OF_ROWS the number of rows in the grid
	 * @param NUMBER_OF_COLUMNS the number of columns in the grid
	 */
	public ConwayCellsArray(final int NUMBER_OF_ROWS, final int NUMBER_OF_COLUMNS) {
		this.NUMBER_OF_ROWS = NUMBER_OF_ROWS;
		this.NUMBER_OF_COLUMNS = NUMBER_OF_COLUMNS;

		cellsArray = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		cellsArrayHistory = new LinkedList<Cell[][]>();

		initializeCellsArray(cellsArray);
	}

	/**
	 * Computes the next generation of the cell grid based on Conway's Game of Life rules.
	 * 
	 * @return a 2D array of {@link Cell} objects representing the next generation
	 */
	public Cell[][] getNextGeneration() {
		Cell[][] nextGenerationArray = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		LifeState cellState;
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				nextGenerationArray[i][j] = new Cell(this.getCell(i, j));
				cellState = getCellLifeState(i, j);
				if (cellState == LifeState.BIRTH || cellState == LifeState.EXISTENCE) {
					nextGenerationArray[i][j].setAlive(true);
				} else if (cellState == LifeState.DEATH) {
					nextGenerationArray[i][j].setAlive(false);
				}
			}
		}
		return nextGenerationArray;
	}
	
	/**
	 * Retrieves the life state of the cell at the specified position.
	 * 
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @return the {@link LifeState} of the cell
	 */
	public LifeState getCellLifeState(int row, int column) {
		LifeRule rule = getCellLifeRule(row, column);
		LifeState state = null;

		if (rule == LifeRule.BIRTH) {
			state = LifeState.BIRTH;
		} else if (rule == LifeRule.LONELINESS_DEATH || rule == LifeRule.OVERPOPULATION_DEATH) {
			state = LifeState.DEATH;
		} else if (rule == LifeRule.EXISTENCE) {
			state = LifeState.EXISTENCE;
		} else if (rule == LifeRule.OTHER) {
			state = LifeState.DEATH;
		}
		if (state == null) {
			throw new IllegalArgumentException("The following cell does not match any life state!");
		}
		return state;
	}

	/**
	 * Retrieves the life rule associated with the cell at the specified position.
	 * 
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @return the {@link LifeRule} applied to the cell
	 */
	public LifeRule getCellLifeRule(int row, int column) {
		final Cell currentCell = getCell(row, column);
		final List<Cell> neighbors = getCellNeighbors(row, column);

		int howManyAlive = 0;
		LifeRule rule = null;

		for (Cell cell : neighbors) {
			howManyAlive += (cell.isAlive() ? 1 : 0);
		}
		if (LifeRule.BIRTH.satisfiesRule(currentCell, howManyAlive)) {
			rule = LifeRule.BIRTH;
		} else if (LifeRule.LONELINESS_DEATH.satisfiesRule(currentCell, howManyAlive)) {
			rule = LifeRule.LONELINESS_DEATH;
		} else if (LifeRule.OVERPOPULATION_DEATH.satisfiesRule(currentCell, howManyAlive)) {
			rule = LifeRule.OVERPOPULATION_DEATH;
		} else if (LifeRule.EXISTENCE.satisfiesRule(currentCell, howManyAlive)) {
			rule = LifeRule.EXISTENCE;
		} else {
			rule = LifeRule.OTHER;
		}
		return rule;
	}

	/**
	 * Checks whether the cell at the specified position is alive.
	 * 
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @return {@code true} if the cell is alive, {@code false} otherwise
	 */
	public boolean isCellAlive(int row, int column) {
		return getCell(row, column).isAlive();
	}

	/**
	 * Sets the alive state of the cell at the specified position.
	 * 
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @param alive {@code true} to make the cell alive, {@code false} to make it dead
	 */
	public void setCellAlive(int row, int column, boolean alive) {
		getCell(row, column).setAlive(alive);
	}

	/**
	 * Toggles the alive state of the cell at the specified position.
	 * 
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @return {@code true} if the cell was alive, {@code false} if it was dead
	 */
	public boolean toggleCellAlive(int row, int column) {
		return getCell(row, column).toggleAlive();
	}

	/**
	 * Retrieves a list of neighboring cells for the cell at the specified position.
	 * 
	 * @param row the row index of the target cell
	 * @param column the column index of the target cell
	 * @return a {@link List} of neighboring {@link Cell} objects
	 */
	public List<Cell> getCellNeighbors(int row, int column) {
		final int RELATIVE_ROW_START = -1;
		final int RELATIVE_ROW_END = 1;
		final int RELATIVE_COLUMN_START = -1;
		final int RELATIVE_COLUMN_END = 1;

		List<Cell> neighbors = new ArrayList<Cell>();

		for (int neighborRow = row + RELATIVE_ROW_START; neighborRow <= row + RELATIVE_ROW_END; neighborRow++) {
			for (int neighborColumn = column + RELATIVE_COLUMN_START; neighborColumn <= column
					+ RELATIVE_COLUMN_END; neighborColumn++) {
				if (isValidRow(neighborRow) && isValidColumn(neighborColumn)) {
					neighbors.add(getCell(neighborRow, neighborColumn));
				}
			}
		}
		neighbors.remove(getCell(row, column));
		return neighbors;
	}

	/**
	 * Initializes the cells array with the specified dimensions and sets default cell states.
	 * 
	 * @param cellsArray a 2D array of {@link Cell} objects to initialize
	 */
	private void initializeCellsArray(Cell[][] cellsArray) {
		Random random = new Random();
		for (int i = 0; i < cellsArray.length; i++) {
			for (int j = 0; j < cellsArray[0].length; j++) {
				cellsArray[i][j] = new Cell(i, j, random.nextBoolean());
			}
		}
	}

	/**
	 * Checks if the specified row index is within the valid range of the grid.
	 * 
	 * @param row the row index to validate
	 * @return {@code true} if the row index is valid, {@code false} otherwise
	 */
	private boolean isValidRow(int row) {
		return (row >= 0) && (row <= NUMBER_OF_ROWS - 1);
	}

	/**
	 * Checks if the specified column index is within the valid range of the grid.
	 * 
	 * @param column the column index to validate
	 * @return {@code true} if the column index is valid, {@code false} otherwise
	 */
	private boolean isValidColumn(int column) {
		return (column >= 0) && (column <= NUMBER_OF_COLUMNS - 1);
	}

	/**
	 * Retrieves the {@link Cell} at the specified position in the grid.
	 * 
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @return the {@link Cell} object at the specified position
	 */
	public Cell getCell(int row, int column) {
		return this.cellsArray[row][column];
	}

	/**
	 * Retrieves the current 2D array of cells representing the game grid.
	 * 
	 * @return a 2D array of {@link Cell} objects
	 */
	public Cell[][] getCellsArray() {
		return this.cellsArray;
	}

	/**
	 * Sets the 2D array of cells representing the game grid.
	 * 
	 * @param cellsArray a 2D array of {@link Cell} objects to set as the current grid
	 */
	public void setCellsArray(Cell[][] cellsArray) {
		this.cellsArray = cellsArray;
	}
	
	/**
	 * Retrieves the history of cell grids over time.
	 * Each entry in the list represents a snapshot of the 2D grid at a specific generation.
	 * 
	 * @return a {@link List} of 2D arrays ({@code Cell[][]}) representing the grid's history
	 */
	public List<Cell[][]> getCellsArrayHistory() {
		return this.cellsArrayHistory;
	}
	
	

}
