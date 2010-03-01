package org.apache.pdflens.plugins.texttopdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.pdfbox.TextToPDF;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class TextToPDFUtils
{
    public static void main(String[] args) throws FileNotFoundException
    {
       //
        textToPDF("helloworld",new FileOutputStream("test/TextToPDF.pdf"));
        System.out.println(TextToPDFUtils.class+ "output test/TextToPDF.pdf");
    }
    
    
    public static void textToPDF(String src, OutputStream output)
    {
        textToPDF(new StringReader(src),output);
    }
    public static void textToPDF(Reader src, OutputStream output)
    {
        TextToPDF app = new TextToPDF();
        PDDocument doc = null;        
        //app.setFont( PDType1Font.HELVETICA);          
        // app.setFontSize(10 );
 
        try
        {
            doc = app.createPDFFromText( src );
            doc.save( output );
        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(COSVisitorException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if( doc != null )
            {
                try
                {
                    doc.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
