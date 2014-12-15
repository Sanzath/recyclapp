/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowTable;

/**
 * Alexandre
 * @author Martin Boisvert
 */
public final class EntryNodeModel extends NodeModel implements java.io.Serializable{
    
    private ExitNodeModel aExitNode;
    
    public EntryNodeModel(ElementModel element)
    {
        super(element);
    }
    
    public EntryNodeModel(ElementModel element, NodeModel other)
    {
        super(element, other);
    }
    
    public void setConveyor(ConveyorModel conveyor)
    {
        aConveyor = conveyor;
    }
    
    public ExitNodeModel getPreviousNode()
    {
        return aExitNode;
    }
    
    public void createLink (ExitNodeModel other)
    {
        if (aExitNode != other)
        {
            if (aExitNode != null)
            {
                aExitNode.setEntryNode(null);
                aExitNode.setConveyor(null);
            }
            
            aConveyor = new ConveyorModel (this, other);
            aExitNode = other;
            
            other.setConveyor(aConveyor);
            other.setEntryNode(this);
            
            DiagramModel.getInstance().resetRecursionCheck();
            updateThroughput(other.getThroughput());
        }
    }
    
    public void removeLink() {
        if (aExitNode != null) {
            aExitNode.setConveyor(null);
            aExitNode.setEntryNode(null);

            aConveyor = null;
            aExitNode = null;

            DiagramModel.getInstance().resetRecursionCheck();
            updateThroughput(new MaterialFlowTable());
        }
    }
    
    @Override
    public MaterialFlowTable getThroughput() {
        if (aExitNode == null) {
            return new MaterialFlowTable();
        }
        return aExitNode.getThroughput();
    }
    
    protected void updateThroughput(MaterialFlowTable throughput) {
        aElement.updateInput(this, throughput);
    }
    
}
