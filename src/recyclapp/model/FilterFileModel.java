/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Sanquer
 */
public class FilterFileModel extends FileFilter {
    
   String [] lesSuffixes;
   String  laDescription;
   public FilterFileModel(String []lesSuffixes, 
                         String laDescription){
      this.lesSuffixes = lesSuffixes;
      this.laDescription = laDescription;
   }
   boolean appartient( String suffixe ){
      for( int i = 0; i<lesSuffixes.length; ++i)
         if(suffixe.equals(lesSuffixes[i]))
            return true;
         return false;
   }
   public boolean accept(File f) {
      if (f.isDirectory())  return true;
      String suffixe = null;
      String s = f.getName();
      int i = s.lastIndexOf('.');
      if(i > 0 &&  i < s.length() - 1)
         suffixe=s.substring(i+1).toLowerCase();
      return suffixe!=null&&appartient(suffixe);
   }
   // la description du filtre
   public String getDescription() {
      return laDescription;
   }
    
}
