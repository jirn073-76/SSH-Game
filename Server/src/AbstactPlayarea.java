import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstactPlayarea {
	private static Random rnd = new Random();
	private int width, height;
	private Timer timer;
	private TimerTask timerTask;
	enum fieldState {
		Empty, Player, Tail
	}

	private Tile[][] playField;

	private PlayerList players;
	//private LinkedList<Player> players;

	public AbstactPlayarea(int width, int height) {
		this.width = width;
		this.height = height;
		playField = new Tile[width][height];
		//players = new LinkedList<Player>();
		players = new PlayerList();
	}

	public abstract byte[] getPlayareaAsByteArray();

	private void update() {
		Player toKill;

		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			// falls der Spieler tot ist geh zum nächsten
			if (!player.isAlive)
				continue;
			// variable die zeigt, welcher spieler getötet werden soll
			toKill = null;

			// bewege den spieler
			movePlayer(player);
			// schaue nach wer sterben soll
			toKill = checkIfSomeoneHasToDie(player);
			
			// falls jemand stirbt 
			if (toKill != null)
				// töte ihn
				killPlayer(toKill);
			if(toKill != player)
				// ansonnsten setzte ein neues tile an der pisition des heads des spielers, welcher gerade gemoved ist
				playField[player.getPos().x][player.getPos().y] = new Tile(player.movementDirection, true, player);
		}
	}
	
	private Player checkIfSomeoneHasToDie(Player player) {
		Player toKill=null;
		// fall der spieler rausgefahren ist
		if (!insideBounds(player))
			// wird er sterbens
			toKill = player;
		// sonnst falls er auf ein nicht leeres Tile gefahren ist
		else if (!tileIsEmpty(player)) {
			// wird er sterben
			toKill = player;
			// falls das tile ein head tile war
			if (getTileAt(player).isHead) {
				Player otherPlayer = getTileAt(player).getPlayer();
				// und falls der besitzer dieses Heads vor ihm gefahren ist, stirbt  ein random spieler von den beiden.
				if(players.indexOf(otherPlayer)<players.indexOf(player) && rnd.nextBoolean())
					toKill = otherPlayer;
			}
		}
		return toKill;
	}

	// diese methoden schauen nach, ob ein tile leer (null) ist
	private boolean tileIsEmpty(Player player) {
		return tileIsEmpty(player.getPos());
	}
	private boolean tileIsEmpty(Coordinate pos) {
		return getTileAt(pos) == null;
	}

	// diese Methode moved einen Spieler
	private void movePlayer(Player player) {
		getTileAt(player.getPos()).isHead = false;
		getTileAt(player.getPos()).direction = player.movementDirection;
		player.move();
	}

	// diese methoden schauen nach, ob eine position out of bounds ist
	private boolean insideBounds(Player player) {
		return insideBounds(player.getPos());
	}
	private boolean insideBounds(Coordinate pos) {
		return pos.x < width && pos.x >= 0 && pos.y >= 0 && pos.y < height;
	}

	// diese methode tötet einen spieler
	private void killPlayer(Player p) {
		// entferne ihn vom feld
		removeFromField(p);

		// falls mehr als nur einer am leben sind, wars das
		if (aliveCount() > 1)
			return;
		
		// sonst kriege den einen spieler, der noch lebt
		Player guyWhoIsStillAlive = null;
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext()) {
			Player player = iterator.next();
			if(player.isAlive) {
				guyWhoIsStillAlive=player;
				break;
			}
		}
		// falls ein spieler noch am leben ist,
		if(guyWhoIsStillAlive != null) {
			// entferne ihm vom spielfeld
			removeFromField(guyWhoIsStillAlive);
			// mach eine "<Spieler Farbe> wins!" string
			String winMessage = 
					guyWhoIsStillAlive.getColor().toString().substring(0,1).toUpperCase()+
					guyWhoIsStillAlive.getColor().toString().substring(1)+" wins!";
			
			// mach ein bisschen ausgabe magic
			StringBuilder sb = new StringBuilder("\r");
			String indent = '\r'+DrawUtil.getStringWith("[C", ((width-winMessage.length())/2));
			
			sb.append(DrawUtil.getStringWith("[A", height/2+2));
	
			sb.append(indent+ColorUtil.getColoredString(guyWhoIsStillAlive.getColor())+DrawUtil.getStringWith(' ', winMessage.length()+2)+"\r[B");
			sb.append(indent+' '+ColorUtil.getBlackString()+winMessage+ColorUtil.getColoredString(guyWhoIsStillAlive.getColor())+" \r[B");
			sb.append(indent+DrawUtil.getStringWith(' ', winMessage.length()+2)+ColorUtil.getBlackString());
			sb.append(DrawUtil.getStringWith("[B", height/2)+2);
			sb.append('\r');
			// schreibe ein schönes kästchen auf die mitte des bildschirms
			sendToAllPlayers(sb.toString().getBytes());
			try {
				// 2 sek sleep
				Thread.sleep(2000);
			} catch (InterruptedException e) { }
		}
		
		// falls mehr als 1 spieler existieren, lass sie respawnen
		if( getPlayerCount() > 1)
			 iterator = players.iterator();
			while(iterator.hasNext()) {
				Player player = iterator.next();
				if (player.isAlive)
					removeFromField(player);
				spawn(player);
			}
	}
	
	// Diese Methode entfernt alle spuren einen spielers vom spielfeld uns setzt sie zurück
	private void removeFromField(Player p) {
		p.isAlive = false;
		resetTile(p.getPos());
		for (Coordinate pos : p.trail) {
			resetTile(pos);
		}
	}

	// diese Methode spawned einen Spieler
	private void spawn(Player p) {
		while (true) {
			int x = rnd.nextInt(width - 10) + 5;
			int y = rnd.nextInt(height - 10) + 5;
			if (playField[x][y] == null) {
				for (Direction direction : Direction.values()) {
					boolean empty = true;
					switch (direction) {
					case up:
						for (int i = 1; i < 6; i++) {
							if (playField[x][y + i] != null) {
								empty = false;
								break;
							}
						}
						break;
					case left:
						for (int i = 1; i < 6; i++) {
							if (playField[x - i][y] != null) {
								empty = false;
								break;
							}
						}
						break;
					case down:
						for (int i = 1; i < 6; i++) {
							if (playField[x][y - i] != null) {
								empty = false;
								break;
							}
						}
						break;
					case right:
						for (int i = 1; i < 6; i++) {
							if (playField[x + i][y] != null) {
								empty = false;
								break;
							}
						}
						break;
					}
					if (!empty)
						continue;

					p.setPosition(x, y, direction);
					playField[x][y] = new Tile(p.movementDirection, true, p);
					p.isAlive = true;
					return;
				}
			}
		}
	}

	// diese Methode fügt einen neuen Spieler Hinzu
	public IPlayer newPlayer(OutputStream out, EColor color) {

		// erstellt die Spieler instanz
		Player p = new Player(color, out);
		players.add(p);
		// falls 2 spieler im spiel sind starte es
		if(players.getLength()==2) {
			Iterator<Player> iterator = players.iterator();
			while (iterator.hasNext()) {
				Player player = iterator.next();
				spawn(player);
			}
		}
			
		// gibt die Events für den spieler zurück
		return p.getFunctions(new IDestroy() {
			@Override
			public void destroy() {
				players.remove(p);
				killPlayer(p);
				if (getPlayerCount()== 1) {
					killPlayer(players.iterator().next());
				}
				else if(getPlayerCount()==0) {
					destroyThis();
				}
			}
		});
	}
	
	// diese Methoden liefert das Tile bei einer Position
	private Tile getTileAt(Player player) {
		return getTileAt(player.getPos());
	}
	private Tile getTileAt(Coordinate pos) {
		return playField[pos.x][pos.y];
	}

	// setzt ein Tile an einer Position auf null
	private void resetTile(Coordinate pos) {
		if (insideBounds(pos))
			resetTile(pos.x, pos.y);
	}
	private void resetTile(int x, int y) {
		playField[x][y] = null;
	}

	// diese Methode liefert die Spieleranzahl
	public int getPlayerCount() {
		return players.getLength();
	}

	// diese Methode liefert die anzahl an lebendigen spielern
	private int aliveCount() {
		int counter = 0;
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext()) {
			Player player = iterator.next();
			if (player.isAlive)
				counter++;
		}
		return counter;
	}

	// start methode
	public void start() {
		// falls der timer nicht null ist läuft es schon
		if (timer != null)
			return;
		timer = new Timer();
		timerTask = new TimerTask() {
			private int dotCount = 0;
			@Override
			public void run() {
				if (getPlayerCount() <= 1) {
					displayWaitMessage(dotCount);
					if (++dotCount > 3) {
						dotCount = 0;
					}
					return;
				}
				update();
				byte[] arr = getPlayareaAsByteArray();

				sendToAllPlayers(arr);
			}
		};
		timer.schedule(timerTask, 0, 200);
	}

	private void displayWaitMessage(int dotCount) {
		StringBuilder sb = new StringBuilder("\r#Waiting for Players");
		
		for (int i = 0; i < 3; i++) {
			if(i<dotCount)
				sb.append('.');			
			else
				sb.append(' ');
		}
		sendToAllPlayers(sb.toString().getBytes());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}
	
	// sendet eine msg an alle spieler
	private void sendToAllPlayers(byte[] msg) {
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext()) {
			Player player = iterator.next();
			OutputStream out = player.getOutStream();
			try {
				out.write(msg);
				out.flush();
			} catch (IOException e) {
			}
		}
	}
	
	private void destroyThis() {
		timer.cancel();
		timerTask.cancel();
		FieldManager.getInstance().playareas.remove(this);
		System.out.println("Playarea destroyed");
	}
}
