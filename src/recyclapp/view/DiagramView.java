/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import recyclapp.model.Controller;
import recyclapp.transport.*;

/**
 *
 * @author Martin Boisvert / Clement Sanquer
 */

public final class DiagramView extends JPanel implements MouseMotionListener, MouseListener
{
    private static DiagramView sInstance;
    
    private static final int GRID_WIDTH = 70;
    private static final int GRID_HEIGHT = 50;
    
    private int aZoom = 50;
    private float aSpacing = 1;
    private boolean aGridActive = true;
    
    public DiagramView() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public static DiagramView getInstance() {
        if (sInstance == null) {
            sInstance = new DiagramView();
        }
        return sInstance;
    }
    
    public void reloadObjects() {
        // Teardown - remove all root references
        DiagramObject.deselectCurrent();
        removeAll();
        
        // Setup elements
        List<ElementProperties> allElements = Controller.getInstance().getAllElements();
        Map<Integer, ElementView> elementIdMap = new HashMap<>();
        for (ElementProperties properties : allElements) {
            ElementView elem = new ElementView(properties);
            add(elem);
            elementIdMap.put(properties.aId, elem);
        }
        // Setup conveyors
        List<ConveyorProperties> allConveyors = Controller.getInstance().getAllConveyors();
        for (ConveyorProperties properties : allConveyors) {
            NodeView entryNode = elementIdMap.get(properties.aEntryParentId).getEntryNodeView(properties.aEntryIndex);
            NodeView exitNode = elementIdMap.get(properties.aExitParentId).getExitNodeView(properties.aExitIndex);
            NodeView.addConveyorView(entryNode, exitNode);
        }
        repaint();
    }
 
    public void setPxPerMeter(int px) {
        aZoom = px;
        repaint();
    }
    
    public void setGridVisible(boolean active) {
        aGridActive = active;
        repaint();
    }
    
    public void setMagnetic(boolean active) {
        Controller.getInstance().setGridActive(active);
    }
    
    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g); 
        
        if(aGridActive == true){
            drawGrille(g);
        }
        
        for (Component child : getComponents()) {
            if (child instanceof DiagramObject) {
                ((DiagramObject)child).updatePosition();
            }
        }
    }
    
    public void drawGrille(Graphics g)
    {
        int i;
        g.setColor(Color.LIGHT_GRAY);
        for(i = 1; i < GRID_WIDTH; i++)    
        {
            g.drawLine((int) (i * aZoom * aSpacing), 0,
                    (int) (i * aZoom * aSpacing), (int) (GRID_HEIGHT * aZoom * aSpacing));
        }
        for(i = 1; i < GRID_HEIGHT; i++)    
        {
            g.drawLine(0, (int) (i * aZoom * aSpacing),
                    (int) (GRID_WIDTH * aZoom * aSpacing), (int) (i * aZoom * aSpacing));
        }
        
    }
    
    @Override
    public Component add(Component child) {
        super.add(child);
        //OverviewView.getInstance().update();
        return child;
    }
    
    public int metreToPixel(double a)
    {
        return (int)(a * aZoom);
    }
    public float pixelToMetre(int a)
    {
        return a / (float) aZoom;
    }
    
    public Point coordsToPoint(Coords coords) {
        int x = metreToPixel(coords.x);
        int y = metreToPixel(coords.y);
        return new Point(x, y);
    }
    
    public Coords pointToCoords(Point p) {
        float x = pixelToMetre(p.x);
        float y = pixelToMetre(p.y);
        return new Coords(x, y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //appeler la méthode de draw de station + créer la station + ouvrir fenètre édition station
            
        int id = Controller.getInstance().createElementFromToolBox();
        if (id != -1) {
            ElementProperties elem = Controller.getInstance().getElementProperties(id);
            elem.aPosition = pointToCoords(e.getPoint());
            Controller.getInstance().setElementPosition(id, elem.aPosition);
            ElementView view = new ElementView(elem);
            add(view);
            view.createPropertiesWindow(elem);
            repaint();
            ToolBoxView.getInstance().deselect();
        }
        else {
            DiagramObject.deselectCurrent();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
   
    public BufferedImage createImage() {

        int w = DiagramView.getInstance().getWidth();
        int h = DiagramView.getInstance().getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        DiagramView.getInstance().print(g);
        return bi;
    }
  }
