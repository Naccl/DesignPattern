public class ShanghaiStock extends Stock {
	public ShanghaiStock(String id, double price) {
		super(id, price);
	}

	public void setPrice(double price) {
		double oldPrice = this.price;
		this.price = price;
		if (Math.abs(price - oldPrice) / price > 0.01) {
			super.notifyVIPObservers();
		}
		if (Math.abs(price - oldPrice) / price > 0.03) {
			super.notifyCommonObserver();
		}
	}
}
