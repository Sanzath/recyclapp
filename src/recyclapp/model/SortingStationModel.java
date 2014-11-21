/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;

import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.SortingStationParameterGroup;
import recyclapp.transport.StationType;
/**
 *
 * @author Martin Boisvert
 */
public final class SortingStationModel extends ElementModel {
    private static final int MINIMUM_EXIT_NODE_COUNT = 2;
    
    private final EntryNodeModel aEntryNode;
    private final List<ExitNodeModel> aExitNodes = new ArrayList<>();
    
    private MaterialFlowMatrix aSortingMatrix = new MaterialFlowMatrix();
    private StationType aType;

    public SortingStationModel() {
        this(MINIMUM_EXIT_NODE_COUNT);
    }
    
    public SortingStationModel(int exitNodeCount) {
        aEntryNode = new EntryNodeModel(this);
        
        final float m = (exitNodeCount - 1) / 2;
        for (int i = 0; i < exitNodeCount; ++i) {
            ExitNodeModel node = new ExitNodeModel(this);
            aExitNodes.add(node);
            
            float angle = EXIT_NODE_DEFAULT_ANGLE + NODE_SPACING * ((float)i - m);
            node.setAngle((int) angle);
        }
    }
    
    public SortingStationModel(SortingStationModel other) {
        super(other);
        
        aEntryNode = new EntryNodeModel(this, other.aEntryNode);
        for (ExitNodeModel node : other.aExitNodes) {
            aExitNodes.add(new ExitNodeModel(this, node));
        }
        
        setParameters(other.getParameters());
    }
    
    @Override
    public void calculateExits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementModel copy() {
        return new SortingStationModel(this);
    }

    @Override
    public boolean canAddEntryNode() {
        return false;
    }

    @Override
    public boolean canAddExitNode() {
        return true;
    }

    @Override
    public boolean canRemoveEntryNode() {
        return false;
    }

    @Override
    public boolean canRemoveExitNode() {
        return aExitNodes.size() > MINIMUM_EXIT_NODE_COUNT;
    }

    @Override
    public int getEntryNodesCount() {
        return 1;
    }

    @Override
    public int getExitNodesCount() {
        return aExitNodes.size();
    }

    @Override
    public void addExitNode() {
        ExitNodeModel node = new ExitNodeModel(this);
        aExitNodes.add(node);
        node.setAngle(EXIT_NODE_DEFAULT_ANGLE);
    }
    
    @Override
    public void removeExitNode(int index) {
        if (canRemoveExitNode()) {
            aExitNodes.remove(index);
        }
    }
    
    @Override
    public ParameterGroup getParameters() {
        return new SortingStationParameterGroup(aSortingMatrix, aType);
    }
    
    @Override
    public void setParameters(ParameterGroup parameters) {
        SortingStationParameterGroup sortingParameters = (SortingStationParameterGroup) parameters;
        aSortingMatrix = sortingParameters.aSortingMatrix;
        aType = sortingParameters.aType;
    }
    
}
