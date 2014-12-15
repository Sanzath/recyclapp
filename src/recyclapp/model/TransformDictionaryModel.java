/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

/**
 *
 * @author Martin Boisvert
 */
public class TransformDictionaryModel implements java.io.Serializable{
    private static TransformDictionaryModel sInstance;
    
    private TransformDictionaryModel() {}
    
    public static TransformDictionaryModel getInstance() {
        if (sInstance == null) {
            sInstance = new TransformDictionaryModel();
        }
        return sInstance;
    }
    
}
