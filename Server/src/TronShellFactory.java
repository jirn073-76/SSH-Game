import org.apache.sshd.common.Factory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.command.Command;

public class TronShellFactory implements Factory<Command> {

	private SshServer s;
	public TronShellFactory(SshServer s) {
		this.s=s;
	}
	
	@Override
	public Command create() {
		return s.getCommandFactory().createCommand("Startup");
	}
}
