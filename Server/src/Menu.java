
public class Menu {
        private String gameMode = "Classic";
        
        private EColor[] colors = {EColor.red, EColor.green, EColor.orange, EColor.blue, EColor.pink, EColor.lightblue, EColor.white, EColor.black};
        private int colorIndex = Integer.MAX_VALUE/2+1;
        private int width, height;
        private int cursorHeightPosition = 0;
        
        public boolean isCursorOnPlay() {
            return cursorHeightPosition == 0;
        }
        
        public menuState getColorAndGamemode() {
            return new menuState(colors[colorIndex%colors.length], gameMode);
        }
        
        final class menuState {
            public final EColor color;
            public final String gameMode;
            public menuState(EColor color, String gameMode) {
                this.gameMode = gameMode;
                this.color = color;
            }
        }
        
        public void moveCursor(Direction dir) {
            switch(dir) {
                    case up:
                        if(cursorHeightPosition < 2) {
                            cursorHeightPosition++;
                        }
                        break;
                    case down:
                        if(cursorHeightPosition > 0) {
                            cursorHeightPosition--;
                        }
                        break;
                    case left:
                        if(cursorHeightPosition == 2 && gameMode.equals("FFA")) {
                            gameMode = "Classic";
                        }
                        
                        if(cursorHeightPosition == 1) {
                            colorIndex--;
                        }
                        break;
                    case right:
                        if(cursorHeightPosition == 2 && gameMode.equals("Classic")) {
                            gameMode ="FFA";
                        }
                        
                        if(cursorHeightPosition == 1) {
                            colorIndex++;
                        }
                        break;
            }
        }
        
        public Menu(    int height, int width) {
                this.width= width;
                this.height = height;
        }
        //Hinterlegen: 30; 107
        
        public byte[] getMenuAsByteArray() {
            StringBuilder sb = new StringBuilder();
            
            String blackCode = ColorUtil.getBlackString();
            String iconCode = ColorUtil.getStringFromColorCode(30) + ColorUtil.getColoredString(colors[colorIndex%colors.length]);
            sb.append("\n\r############################################################################################\n\r" + 
                    "#                                                                                          #\n\r" + 
                    "#                                                                                          #\n\r" + 
                    "#                                                                                          #\n\r" + 
                    "#                 "+ iconCode + "________ ________         ___________" + blackCode+ "                                    #\n\r" + 
                    "#                 " +iconCode+ "\\_____  \\\\_____  \\        \\__    ___/______  ____   ____" +blackCode+ "                 #\n\r" + 
                    "#                 " +iconCode+ "/  ____/ /  ____/   ______  |    |  \\_  __ \\/  _ \\ /    \\" +blackCode+ "                #\n\r" + 
                    "#                 "+iconCode+"/       \\/       \\  /_____/ |    |   |  | \\(  <_> )   |  \\"+blackCode+"               #\n\r" + 
                    "#                 "+iconCode+"\\_______ \\_______ \\         |____|   |__|   \\____/|___|  /"+blackCode+"               #\n\r" + 
                    "#                         "+iconCode+"\\/       \\/                                    \\/"+blackCode+"                #\n\r" + 
                    "#                                                                                          #\n\r" + 
                    "#                                                                                          #\n\r" + 
                    "#                                                                                          #\n\r" + 
                    "#");
            
            /*


________ ________         ___________                     
\_____  \\_____  \        \__    ___/______  ____   ____  
 /  ____/ /  ____/   ______ |    |  \_  __ \/  _ \ /    \ 
/       \/       \  /_____/ |    |   |  | \(  <_> )   |  \
\_______ \_______ \         |____|   |__|   \____/|___|  /
        \/       \/                                    \/

             */

            for(int x = 0; x < width/2-gameMode.length()/2-3; x++) {
                sb.append(" ");
            }
            
            // Is Gamemode  selection
            if(cursorHeightPosition == 2) {
                sb.append(ColorUtil.getStringFromColorCode(107) + ColorUtil.getStringFromColorCode(30) + "<    " +  gameMode + "    >"+ColorUtil.getBlackString());
            }
            else {
                sb.append("<    " +  gameMode + "    >");
            }
            
            for(int x = 0; x < width/2-gameMode.length()/2-8; x++) {
                sb.append(" ");
            }
            sb.append("\u001B[0m#\n\r#");

            for(int x = 0; x < width/2-gameMode.length()/2-3; x++) {
                sb.append(" ");
            }
            
            String spacesForColor = "";
            for(int i = 0; i < gameMode.length(); i++) 
                spacesForColor += " ";
            
            // Color selection
            if(cursorHeightPosition == 1) {
                sb.append(ColorUtil.getStringFromColorCode(107) + ColorUtil.getStringFromColorCode(30) +"< " +  ColorUtil.getColoredString(colors[colorIndex%colors.length])  + spacesForColor  + "      " +  ColorUtil.getStringFromColorCode(107) + ColorUtil.getStringFromColorCode(30)  + " >" +ColorUtil.getBlackString() );
            }
            else {
                sb.append("< " +  ColorUtil.getColoredString(colors[colorIndex%colors.length])  + spacesForColor  + "      "+ ColorUtil.getBlackString() + " >");
            }
            
            for(int x = 0; x < width/2-gameMode.length()/2-8; x++) {
                sb.append(" ");
            }
            sb.append("\u001B[0m#\n\r#");

            for(int x = 0; x < width/2-4; x++) {
                sb.append(" ");
            }
            
            // Is Play selected
            if(cursorHeightPosition == 0) {
                sb.append(ColorUtil.getStringFromColorCode(107) + ColorUtil.getStringFromColorCode(30) + "    " +  "Play!" +  "    " +  ColorUtil.getBlackString());
            }
            else {
                sb.append("    " +  "Play!" +  "    ");
            }

            for(int x = 0; x < width/2-9; x++) {
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
