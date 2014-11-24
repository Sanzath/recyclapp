/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import recyclapp.transport.Coords;

/**
 *
 * @author Martin Boisvert
 */
public class Controller {
    // Singleton
    private static Controller aInstance;
    
    /*
    To get references, use:
    HistoryModel.getInstance()
    DiagramModel.getInstance()
    MaterialDictionaryModel.getInstance()
    ...
     */
    
    private Controller() {}
        
    public static Controller getInstance() {
        if (aInstance == null)
        {
            aInstance = new Controller();
        }
        return aInstance;
    }
    
    // Diagram
    public void zoomIn()
    {
        
    }
    
    public void zoomOut()
    {
        
    }
    
    public void toggleGrid(boolean active)
    {
        
    }
    
    public void setGridCenter(Coords center)
    {
        
    }
    
    public void setGridSpacing(float spacing)
    {
        
    }
    
    public void startMovingView()
    {
        
    }
    
    public void stopMovingView()
    {
        
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
    
    //
}
