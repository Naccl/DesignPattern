public class Headmaster {
	private static volatile Headmaster headmaster;
	private String name;

	private Headmaster(String name) {
		this.name = name;
	}

	public static Headmaster getHeadmaster() {
		if (headmaster == null) {
			synchronized (Headmaster.class) {
				if (headmaster == null) {
					headmaster = new Headmaster("zhangsan");
				}
			}
		}
		return headmaster;
	}

	public void teach() {
		System.out.println("Headmaster teaching students...");
	}
}
