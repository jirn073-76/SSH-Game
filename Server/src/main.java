import java.io.IOException;

import org.apache.sshd.common.session.helpers.AbstractSession;
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
	
		ServerClient sc = new ServerClient(2222);
		try {
			sc.getSshd().open();
		} catch (IOException e) {
			e.printStackTrace();
		}


		System.out.println(sc.getSshd().getActiveSessions());
		System.out.println(sc.getSshd().getHost()+":"+sc.getSshd().getPort());
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
