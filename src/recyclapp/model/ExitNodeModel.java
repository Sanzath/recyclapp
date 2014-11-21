/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

/**
 * Alexandre
 * @author Martin Boisvert
 */
public class ExitNodeModel extends NodeModel
{
    private EntryNodeModel aEntryNode;
    private ConveyorModel aConveyor;
    
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
}