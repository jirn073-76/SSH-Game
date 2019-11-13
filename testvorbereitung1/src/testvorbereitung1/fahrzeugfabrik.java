package testvorbereitung1;

public class fahrzeugfabrik {

	public static fahrzeug[][] fahrzeuge=new fahrzeug[4][25];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 24; i++) {
			fahrzeuge[0][i] = new auto(300, 4000, "Bugatti Type 41");
			fahrzeuge[1][i] = new auto(131, 2500, "DeLorian DMC12");
			fahrzeuge[2][i] = new fahrrad(12, 49, "Tom Turbo");
			fahrzeuge[3][i] = new fahrrad(19, 79, "Alter Gaul");
			
		}
		float sum=0;
		for (int i = 0; i < fahrzeuge.length; i++) {
			for (int j = 0; j < fahrzeuge[i].length; j++) {
				sum+=fahrzeuge[i][j].getVerkaufsPreis();
			}
		}
			}
	public boolean ident(fahrzeug f1, fahrzeug f2) {
		if(f1 instanceof fahrrad && f2 instanceof fahrrad) {
			if (f1.bez.equals(f2.bez) && f1.ppreis==f2.ppreis) {
				if (((fahrrad)f1).gewicht==((fahrrad)f2).gewicht) {
					return true;
				}
				if(f1 instanceof auto && f2 instanceof auto) {
					if (f1.bez.equals(f2.bez) && f1.ppreis==f2.ppreis) {
						if (((auto)f1).ps==((auto)f2).ps) {
							return true;
						}
						
				}
		}

	}
			}
		return false;
	
		}
	}
