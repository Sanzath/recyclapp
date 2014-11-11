/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.EntryPointParameterGroup;

/**
 *
 * @author Martin Boisvert
 */
public class EntryPointModel extends ElementModel {
    
    private MaterialFlowTable aEntryMaterials;


    @Override
    public void calculateExits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMinEntryNodes() {
        return 0;
    }

    @Override
    public int getMinExitNodes() {
        return 1;
    }

    @Override
    public ParameterGroup getParameters() {
        return new EntryPointParameterGroup(aEntryMaterials);
    }

    @Override
    public void setParameters(ParameterGroup parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxEntryNodes() {
        return 0;
    }

    @Override
    public int getMaxExitNodes() {
        return 1;
    }
    
}
