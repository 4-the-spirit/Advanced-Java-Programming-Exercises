import java.util.Random;

public class MyThread extends Thread {
	private ResourcePool pool;
	private final double MAX_SLEEP = 200.0;
	
	public MyThread(ResourcePool pool) {
		this.pool = pool;
	}
	
	public void run() {
		int num = pool.getResource();
		System.out.println(num);
		// Work...
		Random rand = new Random();
		try {
			sleep((long)(rand.nextDouble() * MAX_SLEEP));
		} catch (InterruptedException exp) {
			
		}
		pool.freeResource(num);
	}
}
