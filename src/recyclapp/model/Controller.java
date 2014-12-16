/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

//<<<<<<< HEAD
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
//>>>>>>> origin/master
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.security.auth.DestroyFailedException;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import recyclapp.transport.*;
import recyclapp.view.*;

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
    
    public void exportImage()
    {
        BufferedImage bi = DiagramView.getInstance().createImage();
        
        String chemin =null;
        FilterFileModel filtre = new FilterFileModel(new String[]{"PNG"},"Image PNG (*.png)");
        JFileChooser choix = new JFileChooser();
        choix.addChoosableFileFilter(filtre);
        int retour = choix.showSaveDialog(null);
    
        if(retour==JFileChooser.APPROVE_OPTION){
        // un fichier a été choisi (sortie par OK)
        // nom du fichier  choisi 
            choix.getSelectedFile().getName();
        // chemin absolu du fichier choisi
            chemin = choix.getSelectedFile().getAbsolutePath();
            chemin = chemin.replace('\\', '/');
            chemin = chemin + ".png";
            
            try
            {
                ImageIO.write(bi, "png", new File(chemin));
                
                //try {
                    
                    
                    Desktop.getDesktop().open(new File(chemin));
                    
                    //} 
                //catch{}
                // (DesktopException e2) {
                    //Problème lors du lancement du programme
                    //e2.printStackTrace();
               // }
                
            } catch (IOException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }  

        }  
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Menu">
    public void save()
    {
        
    }
 
    public String saveAs()
    {
        String chemin =null;
        FilterFileModel filtre = new FilterFileModel(new String[]{"rec"},"les fichiers Recyclapp (*.rec)");
        JFileChooser choix = new JFileChooser();
        choix.addChoosableFileFilter(filtre);
        int retour = choix.showSaveDialog(null);
    
        if(retour==JFileChooser.APPROVE_OPTION){
        // un fichier a été choisi (sortie par OK)
        // nom du fichier  choisi 
            choix.getSelectedFile().getName();
        // chemin absolu du fichier choisi
            chemin = choix.getSelectedFile().getAbsolutePath();
            chemin = chemin.replace('\\', '/');
            chemin = chemin + ".rec";
            
            Controller.getInstance().serialize(chemin);
        }
        
        return chemin;
    }
    
    public void load()
    {
        String chemin = null;
        
        FilterFileModel filtre = new FilterFileModel(new String[]{"rec"},"les fichiers Recyclapp (*.rec)");
        
        JFileChooser choix = new JFileChooser();
        choix.addChoosableFileFilter(filtre);
        int retour = choix.showOpenDialog(null);
    
        if(retour==JFileChooser.APPROVE_OPTION){
        // un fichier a été choisi (sortie par OK)
        // nom du fichier  choisi 
            choix.getSelectedFile().getName();
        // chemin absolu du fichier choisi
            chemin = choix.getSelectedFile().getAbsolutePath();
            
            chemin = chemin.replace('\\', '/');
           
            HistoryElement.getInstance().deserializeDiag(chemin);
            HistoryElement.setCounter(0);
            HistoryElement.setMax(0);
        }
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
            HistoryElement.getInstance().deserializeDiag(null);
        }
    }
    
    public void redo()
    {
        if ( HistoryElement.getCounter() < HistoryElement.getMax())
        {
            HistoryElement.setCounter(HistoryElement.getCounter()+1);
            HistoryElement.getInstance().deserializeDiag(null);
        }
    }
    
    public void serialize(String chemin)
    {
        HistoryElement.getInstance().serialiseDiagram(DiagramModel.getInstance(),chemin);
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
    
    // <editor-fold defaultstate="collapsed" desc="Elements">
    private ElementModel getElement(int id) {
        return DiagramModel.getInstance().getElementFromId(id);
    }
    
    public ElementProperties getElementProperties(int id) {
        return getElement(id).toProperties();
    }
    
    public void setElementProperties(ElementProperties properties) {
        getElement(properties.aId).fromProperties(properties);
        serialize(null);
    }
    
    public ParameterGroup getParameters(int id) {
        return getElement(id).getParameters();
    }
    public void setParameters(int id, ParameterGroup parameters) {
        getElement(id).setParameters(parameters);
        serialize(null);
    }
    
    public void setElementPosition(int id, Coords position) {
        getElement(id).setPosition(position);
        serialize(null);
    }
    
    public void setElementSize(int id, Coords size) {
        getElement(id).setSize(size);
        serialize(null);
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
        serialize(null);
    }
    public void addExitNode(int id) {
        getElement(id).canAddExitNode();
        serialize(null);
    }
    public void addEntryNode(int id, String name) {
        getElement(id).addEntryNode().setName(name);
        serialize(null);
    }
    public void addExitNode(int id, String name) {
        getElement(id).addExitNode().setName(name);
        serialize(null);
    }
    
    public void removeEntryNode(int id, int index) {
        getElement(id).removeEntryNode(index);
        serialize(null);
    }
    public void removeExitNode(int id, int index) {
        getElement(id).removeExitNode(index);
        serialize(null);
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
    
    public String getElementName(int id) {
        return getElement(id).getName();
    }
    
    public String getElementType(int id) {
        return getElement(id).getType();
    }
    
    public void removeElement(int id) {
        DiagramModel.getInstance().removeElement(id);
        serialize(null);
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
    
    public String getEntryNodeName(int parentId, int index) {
        return getEntryNode(parentId, index).getName();
    }
    public String getExitNodeName(int parentId, int index) {
        return getExitNode(parentId, index).getName();
    }
    
    public void setEntryNodeName(int parentId, int index, String name) {
        getEntryNode(parentId, index).setName(name);
        serialize(null);
    }
    public void setExitNodeName(int parentId, int index, String name) {
        getExitNode(parentId, index).setName(name);
        serialize(null);
    }
    
    public void setEntryNodeAngle(int parentId, int index, int angle) {
        getEntryNode(parentId, index).setAngle(angle);
        serialize(null);
    }
    public void setExitNodeAngle(int parentId, int index, int angle) {
        getExitNode(parentId, index).setAngle(angle);
        serialize(null);
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
        serialize(null);
    }
    
    public void removeConveyor(int entryParentId, int entryIndex) {
        getEntryNode(entryParentId, entryIndex).removeLink();
        serialize(null);
    }
    
    public List<Coords> getConveyorIntermediatePositions(int entryParentId, int entryIndex) {
        return getConveyor(entryParentId, entryIndex).getIntermediatePositions();
    }
    
    public void insertConveyorIntermediatePosition(int entryParentId, int entryIndex,
            Coords intermediatePosition, int index, boolean undoable) {
        getConveyor(entryParentId, entryIndex).insertIntermediatePosition(intermediatePosition, index);
        if (undoable) {
            serialize(null);
        }
    }
    
    public void removeConveyorIntermediatePosition(int entryParentId, int entryIndex, int index) {
        getConveyor(entryParentId, entryIndex).removeIntermediatePosition(index);
        serialize(null);
    }
    
    public void moveConveyorIntermediatePosition(int entryParentId, int entryIndex,
            Coords intermediatePosition, int index) {
        getConveyor(entryParentId, entryIndex).moveIntermediatePosition(intermediatePosition, index);
        serialize(null);
    }
    
    public String getConveyorName(int entryParentId, int entryIndex) {
        return getConveyor(entryParentId, entryIndex).getName();
    }
    // </editor-fold>
}
