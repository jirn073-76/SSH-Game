import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.*;

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
		
        try { sshd.setHost(InetAddress.getLocalHost().getHostAddress()); } 
        catch (UnknownHostException e) { e.printStackTrace(); }
        
		sshd.setPort(port);
		sshd.setPasswordAuthenticator(new TronPasswordAuthenticator());
		
		SimpleGeneratorHostKeyProvider hkprovider = new SimpleGeneratorHostKeyProvider();
		Path hkfile = Paths.get("hostkey.ser");
		hkprovider.setPath(hkfile);
		
		sshd.setKeyPairProvider(hkprovider);
				
		//Windows
		//sshd.setShellFactory(new ProcessShellFactory("powershell.exe"));
		//"bash -l". config.ssh.shell = "bash -c 'BASH_ENV=/etc/profile exec bash'"
		sshd.setShellFactory(new ProcessShellFactory(new String[] { "/bin/sh -i -l"}));
		sshd.setCommandFactory(new ScpCommandFactory());
	}
}
