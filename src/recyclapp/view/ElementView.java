/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import recyclapp.transport.ElementProperties;
import recyclapp.model.Controller;
import recyclapp.transport.Coords;
/**
 *
 * @author Martin Boisvert
 */
public /*abstract*/ class ElementView extends JPanel implements MouseListener, MouseMotionListener {
    static ElementView sSelected = null;
    
    Point aStartingPosition;
    
    private long aClickTime1;
    private final int aId;
    
    private Coords aPosition;
    private Coords aSize;
    
    private JLabel aName;
    
    public ElementView(ElementProperties properties){
        aId = properties.aId;
        aPosition = properties.aPosition;
        aSize = properties.aSize;
        aName = new JLabel(properties.aName);
        
        updatePosition();
        setBackground(properties.aColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(aName);
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public int getID() {
            return aId;
    }
    
    public static void deselect() {
        if (sSelected != null) {
            sSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
        sSelected = null;
    }
    
    protected void updateParameters(ElementProperties properties) {
        setBackground(properties.aColor);
        aName.setText(properties.aName);
        Controller.getInstance().setElementProperties(properties);
    }
    
    private void updatePosition() {
        setLocation(DiagramView.getInstance().coordsToPoint(aPosition));
        Point size = DiagramView.getInstance().coordsToPoint(aSize);
        setSize(size.x, size.y);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        updatePosition();
        super.paintComponent(g);
    }
            
    protected void createPropertiesWindow(ElementProperties properties){
        ElementPropertiesView propertiesWindow = new ElementPropertiesView(properties);
        propertiesWindow.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
        if (e.getClickCount() % 2 == 1){
            aClickTime1 = System.currentTimeMillis();
        }
        else if (e.getClickCount() % 2 == 0 && System.currentTimeMillis() - aClickTime1 <= 500) {
            createPropertiesWindow(Controller.getInstance().getElementProperties(aId));
        }
        
        if (sSelected != this) {
            if (sSelected != null) {
                sSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
            sSelected = this;
        }
    }
     
    @Override
    public void mouseReleased(MouseEvent e) {
        aStartingPosition = null;
        Controller.getInstance().setElementPosition(aId, aPosition);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (sSelected != this) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        aStartingPosition = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPosition = e.getPoint();
        Point newLocation = getLocation();
        newLocation.x += newPosition.x - aStartingPosition.x;
        newLocation.y += newPosition.y - aStartingPosition.y;
        
        aPosition = DiagramView.getInstance().pointToCoords(newLocation);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
