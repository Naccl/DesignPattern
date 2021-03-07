public class Headmaster_Lazy {
	private static volatile Headmaster_Lazy headmaster;
	private String name;

	private Headmaster_Lazy(String name) {
		this.name = name;
	}

	public static Headmaster_Lazy getHeadmaster() {
		if (headmaster == null) {
			synchronized (Headmaster_Lazy.class) {
				if (headmaster == null) {
					headmaster = new Headmaster_Lazy("zhangsan");
				}
			}
		}
		return headmaster;
	}

	public void teach() {
		System.out.println("Headmaster_Lazy teaching students...");
	}
}
