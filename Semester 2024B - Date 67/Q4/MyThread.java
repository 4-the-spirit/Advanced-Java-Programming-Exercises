import java.util.Random;

public class MyThread extends Thread {
	private ResourcePool resourcePool;
	private final int MAX_SLEEP = 2000;
	
	public MyThread(ResourcePool resourcePool) {
		this.resourcePool = resourcePool;
	}
	
	@Override
	public void run() {
		int num = resourcePool.getResource();	
		try {
			sleep(new Random().nextInt(MAX_SLEEP));
		} catch (InterruptedException exp) {
		}
		resourcePool.freeResource(num);
	}
}
