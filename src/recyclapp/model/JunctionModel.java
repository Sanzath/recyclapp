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
public class JunctionModel extends ElementModel {

    @Override
    public void calculateExits() {
        // All entry points --> single exit point
    }

    @Override
    public int getMinEntryNodes() {
        return 2;
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

    @Override
    public int getMaxEntryNodes() {
        return 99;
    }

    @Override
    public int getMaxExitNodes() {
        return 1;
    }
    
}
