/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.ParameterGroup;
/**
 *
 * @author Martin Boisvert
 */
public class TransformStationModel extends ElementModel {
    
    private int aInputMaterial;
    private MaterialFlowTable aTransformTable;
    // private StationType _type;

    @Override
    public void calculateExits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMinEntryNodes() {
        return 1;
    }

    @Override
    public int getMinExitNodes() {
        return 1;
    }

    @Override
    public ParameterGroup getParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParameters(ParameterGroup parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

