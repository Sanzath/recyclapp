/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

/**
 *
 * @author Martin Boisvert
 */
public class HistoryModel {
    private static HistoryModel aInstance;
    
    private HistoryModel() {}
    
    public static HistoryModel getInstance() {
        if (aInstance == null) {
            aInstance = new HistoryModel();
        }
        return aInstance;
    }
    
}
