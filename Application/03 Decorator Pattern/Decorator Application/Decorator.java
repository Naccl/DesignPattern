public class Decorator implements Transformer {
	private Transformer transformer;

	public Decorator(Transformer transformer) {
		this.transformer = transformer;
	}

	public void move() {
		transformer.move();
	}
}
