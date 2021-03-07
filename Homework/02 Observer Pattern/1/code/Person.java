public class Person extends Subject implements Observer, Member {

	private String name;

	public Person(String name) {
		this.name = name;
	}

	public void action() {
		System.out.println(this.name + " action()...");
	}

	public void update(String name) {
		System.out.println(name + " 受到攻击...");
		action();
	}

	public void beat() {
		notifyObservers();
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this.name);
		}
	}
}
