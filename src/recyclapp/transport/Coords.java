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
public class Coords {
    public float x;
    public float y;
    
    public Coords()
    {
        x = 0;
        y = 0;
    }
    
    public Coords(float _x, float _y)
    {
        x = _x;
        y = _y;
    }
    
    public Coords(Coords other)
    {
        x = other.x;
        y = other.y;
    }
}
