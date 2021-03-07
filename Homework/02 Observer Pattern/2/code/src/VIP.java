public class VIP implements Customer {
	private String name;

	public VIP(String name) {
		super();
		this.name = name;
	}

	@Override
	public void update(double price) {
		System.out.println("VIP:" + name + " have known the price is " + price + " now.");
	}
}
