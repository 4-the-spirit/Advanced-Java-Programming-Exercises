
public class StockItem implements Cloneable {
	// ...
	@Override
	public StockItem clone() throws CloneNotSupportedException {
		return (StockItem) super.clone();
	}
}
