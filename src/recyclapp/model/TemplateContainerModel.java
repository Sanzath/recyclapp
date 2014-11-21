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
public class TemplateContainerModel {
    private static TemplateContainerModel aInstance;
    
    private TemplateContainerModel() {}
    
    public static TemplateContainerModel getInstance() {
        if (aInstance == null) {
            aInstance = new TemplateContainerModel();
        }
        return aInstance;
    }
    
}
