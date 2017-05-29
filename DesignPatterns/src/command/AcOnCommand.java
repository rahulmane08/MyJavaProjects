package command;

public class AcOnCommand extends Command {

	private AC ac;
	
	
	public AcOnCommand(AC ac) {
		super();
		this.ac = ac;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ac.on();
	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		ac.off();
	}

}
