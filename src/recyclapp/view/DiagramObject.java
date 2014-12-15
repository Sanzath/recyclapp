/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.view;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Martin Boisvert
 */
public interface DiagramObject {
    static Map<Class, DiagramObject> sSelected = new HashMap<>();
    
    /**
     * Select specified object after deselecting the previously selected object
     * of its class.
     * @param obj 
     */
    public static void select(DiagramObject obj) {
        deselectSelected(obj.getClass());
        
        obj.select();
        sSelected.put(obj.getClass(), obj);
    }
    
    /**
     * Deselect selected object of specified type.
     * @param objClass 
     */
    public static void deselectSelected(Class objClass) {
        if (sSelected.containsKey(objClass)) {
            sSelected.remove(objClass).deselect();
        }
    }
    
    /**
     * Deselect all Selectables of all types.
     */
    public static void deselectAll() {
        for (Map.Entry<Class, DiagramObject> e : sSelected.entrySet()) {
            e.getValue().deselect();
        }
        sSelected.clear();
    }
    
    /**
     * Get whether the object obj is the currently selected object of its class.
     * @param obj
     * @return 
     */
    public static boolean isSelected(DiagramObject obj) {
        return sSelected.get(obj.getClass()) == obj;
    }
    
    /**
     * Get Selectable object of specified type objClass.
     * @param objClass
     * @return 
     */
    public static DiagramObject getSelected(Class objClass) {
        return sSelected.get(objClass);
    }
    
    /**
     * Logic to apply once an object is selected.
     */
    public void select();
    
    /**
     * Logic to apply once an object is deselected.
     */
    public void deselect();
    
    /**
     * Logic to apply to update the object before repainting.
     */
    public void updatePosition();
    
    /**
     * Logic to apply before deleting this object.
     */
    public void tearDown();
}
