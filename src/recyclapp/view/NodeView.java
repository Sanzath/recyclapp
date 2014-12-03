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
import javax.swing.border.Border;

/**
 *
 * @author Martin Boisvert
 */
public final class NodeView extends JPanel implements MouseListener, MouseMotionListener {
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 2);
    private static final Border UNSELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 1);
    
    private static NodeView sSelected = null;
    
    public static final int ENTRY_NODE = 1;
    public static final int EXIT_NODE = -1;
    
    private final ElementView aParent;
    private int aIndex;
    private final int aNodeType;
    
    private Coords aSize;
    private int aAngle;
    
    
    public NodeView(ElementView parent, int index, int nodeType, NodeProperties properties) {
        aParent = parent;
        aNodeType = nodeType;
        aIndex = index;
        aSize = properties.aSize;
        aAngle = properties.aAngle;
        
        setBorder(UNSELECTED_BORDER);
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
        //updatePosition();
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
        int borderAngle = (int) Math.toDegrees(Math.atan2(parentSize.height, parentSize.width));
        
        // Stuck to right
        if (aAngle < borderAngle || aAngle >= (360 - borderAngle))
        {
            offset.x = (parentSize.width + size.x) / 2;
            offset.y = (int)((float)offset.x * Math.tan(Math.toRadians(aAngle)));
        }
        // Stuck to bottom
        else if (aAngle < (180 - borderAngle))
        {
            offset.y = (parentSize.height + size.y) / 2;
            offset.x = (int)((float)offset.y / Math.tan(Math.toRadians(aAngle)));
        }
        // Stuck to left
        else if (aAngle < (180 + borderAngle))
        {
            offset.x = - (parentSize.width + size.x) / 2;
            offset.y = (int)(offset.x * Math.tan(Math.toRadians(aAngle)));
        }
        // Stuck to top
        else
        {
            offset.y = - (parentSize.height + size.y) / 2;
            offset.x = (int)((float)offset.y / Math.tan(Math.toRadians(aAngle)));
        }
        
        // Offset the position to get the middle position of the node
        position.x += offset.x;
        position.y += offset.y;
        
        // And offset to get the topleft position
        position.x -= size.x / 2;
        position.y -= size.y / 2;
        
        setLocation(position);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (sSelected != null) {
            NodeView other = (NodeView) sSelected;
            if (aNodeType == ENTRY_NODE && other.aNodeType == EXIT_NODE) {
                DiagramView.getInstance().addConveyor(this, other);
            }
            else if (aNodeType == EXIT_NODE && other.aNodeType == ENTRY_NODE) {
                DiagramView.getInstance().addConveyor(other, this);
            }
        }
        select();
    }
    
    public static void deselectSelected() {
        if (sSelected != null) {
            sSelected.deselect();
        }
    }
    
    public void deselect() {
        sSelected = null;
        setBorder(UNSELECTED_BORDER);
    }
    
    public void select() {
        deselectSelected();
        sSelected = this;
        setBorder(SELECTED_BORDER);
        aParent.select();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(SELECTED_BORDER);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (sSelected != this) {
            setBorder(UNSELECTED_BORDER);
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}