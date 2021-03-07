public class ConcreteStock extends Stock {

	private double price;

	public ConcreteStock(double price) {
		this.price = price;
	}

	public void setPrice(double price) {
		if (Math.abs(this.price - price) / this.price > 0.05) {
			notifyObservers(this.price, price);
		}
		this.price = price;
	}

	@Override
	public void notifyObservers(double oldPrice, double newPrice) {
		for (StockHolder stockHolder : stockHolders) {
			stockHolder.update(oldPrice,newPrice);
		}
	}
}
