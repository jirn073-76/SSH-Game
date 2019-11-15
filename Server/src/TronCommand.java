import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.command.Command;

public class TronCommand extends CommandExecutionHelper {

	
	
	private IPlayer issuingPlayer;
	private Direction directionToSet;
	
	public TronCommand(Direction dir) {
		directionToSet = dir;
	}
	
}
