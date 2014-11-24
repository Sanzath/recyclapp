/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.JPanel;

/**
 *
 * @author Martin Boisvert / Clement Sanquer
 */

  import java.awt.Color;
  import java.awt.Graphics;
  import java.awt.Graphics2D;
  import java.awt.BasicStroke;
 
  public class DiagramView extends JPanel
  {
       
      public int aTaille;
      public boolean aGrille;
      public int aIndiceZoom;
      
    public DiagramView()
    {
        aTaille = 50;
        aGrille = true;
        aIndiceZoom = 3;
    }
    
    public DiagramView(int taille,boolean grille)
    {
        aTaille = taille;
        aGrille = grille;
        aIndiceZoom = 1;
        /*
        if(this.aTaille > 25) aIndiceZoom = 2;
        if(this.aTaille > 50) aIndiceZoom = 3;
        if(this.aTaille > 75) aIndiceZoom = 4;  
        if(this.aTaille > 90) aIndiceZoom = 5;
                */
    }
 
    @Override
    protected void paintComponent( Graphics g )
    {

        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        //On le dessine de sorte qu'il occupe toute la surface
        g2.fillRect(0, 0, 70*aTaille, 50*aTaille);
        
        g2.setColor(Color.GRAY);
        //g2.drawRect(0, 0, this.getWidth(), this.getHeight());
        g2.drawRect(0, 0, 70*aTaille, 50*aTaille);
        
        if(aGrille == true){
            drawGrille(g,aIndiceZoom);
        }
      
        drawNode(g2,1,1,"noeud 1",Color.BLACK);
        drawNode(g2,1,2,"noeud 2",Color.BLACK);
        
        
        
        g2.setStroke(new BasicStroke((this.aTaille)/20));
        drawConvoyeur(g2, 3,1,7,2,"Convoyeur 1",Color.DARK_GRAY);
        drawConvoyeur(g2, 1,1,1,2,"Convoyeur 2",Color.DARK_GRAY);
        drawEntreNode(g,1,5,"Entrée 1",Color.GREEN);
        drawSortieNode(g,1,6,"Sortie 1",Color.ORANGE);
        
        drawStation(g, 2.5,2.5,3,1,"Station 1",Color.CYAN);
        
        
    }
    
    public void drawGrille(Graphics g,int a)
    {
        int i ;
        //for(i=1;i<(((Math.max(this.getHeight(),this.getWidth()))/(this.aTaille)+1)*a);i++)
        for(i=1;i<70;i++)    
        {
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(i*this.aTaille/a, 0, i*this.aTaille/a, 50*aTaille);
        }
        
        for(i=1;i<50;i++)    
        {
            g.drawLine(0, i*this.aTaille/a, 70*aTaille, i*this.aTaille/a);
        }
        
    }
    
    public void drawNode(Graphics g, double x, double y, String name, Color c)
    {
        int ax = mettreToPixel(x);
        int ay = mettreToPixel(y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (1*this.aTaille)/7, (1*this.aTaille)/7);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
        
    }
    
    public void drawConvoyeur(Graphics g, int x1, int y1, int x2, int y2, String name, Color c)
    {
        int ax1 = mettreToPixel(x1);
        int ay1 = mettreToPixel(y1);
        
        int ax2 = mettreToPixel(x2);
        int ay2 = mettreToPixel(y2);
        
        
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
        int ax = mettreToPixel(x);
        int ay = mettreToPixel(y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (1*this.aTaille)/3, (1*this.aTaille)/3);
        
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
    }
    
    public void drawSortieNode(Graphics g, double x, double y, String name, Color c)
    {
        int ax = mettreToPixel(x);
        int ay = mettreToPixel(y);
        
        g.setColor(c);
        g.fillOval(ax, ay, (1*this.aTaille)/3, (1*this.aTaille)/3);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);
    }
    
    public void drawStation(Graphics g, double x, double y, double z1, double z2,  String name, Color c)
    {
        int ax = mettreToPixel(x);
        int ay = mettreToPixel(y);
        int az1 = mettreToPixel(z1);
        int az2 = mettreToPixel(z2);
        
        g.setColor(c);
        g.fillRect(ax, ay,az1, az2);
        g.setColor(Color.BLACK);
        g.drawString(name, ax, ay);   
    }
    
    public int mettreToPixel(double a)
    {
        int px;
        a = a*aTaille;
        px = (int)a;      
        return px;
    }
   
  }