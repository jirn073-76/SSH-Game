import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.command.CommandFactory;

public class TronCommandFactory implements CommandFactory {
	
	@Override
	public Command createCommand(String arg0) {
		System.out.println(arg0);
		return new TronCommand();
	}
}
