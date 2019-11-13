package scr;

public class Kind extends Lebewesen{


	
	public Kind(String name) {
		super(name);
	}
	
	@Override
	public void feuer() {
		super.feuer();
		System.out.println("...sagt das Kind");
	}
}
