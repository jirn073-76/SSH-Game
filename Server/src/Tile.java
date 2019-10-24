
public class Tile {
	public Direction direction;
	public boolean isHead;
	public Player player;
	
	public Tile(Direction direction, boolean isHead, Player player) {
		this.direction = direction;
		this.isHead = isHead;
		this.player = player;
	}
	
	char getChar(){
		switch(direction) {
			case up:
			case down:
				return '|';
			case left:
			case right:
				return '-';
			default:
				return '+';
		}
	}
}
