public class Headmaster_Early {
	private static volatile Headmaster_Early headmaster = new Headmaster_Early("zhangsan");
	private String name;

	private Headmaster_Early(String name) {
		this.name = name;
	}

	public static Headmaster_Early getHeadmaster() {
		return headmaster;
	}

	public void teach() {
		System.out.println("Headmaster_Early teaching students...");
	}
}
