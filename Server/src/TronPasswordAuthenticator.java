import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;

public class TronPasswordAuthenticator implements PasswordAuthenticator {
	
	@Override
	public boolean authenticate(String uname, String pw, ServerSession arg2) {
		if (uname.toUpperCase().equals("22TRON") || uname.toUpperCase().equals("TRON")) 
			return true;

		return false;
	}
}
