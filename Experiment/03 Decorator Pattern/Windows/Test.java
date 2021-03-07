public class Test {
	public static void main(String[] args) {
		Windows window = new Window();
		window.show();

		Windows h = new HorizontalScrollbar(window);
		h.show();

		Windows v = new VerticalScrollbar(window);
		v.show();
	}
}
