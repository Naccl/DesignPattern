public class Airplane extends Decorator {

	public void fly() {
		System.out.println("fly...");
	}

	public Airplane(Transformer tran) {
		super(tran);
	}

}
