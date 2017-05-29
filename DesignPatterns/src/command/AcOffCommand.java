package command;

public class AcOffCommand extends Command {

	private AC ac;
	
	
	public AcOffCommand(AC ac) {
		super();
		this.ac = ac;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ac.off();
	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		ac.on();
	}

}
