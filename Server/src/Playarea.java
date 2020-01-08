import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
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
	
	private LinkedList<Player> players;
	
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
			sb.append("\u001B[0m#\n\r");
		}
		for(int y = 0; y < width+2; y++) {
			sb.append("#");
		}
		int playerCount = getPlayerCount();
		int amount = 8;
		if(playerCount==0)
			return sb.toString().getBytes();
		int breite = (width+1) / (amount)-1;
		int extra = (width +1)% amount;
		sb.append("\n\r\u001B[0m\u001B[5m#\u001B[0m");
		Iterator<Player> iterator = players.iterator();	
		for (int i = 0; i < amount; i++) {
			if(i >= playerCount) {
				for(int j = 0; j<breite;j++)
					sb.append(" ");
				sb.append("\u001B[0m\u001B[5m#\u001B[0m");
				continue;
			}
			Player p = iterator.next();
			if(i == playerCount/2)
				breite += extra;
			int taillength = p.trail.size();
			int elementWidth = breite - String.valueOf(taillength).length();
			sb.append("\u001B["+(100+p.getColor().ordinal())+"m");
			for(int j = 0; j<elementWidth/2;j++)
				sb.append(" ");
			sb.append(taillength);
			for(int j = 0; j<elementWidth/2+elementWidth%2;j++)
				sb.append(" ");
			sb.append("\u001B[0m\u001B[5m#\u001B[0m");
			if(i == playerCount/2)
				breite -= extra;
		}
		sb.append("\n\r");
		for(int y = 0; y < width+2; y++) {
			sb.append("#");
		}
		sb.append("\n\r");
		return sb.toString().getBytes();
	}
	
	private void update() {
		int i = 0;
		int j;
		for(int k = 0; k<getPlayerCount();k++) {
			Player p = players.get(k);
			if(!p.isAlive)
				continue;
			getTileAt(p.getPos()).isHead = false;
			getTileAt(p.getPos()).direction=p.movementDirection;
			p.move();
			Player toKill=null;
			if(!insideBounds(p.getPos()) )
				toKill=p;
			else
			if( getTileAt(p.getPos()) != null) {
				toKill=p;
				if(getTileAt(p.getPos()).isHead) {
					j = 0;
					for(Player otherP : players) {
						if(j>=i)
							break;
						if(otherP.getPos()== p.getPos()) {
							if(rnd.nextBoolean()) {
								toKill=otherP;
								break;		
							}
						}
						j++;
					}
				}
			} 
			if(toKill!=null)
				killPlayer(toKill);
			else
				playField[p.getPos().x][p.getPos().y] = new Tile(p.movementDirection,false,p);
			i++;
		}
	}
	
	private boolean insideBounds(Coordinate pos) {
		return pos.x < width && pos.x>=0&& pos.y >= 0 && pos.y <height;
	}

	private void killPlayer(Player p) {
		removeFromField(p);

		
		if(aliveCount()>1)
			return;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		
		for (Player player : players) {
			if(player.isAlive)
				removeFromField(player);
			spawn(player);
		}
	}
	private void removeFromField(Player p) {
		p.isAlive=false;
		resetTile(p.getPos());
		for (Coordinate pos : p.trail) {
			resetTile(pos);
		}
	}
	private void spawn(Player p) {
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
					
					p.setPosition(x, y, direction);
					playField[x][y] = new Tile(p.movementDirection,true,p);
					p.isAlive = true;
					return;
				}
			}
		}
	}
	
	public IPlayer newPlayer(OutputStream out, EColor color) {

		Player p = new Player(color, out);
		players.add(p);	
		spawn(p);
		return p.getFunctions(new IDestroy() {
			@Override
			public void destroy() {
				players.remove(p);
				removeFromField(p);
				if(getPlayerCount()<2) {
					for (Player player : players) {
							killPlayer(player);
					}
//							if(getPlayerCount()<=0) {
//								timer.cancel();
//								try {
//									this.finalize();
//								} catch (Throwable e) {
//									e.printStackTrace();
//								}
//							}
				}
			}
		});
	}
		
	private Tile getTileAt(Coordinate pos) {
		return playField[pos.x][pos.y];
	}
	private void resetTile(Coordinate pos) {
		if(insideBounds(pos))
			resetTile(pos.x,pos.y);
	}
	private void resetTile(int x, int y) {
		playField[x][y] = null;
	}

	public int getPlayerCount() {
		return players.size();
	}
	
	private int aliveCount() {
		int counter = 0;
		for(Player p : players)
		{
			if(p.isAlive)
				counter++;
		}
		return counter;
	}
	
	public void start() {
		if(timer != null)
			return;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			private int dotCount = 0;
			@Override
			public void run() {
				if(getPlayerCount()<=1) {
					String dots = "";
					for(int i = 0; i < dotCount; i++)
						dots += ".";
					
					// Clearing out the 3x # after Waiting for Players before pasting dots 
					sendToAllPlayers("\r#Waiting for Players   ".getBytes());					

					if(dotCount++ > 3) {
						dotCount = 0;
						sendToAllPlayers("\r#Waiting for Players   ".getBytes());					
					}
					else {
						sendToAllPlayers("\r#Waiting for Players".getBytes());					
						sendToAllPlayers(dots.getBytes());
					}
						
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return;
				}
				update();
				byte[] arr = getPlayareaAsByteArray();

				sendToAllPlayers(arr);
			}
		}, 0,200);
	}
	
	private void sendToAllPlayers(byte[] msg) {
		for(Player p: players) {
			OutputStream out = p.getOutStream();
			try {
				out.write(msg);
				out.flush();
			} catch(IOException e) { }
		}
	}
}
