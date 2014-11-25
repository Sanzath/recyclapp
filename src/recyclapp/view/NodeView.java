/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import recyclapp.transport.NodeProperties;
import recyclapp.transport.Coords;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Martin Boisvert
 */
public final class NodeView extends JPanel implements MouseListener, MouseMotionListener {
    
    private final ElementView aParent;
    private int aIndex;
    
    private Coords aSize;
    private int aAngle;
    
    
    public NodeView(ElementView parent, int index, NodeProperties properties) {
        aParent = parent;
        aIndex = index;
        aSize = properties.aSize;
        aAngle = properties.aAngle;
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        setBackground(Color.GRAY);
        Point size = DiagramView.getInstance().coordsToPoint(aSize);
        setSize(size.x, size.y);
        repaint();
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void updateIndex(int newIndex) {
        aIndex = newIndex;
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        updatePosition();
        super.paintComponent(g);
    }
    
    public void updatePosition()
    {
        Point position = aParent.getLocation();
        Dimension parentSize = aParent.getSize();
        Point offset = new Point(0, 0);
        Point size = DiagramView.getInstance().coordsToPoint(aSize);
        setSize(size.x, size.y);
        
        // Get center position -- assuming pos is top left
        position.x += parentSize.width / 2;
        position.y += parentSize.height / 2;
        
        // Now determine which of the four borders this node is "stuck" to
        int borderAngle = (int) Math.toDegrees(Math.atan(parentSize.height / parentSize.width));
        
        // Stuck to right
        if (aAngle < borderAngle || aAngle >= (360 - borderAngle))
        {
            offset.x = (parentSize.width + size.x) / 2;
            offset.y = offset.x * (int)Math.tan(Math.toRadians(aAngle));
        }
        // Stuck to bottom
        else if (aAngle < (180 - borderAngle))
        {
            offset.y = (parentSize.height + size.y) / 2;
            offset.x = offset.y / (int)Math.tan(Math.toRadians(aAngle));
        }
        // Stuck to left
        else if (aAngle < (180 + borderAngle))
        {
            offset.x = - (parentSize.width + size.x) / 2;
            offset.y = offset.x * (int)Math.tan(Math.toRadians(aAngle));
        }
        // Stuck to top
        else
        {
            offset.y = - (parentSize.height + size.y) / 2;
            offset.x = offset.y / (int)Math.tan(Math.toRadians(aAngle));
        }
        
        // Offset the position to get the middle position of the node
        position.x += offset.x;
        position.y += offset.y;
        
        // And offset to get the topleft position
        position.x -= size.x / 2;
        position.y -= size.y / 2;
        
        setLocation(position);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}