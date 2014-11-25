/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Martin Boisvert
 */
public final class ToolBoxModel {
    private static ToolBoxModel aInstance;
    
    private final List<ElementModel> aElements = new ArrayList<>();
    private final List<String> aElementStrings = new ArrayList<>();
    private int aSelectedIndex = -1;
    
    private ToolBoxModel() {
        aElements.add(new EntryPointModel());
        aElements.add(new ExitPointModel());
        aElements.add(new SortingStationModel());
        aElements.add(new TransformStationModel());
        aElements.add(new JunctionModel());
        
        aElementStrings.add("Entry Point");
        aElementStrings.add("Exit Point");
        aElementStrings.add("Sorting Station");
        aElementStrings.add("Transformation Station");
        aElementStrings.add("Junction");
    }
    
    public static ToolBoxModel getInstance() {
        if (aInstance == null) {
            aInstance = new ToolBoxModel();
        }
        return aInstance;
    }
    
    public List<String> getElements() {
        return aElementStrings;
    }
    
    public void selectElement(int index) {
        aSelectedIndex = index;
        if (aSelectedIndex < 0 || aSelectedIndex >= aElements.size()) {
            aSelectedIndex = -1;
        }
    }
    
    public void deselectElement() {
        aSelectedIndex = -1;
    }
    
    ElementModel copySelectedElement() {
        if (aSelectedIndex < 0) {
            return null;
        }
        return aElements.get(aSelectedIndex).copy();
    }
}

