public class TurnOffCommand implements Command {

	private TV tv;

	public void execute() {
		tv.off();
	}

	public TurnOffCommand(TV tv) {
		this.tv = tv;
	}

}
