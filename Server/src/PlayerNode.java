
public class PlayerNode  {
	private Player player;
	private PlayerNode next;
	
	public PlayerNode(Player player) {
		this.player=player;
	}

	public boolean hasNext() {
		return next !=null;
	}
	
	public PlayerNode getNext() {
		return next;
	}

	public Player getPlayer() {
		return player;
	}

	public void setNext(PlayerNode node) {
		next = node;		
	}
}
