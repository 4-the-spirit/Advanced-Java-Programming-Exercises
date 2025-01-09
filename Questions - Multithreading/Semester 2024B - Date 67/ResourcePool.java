
public class ResourcePool {
	private boolean[] taken;
	private int[] count;
	
	public ResourcePool(int n) {
		taken = new boolean[n + 1];
		count = new int[n + 1];
	}
	
	public synchronized int getResource() {
		int num = -1;
		while (true) {
			boolean found = false;
			for (int i = 0; i < taken.length; i++) {
				if (taken[i] == false) {
					found = true;
					num = i;
				}
			}
			if (!found || num < 0) {
				try { wait(); }
				catch (InterruptedException exp) { }
			} else {
				break;
			}
		}
		taken[num] = true;
		count[num] += 1;
		return num;
	}
	
	public synchronized void freeResource(int n) {
		taken[n] = false;
		notifyAll();
	}
	
	public synchronized int getUseCount(int n) {
		return count[n];
	}

}
