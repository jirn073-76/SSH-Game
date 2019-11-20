import java.util.LinkedList;

public class Playarea {
	
	static char playerSymbol = '+';
	static char trailSymbol = '#';
	int width, height;
	enum fieldState {
		Empty, Player, Tail
	}
	
	private Tile[][] playField;
	
	LinkedList<Player> players;
	
	public Playarea(int width, int height) {
		this.width = width;
		this.height = height;
		playField = new Tile[height][width];
		players = new LinkedList<Player>();
		
	}
	
	
	
	public byte[] getPlayareaAsCharArray(){
		
		byte[] result = new byte[height*width*2];
	
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(playField[x][y]==null)
				{
					result[x*width+y]=' ';
					continue;
				}
				else
				{
					result[x*width+y] = (byte) playField[x][y].getChar();
				}
			}
		}
		
		return result;
	}
	
	public void update() {
		for(Player p:players) {
			getTileAt(p.getPos()).isHead = false;
			getTileAt(p.getPos()).direction=p.movementDirection;
			p.move();
			try{
				if(getTileAt(p.getPos()) != null)
					throw new Exception();
			} catch (Exception e) {
				killPlayer(p);
			}
			playField[p.getPos().x][p.getPos().y] = new Tile(p.movementDirection,false,p);
		}
	}
	
	
	
	void killPlayer(Player p) {
		for (Coordinate pos : p.trail) {
			resetTile(pos);
		}
		p.reset();
	}
	
	void newPlayer() {
		addPlayer(2,2);
	}
	
	void addPlayer(int x, int y) {
		while(true) 
		{
			if(playField[x][y] == null) {
				System.out.println("field found");
				Player p = new Player(x,y,Direction.down);
				players.add(p);	
				playField[x][y] = new Tile(p.movementDirection, false, p); 
				return;
			}
			x+=2;
			if(x >= playField[0].length)
				x=0;
		}
	}
		
	Tile getTileAt(Coordinate pos) {
		return playField[pos.x][pos.y];
	}
	void resetTile(Coordinate pos) {
		resetTile(pos.x,pos.y);
	}
	void resetTile(int x, int y) {
		playField[x][y] = null;
	}
}
