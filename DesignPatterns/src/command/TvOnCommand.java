package command;

public class TvOnCommand extends Command {

	private TV tv;
	
	
	
	public TvOnCommand(TV tv) {
		super();
		this.tv = tv;
	}



	@Override
	public void execute() {
		// TODO Auto-generated method stub
		tv.on();
	}



	@Override
	public void undo() {
		// TODO Auto-generated method stub
		tv.off();
	}

}
