package src;

public class Feuerwehrmann extends Person{

	public String aufgabenbereich;
	
	public Feuerwehrmann(String vorname, String nachname
			, String aufgabenbereich) {
		super(vorname, nachname);
		this.aufgabenbereich=aufgabenbereich;
	}
	
	@Override
	public void regen() {
		super.wechsleKleidung();
		System.out.println("...sagt der Feuerwehrmann");
	}
	
	@Override
	public void temperaturänderung() {
		super.wechsleKleidung();
		System.out.println("...sagt der Feuerwehrmann");
	}
	
}
