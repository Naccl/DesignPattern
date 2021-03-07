public class TV {

	public int channel = 0;

	public void on() {
		System.out.println("The TV is on...");
	}

	public void off() {
		System.out.println("The TV is off...");
	}

	public void switchChannel(int channel) {
		this.channel = channel;
		System.out.println("Now TV channel is " + channel);
	}

}
