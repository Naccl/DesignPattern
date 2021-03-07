import java.util.ArrayList;

public abstract class Stock {

	protected ArrayList<StockHolder> stockHolders;

	public Stock() {
		this.stockHolders = new ArrayList<StockHolder>();
	}

	public void registerObserver(StockHolder stockHolder) {
		stockHolders.add(stockHolder);
	}

	public void removeObserver(StockHolder stockHolder) {
		stockHolders.remove(stockHolder);
	}

	public abstract void notifyObservers(double oldPrice, double newPrice);

}
