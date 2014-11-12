/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.ArrayList;

import recyclapp.transport.Coords;

/**
 *
 * @author Martin Boisvert
 */
public final class DiagramModel {
    private final static DiagramModel INSTANCE = new DiagramModel();
    
    private final static float ZOOM_DELTA = 1.1F;
    
    private float aZoom = 1.0F;
    private final Coords aCenterPosition = new Coords(0, 0);
    private boolean aGridActive = false;
    private float aGridSpacing = 1.0F;
    
    private final ArrayList<ElementModel> aElements = new ArrayList<>();
    
    private DiagramModel() { }
    
    public DiagramModel getInstance() {
        return INSTANCE;
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
    
    public void createFromSelectedToolbox() {
        // get from Toolbox
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
