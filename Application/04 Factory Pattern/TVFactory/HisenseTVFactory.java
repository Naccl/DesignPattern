public class HisenseTVFactory implements TVFactory {

	public TV produce() {
		System.out.println("海信工厂生产海信电视");
		return new HisenseTV();
	}

}
