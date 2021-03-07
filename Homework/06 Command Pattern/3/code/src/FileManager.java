/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class FileManager {
	public void createDirCommand(String dirName) {
		System.out.println("CreateDir:" + dirName);
	}

	public void deleteDirCommand(String dirName) {
		System.out.println("DeleteDir:" + dirName);
	}

	public void undoCommand() {
		System.out.println("Undo...");
	}
}
