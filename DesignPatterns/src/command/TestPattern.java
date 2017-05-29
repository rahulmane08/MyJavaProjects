package command;

public class TestPattern {
	public static void main(String[] args) {
		RemoteControl rc = new RemoteControl();
		Fan fan = new Fan();
		FanOnCommand fanOn = new FanOnCommand(fan);
		FanOffCommand fanOff = new FanOffCommand(fan);
		AC ac = new AC();
		AcOnCommand acOn = new AcOnCommand(ac);
		AcOffCommand acOff = new AcOffCommand(ac);
		TV tv = new TV();
		TvOnCommand tvOn = new TvOnCommand(tv);
		TvOffCommand tvOff = new TvOffCommand(tv);
		rc.setCommand(tvOff);
		rc.setCommand(tvOn);
		rc.press();
		rc.undo();
		ComplexRemoteControl crc = new ComplexRemoteControl();
		crc.addCommand(tvOn);
		crc.addCommand(acOn);
		crc.addCommand(fanOn);
		crc.press();
		crc.undo();
	}
}
