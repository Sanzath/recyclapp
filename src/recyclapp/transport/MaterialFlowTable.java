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
public class MaterialFlowTable {
    
    final public ArrayList<MaterialFlow> aFlowTable = new ArrayList<>();
    
    public MaterialFlowTable()
    {
    }
    
    public MaterialFlowTable(MaterialFlowTable other)
    {
        for (MaterialFlow flow : other.aFlowTable)
        {
            aFlowTable.add(new MaterialFlow(flow));
        }
    }
}
