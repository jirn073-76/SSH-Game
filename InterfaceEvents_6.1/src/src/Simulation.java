package src;

public class Simulation {
	
	IEventsWetter[] zuhoerer=new IEventsWetter[12];
	int i=0;
	
	public void addListener(IEventsWetter neuerZuhoerer) {
		zuhoerer[i%12]=neuerZuhoerer;
		i++;
	}
	public Simulation() {
		
		
		
	}
	
	
	
	public void eventAusloesen() {
		for (int i = 0; i < this.i && i < 12; i++) {
			zuhoerer[i].regen();
			zuhoerer[i].temperaturänderung();
		}
		}
 
}
