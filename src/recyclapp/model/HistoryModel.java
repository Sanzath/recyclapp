/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

/**
 *
 * @author Martin Boisvert
 */
public class HistoryModel implements java.io.Serializable{
    private static HistoryModel sInstance;
    
    private HistoryModel() {}
    
    public static HistoryModel getInstance() {
        if (sInstance == null) {
            sInstance = new HistoryModel();
        }
        return sInstance;
    }
    
}
