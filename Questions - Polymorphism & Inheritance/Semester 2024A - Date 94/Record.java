
public class Record implements Cloneable {
	private StockItem item1;
	private StockItem item2;
	private String description;
	
	@Override
	public Record clone() throws CloneNotSupportedException {
		Record rec = (Record) super.clone();
		StockItem clonedFirst = rec.getFirst().clone();
		StockItem clonedSecond = rec.getSecond().clone();
		rec.setFirst(clonedFirst);
		rec.setSecond(clonedSecond);
		return rec;
	}
}
