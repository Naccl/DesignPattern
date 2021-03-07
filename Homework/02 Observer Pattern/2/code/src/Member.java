public class Member implements Customer {
	private String name;

	public Member(String name) {
		super();
		this.name = name;
	}

	@Override
	public void update(double price) {
		System.out.println("Member:" + name + " have known the price is " + price + " now.");
	}
}
