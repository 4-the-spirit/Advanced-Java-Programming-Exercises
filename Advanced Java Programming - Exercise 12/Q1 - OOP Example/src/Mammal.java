/**
 * Represents a Mammal, a subclass of {@code Animal}.
 * The Mammal class introduces an additional property for the owner and overrides
 * methods related to eating, sleeping, and cloning behavior.
 */
public abstract class Mammal extends Animal {
	private Owner owner;
	
	/**
     * Creates and returns a copy (clone) of this Mammal object, including a cloned owner.
     * The cloned object will have the same properties as the original mammal, 
     * but with a new instance of the owner.
     *
     * @return a new {@code Mammal} object that is a clone of this instance.
     * @throws CloneNotSupportedException if the {@code Mammal} class or its superclass 
     *         does not implement the {@code Cloneable} interface.
     */
	@Override
	public Mammal clone() throws CloneNotSupportedException {
		Owner clonedOwner = this.getOwner().clone();
		Mammal clonedMammal = (Mammal) super.clone();
		clonedMammal.setOwner(clonedOwner);
		return clonedMammal;
	}
	
	/**
     * Simulates eating action for the mammal.
     * This method displays the eating operation along with the provided food.
     *
     * @param food the type of food the mammal eats.
     */
	@Override
	public void eat(String food) {
		System.out.println(displayInformation(OperationLabel.EAT, food, UnitLabel.EMPTY));
	}
	
	/**
     * Simulates sleeping action for the mammal.
     * This method displays the sleeping operation along with the number of hours the mammal sleeps.
     *
     * @param hours the number of hours the mammal sleeps.
     */
	@Override
	public void sleep(double hours) {
		System.out.println(displayInformation(OperationLabel.SLEEP, Double.toString(hours), UnitLabel.HOURS));
	}
	
	/**
     * Retrieves the owner of this mammal.
     *
     * @return the owner of the mammal.
     */
	public Owner getOwner() {
		return this.owner;
	}
	
	/**
     * Sets the owner of this mammal.
     *
     * @param owner the owner to set for this mammal.
     */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
