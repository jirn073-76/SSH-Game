package src;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulation s=new Simulation();
		s.addListener(new Blume("Gänseblume", "Gelb", 25, 27, 21));
		s.addListener(new Blume("Pusteblume", "Weiß", 31, 34, 26));
		s.addListener(new Baum("Tannenbaum", 125, 137, 101));
		s.addListener(new Baum("Birke", 131, 154, 96));
		s.addListener(new Polizist("Diego", "Maradona", "Legende"));
		s.addListener(new Polizist("Carles", "Puyol", "Amateur"));
		s.addListener(new Feuerwehrmann("Zenedine", "Zidane", "Retter"));
		s.addListener(new Feuerwehrmann("Jose", "Mourinho", "Löscher"));
		s.addListener(new Urlauber("Ole", "Solskjaer", "Schottland", 20));
		s.addListener(new Urlauber("Carlo", "Angelotti", "Spanien", 27));
		s.eventAusloesen();
		
	}

}
