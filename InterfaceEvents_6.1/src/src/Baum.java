package src;

public class Baum extends Pflanzen{

	public Baum(String bezeichnung,
			int wasserstand, int wasserstandMax, 
			int wasserstandMin) {
		super(bezeichnung, wasserstand, wasserstandMax,
				wasserstandMin);
		
	}
	@Override
	public void regen() {
		super.regen();
		System.out.println("...sagt der Baum");
	} 
	
	@Override
	public void temperaturänderung() {
		super.regen();
		System.out.println("...sagt der Baum");
	}
}
