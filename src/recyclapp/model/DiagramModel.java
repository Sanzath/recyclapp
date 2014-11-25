/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import recyclapp.transport.Coords;
import recyclapp.transport.ElementProperties;

/**
 *
 * @author Martin Boisvert
 */
public final class DiagramModel {
    private static DiagramModel aInstance;
    
    private boolean aGridActive = false;
    private float aGridSpacing = 1.0F;
    private boolean aRecursionDetected = false;
    
    private final List<ElementModel> aElements = new ArrayList<>();
    private final List<EntryNodeModel> aRecursionNodes = new ArrayList<>();
    
    private DiagramModel() {
        EntryPointModel entry = new EntryPointModel();
        entry.setSize(new Coords(2, 2));
        entry.setPosition(new Coords(5.5F, 5.5F));
        entry.setColor(Color.GREEN);
        aElements.add(entry);
    }
    
    public static DiagramModel getInstance() {
        if (aInstance == null) {
            aInstance = new DiagramModel();
        }
        return aInstance;
    }
    
    public boolean getGridActive() {
        return aGridActive;
    }
    public void setGridActive(boolean active) {
        aGridActive = active;
    }
    
    public float getGridSpacing() {
        return aGridSpacing;
    }
    public void setGridSpacing(float gridSpacing) {
        aGridSpacing = gridSpacing;
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
    
    public int createFromSelectedToolbox() {
        ElementModel element = ToolBoxModel.getInstance().copySelectedElement();
        if (element != null) {
            addElement(element);
            return element.getId();
        }
        return -1;
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
        ElementModel found = null;
        for (ElementModel element : aElements) {
            if (id == element.getId()) {
                found = element;
            }
        }
        return found;
    }
    
    public List<ElementProperties> getAllElements() {
        List<ElementProperties> elements = new ArrayList<>();
        for (ElementModel model : aElements) {
            elements.add(model.toProperties());
        }
        return elements;
    }
}
