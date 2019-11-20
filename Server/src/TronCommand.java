import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.command.Command;

public class TronCommand implements Command {

	OutputStream out;
	InputStream in;
	ExitCallback exc;
	Thread t;
	
	public TronCommand(IPlayer player) {
		
	}
	
	
	@Override
	public void start(Environment arg0) throws IOException {
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
							
							if(b==3) {
								exc.onExit(0);
							}
							
							out.write(b);
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

		if(t.isAlive())
			t.stop();
	}
	
	@Override
	public void setOutputStream(OutputStream arg0) {
		// TODO Auto-generated method stub
		System.out.println("Command setOutputstream");
		out = arg0;
		try {
			out.write("Test".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void setInputStream(InputStream arg0) {
		// TODO Auto-generated method stub
		System.out.println("Command setInputStream");
		in = arg0;
	}
	
	@Override
	public void setExitCallback(ExitCallback arg0) {
		// TODO Auto-generated method stub
		System.out.println("Command setExitCallback");
		exc=arg0;
	}
	
	@Override
	public void setErrorStream(OutputStream arg0) {
		// TODO Auto-generated method stub
		System.out.println("Command setErrorStream");
	}
}