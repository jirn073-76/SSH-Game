
public class Menu {
		public String gameMode;
		
		public EColor color;
		private int width, height;

		public Menu(	int height, int width) {
				this.width= width;
				this.height = height;
		}
		
		public byte[] getMenuAsByteArray() {
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < height; i ++)
				sb.append("\n\r");
			for(int y = 0; y < width+2; y++) {
				sb.append('#');
			}
			sb.append("\n\r");
			for(int y = 0; y < height; y++) {
				sb.append('#');
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
