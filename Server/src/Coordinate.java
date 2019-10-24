
public class Coordinate {
	public int x, y;
	
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Coordinate(Coordinate c) {
		this.x=c.x;
		this.y=c.y;
	}
	
	public String toString() {
		return "("+x+"/"+y+")";
	}
}
