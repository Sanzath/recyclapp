/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.io.*;

/**
 *
 * @author Martin Boisvert
 */
public class HistoryElement {
    
    private static HistoryElement hInstance;
    private static int aCounter = 0;
    private static int aMax = 0;
    
    private HistoryElement() {
        aCounter = 0;
        aMax = 0;
    }
    
    public static HistoryElement getInstance() {
        boolean wasNull = false;
        if (hInstance == null) {
            wasNull = true;
            hInstance = new HistoryElement();
        }
        return hInstance;
    }
    
    public static int getCounter()
    {
        return aCounter;
    }
    
    public static void setCounter(int a)
    {
        aCounter = a;
    }
    
    public static int getMax()
    {
        return aMax;
    }
    
    public static void setMax(int a)
    {
        aMax = a;
    }
    
    public void serialiseDiagram(DiagramModel diag)
    {   
        aCounter++;
        aMax = aCounter;
        
        String chaine = null;
        try
        {
            chaine = "C:/Windows/Temp/recyclapp"+aCounter + ".rec";
         
            FileOutputStream fileOut = new FileOutputStream(chaine);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(diag);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+ chaine);
            
            
            
      }catch(IOException i)
      {
          i.printStackTrace();
      }
        
        
    }
    
    public void deserializeDiag(String chemin)
    {
        DiagramModel.getInstance().deserialiseDiagram(chemin);
    }
    
    
}
