public class Gold extends Treasure {

	private double number;

	public Gold(double number) {
		this.number = number;
	}

	public void setNumber(double number, String name) {
		notifyAll(this.number, this.number - number, name);
		this.number -= number;
	}

	@Override
	public void notifyAll(double oldCount, double newCount, String name) {
		for (Eudemon eudemon : eudemons) {
			eudemon.update(oldCount, newCount, name, "gold");
		}
	}
}
