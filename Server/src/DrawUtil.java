
public class DrawUtil {
	static public byte[] borderString(String[] buffer, int width , char character) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<10+2;i++)
			sb.append("\n\r");
		
		for(int i = 0; i<width+2;i++)
			sb.append(character);
		sb.append("\n\r");
		
		for (int i = 0; i < buffer.length; i++) {
			sb.append(character);
			sb.append(buffer[i]);
			sb.append(ColorUtil.getBlackString());
			sb.append(character);
			sb.append("\n\r");
		}
		
		for(int i = 0; i<width+2;i++)
			sb.append(character);
		
		return sb.toString().getBytes();
	}

	static public byte[] borderString(String[][] buffer, int width, char character) {
		StringBuilder[] buffer2 = new StringBuilder[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			buffer2[i] = new StringBuilder();
			for (int j = 0; j < buffer[i].length; j++) {
				if(buffer[i][j] == null || buffer[i][j]=="")
					buffer2[i].append(" ");
				else
					buffer2[i].append(buffer[i][j]);
			}
		}
		String[] buffer3 = new String[buffer2.length];
		for (int i = 0; i < buffer2.length; i++) {
			buffer3[i]=buffer2[i].toString();
		}
		return borderString(buffer3,width,character);
	}
	
	static public String[] create1DFrom2D(String[][] buffer) {
		StringBuilder[] buffer2 = new StringBuilder[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			buffer2[i] = new StringBuilder();
			for (int j = 0; j < buffer[i].length; j++) {
				if(buffer[i][j] == null || buffer[i][j]=="")
					buffer2[i].append(" ");
				else
					buffer2[i].append(buffer[i][j]);
			}
		}
		String[] buffer3 = new String[buffer2.length];
		for (int i = 0; i < buffer2.length; i++) {
			buffer3[i]=buffer2[i].toString();
		}
		return buffer3;
	}
	static public String getStringWith(char c, int length) {
		return getStringWith(String.valueOf(c), length);
	}
	static public String getStringWith(String s, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(s);
		}
		return sb.toString();
	}
	static public String[] createLine(int width, char character) {
		String[] result  = new String[width];
		for (int i = 0; i < width; i++) {
			result[i] = String.valueOf(character);
		}
		return result;
	}
}