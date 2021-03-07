/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-14
 */
public class OS {
	private CreateDirCommand createDirCommand;
	private DeleteDirCommand deleteDirCommand;
	private UndoCommand undoCommand;

	public OS(CreateDirCommand createDirCommand, DeleteDirCommand deleteDirCommand, UndoCommand undoCommand) {
		this.createDirCommand = createDirCommand;
		this.deleteDirCommand = deleteDirCommand;
		this.undoCommand = undoCommand;
	}

	public void createDir(String dirName) {
		createDirCommand.executeCommand(dirName);
	}
	public void deleteDir(String dirName){
		deleteDirCommand.executeCommand(dirName);
	}

	public void undo(){
		undoCommand.executeCommand();
	}
}
