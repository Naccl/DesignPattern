public class Person {

	private String name;

	public Person(String name) {
		this.name = name;
	}

	public void take(Treasure treasure, double count) {
		treasure.setNumber(count, this.name);
	}

}
