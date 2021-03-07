public class Test{
	public static void main(String[] args){
		Ticket ticket = new Ticket();
		ticket.setPrice(80);

		ticket.setDiscount(new ChildrenDiscount());
		System.out.println("Children:" + ticket.getPrice());

		ticket.setDiscount(new StudentDiscount());
		System.out.println("Student:" + ticket.getPrice());

		ticket.setDiscount(new VipDiscount());
		System.out.println("VIP:" + ticket.getPrice());
	}
}