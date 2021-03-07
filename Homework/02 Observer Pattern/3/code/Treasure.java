import java.util.ArrayList;

public abstract class Treasure {

	protected ArrayList<Eudemon> eudemons;

	public Treasure() {
		this.eudemons = new ArrayList<Eudemon>();
	}

	public void registerGuard(Eudemon eudemon) {
		eudemons.add(eudemon);
	}

	public void removeGuard(Eudemon eudemon) {
		eudemons.remove(eudemon);
	}

	public abstract void setNumber(double number, String name);

	public abstract void notifyAll(double oldCount, double newCount, String name);

}
