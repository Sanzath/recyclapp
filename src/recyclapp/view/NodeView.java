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
public final class NodeView extends JPanel implements MouseListener, MouseMotionListener, DiagramObject {
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 2);
    private static final Border UNSELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 1);
    
    public static final int ENTRY_NODE = 1;
    public static final int EXIT_NODE = -1;
    
    private Point aParentCenter;
    
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
    
    @Override
    public void updatePosition()
    {
        Point position = aParent.getLocation();
        Dimension parentSize = aParent.getSize();
        Point offset = new Point(0, 0);
        Point size = DiagramView.getInstance().coordsToPoint(aSize);
        Point maxOffset = new Point((parentSize.width - size.x) / 2, (parentSize.height - size.y) / 2);
        setSize(size.x, size.y);
        
        // Get center position -- assuming pos is top left
        position.x += parentSize.width / 2;
        position.y += parentSize.height / 2;
        
        // Now determine which of the four borders this node is "stuck" to
        int borderAngle = (int) Math.toDegrees(Math.atan2(parentSize.height, parentSize.width));
        borderAngle = borderAngle % 360;
        System.out.println("b: " + borderAngle + ", a: " + aAngle);
        
        // Stuck to right
        if (aAngle < borderAngle || aAngle >= (360 - borderAngle))
        {
            offset.x = (parentSize.width + size.x) / 2;
            offset.y = (int)((float)offset.x * Math.tan(Math.toRadians(aAngle)));
            if (offset.y > maxOffset.y) {
                offset.y = maxOffset.y;
            }
            else if (offset.y < -maxOffset.y) {
                offset.y = -maxOffset.y;
            }
        }
        // Stuck to bottom
        else if (aAngle < (180 - borderAngle))
        {
            offset.y = (parentSize.height + size.y) / 2;
            offset.x = (int)((float)offset.y / Math.tan(Math.toRadians(aAngle)));
            if (offset.x > maxOffset.x) {
                offset.x = maxOffset.x;
            }
            else if (offset.x < -maxOffset.x) {
                offset.x = -maxOffset.x;
            }
        }
        // Stuck to left
        else if (aAngle < (180 + borderAngle))
        {
            offset.x = - (parentSize.width + size.x) / 2;
            offset.y = (int)(offset.x * Math.tan(Math.toRadians(aAngle)));
            if (offset.y > maxOffset.y) {
                offset.y = maxOffset.y;
            }
            else if (offset.y < -maxOffset.y) {
                offset.y = -maxOffset.y;
            }
        }
        // Stuck to top
        else
        {
            offset.y = - (parentSize.height + size.y) / 2;
            offset.x = (int)((float)offset.y / Math.tan(Math.toRadians(aAngle)));
            if (offset.x > maxOffset.x) {
                offset.x = maxOffset.x;
            }
            else if (offset.x < -maxOffset.x) {
                offset.x = -maxOffset.x;
            }
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
        
        DiagramObject selected = DiagramObject.getSelected(NodeView.class);
        if (selected != null) {
            NodeView other = (NodeView) selected;
            if (aParent != other.aParent) {
                if (aNodeType == ENTRY_NODE && other.aNodeType == EXIT_NODE) {
                    DiagramView.getInstance().addConveyor(this, other);
                }
                else if (aNodeType == EXIT_NODE && other.aNodeType == ENTRY_NODE) {
                    DiagramView.getInstance().addConveyor(other, this);
                }
            }
        }
        DiagramObject.select(this);
    }
    
    @Override
    public void deselect() {
        setBorder(UNSELECTED_BORDER);
    }
    
    @Override
    public void select() {
        setBorder(SELECTED_BORDER);
        DiagramObject.select(aParent);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Get parent center position on screen to facilitate angle calculation
        aParentCenter = aParent.getLocationOnScreen();
        aParentCenter.x += aParent.getWidth() / 2;
        aParentCenter.y += aParent.getHeight() / 2;
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Clear parent center position
        aParentCenter = null;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(SELECTED_BORDER);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!DiagramObject.isSelected(this)) {
            setBorder(UNSELECTED_BORDER);
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Get mouse position
        Point mousePos = e.getLocationOnScreen();
        aAngle = (int) Math.toDegrees(Math.atan2(mousePos.y - aParentCenter.y, mousePos.x - aParentCenter.x));
        if (aAngle < 0) aAngle += 360;
        updatePosition();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}