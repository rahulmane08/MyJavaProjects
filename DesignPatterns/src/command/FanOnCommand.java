package command;

public class FanOnCommand extends Command {

	public FanOnCommand(Fan fan) {
		super();
		this.fan = fan;
	}

	private Fan fan;
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		fan.on();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		fan.off();
	}

}
