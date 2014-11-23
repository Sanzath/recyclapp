/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.MaterialFlowTable;

/**
 *
 * @author Martin Boisvert
 */
public class ExitPointModel extends ElementModel {

    private final EntryNodeModel aEntryNode;
    
    private MaterialFlowTable aThroughput;
    
    public ExitPointModel() {
        aEntryNode = new EntryNodeModel(this);
    }
    
    public ExitPointModel(ExitPointModel other) {
        super(other);
        aEntryNode = new EntryNodeModel(this, other.aEntryNode);
    }
    
    @Override
    protected ElementModel copy() {
        return new ExitPointModel(this);
    }

    @Override
    public void updateExits() {
        // Nothing left to do!
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

    @Override
    public int getEntryNodesCount() {
        return 1;
    }

    @Override
    public int getExitNodesCount() {
        return 0;
    }

    @Override
    public void updateInput(EntryNodeModel node, MaterialFlowTable throughput) {
        // Recursions can't happen here, don't bother checking
        aThroughput = throughput;
    }

    @Override
    public MaterialFlowMatrix getEntryMaterials() {
        MaterialFlowMatrix entries = new MaterialFlowMatrix();
        entries.add(aThroughput);
        return entries;
    }

    @Override
    public MaterialFlowMatrix getExitMaterials() {
        return new MaterialFlowMatrix();
    }

    @Override
    public MaterialFlowTable getThroughput() {
        return aThroughput;
    }

}
