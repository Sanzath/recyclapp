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
    protected ElementProperties aProperties;
    public ElementView(ElementProperties properties){
        aProperties = properties;
        addMouseListener(this); 
    }
    public int getID() {
            return aProperties.aId;
    }
    public void updateElementProperties(ElementProperties newProp){
        aProperties = newProp;
    }
    
    protected void drawView(Graphics g, ElementProperties aProperties){
        
        g.setColor(aProperties.aColor);
        //Args de fillRect: pos X, pos Y, width, height
        g.fillRect(DiagramView.)
    }
            
    protected abstract void createPropertiesWindow(ElementProperties properties);
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
        if (e.getClickCount() % 2 == 1){
        aClickTime1 = System.currentTimeMillis();
        }
        else if (e.getClickCount() % 2 == 0 && System.currentTimeMillis() - aClickTime1 <= 500)
            createPropertiesWindow(Controller.getInstance().getElementProperties(aID));
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
