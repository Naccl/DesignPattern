/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class CreateDirCommand implements Command {
	FileManager fileManager;

	public CreateDirCommand(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Override
	public void executeCommand() {

	}

	public void executeCommand(String dirName) {
		fileManager.createDirCommand(dirName);;
	}
}
