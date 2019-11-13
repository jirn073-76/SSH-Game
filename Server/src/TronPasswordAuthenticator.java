import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;

public class TronPasswordAuthenticator implements PasswordAuthenticator {

	@Override
	public boolean authenticate(String uname, String pw, ServerSession arg2) {
		if (uname.equals("red") || uname.equals("blue")) 
			return true;
	
		return false;
	}
}
