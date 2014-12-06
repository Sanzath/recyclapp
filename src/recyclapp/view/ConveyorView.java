/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;


import java.awt.*;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import javax.swing.*;
import recyclapp.model.Controller;
import recyclapp.transport.Coords;

/**
 * 
 * @author Martin Boisvert
 */
public final class ConveyorView extends JPanel implements MouseListener, MouseMotionListener, DiagramObject {
    private static final int UNSELECTED_THICKNESS = 1;
    private static final int SELECTED_THICKNESS = 2;
    
    private final ConveyorSection aSections;
    
    private final NodeView aEntry;
    private final NodeView aExit;
    
    private int aThickness = UNSELECTED_THICKNESS;
    private final List<Coords> aIntermediatePositions;
    
    private ConveyorSection aSectionToSeparate;
    private int aDraggingIndex = -1;
    
    public ConveyorView(NodeView entry, NodeView exit) {
        aEntry = entry;
        aExit = exit;
        setOpaque(false);
        setSize(DiagramView.getInstance().getSize());
        
        aSections = new ConveyorSection();
        aSections.aStartPosition = getEntryCenter();
        aSections.aEndPosition = getExitCenter();
        
        aIntermediatePositions = new ArrayList<>();
        
        updatePosition();
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(aThickness));
        
        Point mousePosition = DiagramView.getInstance().getMousePosition();
        for (ConveyorSection section = aSections;
                section != null;
                section = section.aNextSection) {
            g.drawLine(section.aStartPosition.x, section.aStartPosition.y,
                    section.aEndPosition.x, section.aEndPosition.y);
            if (mousePosition != null && section.endPointContains(mousePosition)) {
                g2.draw(section.getEndPointCircle());
            }
        }
    }
    
    @Override
    public boolean contains(int x, int y) {
        boolean result = false;
        
        for (ConveyorSection section = aSections;
                section != null && result == false;
                section = section.aNextSection) {
            result = section.contains(x, y);
        }
        return result;
    }

    @Override
    public void select() {
        aThickness = SELECTED_THICKNESS;
        repaint();
    }

    @Override
    public void deselect() {
        aThickness = UNSELECTED_THICKNESS;
        repaint();
    }

    @Override
    public void updatePosition() {
        aSections.aStartPosition = getEntryCenter();
        
        ConveyorSection section = aSections;
        for (int index = 0; section.aNextSection != null;
                section = section.aNextSection, ++index) {
            Point p = DiagramView.getInstance().coordsToPoint(aIntermediatePositions.get(index));
            section.aEndPosition = p;
            section.aNextSection.aStartPosition = p;
        }
        section.aEndPosition = getExitCenter();
        repaint();
    }
    
    private Point getEntryCenter() {
        Point entryCenter = aEntry.getLocation();
        entryCenter.x += aEntry.getWidth() / 2;
        entryCenter.y += aEntry.getHeight() / 2;
        return entryCenter;
    }
    
    private Point getExitCenter() {
        Point exitCenter = aExit.getLocation();
        exitCenter.x += aExit.getWidth() / 2;
        exitCenter.y += aExit.getHeight() / 2;
        return exitCenter;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DiagramObject.deselectAll();
        DiagramObject.select(this);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void mousePressed(MouseEvent e) {
        Point mousePosition = getMousePosition();
        // First find if we're selecting an intersection
        int index = 0;
        aDraggingIndex = -1;
        for (ConveyorSection section = aSections;
                section.aNextSection != null;
                section = section.aNextSection, ++index) {
            if (section.endPointContains(mousePosition)) {
                aDraggingIndex = index;
            }
        }
        
        if (aDraggingIndex == -1) {
            // An intersection was not selected, so find which section to separate
            aDraggingIndex = 0;
            aSectionToSeparate = aSections;
            for (; aSectionToSeparate != null && !aSectionToSeparate.contains(mousePosition);
                    aSectionToSeparate = aSectionToSeparate.aNextSection, ++aDraggingIndex) { /*Ultra efficient amirite*/ }
            // aSectionToSeparate is now the section to break - not null
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Clear intersection-creation variables
        aSectionToSeparate = null;
        if (aDraggingIndex != -1) {
            Controller.getInstance().moveConveyorIntermediatePosition(
                    aEntry.getParentId(), aEntry.getIndex(), aIntermediatePositions.get(aDraggingIndex), aDraggingIndex);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        aThickness = SELECTED_THICKNESS;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!DiagramObject.isSelected(this)) {
            aThickness = UNSELECTED_THICKNESS;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mousePosition = DiagramView.getInstance().getMousePosition();
        
        // Check if an intersection should be added
        if (aSectionToSeparate != null) {
            DiagramObject.select(this);
            // startingPos - section - endingPos
            // becomes...
            //startingPos - section - intermediatePos - newSection - endingPos
            ConveyorSection newSection = new ConveyorSection();
            newSection.aNextSection = aSectionToSeparate.aNextSection;
            aSectionToSeparate.aNextSection = newSection;
            newSection.aEndPosition = aSectionToSeparate.aEndPosition;
            
            aSectionToSeparate = null;
            
            // This null will be replaced with an actual value during updatePosition()
            aIntermediatePositions.add(aDraggingIndex, null);
            Controller.getInstance().insertConveyorIntermediatePosition(
                    aEntry.getParentId(), aEntry.getIndex(), new Coords(0, 0), aDraggingIndex);
            DiagramObject.select(this);
        }
        aIntermediatePositions.set(aDraggingIndex, DiagramView.getInstance().pointToCoords(mousePosition));
        
        updatePosition();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // If selected, repaint in case the mouse is above one of the intersections
        if (DiagramObject.isSelected(this)) {
            invalidate();
            repaint();
        }
    }
}

class ConveyorSection {
    private final static int SELECT_THICKNESS = 3;
    
    public Point aStartPosition;
    public Point aEndPosition;
    
    public ConveyorSection aNextSection;
    
    public boolean contains(Point p) {
        return contains(p.x, p.y);
    }
    
    public boolean contains(int x, int y) {
        Point offset = new Point();
        double angle = Math.atan2(aEndPosition.y - aStartPosition.y,
                aEndPosition.x - aStartPosition.x);
        
        offset.x = (int) (SELECT_THICKNESS * Math.sin(angle));
        offset.y = (int) (SELECT_THICKNESS * Math.cos(angle));
        
        Polygon poly = new Polygon();
        poly.addPoint(aStartPosition.x + offset.x, aStartPosition.y - offset.y);
        poly.addPoint(aEndPosition.x + offset.x, aEndPosition.y - offset.y);
        poly.addPoint(aEndPosition.x - offset.x, aEndPosition.y + offset.y);
        poly.addPoint(aStartPosition.x - offset.x, aStartPosition.y + offset.y);
        
        return poly.contains(x, y);
    }
    
    public boolean endPointContains(Point p) {
        return endPointContains(p.x, p.y);
    }
    
    public boolean endPointContains(int x, int y) {
        if (aNextSection == null) {
            return false;
        }
        
        return getEndPointCircle().contains(x, y);
    }
    
    public Ellipse2D getEndPointCircle() {
        Ellipse2D c = new Ellipse2D.Float();
        c.setFrameFromCenter(aEndPosition.x, aEndPosition.y,
                aEndPosition.x + SELECT_THICKNESS, aEndPosition.y + SELECT_THICKNESS);
        return c;
    }
}