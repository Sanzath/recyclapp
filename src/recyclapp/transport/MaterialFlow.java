/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

/**
 *
 * @author Martin Boisvert
 */
public class MaterialFlow implements java.io.Serializable {
    public String aName;
    public float aFlow;
    
    public MaterialFlow(String name, float flow)
    {
        aName = name;
        aFlow = flow;
    }
    
    public MaterialFlow(MaterialFlow other)
    {
        aName = other.aName;
        aFlow = other.aFlow;
    }
}
