import java.util.Iterator;

public class PlayerList implements Iterable<Player> {
	
	private PlayerNode head = null;

	public void add(Player player){
		PlayerNode node = new PlayerNode(player);
		node.setNext(head);
		head=node;
	}
	public void remove(Player player) {
		if(head.getPlayer()==player) {
			head = head.getNext();
			return;
		}
		
		PlayerNode node = head;
		while(node.hasNext() && node.getNext().getPlayer()!=player) 
			node=node.getNext();

		if(!node.hasNext())
			return;
		
		node.setNext(node.getNext().getNext());
	}
	
	public int indexOf(Player player) {
		int index = 0;
		PlayerNode node = head;
		while(node!=null) {
			if(node.getPlayer()==player) {
				return index;
			}
			node=node.getNext();
		}
		return -1;
	}
	
	public int getLength() {
		int length = 0;
		PlayerNode node = head;
		while(node != null) {
			length++;
			node = node.getNext();
		}
		return length;
	}
	
	@Override
	public Iterator<Player> iterator() {
		return new Iterator<Player>() {
			PlayerNode node = head;
			@Override
			public Player next() {
				Player player = node.getPlayer();
				node=node.getNext();
				return player;
			}
			
			@Override
			public boolean hasNext() {
				return node!=null;
			}
		};
	}
}
