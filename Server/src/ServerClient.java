import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.*;
import java.util.Scanner;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.config.hosts.HostConfigEntry;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.ChannelListener;
import org.apache.sshd.common.io.PacketWriter;
import org.apache.sshd.common.session.helpers.AbstractSession;
import org.apache.sshd.common.util.buffer.Buffer;
import org.apache.sshd.common.util.buffer.ByteArrayBuffer;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;
import org.apache.sshd.server.*;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;

public class ServerClient extends StandardEnvironment{
	
	private SshServer sshd;
	private SshClient sshc;
	private InputStream sshclientIn;
	private PrintStream sshclientOut;
	
	public SshServer getSshd() {
		return sshd;
	}
	
	public void setSshd(SshServer sshd) {
		this.sshd = sshd;
	}
	
	public ServerClient(int port) {
		setSshd(SshServer.setUpDefaultServer());
		
		String ipAddr = "";
		try { ipAddr = InetAddress.getLocalHost().getHostAddress(); } 
        catch (UnknownHostException e) { e.printStackTrace(); }
        
        sshd.setHost(ipAddr); 
        
		sshd.setPort(port);
		sshd.setPasswordAuthenticator(new TronPasswordAuthenticator());
		
		SimpleGeneratorHostKeyProvider hkprovider = new SimpleGeneratorHostKeyProvider();
		Path hkfile = Paths.get("hostkey.ser");
		hkprovider.setPath(hkfile);
		
		sshd.setKeyPairProvider(hkprovider);
				
		//Windows
		//sshd.setShellFactory(new ProcessShellFactory("powershell.exe"));
		//"bash -l". config.ssh.shell = "bash -c 'BASH_ENV=/etc/profile exec bash'"
		sshd.setShellFactory(new ProcessShellFactory(new String[] { "sudo", "/bin/bash" }));
		sshd.setCommandFactory(new TronProcessShellFactory(this));
}	 
	
	
	public void write2DArrayToScreen(char[][] map) {
		var buffer = new ByteArrayBuffer();
		
		for (char[] row : map) {
			buffer.putAndWipeChars(row);
			for (AbstractSession session : sshd.getActiveSessions()) {
				try {
					if (session != null) {
						sshd
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
