package testvorbereitungSchach;

public class main {
	
	static Integer[][] dame = new Integer[2][8];
	
	public static boolean schachbrett[][]= new boolean[8][8];
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		schachbrett = new boolean[][] {
			{false,false,false,false,false,false,false,true},
            {false,false,false,true,false,false,false,false},
            {true,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false},
            {false,false,true,false,false,true,false,false},
            {false,true,false,false,false,false,true,false},
            {false,false,false,false,false,false,false,false},
            {false,false,false,false,true,false,false,false}
	    };
		schlagabtausch(schachbrett);
		
	}
	public static boolean schlagabtausch(boolean[][] schachbrett) {
	int z�hler=0;
	for (int i = 0; i < schachbrett.length; i++) {
		for (int j = 0; j < schachbrett[i].length; j++) {
			if(schachbrett[i][j]) {
				
				getPosition(i,j, z�hler);
				z�hler++;
				
			}
			}
		}
	int trueCounter=0;
	for (int i = 0; i < schachbrett.length; i++) {
		if(�berpr�feZahlenachse(dame[0][i], dame[1][i], schachbrett)) {
			trueCounter++;
		}
		if(�berpr�feBuchstabenachse(dame[0][i], dame[1][i], schachbrett)){
			trueCounter++;
		}
		if(�berpr�feDiagonale(dame[0][i], dame[1][i], schachbrett)) {
			trueCounter++;
		}
	}
	if(trueCounter==24) {
		System.out.println("8 Damen schlagen sich am Feld nicht");
		return true;
	}
	else {
		System.out.println("8 Damen schlagen sich am Feld ");
		return false;
	}
	
		
		
	
	}
	
	public static void getPosition(int x, int y, int z�hler) {
		dame[0][z�hler] = x;
		dame[1][z�hler]=y;
	}
	public static Boolean �berpr�feZahlenachse(int x, int y, boolean[][] brett) { 
		
		for (int i = 8; i > x; i--) {//rechts von x
			if(brett[i-1][y]) {
				return false;
			}
			
		}
		for (int i = 0; i < x; i++) {//links von x
			if(brett[i-1][y]) {
				return false;
			}
			
		}
		return true;
	}
	public static Boolean �berpr�feBuchstabenachse(int x, int y, boolean[][] brett) {
		for (int i = 8; i > y; i--) {// unter x
			if(brett[y][i-1]) {
				return false;
			}
			
		}
		for (int i = 0; i < y; i++) {//ober x
			if(brett[y][i]) {
				return false;
			}
			
		}
		return true;
	}
	public static Boolean �berpr�feDiagonale(int x, int y, boolean[][] brett) {
		for (int i = y; i < 8; i++) {//6
			try{if(brett[i][x-1]) {
				return false;
			}
			x--;
			}catch(Exception e){}
			
		}
		for (int i = y; i < 8; i++) {
			if(brett[i][x+1]) {
				return false;
			}
			x++;
		}
		for (int i = x; i > 1; i--) {
			if(brett[i-2][x-1]) {
				return false;
			}
			x--;
		}
		for (int i = x; i > 1; i--) {
			try{if(brett[i-2][x+1]) {
				return false;
			}
			x--;
			
		}catch(Exception e){}
		}
		
		return true;
	}

}
