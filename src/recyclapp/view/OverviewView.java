/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import recyclapp.model.Controller;
import recyclapp.model.EntryPointModel;
import recyclapp.model.ExitPointModel;
import recyclapp.model.OverviewModel;

/**
 *
 * @author Martin Boisvert
 */
public final class OverviewView extends JPanel {
    
    private static OverviewView oInstance;
    
    private JPanel entry = new JPanel();
    private JPanel exit = new JPanel();
    private JLabel entries = new JLabel();
    private JLabel exits = new JLabel();
    private List<EntryPointModel> entryPoints = new ArrayList();
    private List<ExitPointModel> exitPoints = new ArrayList();
    
    public static OverviewView getInstance() {
    if (oInstance == null) {
        oInstance = new OverviewView();
        }
    return oInstance;
    }
    
    public OverviewView(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.add(entries);
        this.add(entry);
        this.add(exits);
        this.add(exit);
        
        exit.setSize(196,150);
        entry.setSize(196,150);
        exits.setText("Exits");
        entries.setText("Entries");
        entry.setLayout(new BoxLayout(entry, BoxLayout.Y_AXIS));
        exit.setLayout(new BoxLayout(exit, BoxLayout.Y_AXIS));
    }
    
    public void updateView () {
        entryPoints = Controller.getInstance().getEntryPoints();
        String[] namesEntries = new String[entryPoints.size()];
        for (int i = 0; i <= entryPoints.size()-1; i++){
            namesEntries[i] = entryPoints.get(i).getName();
        }
        
        exitPoints = Controller.getInstance().getExitPoints();
        String[] namesExits = new String[exitPoints.size()];
        for (int i = 0; i <= exitPoints.size()-1; i++){
            namesExits[i] = exitPoints.get(i).getName();
        }
        
        entry.removeAll();
        exit.removeAll();
        
        for (int i = 0; i<=namesEntries.length-1;i++){
            JButton newButton = new JButton(namesEntries[i]);
            entry.add(newButton);
            newButton.setName(Integer.toString(entryPoints.get(i).getId()));
            newButton.addActionListener(this::buttonClicked);
        }
        
        for (int i = 0; i<=namesExits.length-1;i++){
            JButton newButton = new JButton(namesExits[i]);
            exit.add(newButton);
            newButton.setName(Integer.toString(exitPoints.get(i).getId()));
            newButton.addActionListener(this::buttonClicked);
        }
        
        
        
        revalidate();
    }
    
    private void buttonClicked(ActionEvent e) {/*
        String id = this.getName();
        int i = Integer.parseInt(id);
        */
    }
    
}

