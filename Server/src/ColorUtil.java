import java.util.Dictionary;

public class ColorUtil {
		public static String getColoredString(EColor color) {
			 return "\u001B["+(40+color.ordinal())+"m";
		}
		
		public static String getBlackString() {
			 return "\u001B["+0+"m";
		}
		
		public static String getStringFromColorCode(int ccode) {
			 return "\u001B["+ccode+"m";
		}
}