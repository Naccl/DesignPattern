public class Test {

	public static void main(String[] args) {
		Person person1 = new Person("person1");
		Person person2 = new Person("person2");
		Person person3 = new Person("person3");

		person1.registerObserver(person2);
		person1.registerObserver(person3);

		person2.registerObserver(person1);
		person2.registerObserver(person3);

		person3.registerObserver(person1);
		person3.registerObserver(person2);

		person1.beat();
		System.out.println();

		person2.beat();
		System.out.println();

		person3.beat();
	}

}
