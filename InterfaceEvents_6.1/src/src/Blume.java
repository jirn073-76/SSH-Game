package src;

public class Blume extends Pflanzen{

	public String farbe;
	
	public Blume(String bezeichnung, String farbe,
			int wasserstand, int wasserstandMax, 
			int wasserstandMin) {
		super(bezeichnung, wasserstand, wasserstandMax,
				wasserstandMin);
		this.farbe=farbe;
		
	}
	
	@Override
	public void regen() {
		super.regen();
		System.out.println("...sagt die "+farbe+" Blume");
	} 
	
	@Override
	public void temperaturänderung() {
		super.regen();
		System.out.println("...sagt die "+farbe+" Blume");
	}
}
