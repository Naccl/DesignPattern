public class Lion implements Eudemon, Action {

	public void update(double oldCount, double newCount, String name, String treasure) {
		System.out.println("Lion known " + name + " took " + treasure + "\noldCount = " + oldCount + ", newCount = " + newCount);
		attacks(name);
	}

	public void attacks(String name) {
		System.out.println("Lion attacks " + name);
	}

}
