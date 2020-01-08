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
//		Menu menu = new Menu(FieldManager.getInstance().PLAYAREA_HEIGHT, FieldManager.getInstance().PLAYAREA_WIDTH);
//		out.write(menu.getMenuAsByteArray());
//		out.flush();
//		menu.moveCursor(Direction.up);
//		menu.moveCursor(Direction.left);
//		menu.moveCursor(Direction.left);
//		out.write(menu.getMenuAsByteArray());
//		out.flush();
//		
//		Menu.menuState menuState = menu.getColorAndGamemode();
//		System.out.println(menuState.gameMode + " " + menuState.color);
//		boolean alwaysTrue = true;
//		while(alwaysTrue) 
//		{
//			
//		}
		
		player = null;
		System.out.println("Command start: " + arg0);
		t = new Thread(new Runnable() {
			@Override
			public void run() {	
				try {
					for(int i = 1; i < 100;i++)
					{
						out.write((i+": "+"\u001B["+i+"m"+"####\u001B[0m\n\r").getBytes());
					}
					out.flush();
					// Menu I/O Starts here
					Menu menu = new Menu(FieldManager.PLAYAREA_HEIGHT,FieldManager.PLAYAREA_WIDTH);
					for(int i = 0; i < 10; i++) {
						out.write('\n');
						out.write('\r');
					}
					out.write(menu.getMenuAsByteArray());
					out.flush();
					int  by = in.read();
					while(!(menu.isCursorOnPlay() && by == 13)) {
						
						
						if(by!=27) {
							switch(by) {
								case 3:exc.onExit(0);break;
								case 'w':menu.moveCursor(Direction.up);break;
								case 'a':menu.moveCursor(Direction.left);break;
								case 's':menu.moveCursor(Direction.down);break;
								case 'd':menu.moveCursor(Direction.right);break;
							}
						}else {
							by = in.read();
							if(by==91){
								by=in.read();
								switch(by) {
								case 65:menu.moveCursor(Direction.up);break;
								case 66:menu.moveCursor(Direction.down);break;
								case 67:menu.moveCursor(Direction.right);break;
								case 68:menu.moveCursor(Direction.left);break;
							}
							}
						}
						for(int i = 0; i < 10; i++) {
							out.write('\n');
							out.write('\r');
						}
						out.write(menu.getMenuAsByteArray());
						out.flush();
						by = in.read();
					}
					var ms = menu.getColorAndGamemode();
					player = FieldManager.createPlayer(out, ms.color);
				}
				
				// Menu I/O ends here
				
				
				catch(IOException ex) {}
				while (true) {
					try {
						
						if(in.available()>0)
						{
//							byte[] b = in.readNBytes(in.available());
//							out.write(b);
//							for (byte c : b) {
//								System.out.print(String.valueOf(c)+" ");
//							}
//							System.out.println();
//							out.flush();
//							continue;
							
							int  b =in.read();
							if(b!=27) {
								switch(b) {
									case 3:exc.onExit(0);break;
									case 'w':player.changeDirection(Direction.up);break;
									case 'a':player.changeDirection(Direction.left);break;
									case 's':player.changeDirection(Direction.down);break;
									case 'd':player.changeDirection(Direction.right);break;
								}
							}else {
								b = in.read();
								if(b==91){
									b=in.read();
									switch(b) {
									case 65:player.changeDirection(Direction.up);break;
									case 66:player.changeDirection(Direction.down);break;
									case 67:player.changeDirection(Direction.right);break;
									case 68:player.changeDirection(Direction.left);break;
								}
								}
							}
							
						}
						out.flush();
					} catch (IOException e) {
							try {
								destroy();
							} catch (Exception e1) {
							}
							return;
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