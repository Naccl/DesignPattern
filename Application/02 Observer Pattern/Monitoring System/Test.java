public class Test {
	public static void main(String[] args) {
		Gold gold = new Gold();
		Diamond diamond = new Diamond();

		Lion lion = new Lion();
		Tiger tiger = new Tiger();

		gold.registerObserver(lion);
		diamond.registerObserver(lion);
		diamond.registerObserver(tiger);

		gold.setGram(100);
		diamond.setKarat(50);
	}
}
