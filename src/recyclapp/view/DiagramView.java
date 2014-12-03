/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.*;
import java.awt.*;
import recyclapp.transport.*;

/**
 *
 * @author Martin Boisvert / Clement Sanquer
 */

public final class DiagramView extends JPanel
{
    private static DiagramView sInstance;
    
    private static final int GRID_WIDTH = 70;
    private static final int GRID_HEIGHT = 50;
    
    private int aTaille = 50;
    private boolean aGridActive = true;
    
    public DiagramView() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
    
    public static DiagramView getInstance() {
        if (sInstance == null) {
            sInstance = new DiagramView();
        }
        return sInstance;
    }
 
    public void setPxPerMeter(int px) {
        aTaille = px;
        repaint();
    }
    
    public void setGridActive(boolean active) {
        aGridActive = active;
        invalidate();
        repaint();
    }
    
    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g); 
        
        if(aGridActive == true){
            drawGrille(g);
        }
        
        for (Component child : getComponents()) {
            if (child instanceof ElementView) {
                ((ElementView)child).updatePosition();
            }
        }
    }
    
    public void drawGrille(Graphics g)
    {
        int i;
        g.setColor(Color.LIGHT_GRAY);
        for(i = 1; i < GRID_WIDTH; i++)    
        {
            g.drawLine(i * aTaille, 0, i * aTaille, GRID_HEIGHT * aTaille);
        }
        
        for(i = 1; i < GRID_HEIGHT; i++)    
        {
            g.drawLine(0, i * aTaille, GRID_WIDTH * aTaille, i * aTaille);
        }
        
    }
    
    protected void addConveyor(NodeView entry, NodeView exit) {
        
    }
    
    @Override
    public Component add(Component child) {
        super.add(child);
        //OverviewView.getInstance().update();
        return child;
    }
    
    public int metreToPixel(double a)
    {
        return (int)(a * aTaille);
    }
    public float pixelToMetre(int a)
    {
        return ((float)a) / aTaille;
    }
    
    public Point coordsToPoint(Coords coords) {
        int x = metreToPixel(coords.x);
        int y = metreToPixel(coords.y);
        return new Point(x, y);
    }
    
    public Coords pointToCoords(Point p) {
        float x = pixelToMetre(p.x);
        float y = pixelToMetre(p.y);
        return new Coords(x, y);
    }
   
  }