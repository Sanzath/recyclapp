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
    
    /**
     * Perform the material-by-material addition of two Tables. If a material
     * is only present in one of the two tables, it is assumed to be zero in the
     * other.
     * @param a First table.
     * @param b Second table.
     * @return Result of the addition.
     */
    public static MaterialFlowTable add(MaterialFlowTable a, MaterialFlowTable b) {
        MaterialFlowTable total = new MaterialFlowTable(a);
        
        for (MaterialFlow matB : b) {
            boolean alreadyPresent = false;
            
            for (MaterialFlow matA : total) {
                if (matA.aId == matB.aId) {
                    matA.aFlow += matB.aId;
                    alreadyPresent = true;
                    break;
                }
            }
            
            if (!alreadyPresent) {
                total.add(new MaterialFlow(matB));
            }
        }
        
        return total;
    }
    
    /**
     * Perform the material-by-material multiplication of two Tables. If a
     * material is only present in one of the two tables, it is assumed to be
     * zero in the other and is not present in the resulting table.
     * @param a First table.
     * @param b Second table.
     * @return Result of the multiplication.
     */
    public static MaterialFlowTable multiply(MaterialFlowTable a, MaterialFlowTable b) {
        MaterialFlowTable total = new MaterialFlowTable();
        
        for (MaterialFlow matA : a) {
            MaterialFlow result = new MaterialFlow(matA);
            
            for (MaterialFlow matB : b) {
                if (matA.aId == matB.aId) {
                    result.aFlow *= matB.aFlow;
                    total.add(result);
                    break;
                }
            }
        }
        
        return total;
    }
    
    public static MaterialFlowTable multiply(float s, MaterialFlowTable a) {
        MaterialFlowTable total = new MaterialFlowTable(a);
        for (MaterialFlow flow : total) {
            flow.aFlow *= s;
        }
        return total;
    }
    
}
