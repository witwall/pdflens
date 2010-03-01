package org.apache.pdflens.views.treeview;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;



public class PDFTreeView extends JTree implements TreeSelectionListener
{
    private PDDocument document = null;
    
    public PDFTreeView()
    {
        this.setCellRenderer( new PDFTreeCellRenderer() );
        this.setModel( null );
        
        this.addTreeSelectionListener(this);
    }
    
    /**
     * when doubleclick the node
     * @param evt
     */
    public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
    {
        TreePath path = this.getSelectionPath();
        if (path != null)
        {
            try
            {
                Object selectedNode = path.getLastPathComponent();
                String data=convertToString(selectedNode);



                if (data != null)
                {
                   // jTextPane1.setText(data);
                    System.out.println("data:"+data);
                }
                else
                {
                   // jTextPane1.setText( "" );
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private String convertToString( Object selectedNode )
    {
        String data = null;
        if(selectedNode instanceof COSBoolean)
        {
            data = "" + ((COSBoolean)selectedNode).getValue();
        }
        else if( selectedNode instanceof COSFloat )
        {
            data = "" + ((COSFloat)selectedNode).floatValue();
        }
        else if( selectedNode instanceof COSNull )
        {
            data = "null";
        }
        else if( selectedNode instanceof COSInteger )
        {
            data = "" + ((COSInteger)selectedNode).intValue();
        }
        else if( selectedNode instanceof COSName )
        {
            data = "" + ((COSName)selectedNode).getName();
        }
        else if( selectedNode instanceof COSString )
        {
            data = "" + ((COSString)selectedNode).getString();
        }
        else if( selectedNode instanceof COSStream )
        {
            try
            {
                COSStream stream = (COSStream)selectedNode;
                InputStream ioStream = stream.getUnfilteredStream();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int amountRead = 0;
                while( (amountRead = ioStream.read( buffer, 0, buffer.length ) ) != -1 )
                {
                    byteArray.write( buffer, 0, amountRead );
                }
                data = byteArray.toString();
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if( selectedNode instanceof MapEntry )
        {
            data = convertToString( ((MapEntry)selectedNode).getValue() );
        }
        else if( selectedNode instanceof ArrayEntry )
        {
            data = convertToString( ((ArrayEntry)selectedNode).getValue() );
        }
        return data;
    }

    /**
     * @param document
     */
    public void setPDFDocument(PDDocument doc)
    {
        this.document=doc;
        TreeModel model=new PDFTreeModel(document);
        this.setModel(model);
        // TODO Auto-generated method stub
        
    }
}
