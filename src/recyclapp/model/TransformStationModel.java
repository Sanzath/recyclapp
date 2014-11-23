/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.StationType;
import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.MaterialFlow;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.TransformStationParameterGroup;
/**
 *
 * @author Martin Boisvert
 */
public final class TransformStationModel extends ElementModel {
    
    private final EntryNodeModel aEntryNode;
    private final ExitNodeModel aExitNode;
    
    private int aInputMaterial = -1;
    private MaterialFlowTable aTransformTable = new MaterialFlowTable();
    private StationType aType;
    
    private MaterialFlowTable aInput;
    private MaterialFlowTable aOutput;

    public TransformStationModel() {
        aEntryNode = new EntryNodeModel(this);
        aExitNode = new ExitNodeModel(this);
        
        aEntryNode.setAngle(ENTRY_NODE_DEFAULT_ANGLE);
        aExitNode.setAngle(EXIT_NODE_DEFAULT_ANGLE);
    }
    
    public TransformStationModel(TransformStationModel other) {
        aEntryNode = new EntryNodeModel(this, other.aExitNode);
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
            if (input.aId == aInputMaterial) {
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
    public int getEntryNodesCount() {
        return 1;
    }

    @Override
    public int getExitNodesCount() {
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
        
        updateExits();
        
    }

    @Override
    public MaterialFlowMatrix getEntryMaterials() {
        MaterialFlowMatrix entry = new MaterialFlowMatrix();
        entry.add(aInput);
        return entry;
    }

    @Override
    public MaterialFlowMatrix getExitMaterials() {
        MaterialFlowMatrix exit = new MaterialFlowMatrix();
        exit.add(aOutput);
        return exit;
    }

    @Override
    public MaterialFlowTable getThroughput() {
        return aInput;
    }
    
}

