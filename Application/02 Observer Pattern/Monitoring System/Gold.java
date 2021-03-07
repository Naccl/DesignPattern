public class Gold extends Jewelry {

	private int gram;

	public void setGram(int gram) {
		this.gram = gram;
		this.notifyObservers();
	}

	public void notifyObservers() {
		for (Beast beast : beasts) {
			beast.update(gram);
		}
	}
}
