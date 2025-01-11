import java.util.ArrayList;
import java.util.List;

public class MyList<T> extends ArrayList<T> implements Swappable<MyList<T>> {
	public void swap(MyList<T> other) {
		List<T> tmp = new ArrayList<T>();
		for (int i = 0; i < this.size(); i++) {
			tmp.add(this.get(i));
		}
		this.removeAll(this);
		for (int i = 0; i < other.size(); i++) {
			this.add(other.get(i));
		}
		other.removeAll(other);
		for (int i = 0; i < tmp.size(); i++) {
			other.add(tmp.get(i));
		}
	}

}
