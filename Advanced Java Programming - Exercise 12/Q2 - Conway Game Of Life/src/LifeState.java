
/**
 * Enum representing the possible life states of a cell in the Conway's Game of Life.
 * The states are:
 * <ul>
 *     <li>{@code BIRTH} - Indicates that a cell is being born in the next generation.</li>
 *     <li>{@code DEATH} - Indicates that a cell is dying (or has already died) in the next generation.</li>
 *     <li>{@code EXISTENCE} - Indicates that a cell continues to exist (remains alive) in the next generation.</li>
 * </ul>
 */
public enum LifeState {
	BIRTH, DEATH, EXISTENCE;
}
