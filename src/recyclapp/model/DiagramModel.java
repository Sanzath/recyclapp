/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;

import recyclapp.transport.Coords;

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
    
    private final List<ElementModel> aElements = new ArrayList<>();
    
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
}
