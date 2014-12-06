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
public class TemplateContainerModel implements java.io.Serializable{
    private static TemplateContainerModel sInstance;
    
    private TemplateContainerModel() {}
    
    public static TemplateContainerModel getInstance() {
        if (sInstance == null) {
            sInstance = new TemplateContainerModel();
        }
        return sInstance;
    }
    
}
