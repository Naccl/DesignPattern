/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class Test {
	public static void main(String[] args) {
		FileManager fileManager = new FileManager();
		CreateDirCommand createDirCommand = new CreateDirCommand(fileManager);
		DeleteDirCommand deleteDirCommand = new DeleteDirCommand(fileManager);
		UndoCommand undoCommand = new UndoCommand(fileManager);
		OS os = new OS(createDirCommand, deleteDirCommand, undoCommand);
		os.createDir("commandfoder1");
		os.undo();
		os.createDir("commandfoder2");
	}
}
