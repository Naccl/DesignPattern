public class TestDecorator {
	public static void main(String[] args) {
		Icecream simpleIcecream = new SimpleIcecream();
		System.out.println(simpleIcecream.makeIcecream());

		NuttyDecorator nuttyDecorator = new NuttyDecorator(simpleIcecream);
		System.out.println(nuttyDecorator.addNuts());

		HoneyDecorator honeyDecorator = new HoneyDecorator(simpleIcecream);
		System.out.println(honeyDecorator.addHoney());
	}
}
