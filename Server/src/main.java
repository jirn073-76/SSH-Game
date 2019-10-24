import java.io.IOException;

import org.apache.sshd.server.SshServer;
//import org.slf4j.
//import IoServiceFactoryFactory
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.shell.ProcessShellFactory;

public class main {
	public static void main(String[] args) 
	{
//		System.out.println("Hello, world (From Armin)!");
//		System.out.println("Treffen sich zwei, der eine kommt nicht. ");
//		
//		
//		
//		Playarea p = new Playarea(10,10);
//		p.newPlayer();
//		StringBuilder sb = new StringBuilder();
//		char[][] arr = p.getPlayareaAsCharArray();
//		System.out.println(sb.toString());
//		for (int i = 0; i < 10; i++) {
//			if(i==3)
//				p.players.get(0).getFunctions().changeDirection(Direction.right);;
//			p.update();
//			sb = new StringBuilder();
//			arr = p.getPlayareaAsCharArray();
//			for(int y = 0; y < arr.length;y++)
//			{
//				for(int x = 0; x < arr[0].length; x++)
//					sb.append(arr[x][y]);
//				sb.append('\n');
//			}
//			System.out.println(sb.toString());
//		}	
		
//		ServerClient sc = new ServerClient(22);
//		try {
//			sc.getSshd().start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
			SshServer sshServer = SshServer.setUpDefaultServer();
	        sshServer.setHost("127.0.0.1");
	        sshServer.setPort(2222);
	        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
	        sshServer.setPasswordAuthenticator((username, password, session) -> {
	            return true;
	        });
	        sshServer.setShellFactory(new ProcessShellFactory("/bin/sh", "-i", "-l"));
	}
}
