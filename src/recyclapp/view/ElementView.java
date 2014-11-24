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
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(aProperties.aColor);
        //Args de fillRect(Remplie un rectangle de couleur): pos X, pos Y, width, height
        g.fillRect(DiagramView.coordsToPoint(aProperties.aPosition).x, DiagramView.coordsToPoint(aProperties.aPosition).y,
                DiagramView.coordsToPoint(aProperties.aSize).x, DiagramView.coordsToPoint(aProperties.aSize).y);
        g.setColor(Color.BLACK);
        //Args de drawRect(bordure du rectangle): pos X, pos Y, width, height
        g.drawRect(DiagramView.coordsToPoint(aProperties.aPosition).x, DiagramView.coordsToPoint(aProperties.aPosition).y,
                DiagramView.coordsToPoint(aProperties.aSize).x, DiagramView.coordsToPoint(aProperties.aSize).y);
        g.setClip(DiagramView.coordsToPoint(aProperties.aPosition).x, DiagramView.coordsToPoint(aProperties.aPosition).y,
                DiagramView.coordsToPoint(aProperties.aSize).x, DiagramView.coordsToPoint(aProperties.aSize).y);
    }
            
    protected abstract void createPropertiesWindow(ElementProperties properties);
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
        if (e.getClickCount() % 2 == 1){
        aClickTime1 = System.currentTimeMillis();
        }
        else if (e.getClickCount() % 2 == 0 && System.currentTimeMillis() - aClickTime1 <= 500)
            createPropertiesWindow(aProperties);
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
