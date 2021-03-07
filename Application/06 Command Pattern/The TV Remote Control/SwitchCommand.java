public class SwitchCommand implements Command {

	private TV tv;
	private int channel;

	public void execute() {
		tv.switchChannel(channel);
	}

	public SwitchCommand(TV tv, int channel) {
		this.tv = tv;
		this.channel = channel;
	}

}
