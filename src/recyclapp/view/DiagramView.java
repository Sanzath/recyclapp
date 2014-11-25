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
    private static DiagramView aInstance;
    
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
        if (aInstance == null) {
            aInstance = new DiagramView();
        }
        return aInstance;
    }
 
    public void setPxPerMeter(int px) {
        aTaille = px;
        invalidate();
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
        
        //Coords p = new Coords(1,1);
        //Coords p2 = new Coords(1,2);
        
        //drawNode(g,p,"noeud 1",Color.BLACK);
        //drawNode(g,p2,"noeud 2",Color.BLACK);
        
        //g.setStroke(new BasicStroke((this.aTaille)/20));
        //drawConvoyeur(g, 3,1,7,2,"Convoyeur 1",Color.DARK_GRAY);
        //drawConvoyeur(g, 1,1,1,2,"Convoyeur 2",Color.DARK_GRAY);
        //drawEntreNode(g,1,5,"Entrée 1",Color.GREEN);
        //drawSortieNode(g,1,6,"Sortie 1",Color.ORANGE);
        
        //drawStation(g, 2.5,2.5,3,1,"Station 1",Color.CYAN);
        
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
    
    public void drawNode(Graphics g, Coords p, String name, Color c)
    {
        int ax = metreToPixel(p.x);
        int ay = metreToPixel(p.y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (1*this.aTaille)/7, (1*this.aTaille)/7);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
        
    }
    
    public void drawConvoyeur(Graphics g, double x1, double y1, double x2, double y2, String name, Color c)
    {
        int ax1 = metreToPixel(x1);
        int ay1 = metreToPixel(y1);
        
        int ax2 = metreToPixel(x2);
        int ay2 = metreToPixel(y2);
        
        
        int xpoints[] = {ax2-(this.aTaille/10),ax2,ax2-(this.aTaille/10)};
        int ypoints[] = {ay2-(this.aTaille/10),ay2,ay2+(this.aTaille/10)};
        int npoints = 3;
        
        double op = y2-y1;
        double ad = x2-x1;
        
        double Phi = Math.atan((op/ad));
        
        g.setColor(c);
        g.drawLine(ax1, ay1, ax2-(this.aTaille/10), ay2);
        g.fillPolygon(xpoints, ypoints, npoints);
        g.setColor(Color.BLACK);
        g.drawString(name, (ax1+ax2)/2, (ay1+ay2)/2);
    }
    
    public void drawEntreNode(Graphics g, double x, double y, String name, Color c)
    {
        int ax = metreToPixel(x);
        int ay = metreToPixel(y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (1*this.aTaille)/3, (1*this.aTaille)/3);
        
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
    }
    
    public void drawSortieNode(Graphics g, double x, double y, String name, Color c)
    {
        int ax = metreToPixel(x);
        int ay = metreToPixel(y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (1*this.aTaille)/3, (1*this.aTaille)/3);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
    }
    
    public void drawStation(Graphics g, double x, double y, double z1, double z2,  String name, Color c)
    {
        int ax = metreToPixel(x);
        int ay = metreToPixel(y);
        int az1 = metreToPixel(z1);
        int az2 = metreToPixel(z2);
        
        g.setColor(c);
        g.fillRect(ax, ay,az1, az2);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);   
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