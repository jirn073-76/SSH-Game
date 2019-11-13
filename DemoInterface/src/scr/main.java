package scr;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Simulation s=new Simulation();
		s.addListener(new Hund("Bello"));
		s.addListener(new Hund("Hund"));
		s.addListener(new Kind("Kind"));
		s.addListener(new Kind("Fritz"));
		s.addListener(new Haus("1220 Wien, AndereAdresse 47"));
		s.addListener(new Haus("1010 Wien, SuperStraße 1"));
		s.eventAusloesen();
	}

}
