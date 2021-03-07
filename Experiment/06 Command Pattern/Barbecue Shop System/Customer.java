public class Customer {

	public static void main(String[] args) {
		Chef chef = new Chef();
		Command bakeChickenCommand = new BakeChickenCommand(chef);
		Command bakeMuttonCommand = new BakeMuttonCommand(chef);
		Servant servant = new Servant();
		servant.takeOrder(bakeChickenCommand);
		servant.takeOrder(bakeMuttonCommand);
		servant.executeCommand();
	}
}
