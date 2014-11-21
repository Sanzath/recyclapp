/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.Coords;

/**
 *
 * @author Martin Boisvert
 */
public abstract class NodeModel {
    private final static float SIZE = 0.1F;
    
    private String aName;
    private MaterialFlowTable aThroughput;
    private int aAngle;
    
    private final ElementModel aElement;
    
    public NodeModel(ElementModel element)
    {
        aElement = element;
        
        aName = "no name";
        aThroughput = new MaterialFlowTable();
        aAngle = 0;
    }
    
    public NodeModel(ElementModel element, NodeModel other)
    {
        aElement = element;
        
        aName = other.aName;
        aThroughput = new MaterialFlowTable(other.aThroughput);
        aAngle = other.aAngle;
    }
    
    final public ElementModel getElement()
    {
        return aElement;
    }
    
    protected final void setThroughput(MaterialFlowTable throughput)
    {
        aThroughput = new MaterialFlowTable(throughput);
    }
    
    public final MaterialFlowTable getThroughput()
    {
        return aThroughput;
    }
    
    public final String getName()
    {
        return aName;
    }
    
    public final void setName(String name)
    {
        aName = name;
    }
    
    public final int getAngle()
    {
        return aAngle;
    }
    
    public final void setAngle(int angle)
    {
        aAngle = angle % 360;
    }
    
    /**
     * Calculates and returns top-left position of the Node. Bases calculation
     * on the parent element's middle position, its size, and the node's angle.
     * @return the middle position of the Node.
     */
    public final Coords calculatePosition()
    {
        Coords position = aElement.getPosition();
        Coords parentSize = aElement.getSize();
        Coords offset = new Coords();
        
        // Get center position -- assuming pos is top left
        position.x += parentSize.x / 2;
        position.y += parentSize.y / 2;
        
        // Now determine which of the four borders this node is "stuck" to
        int borderAngle = (int) Math.toDegrees(Math.atan(parentSize.y / parentSize.x));
        
        // Stuck to right
        if (aAngle < borderAngle || aAngle >= (360 - borderAngle))
        {
            offset.x = (parentSize.x + SIZE) / 2;
            offset.y = (float) (offset.x * Math.tan(Math.toRadians(aAngle)));
        }
        // Stuck to bottom
        else if (aAngle < (180 - borderAngle))
        {
            offset.y = (parentSize.y + SIZE) / 2;
            offset.x = (float) (offset.y / Math.tan(Math.toRadians(aAngle)));
        }
        // Stuck to left
        else if (aAngle < (180 + borderAngle))
        {
            offset.x = - (parentSize.x + SIZE) / 2;
            offset.y = (float) (offset.x * Math.tan(Math.toRadians(aAngle)));
        }
        // Stuck to top
        else
        {
            offset.y = - (parentSize.y + SIZE) / 2;
            offset.x = (float) (offset.y / Math.tan(Math.toRadians(aAngle)));
        }
        
        // Offset the position to get the middle position of the node
        position.x += offset.x;
        position.y += offset.y;
        
        // And offset to get the topleft position
        position.x -= SIZE / 2;
        position.y -= SIZE / 2;
        
        return position;
    }
    
    public final Coords getSize() {
        return new Coords(SIZE, SIZE);
    }
    
}
