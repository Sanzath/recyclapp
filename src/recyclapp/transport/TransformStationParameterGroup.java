/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;


public class TransformStationParameterGroup implements ParameterGroup, java.io.Serializable {
    
    public String aInputMaterial;
    public MaterialFlowTable aTransformTable;
    public StationType aType;
    
    public TransformStationParameterGroup(
            String inputMaterial, MaterialFlowTable transformTable, StationType type) {
        aInputMaterial = inputMaterial;
        aTransformTable = new MaterialFlowTable(transformTable);
        aType = type;
    }
    
}
