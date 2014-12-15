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
import recyclapp.model.Controller;

/**
 *
 * @author Martin Boisvert
 */
public final class NodeView extends JPanel implements MouseListener, MouseMotionListener, DiagramObject {
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 2);
    private static final Border UNSELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 1);
    
    public static final int ENTRY_NODE = 1;
    public static final int EXIT_NODE = -1;
    
    private int aSide;
    
    private Point aParentCenter;
    
    private ElementView aParent;
    protected ConveyorView aConveyor;
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
        updatePosition();
        
        addMouseListener(this);
    }
    
    public void decrementIndex() {
        aIndex--;
    }
    
    public int getIndex() {
        return aIndex;
    }
    
    public int getParentId() {
        return aParent.getID();
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent(g);
        
        // Draw an arrow in the correct orientation
        // Facing right
        Point p1, p2, p3;
        if ((aSide == SwingConstants.RIGHT && aNodeType == EXIT_NODE) ||
                (aSide == SwingConstants.LEFT && aNodeType == ENTRY_NODE)) {
            p1 = new Point(0, 0);
            p2 = new Point(getSize().width, getSize().height / 2);
            p3 = new Point(0, getSize().height);
        }
        // Facing left
        else if ((aSide == SwingConstants.LEFT && aNodeType == EXIT_NODE) ||
                (aSide == SwingConstants.RIGHT && aNodeType == ENTRY_NODE)) {
            p1 = new Point(getSize().width, 0);
            p2 = new Point(0, getSize().height / 2);
            p3 = new Point(getSize().width, getSize().height);
        }
        // Facing up
        else if ((aSide == SwingConstants.TOP && aNodeType == EXIT_NODE) ||
                (aSide == SwingConstants.BOTTOM && aNodeType == ENTRY_NODE)) {
            p1 = new Point(0, getSize().height);
            p2 = new Point(getSize().width / 2, 0);
            p3 = new Point(getSize().width, getSize().height);
        }
        // Facing down
        else {
            p1 = new Point(0, 0);
            p2 = new Point(getSize().width / 2, getSize().height);
            p3 = new Point(getSize().width, 0);
        }
        g.setColor(Color.black);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        g.drawLine(p3.x, p3.y, p2.x, p2.y);
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
        
        // Stuck to right
        if (aAngle < borderAngle || aAngle >= (360 - borderAngle))
        {
            aSide = SwingConstants.RIGHT;
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
            aSide = SwingConstants.BOTTOM;
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
            aSide = SwingConstants.LEFT;
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
            aSide = SwingConstants.TOP;
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
        // If we have no conveyor, and another node is selected, and it has
        // no conveyor, these two elements don't have the same parent,
        // check if the node types are opposite, and create a new conveyor.
        if (aConveyor == null) {
            DiagramObject selected = DiagramObject.getSelected(NodeView.class);
            if (selected != null) {
                NodeView other = (NodeView) selected;
                if (other.aConveyor == null && aParent != other.aParent) {
                    NodeView entry = null;
                    NodeView exit = null;
                    if (aNodeType == ENTRY_NODE && other.aNodeType == EXIT_NODE) {
                        entry = this;
                        exit = other;
                    }
                    else if (aNodeType == EXIT_NODE && other.aNodeType == ENTRY_NODE) {
                        entry = other;
                        exit = this;
                    }
                    
                    if (entry != null && exit != null) {
                        Controller.getInstance().addConveyor(
                                entry.aParent.getID(), entry.aIndex,
                                exit.aParent.getID(), exit.aIndex);
                        addConveyorView(entry, exit);
                    }
                }
            }
        }
        DiagramObject.select(this);
    }
    
    protected static void addConveyorView(NodeView entry, NodeView exit) {
        entry.aConveyor = new ConveyorView(entry, exit);
        exit.aConveyor = entry.aConveyor;
        DiagramView.getInstance().add(entry.aConveyor);
        entry.aConveyor.repaint();
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
        addMouseMotionListener(this);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Clear parent center position
        aParentCenter = null;
        removeMouseMotionListener(this);
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

    @Override
    public void tearDown() {
        aParent = null;
        if (aConveyor != null) {
            aConveyor.tearDown();
            aConveyor = null;
        }
    }
}