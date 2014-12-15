/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

import java.util.ArrayList;

/**
 *
 * @author Martin Boisvert
 */
public class MaterialFlowMatrix extends ArrayList<MaterialFlowTable> implements java.io.Serializable {
    
    public MaterialFlowMatrix() {}
    
    public MaterialFlowMatrix(MaterialFlowMatrix other)
    {
        for (MaterialFlowTable flowTable : other)
        {
            add(new MaterialFlowTable(flowTable));
        }
    }
    
}
