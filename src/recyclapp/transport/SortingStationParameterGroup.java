/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

public class SortingStationParameterGroup implements ParameterGroup
{
    public MaterialFlowMatrix aSortingMatrix;
    public StationType aType;
    
    public SortingStationParameterGroup(
            MaterialFlowMatrix sortingMatrix, StationType stationType) {
        aSortingMatrix = new MaterialFlowMatrix(sortingMatrix);
        aType = stationType;
    }
    
}
