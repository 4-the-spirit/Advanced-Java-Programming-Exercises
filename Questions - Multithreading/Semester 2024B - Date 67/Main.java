
public class Main {
	public static void main(String[] args) {
		ResourcePool pool = new ResourcePool(3);
		MyThread[] threads = new MyThread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MyThread(pool);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException exp) {
				
			}
		}
		for (int i = 1; i <= 3; i++) {
			System.out.println("Resouorce" + i + ":" + " " + pool.getUseCount(i));
		}
	}
}
