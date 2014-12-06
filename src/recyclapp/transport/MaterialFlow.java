/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

/**
 *
 * @author Martin Boisvert
 */
public class MaterialFlow implements java.io.Serializable {
    public int aId;
    public float aFlow;
    
    public MaterialFlow(int id, float flow)
    {
        aId = id;
        aFlow = flow;
    }
    
    public MaterialFlow(MaterialFlow other)
    {
        aId = other.aId;
        aFlow = other.aFlow;
    }
}
