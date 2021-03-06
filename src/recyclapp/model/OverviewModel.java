/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.util.List;
import java.util.ArrayList;

import recyclapp.transport.MaterialFlowMatrix;

/**
 *
 * @author Martin Boisvert
 */
public final class OverviewModel implements java.io.Serializable{
    private static OverviewModel sInstance;
    
    private final List<EntryPointModel> aEntryPoints = new ArrayList<>();
    private final List<ExitPointModel> aExitPoints = new ArrayList<>();
    
    private OverviewModel() {}
    
    public static OverviewModel getInstance() {
        if (sInstance == null) {
            sInstance = new OverviewModel();
        }
        return sInstance;
    }
    
    protected void addEntryPoint(EntryPointModel element) {
        aEntryPoints.add(element);
    }
    
    protected void removeEntryPoint(EntryPointModel element) {
        aEntryPoints.remove(element);
    }
    
    protected void addExitPoint(ExitPointModel element) {
        aExitPoints.add(element);
    }
    
    protected void removeExitPoint(ExitPointModel element) {
        aExitPoints.remove(element);
    }
    
    public MaterialFlowMatrix getEntryMaterials() {
        MaterialFlowMatrix entryMaterials = new MaterialFlowMatrix();
        
        for (EntryPointModel element : aEntryPoints) {
            // entryMaterials.add(element.getThroughput());
        }
        
        return entryMaterials;
    }
    
    public MaterialFlowMatrix getExitMaterials() {
        MaterialFlowMatrix exitMaterials = new MaterialFlowMatrix();
        
        for (ExitPointModel element : aExitPoints) {
            // exitMaterials.add(element.getThroughput());
        }
        
        return exitMaterials;
    }
    
    public List<String> getEntryNames() {
        List<String> names = new ArrayList<>();
        
        for (EntryPointModel element : aEntryPoints) {
            names.add(element.getName());
        }
        
        return names;
    }
    
    public List<EntryPointModel> getEntryPoints(){
        return aEntryPoints;
    }
    
    public List<ExitPointModel> getExitPoints(){
        return aExitPoints;
    }
    
    public List<String> getExitNames() {
        List<String> names = new ArrayList<>();
        
        for (ExitPointModel element : aExitPoints) {
            names.add(element.getName());
        }
        
        return names;
    }
}
