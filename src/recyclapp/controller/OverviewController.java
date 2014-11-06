/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.controller;

import recyclapp.model.OverviewModel;

/**
 *
 * @author Martin Boisvert
 */
public class OverviewController {
    
    private OverviewModel model;
    
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
    
}
