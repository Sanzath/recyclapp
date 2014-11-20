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
       
      public int taille;
      
    public DiagramView()
    {
        taille = 25;
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
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
        
        g2.setColor(Color.GRAY);
        
        
        
        int i ;
        for(i=1;i<((Math.max(this.getHeight(),this.getWidth()))/this.taille)+1;i++)
        {
            g2.drawLine(i*this.taille, 0, i*this.taille, this.getHeight());
            g2.drawLine(0, i*this.taille, this.getWidth(), i*this.taille);
        }
    }
  }