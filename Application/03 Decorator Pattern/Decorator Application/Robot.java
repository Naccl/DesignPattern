public class Robot extends Decorator {

	public void speak() {
		System.out.println("speak...");
	}

	public Robot(Transformer tran) {
		super(tran);
	}

}
