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
public final class MaterialDictionaryModel implements java.io.Serializable{
    private static MaterialDictionaryModel sInstance;
    
    private final Map<Integer, String> aMaterials = new HashMap<>();
    private int aNextId = 0;
    
    private MaterialDictionaryModel() {
        // By default, have 3 materials
        addMaterial("Metal");
        addMaterial("Bois");
        addMaterial("Plastique");
    }
    
    public static MaterialDictionaryModel getInstance() {
        if (sInstance == null) {
            sInstance = new MaterialDictionaryModel();
        }
        return sInstance;
    }
    
    public void addMaterial(String name) {
        if (!aMaterials.containsValue(name))
        {
            aMaterials.put(aNextId, name);
            aNextId++;
        }
    }
    
    public String getMaterialName(int id) {
        return aMaterials.get(id);
    }
    
    public int getMaterialId(String name) {
        for (Map.Entry<Integer, String> entry : aMaterials.entrySet())
        {
            if (entry.getValue().equals(name))
            {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    public void removeMaterial(int id) {
        aMaterials.remove(id);
    }
    
    public void removeMaterial(String name) {
        removeMaterial(getMaterialId(name));
    }
}