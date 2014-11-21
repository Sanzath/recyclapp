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
public final class EntryPointModel extends ElementModel {
    
    private MaterialFlowTable aEntryMaterials = new MaterialFlowTable();
    private final ExitNodeModel aExitNode;
    
    public EntryPointModel() {
        aExitNode = new ExitNodeModel(this);
        aExitNode.setAngle(EXIT_NODE_DEFAULT_ANGLE);
    }
    
    public EntryPointModel(EntryPointModel other) {
        super(other);
        aExitNode = new ExitNodeModel(this, other.aExitNode);
        setParameters(other.getParameters());
    }
    
    @Override
    protected ElementModel copy() {
        return new EntryPointModel(this);
    }

    @Override
    public void calculateExits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ParameterGroup getParameters() {
        return new EntryPointParameterGroup(aEntryMaterials);
    }

    @Override
    public void setParameters(ParameterGroup parameters) {
        EntryPointParameterGroup entryParameters = (EntryPointParameterGroup) parameters;
        aEntryMaterials = entryParameters.aEntryMaterials;
    }

    @Override
    public int getEntryNodesCount() {
        return 0;
    }

    @Override
    public int getExitNodesCount() {
        return 1;
    }

    @Override
    public boolean canAddEntryNode() {
        return false;
    }

    @Override
    public boolean canAddExitNode() {
        return false;
    }

    @Override
    public boolean canRemoveEntryNode() {
        return false;
    }

    @Override
    public boolean canRemoveExitNode() {
        return false;
    }
    
}
