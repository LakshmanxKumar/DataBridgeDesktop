package modernfonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ModernFonts {
	private static float defaultSize=24.0f;
	
	public static Font loadFont(String fontFileName, float size) {
		size=size==0?defaultSize:size;
		String  path = "/fontCollection/"+fontFileName+".ttf";
		InputStream font = ModernFonts.class.getResourceAsStream(path);

		Font myFont = new Font("Arial", Font.BOLD, 14);
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return  myFont.deriveFont(size);
	}
		
}
