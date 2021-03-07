/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class DeleteDirCommand implements Command {
	FileManager fileManager;

	public DeleteDirCommand(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Override
	public void executeCommand() {

	}

	public void executeCommand(String dirName) {
		fileManager.deleteDirCommand(dirName);
	}
}
