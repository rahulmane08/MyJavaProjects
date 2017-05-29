package command;

public abstract class Command {
	abstract public void execute();
	abstract public void undo();
}
