/**
 * Represents the owner of an animal, with details such as their ID, first name, last name, and phone number.
 * This class supports cloning to create copies of owner instances.
 */
public class Owner implements Cloneable {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	/**
     * Constructs an {@code Owner} object with the specified details.
     *
     * @param id the ID of the owner.
     * @param firstName the first name of the owner.
     * @param lastName the last name of the owner.
     * @param phoneNumber the phone number of the owner.
     */
	public Owner(
			int id, 
			String firstName,
			String lastName,
			String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	/**
     * Creates and returns a copy of this {@code Owner} object.
     * 
     * @return a clone of the current {@code Owner}.
     * @throws CloneNotSupportedException if the cloning operation is not supported.
     */
	@Override
	public Owner clone() throws CloneNotSupportedException {
		return (Owner) super.clone();
	}
	
	/**
     * Returns a string representation of this {@code Owner} object.
     * 
     * @return a string representing the {@code Owner}'s details.
     */
	@Override
	public String toString() {
		return "Owner(firstName=" + this.getFirstName() +
				", lastName=" + this.getLastName() + ", id=" + this.getId() + ")"; 
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
