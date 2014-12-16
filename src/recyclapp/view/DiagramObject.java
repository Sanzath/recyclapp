/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Martin Boisvert
 */
public abstract class DiagramObject extends JPanel implements KeyListener {
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
     * Logic to apply once an object is selected. Should register as keyListener.
     */
    public abstract void select();
    
    /**
     * Logic to apply once an object is deselected. Should deregister as keyListener.
     */
    public abstract void deselect();
    
    /**
     * Logic to apply to update the object before repainting.
     */
    public abstract void updatePosition();
    
    /**
     * Logic to apply to delete from both the model and the view. This calls
     * DiagramView.remove().
     */
    public abstract void deleteFromDiagram();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            deleteFromDiagram();
        }
        DiagramView.getInstance().repaint();
    }
}
