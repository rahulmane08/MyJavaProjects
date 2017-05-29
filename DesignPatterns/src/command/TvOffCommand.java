package command;

public class TvOffCommand extends Command {

	private TV tv;
	
	
	public TvOffCommand(TV tv) {
		super();
		this.tv = tv;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		tv.off();
	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		tv.on();
	}

}
