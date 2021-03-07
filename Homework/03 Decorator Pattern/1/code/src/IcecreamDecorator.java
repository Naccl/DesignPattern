public class IcecreamDecorator implements Icecream {
	Icecream specialIcecream;

	public IcecreamDecorator(Icecream specialIcecream) {
		this.specialIcecream = specialIcecream;
	}

	@Override
	public String makeIcecream() {
		return "Make SimpleIcecream...";
	}
}
