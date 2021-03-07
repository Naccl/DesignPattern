public class Test {
	public static void main(String[] args) {
		ConcreteStock concreteStock = new ConcreteStock(100);

		ConcreteStockHolder concreteStockHolder1 = new ConcreteStockHolder();
		ConcreteStockHolder concreteStockHolder2 = new ConcreteStockHolder();

		concreteStock.registerObserver(concreteStockHolder1);
		concreteStock.registerObserver(concreteStockHolder2);

		concreteStock.setPrice(98);
		concreteStock.setPrice(92);
	}
}
