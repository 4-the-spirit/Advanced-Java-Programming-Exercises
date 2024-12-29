
public class Main {
	private final static int NUM_OF_RESOURCES = 3;
	
	public static void main(String[] args) {
		ResourcePool resourcePool = new ResourcePool(NUM_OF_RESOURCES);
		MyThread t1 = new MyThread(resourcePool);
		MyThread t2 = new MyThread(resourcePool);
		MyThread t3 = new MyThread(resourcePool);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException exp) {
		}
		for (int i = 1; i <= NUM_OF_RESOURCES; i++) {
			System.out.println("Resource: " + i + ", " + "Used: " + resourcePool.getUseCount(i));
		}	
	}
}
