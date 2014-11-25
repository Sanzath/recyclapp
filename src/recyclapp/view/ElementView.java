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
import recyclapp.view.DiagramView;
/**
 *
 * @author Martin Boisvert
 */
public abstract class ElementView extends JComponent implements MouseListener {
    private long aClickTime1;
    private int aId;
    public ElementView(ElementProperties properties){
        aId = properties.aId;
        setLocation(DiagramView.coordsToPoint(properties.aPosition));
        setSize(DiagramView.coordsToPoint(properties.aSize).x, DiagramView.coordsToPoint(properties.aSize).y);
        setBackground(properties.aColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        addMouseListener(this); 
    }
    public int getID() {
            return aId;
    }
            
    protected void createPropertiesWindow(ElementProperties properties){
        ElementPropertiesView propertiesWindow = new ElementPropertiesView(properties);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
        if (e.getClickCount() % 2 == 1){
        aClickTime1 = System.currentTimeMillis();
        }
        else if (e.getClickCount() % 2 == 0 && System.currentTimeMillis() - aClickTime1 <= 500)
            createPropertiesWindow(Controller.getInstance().getElementProperties(aId));
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
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
