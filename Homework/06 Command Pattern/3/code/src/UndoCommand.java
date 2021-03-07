/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class UndoCommand implements Command {
	FileManager fileManager;

	public UndoCommand(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Override
	public void executeCommand() {
		fileManager.undoCommand();
	}
}
