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
public class ToolBoxModel {
    private static ToolBoxModel aInstance;
    
    private ToolBoxModel() {}
    
    public static ToolBoxModel getInstance() {
        if (aInstance == null) {
            aInstance = new ToolBoxModel();
        }
        return aInstance;
    }
    
}
