/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.ParameterGroup;

/**
 *
 * @author Martin Boisvert
 */
public class ExitPointModel extends ElementModel {

    @Override
    public void calculateExits() {
        // No exits to calculate
    }

    @Override
    public int getMinEntryNodes() {
        return 1;
    }

    @Override
    public int getMinExitNodes() {
        return 0;
    }

    @Override
    public ParameterGroup getParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParameters(ParameterGroup parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxEntryNodes() {
        return 1;
    }

    @Override
    public int getMaxExitNodes() {
        return 0;
    }
    
}
