/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.Coords;
import recyclapp.transport.NodeProperties;

/**
 *
 * @author Martin Boisvert
 */
public abstract class NodeModel implements java.io.Serializable{
    private final static float SIZE = 0.25F;
    
    private String aName;
    private int aAngle;
    
    protected final ElementModel aElement;
    protected ConveyorModel aConveyor;
    
    
    
    public NodeModel(ElementModel element)
    {
        aElement = element;
        
        aName = "no name";
        aAngle = 0;
    }
    
    public NodeModel(ElementModel element, NodeModel other)
    {
        aElement = element;
        
        aName = other.aName;
        aAngle = other.aAngle;
    }
    
    final public ElementModel getElement()
    {
        return aElement;
    }
    
    public abstract MaterialFlowTable getThroughput();
    
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
        if (aAngle < 0) {
            aAngle += 360;
        }
    }

    public final Coords getSize() {
        return new Coords(SIZE, SIZE);
    }
    
    final public NodeProperties toProperties() {
        NodeProperties properties = new NodeProperties();
        
        properties.aParentId = aElement.getId();
        properties.aName = aName;
        properties.aAngle = aAngle;
        //properties.aPosition = calculatePosition();
        properties.aSize = getSize();
        
        return properties;
    }
    
    protected final ConveyorModel getConveyor() {
        return aConveyor;
    }
    
}
