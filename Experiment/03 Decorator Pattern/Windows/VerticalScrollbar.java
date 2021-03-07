public class VerticalScrollbar extends ScrollingWindowDecorator {

	public VerticalScrollbar(Windows windows) {
		super(windows);
	}

	@Override
	public void show() {
		System.out.println("VerticalScrollbar's show()...");
	}
}
