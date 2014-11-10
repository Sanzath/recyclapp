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
}
