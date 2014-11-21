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
public class MenuModel {
    private static MenuModel aInstance;
    
    private MenuModel() {}
    
    public MenuModel getInstance() {
        if (aInstance == null) {
            aInstance = new MenuModel();
        }
        return aInstance;
    }
    
}
