
public class ColorUtil {
		public static String getColoredString(EColor color) {
			 return "\u001B["+(40+color.ordinal())+"m|";
		}
		
		public static String getBlackString() {
			 return "\u001B["+0+"m|";
		}
}