/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import recyclapp.transport.Coords;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.ElementProperties;

/**
 *
 * @author Martin Boisvert
 */
public abstract class ElementModel implements java.io.Serializable{
    // Node angles rotate clockwise. 0 = right, 90 = down, etc
    protected static final int ENTRY_NODE_DEFAULT_ANGLE = 180;
    protected static final int EXIT_NODE_DEFAULT_ANGLE = 0;
    protected static final int NODE_SPACING = 30;
    
    private static final long serialVersionUID = 1247776848009982420L;
    
    private static int sNextId = 0;
    
    private final int aId;
    private String aName;
    private String aDescription;
    private Float aMaxInput;
    private Color aColor;
    private Coords aPosition;
    private Coords aSize;
    
    public ElementModel()
    {
        aId = sNextId++;
        aName = "no name";
        aDescription = "no description";
        aMaxInput = 0.F;
        aColor = Color.BLACK;
        aPosition = new Coords();
        aSize = new Coords(1, 1);
    }
    
    public ElementModel(ElementModel other)
    {
        aId = sNextId++;
        aName = other.aName;
        aDescription = other.aDescription;
        aMaxInput = other.aMaxInput;
        aColor = other.aColor;
        aPosition = new Coords(other.aPosition);
        aSize = new Coords(other.aSize);
    }
    
    protected abstract ElementModel copy();
    
    protected abstract void updateExits();
    protected abstract void updateInput(EntryNodeModel node, MaterialFlowTable throughput);
    
    public abstract boolean canAddEntryNode();
    public abstract boolean canAddExitNode();
    public abstract boolean canRemoveEntryNode();
    public abstract boolean canRemoveExitNode();
    
    public EntryNodeModel addEntryNode() { return null; }
    public ExitNodeModel addExitNode() { return null; }
    public void removeEntryNode(int index) {}
    public void removeExitNode(int index) {}
    
    public abstract int getEntryNodeCount();
    public abstract int getExitNodeCount();
    
    public ParameterGroup getParameters() { return null; }
    public void setParameters(ParameterGroup parameters) {}
    
    public final int getId() {
        return aId;
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
        return aPosition;
    }

    /**
     * @param position the aPosition to set
     */
    final public void setPosition(Coords position) {
        aPosition = position;
    }

    /**
     * @return the aSize
     */
    final public Coords getSize() {
        return aSize;
    }

    /**
     * @param size the aSize to set
     */
    final public void setSize(Coords size) {
        aSize = size;
    }
    
    public ElementProperties toProperties() {
        ElementProperties properties = new ElementProperties();
        
        properties.aId = aId;
        properties.aName = aName;
        properties.aDescription = aDescription;
        properties.aPosition = aPosition;
        properties.aSize = aSize;
        properties.aColor = aColor;
        properties.aMaxInput = aMaxInput;
        properties.aParameters = getParameters();
        
        return properties;
    }
    
    public void fromProperties(ElementProperties properties) {
        aName = properties.aName;
        aDescription = properties.aDescription;
        aPosition = properties.aPosition;
        aSize = properties.aSize;
        aColor = properties.aColor;
        aMaxInput = properties.aMaxInput;
        setParameters(properties.aParameters);
        
    }
    
    public abstract MaterialFlowMatrix getInputMaterials(); 
    public abstract MaterialFlowMatrix getOutputMaterials();
    public abstract MaterialFlowTable getThroughput();
    
    protected abstract EntryNodeModel getEntryNode(int index);
    protected abstract ExitNodeModel getExitNode(int index);
    
    protected List<String> getEntryNodeNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < getEntryNodeCount(); ++i) {
            names.add(getEntryNode(i).getName());
        }
        return names;
    }
    
    protected List<String> getExitNodeNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < getExitNodeCount(); ++i) {
            names.add(getExitNode(i).getName());
        }
        return names;
    }
    
    public abstract String getType();
    
    protected abstract void removeAllLinks();
}
