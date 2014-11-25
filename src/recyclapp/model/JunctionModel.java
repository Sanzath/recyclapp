/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;
import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.MaterialFlowTable;

/**
 *
 * @author Martin Boisvert
 */
public final class JunctionModel extends ElementModel {
    private static final int MINIMUM_ENTRY_NODE_COUNT = 2;
    
    private final List<EntryNodeModel> aEntryNodes = new ArrayList<>(MINIMUM_ENTRY_NODE_COUNT);
    private final ExitNodeModel aExitNode;
    
    private final MaterialFlowMatrix aInputs = new MaterialFlowMatrix();
    private MaterialFlowTable aOutput;
    
    public JunctionModel() {
        this(MINIMUM_ENTRY_NODE_COUNT);
    }
    
    public JunctionModel(int entryNodeCount) {
        final float m = (entryNodeCount - 1) / 2;
        
        for (int i = 0; i < entryNodeCount; ++i) {
            // Entry nodes on the left side (180 degrees) spaced by 30 degrees each
            EntryNodeModel node = new EntryNodeModel(this);
            aEntryNodes.add(node);
            
            float angle = ENTRY_NODE_DEFAULT_ANGLE + NODE_SPACING * ((float)i - m);
            node.setAngle((int) angle);
        }
        aExitNode = new ExitNodeModel(this);
        aExitNode.setAngle(EXIT_NODE_DEFAULT_ANGLE);
    }
    
    public JunctionModel(JunctionModel other) {
        super(other);
        
        for (EntryNodeModel node : other.aEntryNodes) {
            aEntryNodes.add(new EntryNodeModel(this, node));
        }
        aExitNode = new ExitNodeModel(this, other.aExitNode);
    }
    
    @Override
    protected ElementModel copy() {
        return new JunctionModel(this);
    }

    @Override
    public void updateExits() {
        aOutput = new MaterialFlowTable();
        for (MaterialFlowTable entry : aInputs) {
            aOutput = MaterialFlowTable.add(aOutput, entry);
        }
        
        aExitNode.updateThroughput(aOutput);
    }
    
    @Override
    public void addEntryNode() {
        EntryNodeModel node = new EntryNodeModel(this);
        aEntryNodes.add(node);
        node.setAngle(ENTRY_NODE_DEFAULT_ANGLE);
    }
    
    @Override
    public void removeEntryNode(int index) {
        if (canRemoveEntryNode())
        {
            aEntryNodes.get(index).removeLink();
            aEntryNodes.remove(index);
        }
    }

    @Override
    public boolean canAddEntryNode() {
        return true;
    }

    @Override
    public boolean canAddExitNode() {
        return false;
    }

    @Override
    public boolean canRemoveEntryNode() {
        return aEntryNodes.size() > MINIMUM_ENTRY_NODE_COUNT;
    }

    @Override
    public boolean canRemoveExitNode() {
        return false;
    }

    @Override
    public int getEntryNodesCount() {
        return aEntryNodes.size();
    }

    @Override
    public int getExitNodesCount() {
        return 1;
    }

    @Override
    public void updateInput(EntryNodeModel node, MaterialFlowTable throughput) {
        if (DiagramModel.getInstance().checkRecursion(node)) {
            return; // Recursion detected
        }
        
        aInputs.set(aEntryNodes.indexOf(node), throughput);
        
        updateExits();
    }

    @Override
    public MaterialFlowMatrix getInputMaterials() {
        return aInputs;
    }

    @Override
    public MaterialFlowMatrix getOutputMaterials() {
        MaterialFlowMatrix exits = new MaterialFlowMatrix();
        exits.add(aOutput);
        return exits;
    }

    @Override
    public MaterialFlowTable getThroughput() {
        return aOutput;
    }

    @Override
    protected EntryNodeModel getEntryNode(int index) {
        return aEntryNodes.get(index);
    }

    @Override
    protected ExitNodeModel getExitNode(int index) {
        return aExitNode;
    }

}
