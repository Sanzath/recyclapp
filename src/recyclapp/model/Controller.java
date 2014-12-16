/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.*;

import java.util.List;
import java.util.ArrayList;
import recyclapp.view.OverviewView;

/**
 *
 * @author Martin Boisvert
 */
public class Controller {
    // Singleton
    private static Controller sInstance;
    
    private Controller() {}
        
    public static Controller getInstance() {
        if (sInstance == null)
        {
            sInstance = new Controller();
        }
        return sInstance;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Diagram" >
    public void setGridActive(boolean active) {
        DiagramModel.getInstance().setGridActive(active);
    }
    
    public boolean getGridActive() {
        return DiagramModel.getInstance().getGridActive();
    }
    
    public void setGridSpacing(float spacing) {
        DiagramModel.getInstance().setGridSpacing(spacing);
    }
    
    public float getGridSpacing() {
        return DiagramModel.getInstance().getGridSpacing();
    }
    
    public boolean checkRecursion() {
        return DiagramModel.getInstance().checkRecursion();
    }
    
    public List<ElementProperties> getAllElements() {
        return DiagramModel.getInstance().getAllElements();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Menu">
    public void save()
    {
        
    }
 
    public void saveAs(String name)
    {
        
    }
    
    public void load(String name)
    {
        
    }
    
    public void copy()
    {
        // Copy from selection - determined by DiagramModel
    }
    
    public void paste()
    {
        // Paste to position - determined by DiagramModel
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="History">
    public String getOptions()
    {
        return "placeholder";
    }
    
    public void undo()
    {   
        if (HistoryElement.getCounter() > 1)
        {
            HistoryElement.setCounter(HistoryElement.getCounter()-1);
            HistoryElement.getInstance().deserializeDiag();
        }
    }
    
    public void redo()
    {
        if ( HistoryElement.getCounter() < HistoryElement.getMax())
        {
            HistoryElement.setCounter(HistoryElement.getCounter()+1);
            HistoryElement.getInstance().deserializeDiag();
        }
    }
    
    public void serialize()
    {
        HistoryElement.getInstance().serialiseDiagram(DiagramModel.getInstance());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ToolBox">
    public List<String> getToolBoxElements()
    {
        return ToolBoxModel.getInstance().getElements();
    }
    
    public void selectElement(int index)
    {
        ToolBoxModel.getInstance().selectElement(index);
    }
    
    public int createElementFromToolBox(){
        int id = DiagramModel.getInstance().createFromSelectedToolbox();
        deselectElement();
        return id;
    }
    
    public void deselectElement()
    {
        ToolBoxModel.getInstance().deselectElement();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="TransformDictionary">
    public void addTransform(String sourceMaterial, String destinationMaterial)
    {
        
    }
    
    public void removeTransform(String sourceMaterial, String destinationMaterial)
    {
        
    }
    
    // Void devrait être quelque chose d'autre, possiblement String ou String[] ou...
    public void getTransforms()
    {
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overview">
    public List<EntryPointModel> getEntryPoints()
    {
        List<EntryPointModel> entries = OverviewModel.getInstance().getEntryPoints();
        return entries;
    }
    
    public List<ExitPointModel> getExitPoints()
    {
        List<ExitPointModel> exits = OverviewModel.getInstance().getExitPoints();
        return exits;
    }
    
    public void selectEntryPoint(int index)
    {
        
    }
    
    public void selectExitPoint(int index)
    {
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MaterialDictonary">
    public String[] getMaterials()
    {
        String materials[] = new String[1];
        materials[1] = "placeholder";
        
        return materials;
    }
    
    public void addMaterial(String name)
    {
        
    }
    
    public void removeMaterial(String name)
    {
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="TemplateContainer">
    // void devrait être un String[] ou quelque chose dans le genre
    public void getTemplates()
    {
        
    }
    
    public void selectTemplate(int index)
    {
        
    }
    
    public void deselectTemplate()
    {
        
    }
    
    public void addTemplate(/* quelque chose */)
    {
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Elements">
    private ElementModel getElement(int id) {
        return DiagramModel.getInstance().getElementFromId(id);
    }
    
    public ElementProperties getElementProperties(int id) {
        return getElement(id).toProperties();
    }
    
    public void setElementProperties(ElementProperties properties) {
        ElementProperties initial = getElement(properties.aId).toProperties();
        getElement(properties.aId).fromProperties(properties);
        Controller.getInstance().serialize();
    }
    
    public ParameterGroup getParameters(int id) {
        return getElement(id).getParameters();
    }
    public void setParameters(int id, ParameterGroup parameters) {
        getElement(id).setParameters(parameters);
        Controller.getInstance().serialize();
    }
    
    public void setElementPosition(int id, Coords position) {
        getElement(id).setPosition(position);
        Controller.getInstance().serialize();
    }
    
    public void setElementSize(int id, Coords size) {
        getElement(id).setSize(size);
        Controller.getInstance().serialize();
    }
    
    public boolean canAddEntryNode(int id) {
        return getElement(id).canAddEntryNode();
    }
    public boolean canAddExitNode(int id) {
        return getElement(id).canAddExitNode();
    }
    public boolean canRemoveEntryNode(int id) {
        return getElement(id).canRemoveEntryNode();
    }
    public boolean canRemoveExitNode(int id) {
        return getElement(id).canRemoveExitNode();
    }
    
    public void addEntryNode(int id) {
        getElement(id).addEntryNode();
        Controller.getInstance().serialize();
    }
    public void addExitNode(int id) {
        getElement(id).canAddExitNode();
        Controller.getInstance().serialize();
    }
    public void addEntryNode(int id, String name) {
        getElement(id).addEntryNode().setName(name);
        Controller.getInstance().serialize();
    }
    public void addExitNode(int id, String name) {
        getElement(id).addExitNode().setName(name);
        Controller.getInstance().serialize();
    }
    
    public void removeEntryNode(int id, int index) {
        getElement(id).removeEntryNode(index);
        Controller.getInstance().serialize();
    }
    public void removeExitNode(int id, int index) {
        getElement(id).removeExitNode(index);
        Controller.getInstance().serialize();
    }
    
    public int getEntryNodeCount(int id) {
        return getElement(id).getEntryNodeCount();
    }
    public int getExitNodeCount(int id) {
        return getElement(id).getExitNodeCount();
    }
    public List<String> getEntryNodeNames(int id) {
        return getElement(id).getEntryNodeNames();
    }
    public List<String> getExitNodeNames(int id) {
        return getElement(id).getExitNodeNames();
    }
    
    public MaterialFlowMatrix getInputMaterials(int id) {
        return getElement(id).getInputMaterials();
    }
    public MaterialFlowMatrix getOutputMaterials(int id) {
        return getElement(id).getOutputMaterials();
    }
    public MaterialFlowTable getThroughput(int id) {
        return getElement(id).getThroughput();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Nodes">
    private EntryNodeModel getEntryNode(int parentId, int index) {
        return getElement(parentId).getEntryNode(index);
    }
    private ExitNodeModel getExitNode(int parentId, int index) {
        return getElement(parentId).getExitNode(index);
    }
    
    public NodeProperties getEntryNodeProperties(int parentId, int index) {
        return getEntryNode(parentId, index).toProperties();
    }
    public NodeProperties getExitNodeProperties(int parentId, int index) {
        return getExitNode(parentId, index).toProperties();
    }
    
    public MaterialFlowTable getEntryNodeThroughput(int parentId, int index) {
        return getEntryNode(parentId, index).getThroughput();
    }
    public MaterialFlowTable getExitNodeThroughput(int parentId, int index) {
        return getExitNode(parentId, index).getThroughput();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Conveyors">
    private ConveyorModel getConveyor(int entryParentId, int entryIndex) {
        return getEntryNode(entryParentId, entryIndex).getConveyor();
    }
    
    public List<ConveyorProperties> getAllConveyors() {
        List<ConveyorProperties> conveyorProperties = new ArrayList<>();
        // Goes through each element. For each element, get all entry nodes (i).
        // If the entry node has a conveyor, get the corresponding exit element.
        // Find which index is associated with the exit node connected to the conveyor.
        // Add a new ConveyorProperties to the list for each conveyor found.
        
        List<ElementProperties> allElements = DiagramModel.getInstance().getAllElements();
        for (ElementProperties properties : allElements) {
            ElementModel entryElement = getElement(properties.aId);
            int entryNodeCount = entryElement.getEntryNodeCount();
            for (int i = 0; i < entryNodeCount; i++) {
                ConveyorModel conveyor = entryElement.getEntryNode(i).getConveyor();
                if (conveyor != null) {
                    ElementModel exitElement = conveyor.getExitNode().getElement();
                    int exitNodeCount = exitElement.getExitNodeCount();
                    for (int j = 0; j < exitNodeCount; j++) {
                        if (exitElement.getExitNode(j) == conveyor.getExitNode()) {
                            ConveyorProperties conveyorProp = new ConveyorProperties();
                            conveyorProp.aEntryParentId = entryElement.getId();
                            conveyorProp.aEntryIndex = i;
                            conveyorProp.aExitParentId = exitElement.getId();
                            conveyorProp.aExitIndex = j;
                            conveyorProp.aIntermediatePositions = conveyor.getIntermediatePositions();
                            conveyorProperties.add(conveyorProp);
                            break;
                        }
                    }
                }
            }
        }
        
        return conveyorProperties;
    }
    
    public void addConveyor(int entryParentId, int entryIndex, int exitParentId, int exitIndex) {
        EntryNodeModel entryNode = getEntryNode(entryParentId, entryIndex);
        ExitNodeModel exitNode = getExitNode(exitParentId, exitIndex);
        
        entryNode.createLink(exitNode);
        Controller.getInstance().serialize();
    }
    
    public void removeConveyor(int entryParentId, int entryIndex) {
        getEntryNode(entryParentId, entryIndex).removeLink();
        Controller.getInstance().serialize();
    }
    
    public List<Coords> getConveyorIntermediatePositions(int entryParentId, int entryIndex) {
        return getConveyor(entryParentId, entryIndex).getIntermediatePositions();
    }
    
    public void insertConveyorIntermediatePosition(int entryParentId, int entryIndex,
            Coords intermediatePosition, int index) {
        getConveyor(entryParentId, entryIndex).insertIntermediatePosition(intermediatePosition, index);
    }
    
    public void removeConveyorIntermediatePosition(int entryParentId, int entryIndex, int index) {
        getConveyor(entryParentId, entryIndex).removeIntermediatePosition(index);
    }
    
    public void moveConveyorIntermediatePosition(int entryParentId, int entryIndex,
            Coords intermediatePosition, int index) {
        getConveyor(entryParentId, entryIndex).moveIntermediatePosition(intermediatePosition, index);
    }
    // </editor-fold>
}
