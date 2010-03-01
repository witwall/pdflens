package org.apache.pdflens.views.pagesview;

import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PagesView extends JPanel
{
    
    public PDDocument document = null;
    public List pages= null;
    
    public int currentPage = 0;
    public int numberOfPages = 0;
    
    
    private void showPage(int pageNumber)
    {
        try 
        { 
            PageWrapper wrapper = new PageWrapper();
            wrapper.displayPage( (PDPage)pages.get(pageNumber) );
            if (this.getComponentCount() > 0)
            {
                this.remove(0);
            }
            this.add( wrapper.getPanel() );
           //  this.getParent()  pack();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
    
  
    public void nextPage()
    {
        if (currentPage < numberOfPages-1) 
        {
            currentPage++;
            showPage(currentPage);
        }
    }
    
    public void previousPage()
    {
        if (currentPage > 0 ) 
        {
            currentPage--;
            showPage(currentPage);
        }
    }


    public void setModel(PDDocument document)
    {
        pages = document.getDocumentCatalog().getAllPages();
        numberOfPages = pages.size();
        currentPage = 0;
        showPage(0);
    }
    
}
