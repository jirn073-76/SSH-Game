import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.scp.ScpCommandFactory;

public class TronProcessShellFactory extends ScpCommandFactory {
	
	public ServerClient env;
	
	public TronProcessShellFactory(ServerClient environment) {
		env = environment;
	}
	
	@Override
	public TronCommand createCommand(String command) {
		TronCommand com = null; 
		
		if (command.equals("W") || command.equals("w")) {
			com = new TronCommand(Direction.up);
		}
		
		else if (command.equals("S") || command.equals("s")) {
			com = new TronCommand(Direction.down);
		}
		
		else if (command.equals("A") || command.equals("a")) {
			com = new TronCommand(Direction.left);
		}
		
		else if (command.equals("D") || command.equals("d")) {
			com = new TronCommand(Direction.right);
		}
		
		else { }
		
		return com;
	}
}