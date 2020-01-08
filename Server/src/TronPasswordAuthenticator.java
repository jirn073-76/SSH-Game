import java.io.IOException;

import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;

public class TronPasswordAuthenticator implements PasswordAuthenticator {
	
	@Override
	public boolean authenticate(String uname, String pw, ServerSession arg2) {
		if (uname.toUpperCase().equals("22TRON") || uname.toUpperCase().equals("TRON")) 
			return true;
		else
			try {
				arg2.disconnect(404, "Username not found\n\rPlease consider logging in with the username 'tron' if you're interested in playing 22-Tron :)");
			} catch (IOException e) {
				e.printStackTrace();
			}
		return false;
	}
}
