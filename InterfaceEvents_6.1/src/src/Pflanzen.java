package src;

public abstract class Pflanzen implements IEventsWetter{

	public String bezeichnung;
	public int wasserstand;
	public int wasserstandMax;
	public int wasserstandMin;
	
	public Pflanzen(String bezeichnung, int wasserstand, 
			int wasserstandMax, int wasserstandMin) {
		this.bezeichnung=bezeichnung;
		this.wasserstand=wasserstand;
		this.wasserstandMax=wasserstandMax;
		this.wasserstandMin=wasserstandMin;
	}
	
	@Override
	public void regen() {
		if (true) {
			System.out.println("Ich verliere Blätter!");
		}
	} 
	@Override
	public void temperaturänderung() {
		if (true) {
			System.out.println("Ich verliere Blätter!");
		}
	} 
}
