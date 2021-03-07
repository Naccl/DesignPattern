public class TurnOnCommand implements Command {

	private TV tv;

	public void execute() {
		tv.on();
	}

	public TurnOnCommand(TV tv) {
		this.tv = tv;
	}

}
