package testvorbereitung1;

public class fahrrad extends fahrzeug {

	public int gewicht;
	public fahrrad(int gewicht, float ppreis, String bez) {
		super(ppreis, bez);
		// TODO Auto-generated constructor stub
		this.gewicht=gewicht;
	}
	
	@Override
	public float getVerkaufsPreis() {
		return super.getVerkaufsPreis()/100*93;
		
	}
}
