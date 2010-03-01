/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdflens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.apache.pdfbox.exceptions.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.ExtensionFileFilter;
import org.apache.pdflens.views.nodeview.NodeView;
import org.apache.pdflens.views.pagesview.PagesView;
import org.apache.pdflens.views.tagsview.TagsView;
import org.apache.pdflens.views.treeview.PDFTreeView;

/**
 * copy from pdfbox.Debugger
 * @author nisen.cn@gmail.com
 * @version $Revision: 0.1 $
 */
public class Main extends javax.swing.JFrame
{
    private File currentDir=new File(".");
    private PDDocument document = null;
    
    
    private PagesView pagesView = new PagesView();
    private TagsView tagsView=new TagsView();
    
    private NodeView nodeView=new NodeView();
    private PDFTreeView treeView=new PDFTreeView();
    /**
     * Constructor.
     */
    public Main()
    {
        initComponents();
    }


    private void initComponents()
    {
        main = new javax.swing.JSplitPane();
        left = new javax.swing.JScrollPane();
        right = new javax.swing.JScrollPane();

        nodeMessage = new javax.swing.JTextPane();
        
        
        menuBar = new javax.swing.JMenuBar();
        
        fileMenu = new javax.swing.JMenu();        
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        
        viewsMenu = new javax.swing.JMenu();
        treeviewItem = new javax.swing.JMenuItem();
        pageviewItem = new javax.swing.JMenuItem();
        
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();        
        
        setTitle("PDFLen");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                exitForm(evt);
            }
        });


        left.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        left.setPreferredSize(new java.awt.Dimension(300, 500));

        left.setViewportView(treeView);
        
        
        right.setPreferredSize(new java.awt.Dimension(300, 500));
        right.setViewportView(pagesView);

        main.setRightComponent(right);
        main.setLeftComponent(left);

        JScrollPane documentScroller = new JScrollPane();
        //documentScroller.setPreferredSize( new Dimension( 300, 500 ) );
        documentScroller.setViewportView( documentPanel );

        getContentPane().add( main, java.awt.BorderLayout.CENTER );

        fileMenu.setText("File");
        openMenuItem.setText("Open");
        openMenuItem.setToolTipText("Open PDF file");
        openMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                openMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                JOptionPane.showMessageDialog(null, "doing");
            }
        });

        saveAsMenuItem.setText("Save As ...");
        fileMenu.add(saveAsMenuItem);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                JOptionPane.showMessageDialog(null, "doing");
            }
        });

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exitMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        viewsMenu.setText("Views");
        treeviewItem.setText("treeView");
        viewsMenu.add(treeviewItem);
        treeviewItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                left.setViewportView(treeView);
                right.setViewportView(nodeView);
            }
        });
        

        pageviewItem.setText("pageview");
        viewsMenu.add(pageviewItem);
        menuBar.add(viewsMenu);
        pageviewItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                left.setViewportView(tagsView);
                right.setViewportView(pagesView);
            }
        });

        viewsMenu.setText("View");
        nextPageItem.setText("Next page");
        nextPageItem.setAccelerator(KeyStroke.getKeyStroke('+'));
        nextPageItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pagesView.nextPage();
                pack();
            }
        });
        viewsMenu.add(nextPageItem);

        previousPageItem.setText("Previous page");
        previousPageItem.setAccelerator(KeyStroke.getKeyStroke('-'));
        previousPageItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pagesView.previousPage();
                pack();
            }
        });
        viewsMenu.add(previousPageItem);
        
        
        
        helpMenu.setText("Help");
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);


        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-700)/2, (screenSize.height-600)/2, 700, 600);
    }//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(currentDir);

        ExtensionFileFilter pdfFilter = new ExtensionFileFilter(new String[] {"pdf", "PDF"}, "PDF Files");
        chooser.setFileFilter(pdfFilter);
        int result = chooser.showOpenDialog(Main.this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String name = chooser.getSelectedFile().getPath();
            currentDir = new File(name).getParentFile();
            try
            {
                readPDFFile(name);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_openMenuItemActionPerformed





    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        if( document != null )
        {
            try
            {
                document.close();
            }
            catch( IOException io )
            {
                io.printStackTrace();
            }
        }
        System.exit(0);
    }

    /**
     * Exit the Application.
     */
    private void exitForm(java.awt.event.WindowEvent evt)
    {
        if( document != null )
        {
            try
            {
                document.close();
            }
            catch( IOException io )
            {
                io.printStackTrace();
            }
        }
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     *
     * @throws Exception If anything goes wrong.
     */
    public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Main viewer = new Main();
        if( args.length >0 )
        {
            viewer.readPDFFile( args[0] );
        }
        viewer.setVisible(true);
    }

    private void readPDFFile(String file) throws Exception
    {
        if( document != null )
        {
            document.close();
        }
        InputStream input = null;
        File f = new File( file );
        input = new FileInputStream(f);
        document = parseDocument( input );
        
        setTitle( "PDFLens"+" - " + f.getAbsolutePath() );
        
        treeView.setPDFDocument(document);
        pagesView.setModel(document);
        pack();
        
        
        /*
        List pages = document.getDocumentCatalog().getAllPages();
        for( int i=0; i<pages.size(); i++ )
        {
            PageWrapper wrapper = new PageWrapper();
            wrapper.displayPage( (PDPage)pages.get(i) );
            documentPanel.add( wrapper.getPanel() );
        }*/
    }
        /**
     * This will parse a document.
     *
     * @param input The input stream for the document.
     *
     * @return The document.
     *
     * @throws IOException If there is an error parsing the document.
     */
    private static PDDocument parseDocument( InputStream input )throws IOException
    {
        PDDocument document = PDDocument.load( input );
        if( document.isEncrypted() )
        {
            try
            {
                document.decrypt( "" );
            }
            catch( InvalidPasswordException e )
            {
                System.err.println( "Error: The document is encrypted." );
            }
            catch( org.apache.pdfbox.exceptions.CryptographyException e )
            {
                e.printStackTrace();
            }
        }

        return document;
    }
    
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;     
    private javax.swing.JMenuItem pageviewItem;
    private javax.swing.JMenuItem treeviewItem; 
    private javax.swing.JMenu viewsMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane left;
    private javax.swing.JScrollPane right;
    private javax.swing.JSplitPane main;
    private javax.swing.JTextPane nodeMessage;
    

    
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private JPanel documentPanel = new JPanel();

    private javax.swing.JMenuItem nextPageItem=new javax.swing.JMenuItem();
    private javax.swing.JMenuItem previousPageItem=new javax.swing.JMenuItem();

   

}
