import java.util.ArrayList;
import java.util.List;

public class ResourcePool {
	private int[] usages;
	private List<Integer> freeResources;
	
	public ResourcePool(int n) {
		usages = new int[n + 1];
		freeResources = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			freeResources.add(i);
		}
	}
	
	public synchronized void freeResource(int resource) {
		freeResources.add(resource);
		notifyAll();
	}
	
	public synchronized int getUseCount(int resource) {
		return usages[resource];
	}
	
	public synchronized int getResource() {
		while (freeResources.size() == 0) {
			try {
				wait();
			} catch (InterruptedException exp) {
				System.out.println(exp.toString());
			}
		}
		int resource = freeResources.remove(0);
		usages[resource] += 1;
		return resource;
	}
	
	public synchronized int[] getUsages() {
		return usages;
	}
}
