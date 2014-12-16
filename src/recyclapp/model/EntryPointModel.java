/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import recyclapp.transport.MaterialFlowTable;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.EntryPointParameterGroup;
import recyclapp.transport.MaterialFlowMatrix;

/**
 *
 * @author Martin Boisvert
 */
public final class EntryPointModel extends ElementModel implements java.io.Serializable{
    private static final String ELEMENT_TYPE = "Entry Point";
    
    private MaterialFlowTable aEntryMaterials = new MaterialFlowTable();
    private final ExitNodeModel aExitNode;
    
    public EntryPointModel() {
        setColor(Color.GREEN);
        aExitNode = new ExitNodeModel(this);
        aExitNode.setAngle(EXIT_NODE_DEFAULT_ANGLE);
    }
    
    public EntryPointModel(EntryPointModel other) {
        super(other);
        aExitNode = new ExitNodeModel(this, other.aExitNode);
        setParameters(other.getParameters());
    }
    
    @Override
    protected ElementModel copy() {
        return new EntryPointModel(this);
    }

    @Override
    public void updateExits() {
        aExitNode.updateThroughput(aEntryMaterials);
    }
    
    @Override
    public void updateInput(EntryNodeModel node, MaterialFlowTable throughput) {
        // Should never happen for this type of element.
    }

    @Override
    public ParameterGroup getParameters() {
        return new EntryPointParameterGroup(aEntryMaterials);
    }

    @Override
    public void setParameters(ParameterGroup parameters) {
        EntryPointParameterGroup entryParameters = (EntryPointParameterGroup) parameters;
        aEntryMaterials = entryParameters.aEntryMaterials;
        
        DiagramModel.getInstance().resetRecursionCheck();
        updateExits();
    }

    @Override
    public int getEntryNodeCount() {
        return 0;
    }

    @Override
    public int getExitNodeCount() {
        return 1;
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
    public MaterialFlowMatrix getInputMaterials() {
        return new MaterialFlowMatrix();
    }

    @Override
    public MaterialFlowMatrix getOutputMaterials() {
        MaterialFlowMatrix exitMaterials = new MaterialFlowMatrix();
        exitMaterials.add(aEntryMaterials);
        return exitMaterials;
    }

    @Override
    public MaterialFlowTable getThroughput() {
        return aEntryMaterials;
    }

    @Override
    protected EntryNodeModel getEntryNode(int index) {
        return null;
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
        aExitNode.removeLink();
    }
    
}
