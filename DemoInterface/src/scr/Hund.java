package scr;

public class Hund extends Lebewesen {

	
	
	public Hund(String name) {
		super(name);
	}
	
	@Override
	public void feuer() {
		super.feuer();
		System.out.println("...bellt der Hund");
	}
}
