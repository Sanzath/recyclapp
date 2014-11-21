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
public class EntryNodeModel extends NodeModel
{
    private ExitNodeModel aExitNode;
    private ConveyorModel aConveyor;
    
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
            other.setConveyor(aConveyor);
            other.setEntryNode(this);
            aExitNode = other;
        }
    }
}
