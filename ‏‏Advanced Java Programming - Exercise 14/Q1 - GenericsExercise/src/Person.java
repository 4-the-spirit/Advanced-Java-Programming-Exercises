/**
 * The Person class represents an individual with personal details like id, name, and birth year.
 * It implements the Comparable interface to compare `Person` objects based on their unique id.
 */
public class Person implements Comparable<Person> {
	private int id;
	private String firstName;
	private String lastName;
	private int birthYear;
	
	/**
     * Constructs a new Person object with the specified details.
     * 
     * @param id The unique identifier for the person.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @param birthYear The birth year of the person.
     */
	public Person(int id, String firstName, String lastName, int birthYear) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
	}
	
	/**
     * Returns a string representation of the person.
     * The format is: ClassName(id=<id>).
     * 
     * @return A string representation of the Person object.
     */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(id=" + getId() + ")";
	}
	
	/**
     * Compares this person to another person based on their id.
     * 
     * @param otherPerson The other person to compare to.
     * @return A negative integer, zero, or a positive integer as this person's id
     *         is less than, equal to, or greater than the other person's id.
     */
	@Override
	public int compareTo(Person otherPerson) {
		return this.getId() - otherPerson.getId();
	}
	
	/**
     * Gets the unique identifier of the person.
     * 
     * @return The id of the person.
     */
	public int getId() {
		return id;
	}
	
	/**
     * Sets the unique identifier of the person.
     * 
     * @param id The id to set for the person.
     */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
     * Gets the first name of the person.
     * 
     * @return The first name of the person.
     */
	public String getFirstName() {
		return firstName;
	}
	
	/**
     * Sets the first name of the person.
     * 
     * @param firstName The first name to set for the person.
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
     * Gets the last name of the person.
     * 
     * @return The last name of the person.
     */
	public String getLastName() {
		return lastName;
	}
	
	/**
     * Sets the last name of the person.
     * 
     * @param lastName The last name to set for the person.
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
     * Gets the birth year of the person.
     * 
     * @return The birth year of the person.
     */
	public int getBirthYear() {
		return birthYear;
	}

	/**
     * Sets the birth year of the person.
     * 
     * @param birthYear The birth year to set for the person.
     */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	
	

}
