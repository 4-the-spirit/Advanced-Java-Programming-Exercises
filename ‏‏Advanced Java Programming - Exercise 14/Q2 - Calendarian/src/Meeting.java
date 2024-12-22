import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a meeting with a title, description, date, and time.
 * 
 * <p>Each meeting is assigned a unique ID upon creation. The class provides
 * methods to manage meeting details such as title, description, date, and time.
 * 
 * <p>Example usage:
 * <pre>{@code
 * Meeting meeting = new Meeting("Team Sync", "Discuss project updates");
 * meeting.setDate(LocalDate.of(2024, 12, 25));
 * meeting.setTime(LocalTime.of(14, 0));
 * System.out.println(meeting);
 * }</pre>
 *
 */
public class Meeting {
	private static int id = 1;
	
	private String title;
	private String description;
	private LocalDate date;
	private LocalTime time;
	
    // Static initializer block to increment the ID for every new instance
	static {
		Meeting.id += 1;
	}
	
	/**
     * Constructs a Meeting with the specified title and description.
     * 
     * @param title       The title of the meeting
     * @param description The description of the meeting
     */
	public Meeting(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	/**
     * Returns a string representation of the meeting.
     * 
     * <p>The format includes the class name, time, date, and title of the meeting.
     * 
     * @return A string representation of the meeting
     */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + 
				"time=" + getTime().toString() + ", " + 
				"date=" + getDate().toString() + ", " +
				"title=" + getTitle() + ")";
	}
	
	/**
     * Gets the unique ID of the meeting.
     * 
     * @return The unique ID of the meeting
     */
	public int getId() {
		return id;
	}
	
	/**
     * Gets the title of the meeting.
     * 
     * @return The title of the meeting
     */
	public String getTitle() {
		return title;
	}
	
	/**
     * Sets the title of the meeting.
     * 
     * @param title The new title of the meeting
     */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
     * Gets the description of the meeting.
     * 
     * @return The description of the meeting
     */
	public String getDescription() {
		return description;
	}
	
	/**
     * Sets the description of the meeting.
     * 
     * @param description The new description of the meeting
     */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
     * Gets the date of the meeting.
     * 
     * @return The date of the meeting
     */
	public LocalDate getDate() {
		return date;
	}
	
	/**
     * Sets the date of the meeting.
     * 
     * @param date The new date of the meeting
     */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
     * Gets the time of the meeting.
     * 
     * @return The time of the meeting
     */
	public LocalTime getTime() {
		return time;
	}
	
	/**
     * Sets the time of the meeting.
     * 
     * @param time The new time of the meeting
     */
	public void setTime(LocalTime time) {
		this.time = time;
	}
}
