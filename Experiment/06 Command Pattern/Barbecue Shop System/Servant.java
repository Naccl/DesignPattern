import java.util.ArrayList;
import java.util.List;

public class Servant {

	private List<Command> commands = new ArrayList<>();

	public void takeOrder(Command command) {
		this.commands.add(command);
	}

	public void executeCommand() {
		for (Command command : commands) {
			command.executeCommand();
		}
		commands.clear();
	}

}
