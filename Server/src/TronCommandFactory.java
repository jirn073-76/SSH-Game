import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.command.CommandFactory;

public class TronCommandFactory implements CommandFactory {
	private FieldManager fm;
	public TronCommandFactory(FieldManager fm) {
		this.fm = fm;
	}
	@Override
	public Command createCommand(String arg0) {
		System.out.println(arg0);
		return new TronCommand(fm.createPlayer());
	}
}
