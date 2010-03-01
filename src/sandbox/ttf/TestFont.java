package ttf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

/**
 * how to use pdf ttf
 * @author nisen.cn@gmail.com * 
 */
public class TestFont {
	public static void main(String[] args) {
		try {
			PDDocument doc = PDDocument.load("test/a.pdf");
			PDFont font = PDTrueTypeFont.loadTTF( doc, new File("test/ArialMT.ttf") );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
