import java.io.OutputStream;
import java.util.LinkedList;

public class Player {
	
	private EColor color;
	private Coordinate pos;
	private OutputStream out;
	private boolean positionSet;
	public boolean isAlive = false;
	LinkedList<Coordinate> trail;

	Direction startMovementDirection;
	Direction movementDirection;
	
	public Player(EColor color, OutputStream out) {

		this.color = color; 
		this.out = out;
		trail = new LinkedList<Coordinate>();
	}
	public void setPosition(int x, int y, Direction direction) {
		pos= new Coordinate(x,y);
		trail = new LinkedList<Coordinate>();
		startMovementDirection = movementDirection = direction;
	}
	public IPlayer getFunctions(IDestroy dest) {
		return new IPlayer() {

			@Override
			public void changeDirection(Direction d) {
				Player.this.changeDirection(d);
			}

			@Override
			public void disconnect() {
				dest.destroy();
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
		positionSet=false;
	}

	public void reset(Coordinate pos) {
		this.pos = pos;
		trail = new LinkedList<Coordinate>();
		movementDirection = startMovementDirection;
	}
	
private void changeDirection(Direction d) {
	if(!positionSet)
	{
		if(d == Direction.down && movementDirection == Direction.up)
			return;	
		if(d == Direction.up && movementDirection == Direction.down)
			return;	
		if(d == Direction.left && movementDirection == Direction.right)
			return;	
		if(d == Direction.right && movementDirection == Direction.left)
			return;
		movementDirection = d;
		positionSet = true;
	}
}

	public EColor getColor() {
		return color;
	}

	public OutputStream getOutStream() {
		return out;
	}
}
