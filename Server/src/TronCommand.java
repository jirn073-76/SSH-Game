import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.command.Command;

public class TronCommand implements Command {

	OutputStream out;
	InputStream in;
	@Override
	public void start(Environment arg0) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Command start: " + arg0);
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.write("test".getBytes());
		}
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Command destroy");
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
	}
	
	@Override
	public void setErrorStream(OutputStream arg0) {
		// TODO Auto-generated method stub
		System.out.println("Command setErrorStream");
	}
}