import java.util.LinkedList;

public class Player {
	
	public Color color;
	private Coordinate pos;
	
	LinkedList<Coordinate> trail;

	Direction startMovementDirection;
	Direction movementDirection;
	
	public Player(int x, int y, Direction direction, Color color) {
		
		pos= new Coordinate(x,y);
		
		trail = new LinkedList<Coordinate>();

		startMovementDirection = movementDirection = direction;
		
		this.color = color; 
	}
	
	public Player(int x, int y, Direction direction) {
		
		pos= new Coordinate(x,y);
		
		trail = new LinkedList<Coordinate>();

		startMovementDirection = movementDirection = direction;
	}
	
	public IPlayer getFunctions() {
		return new IPlayer() {
			
			@Override
			public void changeDirection(Direction d) {
				Player.this.changeDirection(d);
			}
		};
	}
	
	public Coordinate getPos() {return pos; }

	public void move() {
		
		trail.add(new Coordinate(pos));
		
		switch(movementDirection) {
			case up:
				pos.y--;
				break;
			case down:
				pos.y++;
				break;
			case right:
				pos.x++;
				break;
			case left:
				pos.x--;
				break;
		}
	}

	public void reset() {
		pos = trail.get(0);
		trail = new LinkedList<Coordinate>();
		movementDirection = startMovementDirection;
	}
	
	private void changeDirection(Direction d) {
		movementDirection = d;
	}
}
