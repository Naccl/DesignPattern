public class ShenzhenStock extends Stock {
	public ShenzhenStock(String id, double price) {
		super(id, price);
	}

	public void setPrice(double price) {
		double oldPrice = this.price;
		this.price = price;
		if (Math.abs(price - oldPrice) / price > 0.02) {
			super.notifyVIPObservers();
		}
		if (Math.abs(price - oldPrice) / price > 0.05) {
			super.notifyCommonObserver();
		}
	}
}
