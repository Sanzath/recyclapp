/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

public class EntryPointParameterGroup implements ParameterGroup, java.io.Serializable{
    
    public MaterialFlowTable aEntryMaterials;

    public EntryPointParameterGroup(MaterialFlowTable pEntryMaterials) {
        aEntryMaterials = new MaterialFlowTable(pEntryMaterials);
    }
    
}
