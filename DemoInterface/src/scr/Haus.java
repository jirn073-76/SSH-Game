package scr;

public class Haus implements IEvents {

	public String adresse;
	
	public Haus(String adresse) {
		this.adresse=adresse;
	}
	
	@Override
	public void feuer() {
		System.out.println("Es brennt! Adresse:"+adresse);
	}
}
