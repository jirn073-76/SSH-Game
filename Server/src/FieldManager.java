import java.io.OutputStream;
import java.util.LinkedList;

public class FieldManager {
	private static final int MAX_PLAYER_COUNT= 8;
	private static final int PLAYAREA_HEIGHT= 30;
	private static final int PLAYAREA_WIDTH= 90;
	
	LinkedList<Playarea> playareas = new LinkedList<Playarea>();
	
	public IPlayer createPlayer(OutputStream out, Color color) {
		for(Playarea area: playareas) {
			if(area.getPlayerCount()<MAX_PLAYER_COUNT) {
				return area.newPlayer(out, color);
			}
		}
		Playarea p = new Playarea(PLAYAREA_WIDTH, PLAYAREA_HEIGHT);
		System.out.println("New Playarea created!");
		playareas.add(p);
		IPlayer iPlayer = p.newPlayer(out, color);
		p.start();
		return iPlayer;
	}
	
}
