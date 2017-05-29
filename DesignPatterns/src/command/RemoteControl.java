package command;

public class RemoteControl {
	private Command command;
	private Command undoCommand;
	
	

	public void setUndoCommand(Command undoCommand) {
		this.undoCommand = undoCommand;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void press()
	{
		command.execute();
		
	}
	
	public void undo()
	{
		command.undo();
	}
}
