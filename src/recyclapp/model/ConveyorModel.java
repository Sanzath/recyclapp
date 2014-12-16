/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.*;
import recyclapp.transport.Coords;
import recyclapp.transport.MaterialFlowTable;

/**
 * Alexandre
 * @author Martin Boisvert
 */
public class ConveyorModel  implements java.io.Serializable{
    
    private String aName = "";
    private Float aMaxThroughput = 0.0F;
    private final List<Coords> aIntermediatePositions;
    private final EntryNodeModel aEntryNode;
    private final ExitNodeModel aExitNode;
    
    public MaterialFlowTable getThroughput ()
    {
        return aExitNode.getThroughput();
    }
    
    public ConveyorModel (EntryNodeModel entryNode, ExitNodeModel exitNode)
    {
        aIntermediatePositions = new ArrayList<>();
        aEntryNode = entryNode;
        aExitNode = exitNode;
    }
    
    public ConveyorModel(EntryNodeModel entryNode, ExitNodeModel exitNode, List<Coords> positions) {
        aIntermediatePositions = positions;
        aEntryNode = entryNode;
        aExitNode = exitNode;
    }
    
    public EntryNodeModel getEntryNode ()
    {
        return aEntryNode;
    }
    
    public ExitNodeModel getExitNode ()
    {
        return aExitNode;
    }
    
    public String getName() {
        return aName;
    }
    
    public void setName (String name)
    {
        aName = name;
    }
    
    public float getMaxThroughput() {
        return aMaxThroughput;
    }
    
    public void setMaxThroughput(Float MaxThroughput)
    {
        aMaxThroughput = MaxThroughput;
    }
    
    public void removeIntermediatePosition (int index)
    {
        aIntermediatePositions.remove(index);
    }
    
    public void insertIntermediatePosition (Coords position, int index)
    {
        aIntermediatePositions.add(index, position);
    }
    
    public void moveIntermediatePosition (Coords position, int index)
    {
        aIntermediatePositions.set(index, position);
    }
    
    public List<Coords> getIntermediatePositions() {
        List<Coords> positions = new ArrayList<>();
        
        for (Coords position : aIntermediatePositions) {
            positions.add(new Coords(position));
        }
        
        return positions;
    }
}
