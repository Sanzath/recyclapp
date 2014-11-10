/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

import java.util.ArrayList;

/**
 *
 * @author Martin Boisvert
 */
public class MaterialFlowMatrix {
    
    final public ArrayList<MaterialFlowTable> aFlowMatrix = new ArrayList<>();
    
    public MaterialFlowMatrix()
    {
    }
    
    public MaterialFlowMatrix(MaterialFlowMatrix other)
    {
        for (MaterialFlowTable flowTable : other.aFlowMatrix)
        {
            aFlowMatrix.add(new MaterialFlowTable(flowTable));
        }
    }
    
}
