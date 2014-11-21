/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Martin Boisvert
 */
public class MaterialDictionaryModel {
    private static MaterialDictionaryModel aInstance;
    
    private Map<Integer, String> aMaterials = new HashMap<>();
    private int nextId = 0;
    
    private MaterialDictionaryModel() {}
    
    public static MaterialDictionaryModel getInstance() {
        if (aInstance == null) {
            aInstance = new MaterialDictionaryModel();
        }
        return aInstance;
    }
    
    
}
