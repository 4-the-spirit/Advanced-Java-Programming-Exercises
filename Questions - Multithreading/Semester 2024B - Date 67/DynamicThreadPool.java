import java.util.ArrayList;
import java.util.List;

public class DynamicThreadPool {
	private List<Integer> free;
	private int[] count;
	
	public DynamicThreadPool(int n) {
		free = new ArrayList<Integer>();
		count = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			free.add(i);
		}
	}
	
	public synchronized int getResource() {
		while (free.size() == 0) {
			try {
				wait();
			} catch (InterruptedException exp) {
				
			}
		}
		int res = free.get(0);
		count[res] += 1;
		return res;
	}
	
	public synchronized void freeResource(int n) {
		free.add(n);
		notifyAll();
	}
	
	public synchronized int getUseCount(int n) {
		return count[n];
	}
}
