/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;

import recyclapp.transport.Coords;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.ElementProperties;

/**
 *
 * @author Martin Boisvert
 */
public final class DiagramModel {
    private static DiagramModel aInstance;
    
    private final static float ZOOM_DELTA = 1.1F;
    
    private float aZoom = 1.0F;
    private Coords aCenterPosition = new Coords(0, 0);
    private boolean aGridActive = false;
    private float aGridSpacing = 1.0F;
    private boolean aRecursionDetected = false;
    
    private final List<ElementModel> aElements = new ArrayList<>();
    private final List<EntryNodeModel> aRecursionNodes = new ArrayList<>();
    
    private DiagramModel() {
        aElements.add(new EntryPointModel());
    }
    
    public static DiagramModel getInstance() {
        if (aInstance == null) {
            aInstance = new DiagramModel();
        }
        return aInstance;
    }
    
    public void zoomIn() {
        aZoom *= ZOOM_DELTA;
    }
    
    public void zoomOut() {
        aZoom /= ZOOM_DELTA;
    }
    
    public boolean getGridActive() {
        return aGridActive;
    }
    
    public void setGridActive(boolean active) {
        aGridActive = active;
    }
    
    public void saveDiagram(String filename) {
        // Save
    }
    
    public void loadDiagram(String filename) {
        clear();
        // Load
    }
    
    private void addElement(ElementModel element) {
        aElements.add(element);
        
        if (element.getClass() == EntryPointModel.class) {
            OverviewModel.getInstance().addEntryPoint((EntryPointModel) element);
        }
    }
    
    public void createFromSelectedToolbox() {
        ElementModel element = ToolBoxModel.getInstance().copySelectedElement();
        if (element != null) {
            addElement(element);
        }
    }
    
    public void createFromSelectedTemplate() {
        // get from TemplateContainer
    }
    
    public void selectAll() {
        
    }
    
    public void selectNone() {
        
    }
    
    public void clear() {
        
    }
    
    public void simulate() {
        
    }
    
    protected void resetRecursionCheck() {
        aRecursionNodes.clear();
        aRecursionDetected = false;
    }
    
    protected boolean checkRecursion(EntryNodeModel node) {
        if (aRecursionNodes.contains(node)) {
            aRecursionDetected = true;
        }
        aRecursionNodes.add(node);
        return aRecursionDetected;
    }
    
    public boolean checkRecursion() {
        return aRecursionDetected;
    }
    
    protected ElementModel getElementFromId(int id) {
        return aElements.get(aElements.indexOf(id));
    }
    
    public ParameterGroup getParameters(int id) {
        return getElementFromId(id).getParameters();
    }
    public void setParameters(int id, ParameterGroup parameters) {
        getElementFromId(id).setParameters(parameters);
    }
    
    public boolean canAddEntryNode(int id) {
        return getElementFromId(id).canAddEntryNode();
    }
    public boolean canAddExitNode(int id) {
        return getElementFromId(id).canAddExitNode();
    }
    public boolean canRemoveEntryNode(int id) {
        return getElementFromId(id).canRemoveEntryNode();
    }
    public boolean canRemoveExitNode(int id) {
        return getElementFromId(id).canRemoveExitNode();
    }
    
    public void addEntryNode(int id) {
        getElementFromId(id).addEntryNode();
    }
    public void addExitNode(int id) {
        getElementFromId(id).canAddExitNode();
    }
    public void removeEntryNode(int id, int index) {
        getElementFromId(id).removeEntryNode(index);
    }
    public void removeExitNode(int id, int index) {
        getElementFromId(id).removeExitNode(index);
    }
    
    public List<ElementProperties> getAllElements() {
        List<ElementProperties> elements = new ArrayList<>();
        for (ElementModel model : aElements) {
            elements.add(model.toProperties());
        }
        return elements;
    }
    
    //public ElementProperties getElement(int id) {
    //    return 
    //}
}
