package scr;

public class Simulation {

	IEvents[] zuhoerer=new IEvents[6];
	int i=0;
	
	public void addListener(IEvents neuerZuhoerer) {
		zuhoerer[i%4]=neuerZuhoerer;
		i++;
	}
	
	public Simulation() {
	
	}
	
	public void eventAusloesen() {
		for (int i = 0; i < 4; i++) {
			zuhoerer[i].feuer();
		}
	}
}
