import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.command.CommandFactory;

public class TronCommandFactory implements CommandFactory {
	private FieldManager fm = FieldManager.getInstance();
	
	public TronCommandFactory() { }
	
	@Override
	public Command createCommand(String arg0) {
		return new TronCommand(fm);
	}
}
