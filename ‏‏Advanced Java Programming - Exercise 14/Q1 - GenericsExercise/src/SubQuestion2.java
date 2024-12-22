import java.util.Random;
import java.util.Scanner;

public class SubQuestion2 {
	public static void main(String[] args) {
		Random random = new Random();
		
		Set<Integer> firstSet = new Set<Integer>();
		Set<Integer> secondSet = new Set<Integer>();
		Set<Integer> thirdSet = new Set<Integer>();
		
		for (int i = 0; i < 10; i++) {
			firstSet.insert(random.nextInt(101));
			secondSet.insert(random.nextInt(101));
			thirdSet.insert(random.nextInt(101));
		}
		
		System.out.println("First Set:\t\t" + firstSet);
		System.out.println("Second Set:\t\t" + secondSet);
		System.out.println("Third Set:\t\t" + thirdSet);
		
		Set<Integer> unionSet = firstSet.union(secondSet);
		System.out.println("Union Set:\t\t" + unionSet);
		
		Set<Integer> intersectSet = unionSet.intersect(thirdSet);
		System.out.println("Intersection Set:\t" + intersectSet);
		
		System.out.println("======================================");
	
		int times = 2;
		Scanner scanner = new Scanner(System.in);
		Set<Integer> integersSet = new Set<Integer>();
		
		System.out.println("First Set now:\t\t" + firstSet);
		
		for (int i = 0; i < times; i++) {
			System.out.println("Please provide the " + (i + 1) + "-th number of the set:");
			integersSet.insert(scanner.nextInt());
		}
		
		System.out.println("Your integers set is: " + integersSet);
		System.out.println("Your integers set is subset of firstSet: " + firstSet.isSubset(integersSet));
		
		System.out.println("======================================");
		
		System.out.println("Please enter a number (integer):");
		int num = scanner.nextInt();
		System.out.println("First Set:\t\t" + firstSet);
		System.out.println("Num Belongs to First Set:\t\t" + firstSet.isMember(num));
		
		System.out.println("Second Set:\t\t" + secondSet);
		secondSet.insert(num);
		System.out.println("Second Set now:\t\t" + secondSet);
		
		thirdSet.delete(num);
		System.out.println("Third Set:\t\t" + thirdSet);
		System.out.println("Third Set now:\t\t" + thirdSet);
		
		System.out.println("======================================");
		
		
		
	}
}
