public class ConcreteStockHolder implements StockHolder, Action {

	public void update(double oldPrice, double newPrice) {
		System.out.println("oldPrice = " + oldPrice + ",newPrice = " + newPrice);
		act();
	}

	public void act() {
		System.out.println("ConcreteStockHolder act()...");
	}

}
