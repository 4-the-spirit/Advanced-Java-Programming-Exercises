
public class Cell {
	private boolean alive;
	private int row;
	private int column;
	
	private LifeState state;
	
	/**
	 * Constructs a new {@code Cell} with the specified row, column, and alive state.
	 *
	 * @param row the row index of the cell
	 * @param column the column index of the cell
	 * @param alive {@code true} if the cell is initially alive, {@code false} otherwise
	 */
	public Cell(int row, int column, boolean alive) {
		this.alive = alive;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Constructs a new {@code Cell} as a copy of the specified cell.
	 *
	 * @param cell the {@code Cell} object to copy
	 */
	public Cell(Cell cell) {
		this.setAlive(cell.isAlive());
		this.setRow(cell.getRow());
		this.setColumn(cell.getColumn());
		this.setState(cell.getState());
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + "row=" + getRow() + ", " + 
				"column=" + getColumn() + ", " + 
				"alive=" + isAlive() + ")";
	}
	
	/**
	 * Toggles the alive state of the cell.
	 * If the cell is currently alive, it becomes dead. If it is dead, it becomes alive.
	 *
	 * @return the old alive state of the cell: {@code true} if alive, {@code false} if dead
	 */
	public boolean toggleAlive() {
		setAlive(isAlive() ? false : true);
		return !isAlive();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Retrieves the current life state of the cell.
	 *
	 * @return the {@link LifeState} of the cell (e.g., BIRTH, DEATH, EXISTENCE)
	 */
	public LifeState getState() {
		return state;
	}

	/**
	 * Sets the life state of the cell.
	 *
	 * @param state the {@link LifeState} to assign to the cell
	 */
	public void setState(LifeState state) {
		this.state = state;
	}
	
	
	
}
