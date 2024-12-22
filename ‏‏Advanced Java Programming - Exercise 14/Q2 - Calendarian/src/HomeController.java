import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TableColumn;


public class HomeController {
	@FXML
	private TableView<Meeting> tableView;

	@FXML
	private GridPane gridPane;

	@FXML
	private Label monthLabel;

	@FXML
	private Label yearLabel;

	private final int NUM_OF_ROWS = 6;
	
	private final int NUM_OF_COLUMNS = 7;

	private final String CSS_CELL_STYLE = "-fx-alignment: center; -fx-border-color: lightgray; "
			+ "-fx-background-color: white; -fx-text-fill: black;";
	private final String CSS_CELL_STYLE_HOVER = "-fx-alignment: center; -fx-border-color: lightgray; "
			+ "-fx-background-color: #2c2c2c; -fx-text-fill: white;";

	private Map<LocalDate, List<Meeting>> meetingMap = new HashMap<LocalDate, List<Meeting>>();
	
	private List<Meeting> monthlyMeetings = new ArrayList<Meeting>();

	private int selectedMonthNum = LocalDate.now().getMonthValue();

	private int selectedYearNum = LocalDate.now().getYear();
	
	private final int MIN_MONTH_NUM = 1;

	private final int MAX_MONTH_NUM = 12;

	private final int MIN_YEAR_NUM = selectedYearNum - 10;

	private final int MAX_YEAR_NUM = selectedYearNum + 10;

	public void initialize() {
		displayDateDetails();
		updateCalendarDetails();
		initializeTableDetails();
		
	}

	@FXML
	void pressedPreviousMonth(ActionEvent event) {
		updateDateDetails(ApplicationButton.PREVIOUS_MONTH);
		updateCalendarDetails();
		displayTableDetails();
	}

	@FXML
	void pressedNextMonth(ActionEvent event) {
		updateDateDetails(ApplicationButton.NEXT_MONTH);
		updateCalendarDetails();
		displayTableDetails();
	}

	@FXML
	void pressedPreviousYear(ActionEvent event) {
		updateDateDetails(ApplicationButton.PREVIOUS_YEAR);
		updateCalendarDetails();
		displayTableDetails();
	}

	@FXML
	void pressedNextYear(ActionEvent event) {
		updateDateDetails(ApplicationButton.NEXT_YEAR);
		updateCalendarDetails();
		displayTableDetails();
	}
	
	/**
	 * Updates the calendar grid for the selected month and year.
	 * <p>
	 * Calculates the starting day of the week and the number of days in the month
	 * using the {@link java.time.YearMonth} API, then updates the grid by calling
	 * {@link #displayCalendarDetails(int, int)}.
	 */
	private void updateCalendarDetails() {
		YearMonth yearMonth = YearMonth.of(selectedYearNum, selectedMonthNum);
		final int DAYS_IN_WEEK = 7;
		int startDayNum = ((yearMonth.atDay(MIN_MONTH_NUM).getDayOfWeek().getValue() + 1)) % (DAYS_IN_WEEK + 1);
		// Handle the case of Sunday.
		startDayNum = startDayNum == 0 ? 1 : startDayNum;
		displayCalendarDetails(startDayNum, yearMonth.atEndOfMonth().getDayOfMonth());
	}

	/**
	 * Populates the calendar grid with labels representing the days of the selected month.
	 *
	 * @param startDayNum the day of the week the month starts on (1=Monday, 7=Sunday)
	 * @param endDayNum   the total number of days in the month
	 * 
	 * Clears the grid, then iterates through rows and columns to add labels for each day in the range.
	 * Each label is styled, supports hover effects, and opens a popup for adding meetings when clicked.
	 */
	private void displayCalendarDetails(int startDayNum, int endDayNum) {
		clearGridPane();

		int dayOfTheMonth = 1;

		// Add the labels to the grid pane.
		for (int i = 0; i < NUM_OF_ROWS; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				// As long as we are in the range of dates we need to display, display them.
				if (dayOfTheMonth - startDayNum >= 0 && dayOfTheMonth - startDayNum + 1 <= endDayNum) {
					Label label = new Label(Integer.toString(dayOfTheMonth - startDayNum + 1));

					label.setFont(Font.font("System", 20));
					label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Allow the label to expand
					label.setStyle(CSS_CELL_STYLE);

					label.setOnMouseEntered(event -> {
						label.setStyle(CSS_CELL_STYLE_HOVER);
					});

					label.setOnMouseExited(event -> {
						label.setStyle(CSS_CELL_STYLE);
					});

					final int day = dayOfTheMonth;
					label.setOnMouseClicked(event -> {
						openAddMeetingPopup(LocalDate.of(selectedYearNum, selectedMonthNum, day - startDayNum + 1));
					});

					gridPane.add(label, j, i);
				}
				dayOfTheMonth += 1;
			}
		}
	}

	/**
	 * Clears the grid pane by resetting all cells to empty labels with default styles.
	 * 
	 * Iterates through each cell in the grid, replacing its content with a blank label.
	 * Ensures the labels are styled, sized proportionally, and can expand to fit their cells.
	 */
	private void clearGridPane() {
		for (int i = 0; i < NUM_OF_ROWS; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				Label label = new Label();

				label.setMinHeight(gridPane.getHeight() / NUM_OF_ROWS);
				label.setMinWidth(gridPane.getWidth() / NUM_OF_COLUMNS);
				label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Allow the label to expand
				label.setStyle(CSS_CELL_STYLE);

				gridPane.add(label, j, i);
			}
		}
	}

	/**
	 * Displays an error alert when the selected month or year is out of the allowed range.
	 * 
	 * The method triggers an alert with an error message, indicating that the user needs to select
	 * a month within the valid range (MIN_MONTH_NUM to MAX_MONTH_NUM) and a year within the valid range (MIN_YEAR_NUM to MAX_YEAR_NUM).
	 */
	private void displayOutOfBoundsError() {
		displayAlert(AlertType.ERROR, "Out Of Bounds Error!", "Please select a month in the range " + MIN_MONTH_NUM
				+ "-" + MAX_MONTH_NUM + " and a year in the range " + MIN_YEAR_NUM + "-" + MAX_YEAR_NUM);
	}

	/**
	 * Opens a popup window to add a new meeting for a given date.
	 * 
	 * The method creates a popup where the user can enter the meeting title, description, and time (hour and minute).
	 * Once the user submits the form, it validates if all fields are filled, and if valid, saves the meeting details
	 * and adds it to the meeting map for the selected date. The popup is then closed, and the meeting table is updated.
	 * 
	 * @param meetingDate The date of the meeting being added.
	 */
	private void openAddMeetingPopup(LocalDate meetingDate) {
		Stage popupStage = new Stage();
		popupStage.setTitle("Add Meeting");

		VBox popupContent = new VBox(10);
		popupContent.setStyle("-fx-padding: 20px;");

		// Create the components to add to the VBox.
		Label titleLabel = new Label("Title:");
		TextField titleField = new TextField();

		Label descriptionLabel = new Label("Description:");
		TextArea descriptionField = new TextArea();

		HBox timeHBox = new HBox(5);

		Label timeLabel = new Label("Time (Hour : Minute):");
		ComboBox<Integer> hourComboBox = new ComboBox<Integer>();
		ComboBox<Integer> minuteComboBox = new ComboBox<Integer>();

		final int DAY_STARTING_HOUR = 0;
		final int DAY_ENDING_HOUR = 23;

		// Add the hours to the hours comboBox.
		for (int i = DAY_STARTING_HOUR; i <= DAY_ENDING_HOUR; i++) {
			hourComboBox.getItems().add(i);
		}

		final int HOUR_STARTING_MINUTE = 0;
		final int HOUR_ENDING_MINUTE = 59;

		// Add the minutes to the minutes comboBox.
		for (int i = HOUR_STARTING_MINUTE; i <= HOUR_ENDING_MINUTE; i++) {
			minuteComboBox.getItems().add(i);
		}

		timeHBox.getChildren().addAll(hourComboBox, minuteComboBox);

		Button submitButton = new Button("Save Meeting");

		// Add the components to the VBox.
		popupContent.getChildren().addAll(titleLabel, titleField);
		popupContent.getChildren().addAll(descriptionLabel, descriptionField);
		popupContent.getChildren().addAll(timeLabel, timeHBox);
		popupContent.getChildren().add(submitButton);

		submitButton.setOnAction(event -> {
			boolean areFieldsEmpty = titleField.getText().equals("") || descriptionField.getText().equals("")
					|| hourComboBox.getValue() == null || minuteComboBox.getValue() == null;

			if (areFieldsEmpty) {
				displayAlert(AlertType.INFORMATION, "Non Empty Fields!", "All fields must not be empty.");
			} else {
				// Save the meeting and close the stage.
				Meeting meeting = new Meeting(titleField.getText(), descriptionField.getText());
				LocalTime selectedTime = LocalTime.of(hourComboBox.getValue(), minuteComboBox.getValue());
				meeting.setDate(meetingDate);
				meeting.setTime(selectedTime);

				List<Meeting> meetings = meetingMap.get(meetingDate);

				// Populate the value of the key with a list implementation.
				meetingMap.put(meetingDate, meetings == null ? new ArrayList<Meeting>() : meetings);

				// Fetch the reference to the list of meetings again.
				meetings = meetingMap.get(meetingDate);
				meetings.add(meeting);
				popupStage.close();
				displayTableDetails();
			}

		});

		Scene popupScene = new Scene(popupContent, 400, 400);
		popupStage.setScene(popupScene);
		popupStage.show();
	}

	/**
	 * Updates the selected date based on the clicked button and validates the bounds.
	 * 
	 * This method adjusts the selected month or year based on the user's interaction with 
	 * the application buttons (previous or next month/year). It ensures that the new month or 
	 * year is within the valid range, and if not, displays an error message. The date details 
	 * are updated after the modification.
	 * 
	 * @param button The button clicked by the user, which can be one of the following:
	 *               - ApplicationButton.PREVIOUS_MONTH
	 *               - ApplicationButton.NEXT_MONTH
	 *               - ApplicationButton.PREVIOUS_YEAR
	 *               - ApplicationButton.NEXT_YEAR
	 */
	private void updateDateDetails(ApplicationButton button) {
		if (button == ApplicationButton.PREVIOUS_MONTH) {
			if (selectedMonthNum - 1 < MIN_MONTH_NUM) {
				displayOutOfBoundsError();
				return;
			}
			selectedMonthNum -= 1;
		} else if (button == ApplicationButton.NEXT_MONTH) {
			if (selectedMonthNum + 1 > MAX_MONTH_NUM) {
				displayOutOfBoundsError();
				return;
			}
			selectedMonthNum += 1;
		} else if (button == ApplicationButton.PREVIOUS_YEAR) {
			if (selectedYearNum - 1 < MIN_YEAR_NUM) {
				displayOutOfBoundsError();
				return;
			}
			selectedYearNum -= 1;
		} else if (button == ApplicationButton.NEXT_YEAR) {
			if (selectedYearNum + 1 > MAX_YEAR_NUM) {
				displayOutOfBoundsError();
				return;
			}
			selectedYearNum += 1;
		}
		displayDateDetails();
	}
	
	/**
	 * Updates the list of meetings for the selected month by filtering the meetings stored 
	 * in the meeting map based on the selected month and year.
	 * 
	 * This method first determines the start and end dates for the selected month, then iterates 
	 * over all meetings in the meeting map. It adds all meetings that fall within the selected 
	 * month to the `monthlyMeetings` list.
	 * 
	 * The method clears the `monthlyMeetings` list before adding the filtered meetings to ensure 
	 * only the meetings of the selected month are included.
	 */
	private void updateMonthlyMeetings() {
		LocalDate monthStartDate = LocalDate.of(selectedYearNum, selectedMonthNum, 1);
		LocalDate monthEndDate = LocalDate.of(selectedYearNum, selectedMonthNum, YearMonth.of(selectedYearNum, selectedMonthNum).atEndOfMonth().getDayOfMonth());
		
		monthlyMeetings.removeAll(monthlyMeetings);
		
		for (Map.Entry<LocalDate, List<Meeting>> entry : meetingMap.entrySet()) {	
			// Check if the date of the entry is a date in the selected month.
			if ((entry.getKey().isAfter(monthStartDate) || entry.getKey().isEqual(monthStartDate))
					&& (entry.getKey().isBefore(monthEndDate) || entry.getKey().isEqual(monthEndDate))) {
				// Add all the meetings of this day to the monthly meeting list.
				monthlyMeetings.addAll(entry.getValue());
			}
		}
	}

	/**
	 * Updates the displayed labels for the selected month and year.
	 * 
	 * This method sets the text of `monthLabel` to the current value of `selectedMonthNum` and
	 * `yearLabel` to the current value of `selectedYearNum`. These labels are assumed to display
	 * the selected month and year for the calendar view.
	 */
	private void displayDateDetails() {
		monthLabel.setText(Integer.toString(selectedMonthNum));
		yearLabel.setText(Integer.toString(selectedYearNum));
	}
	
	/**
	 * Initializes the table columns for displaying meeting details in the calendar view.
	 * 
	 * This method creates and configures the table columns for displaying various meeting attributes:
	 * Year, Month, Day, Time, Title, and Description. Each column is populated with the corresponding
	 * data from the `Meeting` object. The columns are then added to the `tableView` for display.
	 */
	private void initializeTableDetails() {
		final String YEAR_COLUMN_NAME = "Year";
		final String MONTH_COLUMN_NAME = "Month";
		final String DAY_COLUMN_NAME = "Day";
		final String TIME_COLUMN_NAME = "Time";
		final String TITLE_COLUMN_NAME = "Title";
		final String DESCRIPTION_COLUMN_NAME = "Description";
		
		TableColumn<Meeting, Integer> yearColumn = new TableColumn<Meeting, Integer>(YEAR_COLUMN_NAME);
		yearColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().getYear()));
		
		TableColumn<Meeting, Integer> monthColumn = new TableColumn<Meeting, Integer>(MONTH_COLUMN_NAME);
		monthColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().getMonthValue()));
		
		TableColumn<Meeting, Integer> dayColumn = new TableColumn<Meeting, Integer>(DAY_COLUMN_NAME);
		dayColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().getDayOfMonth()));
		
		TableColumn<Meeting, LocalTime> timeColumn = new TableColumn<Meeting, LocalTime>(TIME_COLUMN_NAME);
		timeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTime()));
		
		TableColumn<Meeting, String> titleColumn = new TableColumn<Meeting, String>(TITLE_COLUMN_NAME);
		titleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTitle()));
		
		TableColumn<Meeting, String> descriptionColumn = new TableColumn<Meeting, String>(DESCRIPTION_COLUMN_NAME);
		descriptionColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
		
		tableView.getColumns().addAll(yearColumn, monthColumn, dayColumn, timeColumn, titleColumn, descriptionColumn);
		
	}
	
	/**
	 * Clears all the items from the table view.
	 * 
	 * This method removes all the data entries currently displayed in the `tableView`.
	 * It clears the table by removing all items from its list, resulting in an empty table.
	 */
	public void clearTableDetails() {
		tableView.getItems().removeAll(tableView.getItems());
	}
	
	/**
	 * Displays the details of the meetings for the currently selected month and year in the table view.
	 * 
	 * This method performs the following tasks:
	 * 1. Clears the current table view by calling {@link #clearTableDetails()}.
	 * 2. Updates the list of meetings for the selected month by calling {@link #updateMonthlyMeetings()}.
	 * 3. Sets the updated list of meetings for the selected month into the table view.
	 */
	private void displayTableDetails() {
		clearTableDetails();
		updateMonthlyMeetings();
		tableView.getItems().setAll(monthlyMeetings);
	}

	/**
	 * Displays an alert dialog with the specified type, title, and content.
	 * 
	 * @param alertType The type of the alert (e.g., {@link AlertType#ERROR}, {@link AlertType#INFORMATION}).
	 * @param title The title of the alert window.
	 * @param content The content message to be displayed in the alert.
	 * 
	 * This method creates and displays an alert with the specified parameters. The alert is shown as a modal dialog
	 * that waits for the user to acknowledge the message before proceeding.
	 */
	private void displayAlert(AlertType alertType, String title, String content) {
		Alert alert = new Alert(alertType);
		alert.setHeaderText(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
