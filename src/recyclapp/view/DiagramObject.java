/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.view;

import javax.swing.JPanel;

/**
 *
 * @author Martin Boisvert
 */
public abstract class DiagramObject extends JPanel {
    private static DiagramObject sSelected = null;
    
    /**
     * Select specified object after deselecting the previously selected object.
     * @param obj 
     */
    public static void select(DiagramObject obj) {
        deselectCurrent();
        obj.select();
        sSelected = obj;
        SelectedPropertiesPanel.getInstance().setSelection(sSelected);
    }
    
    /**
     * Deselect currently selected object.
     */
    public static void deselectCurrent() {
        if (sSelected != null) {
            sSelected.deselect();
            sSelected = null;
            SelectedPropertiesPanel.getInstance().clearSelection();
        }
    }
    
    /**
     * Get whether the object obj is the currently selected object of its class.
     * @param obj
     * @return 
     */
    public static boolean isSelected(DiagramObject obj) {
        return sSelected == obj;
    }
    
    /**
     * Get currently selected DiagramObject.
     * @return 
     */
    public static DiagramObject getSelected() {
        return sSelected;
    }
    
    /**
     * Logic to apply once an object is selected.
     */
    public abstract void select();
    
    /**
     * Logic to apply once an object is deselected.
     */
    public abstract void deselect();
    
    /**
     * Logic to apply to update the object before repainting.
     */
    public abstract void updatePosition();
}
