/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import java.util.ArrayList;

import recyclapp.transport.Coords;
import recyclapp.transport.ParameterGroup;

/**
 *
 * @author Martin Boisvert
 */
public abstract class ElementModel {
    private String aName;
    private String aDescription;
    private Float aMaxInput;
    private Color aColor;
    private final Coords aPosition;
    private final Coords aSize;
    
    final private ArrayList<EntryNodeModel> aEntryNodes = new ArrayList<>();
    final private ArrayList<ExitNodeModel> aExitNodes = new ArrayList<>();
    
    public ElementModel()
    {
        aName = "no name";
        aDescription = "no description";
        aMaxInput = 0.F;
        aColor = Color.BLACK;
        aPosition = new Coords();
        aSize = new Coords(1, 1);
    }
    
    public ElementModel(ElementModel other)
    {
        aName = other.aName;
        aDescription = other.aDescription;
        aMaxInput = other.aMaxInput;
        aColor = other.aColor;
        aPosition = new Coords(other.aPosition);
        aSize = new Coords(other.aSize);
        
        for (EntryNodeModel node : other.aEntryNodes)
        {
            aEntryNodes.add(new EntryNodeModel(this, node));
        }
        for (ExitNodeModel node : other.aExitNodes)
        {
            aExitNodes.add(new ExitNodeModel(this, node));
        }
    }
    
    public abstract void calculateExits();
    
    public abstract int getMinEntryNodes();
    public abstract int getMinExitNodes();
    
    public abstract ParameterGroup getParameters();
    public abstract void setParameters(ParameterGroup parameters);
    
    final public int getEntryNodesCount()
    {
        return aEntryNodes.size();
    }
    
    final public int getExitNodesCount()
    {
        return aExitNodes.size();
    }
    
    public void addEntryNode()
    {
        aEntryNodes.add(new EntryNodeModel(this));
    }
    
    public void addExitNode()
    {
        aExitNodes.add(new ExitNodeModel(this));
    }
    
    final public void removeEntryNode(int index)
    {
        if (getEntryNodesCount() > getMinEntryNodes())
        {
            aEntryNodes.remove(index);
        }
    }
    
    final public void removeExitNode(int index)
    {
        if (getExitNodesCount() > getMinExitNodes())
        {
            aExitNodes.remove(index);
        }
    }
    

    /**
     * @return the aName
     */
    final public String getName() {
        return aName;
    }

    /**
     * @param name the aName to set
     */
    final public void setName(String name) {
        aName = name;
    }

    /**
     * @return the aDescription
     */
    final public String getDescription() {
        return aDescription;
    }

    /**
     * @param description the aDescription to set
     */
    final public void setDescription(String description) {
        aDescription = description;
    }

    /**
     * @return the aMaxInput
     */
    public Float getMaxInput() {
        return aMaxInput;
    }

    /**
     * @param maxInput the aMaxInput to set
     */
    final public void setMaxInput(Float maxInput) {
        aMaxInput = maxInput;
    }

    /**
     * @return the aColor
     */
    final public Color getColor() {
        return aColor;
    }

    /**
     * @param color the aColor to set
     */
    final public void setColor(Color color) {
        aColor = color;
    }

    /**
     * @return the aPosition
     */
    final public Coords getPosition() {
        return new Coords(aPosition);
    }

    /**
     * @param position the aPosition to set
     */
    public final void setPosition(Coords position) {
        aPosition.x = position.x;
        aPosition.y = position.y;
    }

    /**
     * @return the aSize
     */
    final public Coords getSize() {
        return new Coords(aSize);
    }

    /**
     * @param size the aSize to set
     */
    public final void setSize(Coords size) {
        aSize.x = size.x;
        aSize.y = size.y;
    }
}
