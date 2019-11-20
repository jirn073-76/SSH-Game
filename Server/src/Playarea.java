import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Playarea {
	private static Random rnd = new Random();
	private static char playerSymbol = '+';
	private static char trailSymbol = '#';
	private int width, height;
	private Timer timer;
	
	enum fieldState {
		Empty, Player, Tail
	}
	
	private Tile[][] playField;
	
	LinkedList<Player> players;
	
	public Playarea(int width, int height) {
		this.width = width;
		this.height = height;
		playField = new Tile[width][height];
		players = new LinkedList<Player>();
		
	}
	
	
	
	public byte[] getPlayareaAsByteArray(){
		
		StringBuilder sb= new StringBuilder();

		for(int i = 0; i < height; i ++)
			sb.append("\n\r");
		for(int y = 0; y < width+2; y++) {
			sb.append('#');
		}
		sb.append("\n\r");
		for(int y = 0; y < height; y++) {
			sb.append('#');
			for(int x = 0; x < width; x++) {
				if(playField[x][y]==null)
				{
					sb.append("\u001B[0m ");
					continue;
				}
				else
				{
					sb.append( playField[x][y].getString());
				}
			}
			sb.append("#\n\r");
		}
		for(int y = 0; y < width+2; y++) {
			sb.append('#');
		}
		return sb.toString().getBytes();
	}
	
	private void update() {
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
	
	public IPlayer newPlayer(OutputStream out, Color color) {
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
					
					Player p = new Player(x,y,direction,color, out);
					players.add(p);	
					playField[x][y] = new Tile(p.movementDirection, false, p); 
					return p.getFunctions(new IDestroy() {
						@Override
						public void destroy() {
							players.remove(p);
							for (Coordinate pos : p.trail) {
								resetTile(pos);
							}
							resetTile(p.getPos());
						}
					});
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
	
	public void start() {
		if(timer != null)
			return;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				update();
				byte[] arr = getPlayareaAsByteArray();
				for(Player p: players) {
					OutputStream out = p.getOutStream();
					try {
						out.write(arr);
						out.flush();
					} catch(IOException e) {
						
					}
				}
			}
		}, 0,500);
	}
}
