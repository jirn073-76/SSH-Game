package src;

public class Polizist extends Person{

	public String dienstgrad;
	
	public Polizist(String vorname, String nachname
			, String dienstgrad) {
		super(vorname, nachname);
		// TODO Auto-generated constructor stub
		this.dienstgrad=dienstgrad;
	}
	@Override
	public void regen() {
		super.regen();
		System.out.println("..sagt der Polizist");
	}
	
	@Override
	public void temperaturänderung() {
		super.regen();
		System.out.println("..sagt der Polizist");	
	}
	
}
