/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Martin Boisvert
 */
public class ConveyorView extends JPanel {
    private final NodeView aEntry;
    private final NodeView aExit;
    
    public ConveyorView(NodeView entry, NodeView exit) {
        aEntry = entry;
        aExit = exit;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(aEntry.getLocation().x, aEntry.getLocation().y, 
                aExit.getLocation().x, aExit.getLocation().y);
    }
}
