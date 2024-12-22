import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A generic Set class that represents a mathematical set with basic set operations.
 * 
 * The Set class implements a collection of unique elements and provides methods to
 * perform common set operations like union, intersection, subset checks, and element
 * manipulation (insertion, deletion).
 *
 * @param <T> The type of elements in the set. It can be any object type.
 */
public class Set<T> {
	private final List<T> elements;
	
	/**
     * Default constructor that initializes an empty set.
     */
	public Set() {
		this.elements = new ArrayList<T>();
	}
	
	/**
     * Constructor that initializes a set with an array of elements, ensuring all
     * elements are unique.
     * 
     * @param elements The array of elements to initialize the set.
     */
	public Set(T[] elements) {
		this.elements = new ArrayList<T>();
		for (T e : elements) {
			if (!this.isMember(e)) {
				this.insert(e);;
			}
		}
	}
	
	/**
     * Performs the union of this set and another set.
     * 
     * @param otherSet The other set to union with.
     * @return The resulting set after the union operation.
     */
	public Set<T> union(Set<T> otherSet) {
		List<T> elementsToAdd = new ArrayList<T>();
		for (T element : otherSet.getElements()) {
			if (!this.isMember(element)) {
				elementsToAdd.add(element);
			}
		}
		this.getElements().addAll(elementsToAdd);
		return this;
	}
	
	/**
     * Performs the intersection of this set and another set.
     * 
     * @param otherSet The other set to intersect with.
     * @return The resulting set after the intersection operation.
     */
	public Set<T> intersect(Set<T> otherSet) {
		Iterator<T> thisIterator = this.iterator();
		T element;
		
		while (thisIterator.hasNext()) {
			element = thisIterator.next();
			if (!otherSet.isMember(element)) {
				thisIterator.remove();
			}
		}	
		return this;
	}
	
	/**
     * Checks if this set is a subset of another set.
     * 
     * @param otherSet The other set to check for subset relationship.
     * @return True if this set is a subset of the other set, otherwise false.
     */
	public boolean isSubset(Set<T> otherSet) {
		for (T element : otherSet.getElements()) {
			if (!this.isMember(element)) {
				System.out.println(element);
				return false;
			}
		}
		return true;
	}

	/**
     * Checks if an element is a member of this set.
     * 
     * @param element The element to check for membership.
     * @return True if the element is a member of the set, otherwise false.
     */
	public boolean isMember(T element) {
		return getElements().contains(element);
	}
	
	/**
     * Inserts an element into the set if it is not already a member.
     * 
     * @param element The element to insert into the set.
     */
	public void insert(T element) {
		if (!this.isMember(element)) {
			getElements().add(element);
		}
	}
	
	/**
     * Deletes an element from the set.
     * 
     * @param element The element to delete from the set.
     */
	public void delete(T element) {
		getElements().remove(element);
	}
	
	/**
     * Returns an iterator for iterating over the elements in the set.
     * 
     * @return An iterator for the elements in the set.
     */
	public Iterator<T> iterator() {
		return getElements().iterator();
	}
	
	/**
     * Returns a string representation of the set.
     * 
     * @return A string representing the set, formatted as {element1, element2, ...}.
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		T element;
		for (int i = 0; i < getElements().size(); i++) {
			element = getElements().get(i);
			if (i < getElements().size() - 1) {
				sb.append(" " + element.toString() + ",");
			} else {
				sb.append(" " + element.toString());
			}
		}
		sb.append(" }");
		return sb.toString();
	}

	/**
     * Gets the list of elements in the set.
     * 
     * @return A list of elements in the set.
     */
	public List<T> getElements() {
		return elements;
	}
	
	
}
