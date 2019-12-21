import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.*;
import java.util.Scanner;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.config.hosts.HostConfigEntry;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.Factory;
import org.apache.sshd.common.channel.Channel;
import org.apache.sshd.common.channel.ChannelListener;
import org.apache.sshd.common.io.PacketWriter;
import org.apache.sshd.common.session.Session;
import org.apache.sshd.common.session.SessionListener;
import org.apache.sshd.common.session.helpers.AbstractSession;
import org.apache.sshd.common.util.buffer.Buffer;
import org.apache.sshd.common.util.buffer.ByteArrayBuffer;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;
import org.apache.sshd.server.*;
import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.command.CommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.springframework.orm.toplink.SessionFactory;

import io.netty.channel.ServerChannel;

public class ServerClient extends StandardEnvironment{
	
	private SshServer sshd;
	
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
		sshd.setCommandFactory(new TronCommandFactory());
		SimpleGeneratorHostKeyProvider hkprovider = new SimpleGeneratorHostKeyProvider();
		Path hkfile = Paths.get("hostkey.ser");
		hkprovider.setPath(hkfile);
		
		sshd.setKeyPairProvider(hkprovider);
		sshd.setShellFactory( new TronShellFactory(sshd));
	}	 
}
