package modernfonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class ModernFonts {
	private static float defaultSize=24.0f;
	
	public static Font loadFont(String fontFileName, float size) {
		size=size==0?defaultSize:size;
		String  path = System.getProperty("user.dir")+"/src/main/java/modernfonts/fontCollection/"+fontFileName+".ttf";
		File font = new File(path);
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return  myFont.deriveFont(size);
	}
		
}
