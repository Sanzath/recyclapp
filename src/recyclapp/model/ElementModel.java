/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.model;

import java.awt.Color;
import recyclapp.transport.Coords;
import recyclapp.transport.MaterialFlowMatrix;
import recyclapp.transport.ParameterGroup;
import recyclapp.transport.MaterialFlow;
import recyclapp.transport.MaterialFlowTable;

/**
 *
 * @author Martin Boisvert
 */
public abstract class ElementModel {
    private String _name;
    private String _description;
    private Float _maxInput;
    private Color _color;
    private Coords _coords;
    private Coords _size;
    
    public ElementModel()
    {
        _name = "no name";
        _description = "no description";
        _maxInput = 0.F;
        this._color = Color.BLACK;
        this._coords = new Coords();
        this._size = new Coords();

    }
      
    public void calculateExits()
    {
            
    }
    
    public int getMinEntryNodes()
    {
        return 0;
    }
    
    public int getMinExitNodes()
    {
        return 0;
    }
    
    public int getEntryNodesCount()
    {
        return 0;
    }
    
    public int getExitNodesCount()
    {
        return 0;
    }
    
    public void addEntryNode()
    {

    }
    
    public void addExitNode()
    {

    }
    
    public void removeEntryNode(int index)
    {

    }
    
    public void removeExitNode(int index)
    {

    }
    
    /*
    public ParameterGroup getParameters()
    {
        return 0;
    }
    */
    
    public void setParameters(ParameterGroup parameters)
    {
        
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _description
     */
    public String getDescription() {
        return _description;
    }

    /**
     * @param _description the _description to set
     */
    public void setDescription(String _description) {
        this._description = _description;
    }

    /**
     * @return the _maxInput
     */
    public Float getMaxInput() {
        return _maxInput;
    }

    /**
     * @param _maxInput the _maxInput to set
     */
    public void setMaxInput(Float _maxInput) {
        this._maxInput = _maxInput;
    }

    /**
     * @return the _color
     */
    public Color getColor() {
        return _color;
    }

    /**
     * @param _color the _color to set
     */
    public void setColor(Color _color) {
        this._color = _color;
    }

    /**
     * @return the _coords
     */
    public Coords getCoords() {
        return _coords;
    }

    /**
     * @param _coords the _coords to set
     */
    public void setCoords(Coords _coords) {
        this._coords = _coords;
    }

    /**
     * @return the _size
     */
    public Coords getSize() {
        return _size;
    }

    /**
     * @param _size the _size to set
     */
    public void setSize(Coords _size) {
        this._size = _size;
    }
}
