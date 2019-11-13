package src;

public abstract class Person implements IEventsWetter{

	public String vorname;
	public String nachname;
	
	public Person(String vorname, String nachname) {
		this.vorname=vorname;
		this.nachname=nachname;
	}
	
	@Override
	public void regen() {
		System.out.print("Es regnet!");
	}
	
	@Override
	public void temperatur�nderung() {
		System.out.print("Temperatur �ndert sich!");
	}
	
	public void wechsleKleidung() {
		System.out.print("Wechsle Kleidung!");
	}
}
