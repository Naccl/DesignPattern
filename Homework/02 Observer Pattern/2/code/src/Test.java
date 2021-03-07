public class Test {
	public static void main(String[] args) {
		ShanghaiStock a001 = new ShanghaiStock("a001", 100);
		ShenzhenStock s001 = new ShenzhenStock("s001", 100);

		VIP tom = new VIP("Tom");
		Member jack = new Member("Jack");

		a001.registerObserver(tom);
		a001.registerObserver(jack);

		s001.registerObserver(tom);
		s001.registerObserver(jack);

		a001.setPrice(99);
		a001.setPrice(90);

		s001.setPrice(98);
		s001.setPrice(80);
	}
}
