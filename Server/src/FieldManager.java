import java.util.LinkedList;

public class FieldManager {
	private static final int MAX_PLAYER_COUNT= 8;
	LinkedList<Playarea> playareas = new LinkedList<Playarea>();
	public IPlayer createPlayer() {
		for(Playarea area: playareas) {
			if(area.getPlayerCount()<MAX_PLAYER_COUNT) {
				area.newPlayer();
			}
		}
		return null;
	}
}
