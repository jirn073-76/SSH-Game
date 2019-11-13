package testvorbereitung1;

public abstract class fahrzeug {
	public String bez;
	public float ppreis;
	
	public fahrzeug(float ppreis, String bez) {
		this.bez=bez;
		this.ppreis=ppreis;
	}
	public float getVerkaufsPreis() {
		float verkaufspreis;
		if (ppreis<100) {
			verkaufspreis=ppreis*2;
		}else if (ppreis>100 && ppreis<1000) {
			verkaufspreis=ppreis*3;
		}else if (ppreis>999 && ppreis<10000) {
			verkaufspreis=ppreis*2;
		}else {
			verkaufspreis=ppreis*3;
		}
		
		return verkaufspreis;
	}
}
