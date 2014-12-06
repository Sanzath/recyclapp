/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import recyclapp.model.Controller;

import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.nio.channels.SelectableChannel;
import javax.swing.*;

/**
 * 
 * @author Martin Boisvert
 */
public final class ConveyorView extends JPanel implements MouseListener, MouseMotionListener, DiagramObject {
    private static final int UNSELECTED_THICKNESS = 1;
    private static final int SELECTED_THICKNESS = 2;
    
    private int aThickness = UNSELECTED_THICKNESS;
    
    private ConveyorSection aSections;
    
    private final NodeView aEntry;
    private final NodeView aExit;
    
    public ConveyorView(NodeView entry, NodeView exit) {
        aEntry = entry;
        aExit = exit;
        setOpaque(false);
        setSize(DiagramView.getInstance().getSize());
        
        aSections = new ConveyorSection();
        aSections.aStartPosition = getEntryCenter();
        aSections.aEndPosition = getExitCenter();
        
        updatePosition();
        
        addMouseListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(aThickness));
        
        for (ConveyorSection section = aSections;
                section != null;
                section = section.aNextSection) {
            g.drawLine(section.aStartPosition.x, section.aStartPosition.y,
                    section.aEndPosition.x, section.aEndPosition.y);
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
        aSections.aEndPosition = getExitCenter();
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
        DiagramObject.select(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

class ConveyorSection {
    private final static int SELECT_THICKNESS = 3;
    
    public Point aStartPosition;
    public Point aEndPosition;
    
    public ConveyorSection aNextSection;
    
    public boolean contains(int x, int y) {
        Point offset = new Point();
        double angle = Math.atan2(aEndPosition.y - aStartPosition.y,
                aEndPosition.x - aStartPosition.x);
        
        offset.x = (int) (SELECT_THICKNESS * Math.cos(angle));
        offset.y = (int) (SELECT_THICKNESS * Math.sin(angle));
        
        Polygon poly = new Polygon();
        poly.addPoint(aStartPosition.x + offset.x, aStartPosition.y - offset.y);
        poly.addPoint(aEndPosition.x + offset.x, aEndPosition.y - offset.y);
        poly.addPoint(aEndPosition.x - offset.x, aEndPosition.y + offset.y);
        poly.addPoint(aStartPosition.x - offset.x, aStartPosition.y + offset.y);
        
        return poly.contains(x, y);
    }
}