/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.*;
import recyclapp.transport.Coords;
import recyclapp.transport.MaterialFlowTable;

/**
 * Alexandre
 * @author Martin Boisvert
 */
public class ConveyorModel {
    
    private String aName;
    private Float aMaxThroughput;
    private ArrayList<Coords> aIntermediatePositions;
    private EntryNodeModel aEntryNode;
    private ExitNodeModel aExitNode;
    
    public MaterialFlowTable getThroughput ()
    {
        return aExitNode.getThroughput();
    }
    
    public ConveyorModel ()
    {
        aName = "";
        aMaxThroughput = 0.0f;
        aIntermediatePositions = new ArrayList<> ();
        aEntryNode = null;
        aExitNode = null;
    }
    
    public ConveyorModel (EntryNodeModel entryNode, ExitNodeModel exitNode)
    {
        aName = "";
        aMaxThroughput = 0.0f;
        aIntermediatePositions = new ArrayList<> ();
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
    
    public void setName (String name)
    {
        aName = name;
    }
    
    public void setMaxThroughput(Float MaxThroughput)
    {
        aMaxThroughput = MaxThroughput;
    }
    
    public void setEntryNode (EntryNodeModel EntryNode)
    {
        aEntryNode = EntryNode;
    }
    
    public void setExitNode (ExitNodeModel ExitNode)
    {
        aExitNode = ExitNode;
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
}
