/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.JPanel;
import recyclapp.model.Controller;

/**
 *
 * @author Martin Boisvert
 */
public class ToolBoxView extends JPanel {
    private static ToolBoxView aInstance;
    
    private ToolBoxView() {
        Controller.getInstance().getToolBoxElements();
    }
    
    public ToolBoxView getInstance() {
        if (aInstance == null) {
            aInstance = new ToolBoxView();
        }
        return aInstance;
    }
}
