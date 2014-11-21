/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;


public class TransformStationParameterGroup implements ParameterGroup {
    
    public int aInputMaterial;
    public MaterialFlowTable aTransformTable;
    public StationType aType;
    
    public TransformStationParameterGroup(
            int inputMaterial, MaterialFlowTable transformTable, StationType type) {
        aInputMaterial = inputMaterial;
        aTransformTable = new MaterialFlowTable(transformTable);
        aType = type;
    }
    
}
