/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

/**
 *
 * @author Martin Boisvert
 */
public class ExitPointModel extends ElementModel {

    private final EntryNodeModel aEntryNode;
    
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
    public void calculateExits() {
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

}
