package command;

public class FanOffCommand extends Command {

	private Fan fan;
	
	public FanOffCommand(Fan fan) {
		super();
		this.fan = fan;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		fan.off();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		fan.on();
	}

}
