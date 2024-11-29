/**
 * Represents a Tiger, which is a subclass of {@link Mammal}.
 * The Tiger class adds specific behaviors, such as hunting and climbing, unique to tigers.
 */
public class Tiger extends Mammal {	
	/**
     * Constructs a new Tiger with the specified name, age, color, and owner.
     *
     * @param name the name of the tiger.
     * @param age the age of the tiger.
     * @param color the color of the tiger.
     * @param owner the owner of the tiger.
     */
	public Tiger(
			String name, 
			int age, 
			String color,
			Owner owner) {
		this.setName(name);
		this.setAge(age);
		this.setColor(color);
		this.setOwner(owner);
	}
	
	/**
     * Creates and returns a clone of this Tiger.
     * The cloned Tiger will have the same properties, including its owner.
     *
     * @return a cloned Tiger instance.
     * @throws CloneNotSupportedException if cloning is not supported.
     */
	@Override
	public Tiger clone() throws CloneNotSupportedException {
		return (Tiger) super.clone();
	}
	
	/**
     * Makes the tiger hunt a specific prey.
     * Prints out information about the hunting action.
     *
     * @param prey the name of the prey that the tiger is hunting.
     */
	public void hunt(String prey) {
		System.out.println(displayInformation(OperationLabel.HUNT, prey, UnitLabel.EMPTY));
	}
	
	/**
     * Makes the tiger climb a specific height.
     * Prints out information about the climbing action.
     *
     * @param height the height the tiger is climbing (in meters).
     */
	public void climb(double height) {
		System.out.println(displayInformation(OperationLabel.CLIMB, Double.toString(height), UnitLabel.METERS));
	}
	
	@Override
	public void eat(String food) {
		System.out.println("\nA Tiger is currently eating " + food + "...\n");
	}
}
