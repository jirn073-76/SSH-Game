import org.apache.sshd.server.*;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;

public class ServerClient {
	
	private SshServer sshd;

	public SshServer getSshd() {
		return sshd;
	}
	
	public void setSshd(SshServer sshd) {
		this.sshd = sshd;
	}
	
	public ServerClient(int port) {
		setSshd(SshServer.setUpDefaultServer());
        sshd.setHost("127.0.0.1");
		sshd.setPort(port);
		sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
		//sshd.setShellFactory(new ProcessShellFactory(new String[] { "/bin/sh", "-i", "-l" }));
		sshd.setCommandFactory(new ScpCommandFactory());
	}
}
