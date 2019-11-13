package testvorbereitung1;

public class auto extends fahrzeug {

	public int ps;
	public auto(int ps, float ppreis, String bez) {
		super(ppreis, bez);
		// TODO Auto-generated constructor stub
		this.ps=ps;
	}
	
	public auto(auto copy) {
		super(copy.ppreis, copy.bez);
		this.ps=copy.ps;
	}
	@Override
	public float getVerkaufsPreis() {
		return super.getVerkaufsPreis()/100*103;
		
	}
}
