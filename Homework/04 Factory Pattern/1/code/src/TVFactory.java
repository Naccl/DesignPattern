public class TVFactory {
	public TV produceTV(String brand) {
		if ("Haier".equals(brand)) {
			System.out.println("produce HaierTV...");
			return new HaierTV();
		} else if ("Hisense".equals(brand)) {
			System.out.println("produce HisenseTV...");
			return new HisenseTV();
		}
		return null;
	}
}
