/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

/**
 *
 * @author Martin Boisvert
 */
public class MenuModel implements java.io.Serializable{
    private static MenuModel sInstance;
    
    private MenuModel() {}
    
    public static MenuModel getInstance() {
        if (sInstance == null) {
            sInstance = new MenuModel();
        }
        return sInstance;
    }
    
}
