public class VipDiscount implements Discount {

	public double calculate(double price) {
		System.out.println("give you a gift");
		return price * 0.7;
	}

}
