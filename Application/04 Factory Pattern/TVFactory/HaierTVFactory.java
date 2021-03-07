public class HaierTVFactory implements TVFactory {

	public TV produce() {
		System.out.println("海尔工厂生产海尔电视");
		return new HaierTV();
	}

}
