import java.util.LinkedList;
import java.util.Random;

public class Playarea {
	private static Random rnd = new Random();
	private static char playerSymbol = '+';
	private static char trailSymbol = '#';
	private int width, height;
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
	
	
	
	private void killPlayer(Player p) {
		for (Coordinate pos : p.trail) {
			resetTile(pos);
		}
		p.reset();
	}
	
	public IPlayer newPlayer() {
		while(true) 
		{
			int x = rnd.nextInt(width-10)+5;
			int y = rnd.nextInt(height-10)+5;
			if(playField[x][y] == null) {
				for (Direction direction : Direction.values()) {
					boolean empty = true;
					switch (direction) {
					case up:
							for (int i = 1; i < 6; i++) {
								if(playField[x][y+i]!=null) {
									empty = false;
									break;
								}
							}
						break;
					case left:
						for (int i = 1; i < 6; i++) {
							if(playField[x-i][y]!=null) {
								empty = false;
								break;
							}
						}
						break;
					case down:
						for (int i = 1; i < 6; i++) {
							if(playField[x][y-i]!=null) {
								empty = false;
								break;
							}
						}
						break;
					case right:
							for (int i = 1; i < 6; i++) {
								if(playField[x+i][y]!=null) {
									empty = false;
									break;
								}
							}
							break;
					}
					if(!empty)
						continue;
					
					Player p = new Player(x,y,direction);
					players.add(p);	
					playField[x][y] = new Tile(p.movementDirection, false, p); 
					return p.getFunctions();
				}
			}
		}
	}
		
	private Tile getTileAt(Coordinate pos) {
		return playField[pos.x][pos.y];
	}
	private void resetTile(Coordinate pos) {
		resetTile(pos.x,pos.y);
	}
	private void resetTile(int x, int y) {
		playField[x][y] = null;
	}

	public int getPlayerCount() {
		return players.size();
	}
}
