
public class SubQuestion3 {
	public static void main(String[] args) {
		Owner owner = new Owner(3, "Ronie", "L", "956628198");
		Dog dog = new Dog("Billy", 19, "#002400", owner);
		
		try {
			Dog clonedDog = dog.clone();
			System.out.println(dog);
			System.out.println(clonedDog);
			System.out.println("Same Owners: " + (dog.getOwner() == clonedDog.getOwner()));
			clonedDog.getOwner().setFirstName("Sara");
			System.out.println("Same Owner Names: " + (dog.getOwner().getFirstName() == clonedDog.getOwner().getFirstName()));
			
		} catch (Exception e) {
			
		}
	}
}
