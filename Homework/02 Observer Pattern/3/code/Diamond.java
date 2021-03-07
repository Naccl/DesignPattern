public class Diamond extends Treasure {

	private double number;

	public Diamond(double number) {
		this.number = number;
	}

	public void setNumber(double number, String name) {
		notifyAll(this.number, this.number - number, name);
		this.number -= number;
	}

	@Override
	public void notifyAll(double oldCount, double newCount, String name) {
		for (Eudemon eudemon : eudemons) {
			eudemon.update(oldCount, newCount, name, "diamond");
		}
	}
}
