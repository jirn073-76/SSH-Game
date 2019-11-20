import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.command.Command;

public class TronCommand implements Command {

	OutputStream out;
	InputStream in;
	ExitCallback exc;
	Thread t;
	IPlayer player;
	FieldManager fm;
	public TronCommand(FieldManager fm) {
		this.fm = fm;
	}
	
	
	@Override
	public void start(Environment arg0) throws IOException {
		Color col = null;
		for (Color c : Color.values()) {
			if(c.toString().equals(arg0.getEnv().values().toArray()[2]))
				col = c;
		}
		System.out.println(col);
		if(col == null) {
			exc.notifyAll();
			return;
		}
			
		player = fm.createPlayer(out,col);
		// TODO Auto-generated method stub
		System.out.println("Command start: " + arg0);
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						if(in.available()>0)
						{
							int  b =in.read();
							
							switch(b) {
								case 3:exc.onExit(0);break;
								case 'w':player.changeDirection(Direction.up);break;
								case 'a':player.changeDirection(Direction.left);break;
								case 's':player.changeDirection(Direction.down);break;
								case 'd':player.changeDirection(Direction.right);break;
							}
						}
						out.flush();
					} catch (IOException e) {
							try {
								destroy();
							} catch (Exception e1) {
							}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		System.out.println();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Command destroy");
		player.disconnect();
		if(t.isAlive())
			t.stop();
	}
	
	@Override
	public void setOutputStream(OutputStream arg0) {
		System.out.println("Command setOutputstream");
		out = arg0;
		try {
			out.write("Test".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setInputStream(InputStream arg0) {
		in = arg0;
	}
	
	@Override
	public void setExitCallback(ExitCallback arg0) {
		exc=arg0;
	}
	
	@Override
	public void setErrorStream(OutputStream arg0) {
		// TODO Auto-generated method stub
	}
}