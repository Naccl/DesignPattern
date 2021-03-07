public class BakeChickenCommand implements Command {
	private Chef chef;

	public BakeChickenCommand(Chef chef) {
		this.chef = chef;
	}

	public void executeCommand() {
		chef.BakeChickenCommand();
	}

}
