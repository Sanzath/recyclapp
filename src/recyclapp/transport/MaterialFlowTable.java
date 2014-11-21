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
public class MaterialFlowTable extends ArrayList<MaterialFlow> {
    
    
    public MaterialFlowTable() {}
    
    public MaterialFlowTable(MaterialFlowTable other)
    {
        for (MaterialFlow flow : other)
        {
            add(new MaterialFlow(flow));
        }
    }
}
