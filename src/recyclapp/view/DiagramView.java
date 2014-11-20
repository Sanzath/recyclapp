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
 
  public class DiagramView extends JPanel
  {
       
      public int aTaille;
      public boolean aGrille;
      
    public DiagramView()
    {
        aTaille = 50;
        aGrille = true;
    }
    
    public DiagramView(int taille,boolean grille)
    {
        aTaille = taille;
        aGrille = grille;
    }
 
    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        //On le dessine de sorte qu'il occupe toute la surface
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2.setColor(Color.GRAY);
        g2.drawRect(0, 0, this.getWidth(), this.getHeight());
        
        
        if(aGrille == true){
            drawGrille(g);
        }
        
    }
    
    public void drawGrille(Graphics g)
    {
        int i ;
        for(i=1;i<((Math.max(this.getHeight(),this.getWidth()))/this.aTaille)+1;i++)
        {
            g.setColor(Color.GRAY);
            g.drawLine(i*this.aTaille, 0, i*this.aTaille, this.getHeight());
            g.drawLine(0, i*this.aTaille, this.getWidth(), i*this.aTaille);
        }
    }
  }