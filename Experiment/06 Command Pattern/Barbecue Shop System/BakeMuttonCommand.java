public class BakeMuttonCommand implements Command {
	private Chef chef;

	public BakeMuttonCommand(Chef chef) {
		this.chef = chef;
	}

	public void executeCommand() {
		chef.BakeMuttonCommand();
	}

}
