/**
 * Represents a Dog, a subclass of {@code Mammal}.
 * The Dog class adds functionality for barking and cloning.
 * It inherits properties and behaviors from the {@code Mammal} class, such as name, age, and color.
 */
public class Dog extends Mammal {

	/**
     * Constructs a new Dog instance with the specified name, age, color, and owner.
     *
     * @param name the name of the dog.
     * @param age the age of the dog.
     * @param color the color of the dog.
     * @param owner the owner of the dog.
     */
	public Dog(
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
     * Makes the dog bark for a specified distance, and prints the barking information.
     * This method uses {@code displayInformation} to show the operation and distance in kilometers.
     *
     * @param distance the distance the dog barks for, in kilometers.
     */
	public void bark(int distance) {
		System.out.println(displayInformation(OperationLabel.BARK, Integer.toString(distance), UnitLabel.KILOMETERS));
	}
	
	/**
     * Creates and returns a copy (clone) of this Dog object.
     * The cloned object will have the same field values as the original dog.
     *
     * @return a new {@code Dog} object that is a copy of this instance.
     * @throws CloneNotSupportedException if the {@code Dog} class or its superclass 
     *         does not implement the {@code Cloneable} interface.
     */
	@Override
	public Dog clone() throws CloneNotSupportedException {
		return (Dog) super.clone();
	}
	
	@Override
	public void eat(String food) {
		System.out.println("\nA Dog is currently eating " + food + "...\n");
	}
	
}
