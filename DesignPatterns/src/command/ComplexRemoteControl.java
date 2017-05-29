package command;

import java.util.ArrayList;
import java.util.List;

public class ComplexRemoteControl {
	private List<Command> commands;
	public ComplexRemoteControl() {
		// TODO Auto-generated constructor stub
		commands = new ArrayList<Command>();
	}
	
	public void addCommand(Command command)
	{
		commands.add(command);
	}
	
	public void press()
	{
		for(Command command: commands)
			command.execute();
	}
	
	public void undo()
	{
		for(Command command: commands)
			command.undo();
	}
}
