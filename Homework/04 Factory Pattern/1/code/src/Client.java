public class Client {
	public static void main(String[] args) {
		TVFactory tvFactory = new TVFactory();
		TV tv = tvFactory.produceTV("Haier");
		tv.play();

		tv = tvFactory.produceTV("Hisense");
		tv.play();
	}
}
