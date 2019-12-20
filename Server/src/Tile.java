
public class Tile {
	public Direction direction;
	public boolean isHead;
	public Player player;
	
	public Tile(Direction direction, boolean isHead, Player player) {
		this.direction = direction;
		this.isHead = isHead;
		this.player = player;
	}
	
	public String getString(){
		switch(direction) {
			case up:
			case down:
				return "\u001B["+(40+player.getColor().ordinal())+"m|"+ColorUtil.getBlackString();
			case left:
			case right:
				return "\u001B["+(40+player.getColor().ordinal())+"m-"+ColorUtil.getBlackString();
			default:
				return "\u001B["+(40+player.getColor().ordinal())+"m+"+ColorUtil.getBlackString();
		}
	}
	public Player getPlayer() {
		return player;
	}
}
