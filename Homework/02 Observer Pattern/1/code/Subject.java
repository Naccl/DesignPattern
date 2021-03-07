import java.util.ArrayList;

public abstract class Subject {

	protected ArrayList<Observer> observers;

	public Subject() {
		this.observers = new ArrayList<Observer>();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}

	public abstract void notifyObservers();

}
