/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.MaterialFlowTable;

/**
 *
 * @author Martin Boisvert
 */
public class ExitPointModel extends ElementModel implements java.io.Serializable{

    private final EntryNodeModel aEntryNode;
    private static final long serialVersionUID = 4632964078848835517L;
    
    private MaterialFlowTable aInput;
    
    public ExitPointModel() {
        setColor(Color.BLUE);
        aEntryNode = new EntryNodeModel(this);
        aEntryNode.setAngle(ENTRY_NODE_DEFAULT_ANGLE);
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
    public int getEntryNodeCount() {
        return 1;
    }

    @Override
    public int getExitNodeCount() {
        return 0;
    }

    @Override
    public void updateInput(EntryNodeModel node, MaterialFlowTable throughput) {
        // Recursions can't happen here, don't bother checking
        aInput = throughput;
    }

    @Override
    public MaterialFlowMatrix getInputMaterials() {
        MaterialFlowMatrix entries = new MaterialFlowMatrix();
        entries.add(aInput);
        return entries;
    }

    @Override
    public MaterialFlowMatrix getOutputMaterials() {
        return new MaterialFlowMatrix();
    }

    @Override
    public MaterialFlowTable getThroughput() {
        return aInput;
    }

    @Override
    protected EntryNodeModel getEntryNode(int index) {
        return aEntryNode;
    }

    @Override
    protected ExitNodeModel getExitNode(int index) {
        return null;
    }

}
