/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowTable;

/**
 * Alexandre
 * @author Martin Boisvert
 */
public class ExitNodeModel extends NodeModel
{
    private MaterialFlowTable aThroughput;
    
    private EntryNodeModel aEntryNode;
    
    public ExitNodeModel(ElementModel element)
    {
        super(element);
    }
    
    public ExitNodeModel(ElementModel element, NodeModel other)
    {
        super(element, other);
    }
    
    public void setEntryNode(EntryNodeModel entryNode)
    {
        aEntryNode = entryNode;
    }
    public void setConveyor(ConveyorModel conveyor)
    {
        aConveyor = conveyor;
    }
    
    public EntryNodeModel getPreviousNode()
    {
        return aEntryNode;
    }
    
    public void createLink (EntryNodeModel other)
    {
        other.createLink(this);
    }
    
    public void removeLink() {
        if (aEntryNode != null) {
            aEntryNode.removeLink();
        }
    }
    
    public void updateThroughput(MaterialFlowTable throughput) {
        aThroughput = throughput;
        
        if (aEntryNode != null) {
            aEntryNode.updateThroughput(throughput);
        }
    }
    
    @Override
    public MaterialFlowTable getThroughput() {
        return aThroughput;
    }
    
}