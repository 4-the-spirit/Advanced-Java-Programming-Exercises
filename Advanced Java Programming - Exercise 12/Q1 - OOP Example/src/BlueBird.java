/**
 * Represents a BlueBird, a specific type of Bird.
 * BlueBirds have additional sleeping hours and custom behaviors for flying, eating, and sleeping.
 * This class extends the {@link Bird} class and provides specific implementations for its abstract methods.
 */
public class BlueBird extends Bird {
	public static final double ADDITIONAL_SLEEPING_HOURS = 1.4;
	
	/**
     * Constructs a new BlueBird with the specified name, age, and color.
     * 
     * @param name the name of the BlueBird
     * @param age the age of the BlueBird
     * @param color the color of the BlueBird
     */
	public BlueBird(
			String name, 
			int age, 
			String color) {
		this.setName(name);
		this.setAge(age);
		this.setColor(color);
	}
	
	/**
     * Makes the BlueBird fly from one point to another.
     * Displays a message indicating the start and end points of the flight.
     * 
     * @param a the starting point of the flight
     * @param b the ending point of the flight
     */
	@Override
	public void fly(Point a, Point b) {
		System.out.println("\nA BlueBird is flying from point " + a.toString()
		+ " to point " + b.toString() + "...\n");
	}
	
	/**
     * Makes the BlueBird eat the specified food.
     * Displays a message with details about the food being eaten.
     * 
     * @param food the type of food the BlueBird is eating
     */
	@Override
	public void eat(String food) {
		System.out.println(displayInformation(OperationLabel.HUNT, food, UnitLabel.EMPTY));
	}
	
	/**
     * Makes the BlueBird sleep for the specified number of hours.
     * Adds additional sleeping hours specific to BlueBirds.
     * Displays a message indicating the total hours of sleep.
     * 
     * @param hours the base hours the BlueBird should sleep
     */
	@Override
	public void sleep(double hours) {
		System.out.println(displayInformation(
				OperationLabel.SLEEP, 
				Double.toString(hours + ADDITIONAL_SLEEPING_HOURS), 
				UnitLabel.HOURS));
	}
	
	@Override
	public BlueBird clone() throws CloneNotSupportedException {
		return (BlueBird) super.clone();
	}

}
