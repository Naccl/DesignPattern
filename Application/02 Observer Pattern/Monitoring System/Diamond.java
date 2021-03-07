public class Diamond extends Jewelry {

	private int karat;

	public void setKarat(int karat) {
		this.karat = karat;
		this.notifyObservers();
	}

	public void notifyObservers() {
		for (Beast beast : beasts) {
			beast.update(karat);
		}
	}
}
