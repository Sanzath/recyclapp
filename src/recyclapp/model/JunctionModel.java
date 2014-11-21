/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Martin Boisvert
 */
public final class JunctionModel extends ElementModel {
    private static final int MINIMUM_ENTRY_NODE_COUNT = 2;
    
    private final List<EntryNodeModel> aEntryNodes = new ArrayList<>(MINIMUM_ENTRY_NODE_COUNT);
    private final ExitNodeModel aExitNode;
    
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
    public void calculateExits() {
        // Wait for all entries to be received...
        // If an entry is received more than once for the same recursion flag (to be implemented), stop.
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
