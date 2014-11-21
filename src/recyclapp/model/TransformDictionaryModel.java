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
public class TransformDictionaryModel {
    private static TransformDictionaryModel aInstance;
    
    private TransformDictionaryModel() {}
    
    public TransformDictionaryModel getInstance() {
        if (aInstance == null) {
            aInstance = new TransformDictionaryModel();
        }
        return aInstance;
    }
    
}
