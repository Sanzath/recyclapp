/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import recyclapp.transport.NodeProperties;

import javax.swing.JComponent;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Martin Boisvert
 */
public class NodeView extends JComponent implements MouseListener {
    
    private int aParentId;
    private int aIndex;
    private NodeProperties aProperties;
    
    /*public NodeView(int parentId, int index, NodeProperties properties) {
        
    }*/
    
    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g); 
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
