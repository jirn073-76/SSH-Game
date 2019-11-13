package scr;

public abstract class Lebewesen implements IEvents{

	public String name;
	
	public Lebewesen(String name) {
		this.name=name;
	}
	
	@Override
	public void feuer() {
		System.out.println("Hilfe, es brennt!");
	}
}
