import java.util.ArrayList;
import java.util.Random;

public class SubQuestion2 {
	public static void main(String[] args) {
		Owner owner1 = new Owner(1, "Tim", "E", "913523098");
		Owner owner2 = new Owner(2, "Dana", "R", "947223098");
		Owner owner3 = new Owner(3, "Ronie", "L", "956628198");
		
		Animal a1 = new Dog("Luna", 2, "#FFFFFF", owner2);
		Animal a2 = new Tiger("Roy", 25, "#F32000", owner1);
		Animal a3 = new Dog("Rubi", 24, "#FF0052", owner2);
		Animal a4 = new Tiger("George", 10, "#0FF0F3", owner3);
		Animal a5 = new Dog("Billy", 19, "#002400", owner3);
		Animal a6 = new Tiger("Dina", 17, "#0FF0F3", owner1);
		
		ArrayList<Animal> animals = new ArrayList<Animal>();
		
		animals.add(a1);
		animals.add(a2);
		animals.add(a3);
		animals.add(a4);
		animals.add(a5);
		animals.add(a6);
		
		String[] foods = {"chocolate", "cake", "chips", "broccoli", "tuna", "potato"};
		String[] preys = {"monkey", "crocodile", "aligator", "zebra", "goat"};
		
		Random random = new Random();
		int index;
		
		for (Animal animal : animals) {
			index = random.nextInt(foods.length);
			animal.sleep(index * index + index);
			animal.eat(foods[index]);
			
			if (animal instanceof Dog) {
				((Dog) animal).bark(index + index);
			}
			if (animal instanceof Tiger) {
				((Tiger) animal).eat(foods[random.nextInt(foods.length)]);
				((Tiger) animal).climb(index * index + index);
				((Tiger) animal).hunt(preys[(index * index) % preys.length]);
			}
			
		}
	}
}
