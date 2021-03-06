/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import recyclapp.transport.*;

import recyclapp.transport.Coords;
import recyclapp.transport.ElementProperties;
import recyclapp.transport.EntryPointParameterGroup;

/**
 *
 * @author Martin Boisvert
 */
public final class DiagramModel implements java.io.Serializable{
    private static DiagramModel sInstance;
    
    private boolean aGridActive = false;
    private float aGridSpacing = 1.0F;
    private boolean aRecursionDetected = false;
    
    private final List<ElementModel> aElements = new ArrayList<>();
    private final List<EntryNodeModel> aRecursionNodes = new ArrayList<>();
    
    private DiagramModel() {}
    
    public static DiagramModel getInstance() {
        boolean wasNull = false;
        if (sInstance == null) {
            wasNull = true;
            sInstance = new DiagramModel();
        }
        if (wasNull) {
            initEntryNode();
        }
        return sInstance;
    }
    
    private static void initEntryNode() {
        // Pour la démo
        EntryPointModel entry = new EntryPointModel();
        entry.setSize(new Coords(1, 1));
        entry.setPosition(new Coords(5, 5));
        MaterialFlowTable entryMats = new MaterialFlowTable();
        entryMats.add(new MaterialFlow("P1", 1000));
        entryMats.add(new MaterialFlow("P2", 1000));
        entry.setParameters(new EntryPointParameterGroup(entryMats));
        sInstance.aElements.add(entry);
        
        ExitPointModel exit = new ExitPointModel();
        exit.setSize(new Coords(1, 1));
        exit.setPosition(new Coords(10, 5));
        sInstance.aElements.add(exit);
        
        entry.getExitNode(0).createLink(exit.getEntryNode(0));
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
        
        if (element instanceof EntryPointModel) {
            OverviewModel.getInstance().addEntryPoint((EntryPointModel) element);
        }
        else if (element instanceof ExitPointModel) {
            OverviewModel.getInstance().addExitPoint((ExitPointModel) element);
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
                break;
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
    
    public void removeElement(int id) {
        ElementModel element = getElementFromId(id);
        
        // Remove from the overview
        if (element instanceof EntryPointModel) {
            OverviewModel.getInstance().removeEntryPoint((EntryPointModel)element);
        }
        else if (element instanceof ExitPointModel) {
            OverviewModel.getInstance().removeExitPoint((ExitPointModel)element);
        }
        
        // Remove links, this removing accessible references to it
        element.removeAllLinks();
        
        // Remove from ourselves
        aElements.remove(element);
    }
    
    public void deserialiseDiagram(String chemin)
    {
        String chaine = null;
        DiagramModel e = null;
        if(chemin == null)
        {
            chaine = "C:/Windows/Temp/recyclapp" +HistoryElement.getCounter() +".rec";
        }
        else{
            chaine = chemin;
        }
      try
      {
         FileInputStream fileIn = new FileInputStream(chaine);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e = (DiagramModel) in.readObject();
         in.close();
         fileIn.close();
         System.out.println("deser data  in "+ chaine);
         sInstance = e;
         
         
         
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("Recyclapp class not found");
         c.printStackTrace();
         return;
      }
    }
}
