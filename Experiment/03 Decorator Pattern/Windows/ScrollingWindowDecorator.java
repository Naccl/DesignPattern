public class ScrollingWindowDecorator implements Windows {

	private Windows windows;

	public ScrollingWindowDecorator(Windows windows) {
		this.windows = windows;
	}

	public void show() {
		windows.show();
	}

}
