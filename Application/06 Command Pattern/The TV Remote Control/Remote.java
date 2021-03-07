public class Remote {

	private Command command1;

	private Command command2;

	private Command command3;

	public void on() {
		command1.execute();
	}

	public void off() {
		command2.execute();
	}

	public void switchChannel() {
		command3.execute();
	}

	public Remote(Command command1, Command command2, Command command3) {
		this.command1 = command1;
		this.command2 = command2;
		this.command3 = command3;
	}
}
