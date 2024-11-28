
public abstract class Animal implements Cloneable {
	private String name;
	private int age;
	private String color;

	private static int id = 0;

	/**
	 * Defines the behavior of how the animal eats.
	 * Subclasses must provide the specific implementation.
	 *
	 * @param food the type of food the animal consumes (e.g., "meat", "grass").
	 */
	public abstract void eat(String food);

	/**
	 * Defines the behavior of how the animal sleeps.
	 * Subclasses must provide the specific implementation.
	 *
	 * @param hours the duration, in hours, for which the animal sleeps.
	 */
	public abstract void sleep(double hours);

	/**
	 * Generates a string representation of the animal, including its type, 
	 * name, age, operation performed, additional information, and unit of measurement.
	 *
	 * @return a formatted string describing the animal.
	 */
	@Override
	public String toString() {
		return FieldLabel.TYPE.getFieldName() + ": %s  \t" + FieldLabel.NAME.getFieldName() + ": " + this.getName()
				+ "  \t" + FieldLabel.AGE.getFieldName() + ": " + this.getAge() + "  \t"
				+ FieldLabel.OPERATION.getFieldName() + ": %s  \t" + FieldLabel.ADDITIONAL_INFORMATION.getFieldName()
				+ ": %s" + " " + "%s";
	}

	/**
	 * Checks for equality between this Animal and another object.
	 * Two animals are considered equal if they have the same name, age, and color.
	 *
	 * @param other the object to compare with this animal.
	 * @return {@code true} if the animals are equal; otherwise {@code false}.
	 */
	@Override
	public boolean equals(Object other) {
		Animal otherAnimal = (Animal) other;
		return (this.getName().equals(otherAnimal.getName())) 
				&& (this.getAge() == otherAnimal.getAge())
				&& (this.getColor().equals(otherAnimal.getColor()));
	}

	/**
	 * Creates and returns a copy (clone) of this Animal object.
	 * The cloned object will have the same field values as the original.
	 *
	 * @return a new {@code Animal} object that is a copy of this instance.
	 * @throws CloneNotSupportedException if the {@code Animal} class or its subclasses 
	 *         do not implement the {@code Cloneable} interface.
	 */
	@Override
	public Animal clone() throws CloneNotSupportedException {
		return (Animal) super.clone();
	}

	/**
	 * Formats and returns detailed information about an operation performed by the animal.
	 * This includes the type of the animal, the operation, any additional details, 
	 * and the unit of measurement for the operation.
	 *
	 * @param operation the operation performed by the animal (e.g., RUN, CLIMB).
	 * @param additionalInformation additional details about the operation (e.g., "Speed: 50").
	 * @param unit the unit of measurement for the operation (e.g., "km/h").
	 * @return a formatted string containing the operation details.
	 */
	public String displayInformation(OperationLabel operation, String additionalInformation, UnitLabel unit) {
		return String.format(toString(), getClass().getSimpleName(), operation.getOperation(), additionalInformation, unit.getUnit());
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
