/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import recyclapp.model.Controller;

/**
 *
 * @author Martin Boisvert
 */
public class ToolBoxView extends JPanel {
    private static ToolBoxView aInstance;
    
    public ToolBoxView() {
        List<String> elementNames = Controller.getInstance().getToolBoxElements();
        setLayout(new GridLayout(elementNames.size(), 1));
        for (int i = 0; i < elementNames.size(); i++) {
            JToggleButton newButton = new ToolBoxButton(i, elementNames.get(i));
            add(newButton);
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elementPressed(e);
                }
            });
        }
    }
    
    public static ToolBoxView getInstance() {
        if (aInstance == null) {
            aInstance = new ToolBoxView();
        }
        return aInstance;
    }
    
    public void deselect() {
        for (Component child : getComponents()) {
            if (child instanceof ToolBoxButton) {
                ToolBoxButton button = (ToolBoxButton)child;
                button.setSelected(false);
            }
        }
        Controller.getInstance().selectElement(-1);
    }
    
    private void elementPressed(ActionEvent e) {
        ToolBoxButton source = (ToolBoxButton)e.getSource();
        boolean reselect = source.isSelected();
        deselect();
        if (reselect) {
            source.setSelected(true);
            Controller.getInstance().selectElement(source.aIndex);
        }
    }
}

class ToolBoxButton extends JToggleButton {
    public int aIndex;
    ToolBoxButton(int index, String name) {
        super(name);
        aIndex = index;
    }
}