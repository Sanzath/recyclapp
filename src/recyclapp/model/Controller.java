/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.*;

/**
 *
 * @author Martin Boisvert
 */
public class Controller {
    // Singleton
    private static Controller aInstance;
    
    private Controller() {}
        
    public static Controller getInstance() {
        if (aInstance == null)
        {
            aInstance = new Controller();
        }
        return aInstance;
    }
    
    // Diagram
    public void toggleGrid(boolean active)
    {
        
    }
    
    public void setGridCenter(Coords center)
    {
        
    }
    
    public void setGridSpacing(float spacing)
    {
        
    }
    
    public boolean checkRecursion() {
        return DiagramModel.getInstance().checkRecursion();
    }
    
    // Menu
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
    
    // History
    public String getOptions()
    {
        return "placeholder";
    }
    
    public void undo()
    {
        
    }
    
    public void redo()
    {
        
    }
    
    // ToolBox
    public void getElements()
    {
        
    }
    
    public void selectElement(int index)
    {
        ToolBoxModel.getInstance().selectElement(index);
    }
    
    public int createElementFromToolBox(){
        int id = DiagramModel.getInstance().createFromSelectedToolbox();
        return id;
    }
    
    public void deselectElement()
    {
        
    }
    
    // TransformDictionary
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
    
    // Overview
    public String[] getEntryPoints()
    {
        String materials[] = new String[1];
        materials[1] = "placeholder";
        
        return materials;
    }
    
    public String[] getExitPoints()
    {
        String materials[] = new String[1];
        materials[1] = "placeholder";
        
        return materials;
    }
    
    public void selectEntryPoint(int index)
    {
        
    }
    
    public void selectExitPoint(int index)
    {
        
    }
    
    // MaterialDictionary
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
    
    // TemplateContainer
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
    
    // Elements
    private ElementModel getElement(int id) {
        return DiagramModel.getInstance().getElementFromId(id);
    }
    
    public ElementProperties getElementProperties(int id) {
        return getElement(id).toProperties();
    }
    
    public void setElementProperties(ElementProperties properties) {
        getElement(properties.aId).fromProperties(properties);
    }
    
    public ParameterGroup getParameters(int id) {
        return getElement(id).getParameters();
    }
    public void setParameters(int id, ParameterGroup parameters) {
        getElement(id).setParameters(parameters);
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
    }
    public void addExitNode(int id) {
        getElement(id).canAddExitNode();
    }
    public void removeEntryNode(int id, int index) {
        getElement(id).removeEntryNode(index);
    }
    public void removeExitNode(int id, int index) {
        getElement(id).removeExitNode(index);
    }
    
    public int getEntryNodesCount(int id) {
        return getElement(id).getEntryNodesCount();
    }
    public int getExitNodesCount(int id) {
        return getElement(id).getExitNodesCount();
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
    
    // Node
    private NodeModel getEntryNode(int parentId, int index) {
        return getElement(parentId).getEntryNode(index);
    }
    private NodeModel getExitNode(int parentId, int index) {
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
    
    
}
