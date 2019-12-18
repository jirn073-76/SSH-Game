
public class Menu {
		public String gameMode = "FFA";
		
		//TODO: Cursor Selection = Hinterlegen; Sonst nicht! :)
		
		public EColor color;
		private int width, height;
		private int cursorHeightPosition = 0;
		
		public void moveCursor(Direction dir) {
			switch(dir) {
					case up:
						if(cursorHeightPosition < 1) {
							cursorHeightPosition++;
						}
						break;
					case down:
						if(cursorHeightPosition > 0) {
							cursorHeightPosition--;
						}
						break;
					case left:
						if(cursorHeightPosition == 1 && gameMode.equals("FFA")) {
							gameMode.equals("Classic");
						}
						break;
					case right:
						if(cursorHeightPosition == 1 && gameMode.equals("Classic")) {
							gameMode.equals("FFA");
						}
						break;
			}
		}
		
		public Menu(	int height, int width) {
				this.width= width;
				this.height = height;
		}
		//Hinterlegen: 30; 107
		
		public byte[] getMenuAsByteArray() {
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < height; i ++)
				sb.append("\n\r");
			for(int y = 0; y < width+2; y++) {
				sb.append('#');
			}
			sb.append("\n\r");
			for(int y = 0; y < height/2-3; y++) {
				sb.append('#');
				for(int x = 0; x < width; x++) {
						sb.append("\u001B[0m ");
				}
				sb.append("\u001B[0m#\n\r");
			}
			sb.append('#');
			for(int x = 0; x < width/2-gameMode.length()/2-2; x++) {
				sb.append(" ");
			}
			
			sb.append(ColorUtil.getStringFromColorCode(107) + ColorUtil.getStringFromColorCode(30) + "   " +  gameMode + "   "+ ColorUtil.getBlackString());
			
			for(int x = 0; x < width/2-gameMode.length()/2-5; x++) {
				sb.append(" ");
			}
			sb.append("\u001B[0m#\n\r#");

			for(int x = 0; x < width/2-gameMode.length()/2-2; x++) {
				sb.append(" ");
			}
			
			sb.append(ColorUtil.getStringFromColorCode(107) + ColorUtil.getStringFromColorCode(30) + "  " +  "Play!" +  "  " +  ColorUtil.getBlackString());

			for(int x = 0; x < width/2-gameMode.length()/2-5; x++) {
				sb.append(" ");
			}
			
			sb.append("\u001B[0m#\n\r");
			for(int y = 0; y < height/2; y++) {
				sb.append("#");
				for(int x = 0; x < width; x++) {
						sb.append("\u001B[0m ");
				}
				sb.append("\u001B[0m#\n\r");
			}
			
			for(int y = 0; y < width+2; y++) {
				sb.append("#");
			}
			
			return sb.toString().getBytes();
		}
}
