/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.transport;

import java.awt.Color;

/**
 *
 * @author Martin Boisvert
 */
public final class ElementProperties  implements java.io.Serializable{
    public int aId;
    public String aName;
    public String aDescription;
    public Coords aPosition;
    public Coords aSize;
    public Color aColor;
    public float aMaxInput;
    public ParameterGroup aParameters;
}
