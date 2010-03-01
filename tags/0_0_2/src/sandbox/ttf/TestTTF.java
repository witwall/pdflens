package ttf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.fontbox.ttf.CMAPEncodingEntry;
import org.apache.fontbox.ttf.CMAPTable;
import org.apache.fontbox.ttf.GlyphData;
import org.apache.fontbox.ttf.GlyphTable;
import org.apache.fontbox.ttf.HeaderTable;
import org.apache.fontbox.ttf.HorizontalHeaderTable;
import org.apache.fontbox.ttf.HorizontalMetricsTable;
import org.apache.fontbox.ttf.NameRecord;
import org.apache.fontbox.ttf.NamingTable;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.fontbox.ttf.PostScriptTable;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;


public class TestTTF {
	public static void main(String[] args) {
		try {
			loadTTF(new File("src/sandbox/ArialMT.ttf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 /**
	  * copy from org.apache.pdfbox.pdmodel.font.PDTrueTypeFont.loadTTF()
	  * PDFont 所有pdf的字体  把ttf转化为pdf的字体. 
	  * 
     * This will load a TTF to be embedding into a document.
     *
     * @param doc The PDF document that will hold the embedded font.
     * @param file A TTF file stream.
     * @return A PDF TTF.
     * @throws IOException If there is an error loading the data.
     * @see org.apache.pdfbox.pdmodel.font.PDTrueTypeFont.loadTTF()
     *     */
    public static void loadTTF( File file ) throws IOException
    {
//        PDTrueTypeFont retval = new PDTrueTypeFont();
//        PDFontDescriptorDictionary fd = new PDFontDescriptorDictionary();
//        PDStream fontStream = new PDStream(doc, new FileInputStream( file ), false );

        TrueTypeFont ttf = null;
        try
        {
            TTFParser parser = new TTFParser();
            ttf = parser.parseTTF( file );
            NamingTable naming = ttf.getNaming();
            List records = naming.getNameRecords();
            for( int i=0; i<records.size(); i++ )
            {
                NameRecord nr = (NameRecord)records.get( i );
            	System.out.println("------------bgein----------");
                System.out.println("nr.getNameId()="+nr.getNameId() +" nr.getString()="+nr.getString());
                System.out.println("------------end----------");
                if( nr.getNameId() == NameRecord.NAME_POSTSCRIPT_NAME )
                {
//                    retval.setBaseFont( nr.getString() );
//                    fd.setFontName( nr.getString() );
                }
                else if( nr.getNameId() == NameRecord.NAME_FONT_FAMILY_NAME )
                {
//                    fd.setFontFamily( nr.getString() );
                }
            }
            
            System.out.println("------------no repeat---------------------------");

            OS2WindowsMetricsTable os2 = ttf.getOS2Windows();
            //fd.setNonSymbolic( true );
            switch( os2.getFamilyClass() )
            {
                case OS2WindowsMetricsTable.FAMILY_CLASS_SYMBOLIC:
                   // fd.setSymbolic( true );
                    //fd.setNonSymbolic( false );
                    break;
                case OS2WindowsMetricsTable.FAMILY_CLASS_SCRIPTS:
                    //fd.setScript( true );
                    break;
                case OS2WindowsMetricsTable.FAMILY_CLASS_CLAREDON_SERIFS:
                case OS2WindowsMetricsTable.FAMILY_CLASS_FREEFORM_SERIFS:
                case OS2WindowsMetricsTable.FAMILY_CLASS_MODERN_SERIFS:
                case OS2WindowsMetricsTable.FAMILY_CLASS_OLDSTYLE_SERIFS:
                case OS2WindowsMetricsTable.FAMILY_CLASS_SLAB_SERIFS:
                    //fd.setSerif( true );
                    break;
                default:
                    //do nothing
            }
            switch( os2.getWidthClass() )
            {
                case OS2WindowsMetricsTable.WIDTH_CLASS_ULTRA_CONDENSED:
                    //fd.setFontStretch( "UltraCondensed" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_EXTRA_CONDENSED:
                    //fd.setFontStretch( "ExtraCondensed" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_CONDENSED:
                    //fd.setFontStretch( "Condensed" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_SEMI_CONDENSED:
                   // fd.setFontStretch( "SemiCondensed" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_MEDIUM:
                    //fd.setFontStretch( "Normal" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_SEMI_EXPANDED:
                    //fd.setFontStretch( "SemiExpanded" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_EXPANDED:
                    //fd.setFontStretch( "Expanded" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_EXTRA_EXPANDED:
                   // fd.setFontStretch( "ExtraExpanded" );
                    break;
                case OS2WindowsMetricsTable.WIDTH_CLASS_ULTRA_EXPANDED:
                   // fd.setFontStretch( "UltraExpanded" );
                    break;
                default:
                    //do nothing
            }
            
            System.out.println("os2.getWeightClass()="+os2.getWeightClass());
            //fd.setFontWeight( os2.getWeightClass() );

            //todo retval.setFixedPitch
            //todo retval.setNonSymbolic
            //todo retval.setItalic
            //todo retval.setAllCap
            //todo retval.setSmallCap
            //todo retval.setForceBold

            //头
            HeaderTable header = ttf.getHeader();
           // PDRectangle rect = new PDRectangle();
//            rect.setLowerLeftX( header.getXMin() * 1000f/header.getUnitsPerEm() );
//            rect.setLowerLeftY( header.getYMin() * 1000f/header.getUnitsPerEm() );
//            rect.setUpperRightX( header.getXMax() * 1000f/header.getUnitsPerEm() );
//            rect.setUpperRightY( header.getYMax() * 1000f/header.getUnitsPerEm() );
//            fd.setFontBoundingBox( rect );

            //另外一个变量
            HorizontalHeaderTable hHeader = ttf.getHorizontalHeader();
//            fd.setAscent( hHeader.getAscender() * 1000f/header.getUnitsPerEm() );
//            fd.setDescent( hHeader.getDescender() * 1000f/header.getUnitsPerEm() );

            GlyphTable glyphTable = ttf.getGlyph();
            GlyphData[] glyphs = glyphTable.getGlyphs();

            PostScriptTable ps = ttf.getPostScript();
//            fd.setFixedPitch( ps.getIsFixedPitch() > 0 );
//            fd.setItalicAngle( ps.getItalicAngle() );

            String[] names = ps.getGlyphNames();
            if( names != null )
            {
                for( int i=0; i<names.length; i++ )
                {
                    //if we have a capital H then use that, otherwise use the
                    //tallest letter
                    if( names[i].equals( "H" ) )
                    {
//                        fd.setCapHeight( (glyphs[i].getBoundingBox().getUpperRightY()* 1000f)/
//                                         header.getUnitsPerEm() );
                    }
                    if( names[i].equals( "x" ) )
                    {
//                        fd.setXHeight( (glyphs[i].getBoundingBox().getUpperRightY()* 1000f)/header.getUnitsPerEm() );
                   }
                }
            }

            //hmm there does not seem to be a clear definition for StemV,
            //this is close enough and I am told it doesn't usually get used.
           // fd.setStemV( (fd.getFontBoundingBox().getWidth() * .13f) );


            CMAPTable cmapTable = ttf.getCMAP();  //得到
            CMAPEncodingEntry[] cmaps = cmapTable.getCmaps();
            int[] glyphToCCode = null;
            for( int i=0; i<cmaps.length; i++ )
            {
                if( cmaps[i].getPlatformId() == CMAPTable.PLATFORM_WINDOWS &&
                    cmaps[i].getPlatformEncodingId() == CMAPTable.ENCODING_UNICODE )
                {
                    glyphToCCode = cmaps[i].getGlyphIdToCharacterCode();
                }
            }
            int firstChar = 0;
            int maxWidths=256;
            HorizontalMetricsTable hMet = ttf.getHorizontalMetrics();
            int[] widthValues = hMet.getAdvanceWidth();
            List widths = new ArrayList(maxWidths);
            Integer zero = new Integer( 250 );
            for( int i=0; i<maxWidths; i++ )
            {
                widths.add( zero );
            }
            for( int i=0; i<widthValues.length; i++ )
            {
                if(glyphToCCode[i]-firstChar < widths.size() && glyphToCCode[i]-firstChar >= 0 
                        && widths.get( glyphToCCode[i]-firstChar) == zero )
                {
                    widths.set( glyphToCCode[i]-firstChar,
                        new Integer( (int)(widthValues[i]* 1000f)/header.getUnitsPerEm() ) );
                }
            }
            
//            retval.setWidths( widths );
//            retval.setFirstChar( firstChar );
//            retval.setLastChar( firstChar + widths.size()-1 );

        }
        finally
        {
            if( ttf != null )
            {
                ttf.close();
            }
        }
        return;
        //return retval;
    }
}
