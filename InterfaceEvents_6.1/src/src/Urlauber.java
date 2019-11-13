package src;

public class Urlauber extends Person{

	public String heimatland;
	public int badewettertemp;
	
	public Urlauber(String vorname, String nachname
			, String heimatland, int badewettertemp) {
		super(vorname, nachname);
		// TODO Auto-generated constructor stub
		this.heimatland=heimatland;
		this.badewettertemp=badewettertemp;
	}
	
	@Override
	public void regen() {
		super.regen();
		fahreHeim();
	}
	
	@Override
	public void temperaturänderung() {
		super.regen();
		if (true) {
			geheBaden();
		}
		
	}
	
	
	
	
	public void geheBaden() {
		System.out.println("Ich gehe Baden, sagt der Urlauber");
	}
	public void fahreHeim() {
		System.out.println("Ich fahre Heim, sagt der Urlauber");
	}
	
}
