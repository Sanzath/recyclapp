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
      
        drawNode(g2,1,1);
        drawNode(g2,1,2);
        drawNode(g2,2,1);
        drawNode(g2,2,2);
        
        drawNode(g2,69,49);
        
        g2.setStroke(new BasicStroke(2));
       // drawConvoyeur(g2, 2,2,4,2);
        drawArrow(g, 50, 50, 10, 100);
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
    
    public void drawNode(Graphics g, double x, double y)
    {
        int ax = mettreToPixel(x);
        int ay = mettreToPixel(y);
        
        g.setColor(Color.BLACK);
        g.fillOval(ax, ay, (1*this.aTaille)/7, (1*this.aTaille)/7);
       
        
    }
    
    public void drawConvoyeur(Graphics g, int x1, int y1, int x2, int y2)
    {
        
        int xpoints[] = {};
        int ypoints[] = {};
        int npoints = 6;
        
        g.setColor(Color.red);
        g.drawLine(x1*this.aTaille, y1*this.aTaille, x2*this.aTaille, y2*this.aTaille);
        g.fillPolygon(xpoints, ypoints, npoints);
    }
    
    public static void drawArrow (Graphics g,
				  int x,
				  int y,
				  int largeur,
				  int hauteur)
    {
 
	largeur = largeur / 3;
	hauteur = hauteur / 3;
 
        g.fillRect (x + largeur, y,
		    largeur, 2 * hauteur);
 
	int abcisses[] = new int[] { x,
				     x + (3 * largeur),
				     x + (largeur * 3 / 2)};
	int ordonnes[] = new int[] { y + (2 * hauteur),
				     y + (2 * hauteur),
				     y + (3 * hauteur)};
 
	g.fillPolygon (abcisses, ordonnes, 3);
 
	
    }
    
    public int mettreToPixel(double a)
    {
        int px = 0;
        
        a = a*aTaille;
        
        px = (int)a;
        
        return px;
    }
  }