/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import recyclapp.transport.*;

/**
 *
 * @author Martin Boisvert
 */
public final class TransformStationModel extends ElementModel implements java.io.Serializable{
    private static final String ELEMENT_TYPE = "Transform Station";
    
    private final EntryNodeModel aEntryNode;
    private final ExitNodeModel aExitNode;
    
    private String aInputMaterial = "";
    private MaterialFlowTable aTransformTable = new MaterialFlowTable();
    private StationType aType;
    
    private MaterialFlowTable aInput;
    private MaterialFlowTable aOutput;

    public TransformStationModel() {
        setColor(Color.RED);
        aEntryNode = new EntryNodeModel(this);
        aExitNode = new ExitNodeModel(this);
        
        aEntryNode.setAngle(ENTRY_NODE_DEFAULT_ANGLE);
        aExitNode.setAngle(EXIT_NODE_DEFAULT_ANGLE);
    }
    
    public TransformStationModel(TransformStationModel other) {
        super(other);
        
        aEntryNode = new EntryNodeModel(this, other.aEntryNode);
        aExitNode = new ExitNodeModel(this, other.aExitNode);
        
        setParameters(other.getParameters());
    }
    
    @Override
    protected ElementModel copy() {
        return new TransformStationModel(this);
    }

    @Override
    public void updateExits() {
        aOutput = new MaterialFlowTable();
        
        for (MaterialFlow input : aInput) {
            if (input.aName.equals(aInputMaterial)) {
                aOutput.addAll(MaterialFlowTable.multiply(input.aFlow, aTransformTable));
            }
            else {
                aOutput.add(input);
            }
        }
        
        aExitNode.updateThroughput(aOutput);
    }

    @Override
    public boolean canAddEntryNode() {
        return false;
    }

    @Override
    public boolean canAddExitNode() {
        return false;
    }

    @Override
    public boolean canRemoveEntryNode() {
        return false;
    }

    @Override
    public boolean canRemoveExitNode() {
        return false;
    }

    @Override
    public int getEntryNodeCount() {
        return 1;
    }

    @Override
    public int getExitNodeCount() {
        return 1;
    }

    @Override
    public ParameterGroup getParameters() {
        return new TransformStationParameterGroup(aInputMaterial, aTransformTable, aType);
    }
    
    @Override
    public void setParameters(ParameterGroup parameters) {
        TransformStationParameterGroup transformParameters = (TransformStationParameterGroup) parameters;
        
        aInputMaterial = transformParameters.aInputMaterial;
        aTransformTable = transformParameters.aTransformTable;
        aType = transformParameters.aType;
    }

    @Override
    public void updateInput(EntryNodeModel node, MaterialFlowTable throughput) {
        if (DiagramModel.getInstance().checkRecursion(node)) {
            return; // Recursion detected
        }
        
        aInput = throughput;
        aInputMaterial = throughput.size() == 0 ? "" : throughput.get(0).aName;
        
        updateExits();
        
    }

    @Override
    public MaterialFlowMatrix getInputMaterials() {
        MaterialFlowMatrix entry = new MaterialFlowMatrix();
        entry.add(aInput);
        return entry;
    }

    @Override
    public MaterialFlowMatrix getOutputMaterials() {
        MaterialFlowMatrix exit = new MaterialFlowMatrix();
        exit.add(aOutput);
        return exit;
    }

    @Override
    public MaterialFlowTable getThroughput() {
        return aInput;
    }

    @Override
    protected EntryNodeModel getEntryNode(int index) {
        return aEntryNode;
    }

    @Override
    protected ExitNodeModel getExitNode(int index) {
        return aExitNode;
    }

    @Override
    public String getType() {
        return ELEMENT_TYPE;
    }

    @Override
    protected void removeAllLinks() {
        aEntryNode.removeLink();
        aExitNode.removeLink();
    }
    
}

