/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;

import recyclapp.transport.Coords;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.MaterialFlowMatrix;

/**
 *
 * @author Martin Boisvert
 */
public abstract class ElementModel {
    // Node angles rotate clockwise. 0 = right, 90 = down, etc
    protected static final int ENTRY_NODE_DEFAULT_ANGLE = 180;
    protected static final int EXIT_NODE_DEFAULT_ANGLE = 0;
    protected static final int NODE_SPACING = 30;
    
    private String aName;
    private String aDescription;
    private Float aMaxInput;
    private Color aColor;
    private final Coords aPosition;
    private final Coords aSize;
    
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
    }
    
    protected abstract ElementModel copy();
    
    public abstract void updateExits();
    public abstract void updateInput(EntryNodeModel node, MaterialFlowTable throughput);
    
    public abstract boolean canAddEntryNode();
    public abstract boolean canAddExitNode();
    public abstract boolean canRemoveEntryNode();
    public abstract boolean canRemoveExitNode();
    
    public void addEntryNode() {}
    public void addExitNode() {}
    public void removeEntryNode(int index) {}
    public void removeExitNode(int index) {}
    
    public abstract int getEntryNodesCount();
    public abstract int getExitNodesCount();
    
    public ParameterGroup getParameters() { return null; }
    public void setParameters(ParameterGroup parameters) {}
    
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
    final public Float getMaxInput() {
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
    final public void setPosition(Coords position) {
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
    final public void setSize(Coords size) {
        aSize.x = size.x;
        aSize.y = size.y;
    }
    
    public abstract MaterialFlowMatrix getEntryMaterials(); 
    public abstract MaterialFlowMatrix getExitMaterials();
    public abstract MaterialFlowTable getThroughput();
    
}
