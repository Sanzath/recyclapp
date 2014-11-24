/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.JComponent;
import recyclapp.transport.Coords;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Martin Boisvert
 */
public class NodeView extends JComponent {
    
    private Coords p;
    private Color c;
    private String t;
    
    public NodeView()
    {
        t = "sans nom";
        c = Color.BLACK;
        p = new Coords(1,1);
        
    }
    
    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        
        drawNode(g2,p,t,c);
        
        
    }
    
    
    public void drawNode(Graphics g, Coords p, String name, Color c)
    {
        int ax = metreToPixel(p.x);
        int ay = metreToPixel(p.y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (50)/7, (50)/7);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
        
    }
    
    public int metreToPixel(double a)
    {
        int px;
        a = a*50;
        px = (int)a;      
        return px;
    }
}
