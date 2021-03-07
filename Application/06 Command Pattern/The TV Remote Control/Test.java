/**
 * @Description: Test
 * @Author: Naccl
 * @Date: 2020-04-13
 */
public class Test {
	public static void main(String[] args) {
		TV myTV = new TV();
		TurnOnCommand on = new TurnOnCommand(myTV);
		TurnOffCommand off = new TurnOffCommand(myTV);
		SwitchCommand switchCommand = new SwitchCommand(myTV, 2);
		Remote remote = new Remote(on, off, switchCommand);
		remote.on();
		remote.off();
		remote.switchChannel();
	}
}
