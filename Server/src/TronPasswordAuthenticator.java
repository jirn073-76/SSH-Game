import java.util.ArrayList;

import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;

public class TronPasswordAuthenticator implements PasswordAuthenticator {
	
	private ArrayList<String> allowedUsers = new ArrayList<String>();
	
	
	public TronPasswordAuthenticator() {
		super();
		for (String uname : new String[] { "red", "green", "orange", "blue", "pink", "lightblue", "white"}) {
			allowedUsers.add(uname);
		}
	}
	
	@Override
	public boolean authenticate(String uname, String pw, ServerSession arg2) {
		if (allowedUsers.contains(uname)) 
			return true;
			
		return false;
	}
}
