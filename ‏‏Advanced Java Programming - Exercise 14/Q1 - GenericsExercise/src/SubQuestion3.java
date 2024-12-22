import java.util.Iterator;

public class SubQuestion3 {
	public static void main(String[] args) {
		Person p1 = new Person(888,	"Jasmine",	"Dov",		2000);
		Person p2 = new Person(222,	"Dan",		"Greg",		2003);
		Person p3 = new Person(999,	"Yamit",	"Narkis",	1996);
		Person p4 = new Person(444,	"Naftali",	"Bn",		1870);
		Person p5 = new Person(666,	"Carl",		"Gauss",	1789);
		
		Set<Person> peopleSet = new Set<Person>();
		peopleSet.insert(p1);
		peopleSet.insert(p2);
		peopleSet.insert(p3);
		peopleSet.insert(p4);
		peopleSet.insert(p5);
		
		System.out.println("People Set:\t" + peopleSet);
		System.out.println("Minimum Person:\t" + findMin(peopleSet));
	}
	
	public static <T extends Comparable<T>> T findMin(Set<T> set) {
		Iterator<T> setIterator = set.iterator();
		T currentMin = set.getElements().get(0);
		T currentElement;
		
		while (setIterator.hasNext()) {
			currentElement = setIterator.next();
			if (currentElement.compareTo(currentMin) < 0) {
				currentMin = currentElement;
			}
		}
		return currentMin;
	}
}
