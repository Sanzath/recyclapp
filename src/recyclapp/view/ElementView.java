/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquer * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import java.util.List;
import java.util.ArrayList;
        
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import recyclapp.transport.ElementProperties;
import recyclapp.model.Controller;
import recyclapp.transport.Coords;
/**
 *
 * @author Martin Boisvert
 */
public final class ElementView extends DiagramObject implements MouseListener, MouseMotionListener {
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 2);
    private static final Border UNSELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 1);
    private static final float MINIMUM_SIZE = 0.5F;
    
    private Point aStartingPosition;
    private boolean aMoved = false;
    
    private long aClickTime1;
    private final int aId;
    
    private Coords aPosition;
    private Coords aSize;
    
    private final JLabel aName;
    private final ElementCornerTab aTab;
    
    private final List<NodeView> aEntryNodes = new ArrayList<>();
    private final List<NodeView> aExitNodes = new ArrayList<>();
    
    public ElementView(ElementProperties properties){
        aId = properties.aId;
        aPosition = properties.aPosition;
        aSize = properties.aSize;
        
        setLayout(new GridBagLayout());
        
        aName = new JLabel(properties.aName);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        add(aName, c);
        
        c = new GridBagConstraints();
        c.weighty = 1;
        add(Box.createVerticalGlue(), c);
        
        aTab = new ElementCornerTab(this);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 2, 2);
        add(aTab, c);
        aTab.setVisible(false);
        
        updatePosition();
        setBackground(properties.aColor);
        setBorder(UNSELECTED_BORDER);
        
        for (int i = 0; i < Controller.getInstance().getEntryNodeCount(aId); ++i) {
            NodeView node = new NodeView(this, i, NodeView.ENTRY_NODE, Controller.getInstance().getEntryNodeProperties(aId, i));
            aEntryNodes.add(node);
            DiagramView.getInstance().add(node);
        }
        for (int i = 0; i < Controller.getInstance().getExitNodeCount(aId); ++i) {
            NodeView node = new NodeView(this, i, NodeView.EXIT_NODE, Controller.getInstance().getExitNodeProperties(aId, i));
            aExitNodes.add(node);
            DiagramView.getInstance().add(node);
        }
        
        addMouseListener(this);
    }
    
    public int getID() {
        return aId;
    }
    
    @Override
    public void deselect() {
        setBorder(UNSELECTED_BORDER);
        aTab.setVisible(false);
    }
    
    @Override
    public void select() {
        setBorder(SELECTED_BORDER);
        aTab.setVisible(true);
    }
    
    protected void updateProperties(ElementProperties properties) {
        setBackground(properties.aColor);
        aName.setText(properties.aName);
        Controller.getInstance().setElementProperties(properties);
    }
    
    @Override
    public void updatePosition() {
        setLocation(DiagramView.getInstance().coordsToPoint(aPosition));
        Point size = DiagramView.getInstance().coordsToPoint(aSize);
        setSize(size.x, size.y);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doLayout();
        
        for (NodeView node : aEntryNodes) {
            node.updatePosition();
        }
        for (NodeView node : aExitNodes) {
            node.updatePosition();
        }
    }
            
    protected void createPropertiesWindow(ElementProperties properties){
        ElementPropertiesView propertiesWindow = new ElementPropertiesView(properties, this);
        propertiesWindow.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
        if (e.getClickCount() % 2 == 1){
            aClickTime1 = System.currentTimeMillis();
        }
        else if (e.getClickCount() % 2 == 0 && System.currentTimeMillis() - aClickTime1 <= 500) {
            createPropertiesWindow(Controller.getInstance().getElementProperties(aId));
        }
        
        DiagramObject.select(this);
    }
     
    @Override
    public void mouseReleased(MouseEvent e) {
        aStartingPosition = null;
        if (aMoved) {
            // Only set position model-side once done dragging
            Controller.getInstance().setElementPosition(aId, aPosition);
            aMoved = false;
        }
        removeMouseMotionListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(SELECTED_BORDER);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!DiagramObject.isSelected(this)) {
            setBorder(UNSELECTED_BORDER);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        aStartingPosition = e.getPoint();
        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPosition = e.getPoint();
        Point newLocation = getLocation();
        newLocation.x += newPosition.x - aStartingPosition.x;
        newLocation.y += newPosition.y - aStartingPosition.y;
        
        
        aPosition = DiagramView.getInstance().pointToCoords(newLocation);
        if (Controller.getInstance().getGridActive()) {
            float gridSpacing = Controller.getInstance().getGridSpacing();
            aPosition.x = Math.round(aPosition.x / gridSpacing) * gridSpacing;
            aPosition.y = Math.round(aPosition.y / gridSpacing) * gridSpacing;
        }
        updatePosition();
        aMoved = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    protected void resize(Coords newSize) {
        aSize = newSize;
        
        if (Controller.getInstance().getGridActive()) {
            float gridSpacing = Controller.getInstance().getGridSpacing();
            aSize.x = Math.round(aSize.x / gridSpacing) * gridSpacing;
            aSize.y = Math.round(aSize.y / gridSpacing) * gridSpacing;
            
            if (aSize.x < gridSpacing) {
                aSize.x = gridSpacing;
            }
            if (aSize.y < gridSpacing) {
                aSize.y = gridSpacing;
            }
        }
        else {
            if (aSize.x < MINIMUM_SIZE) {
                aSize.x = MINIMUM_SIZE;
            }
            if (aSize.y < MINIMUM_SIZE) {
                aSize.y = MINIMUM_SIZE;
            }
        }
        
        updatePosition();
    }
    
    protected void saveResize() {
        Controller.getInstance().setElementSize(aId, aSize);
    }
    
    protected void removeEntryNode(int index) {
        // Teardown and remove node and associated conveyor; update indexes of
        // the nodes that come after
        NodeView nodeToRemove = aEntryNodes.get(index);
        ConveyorView conveyorToRemove = nodeToRemove.aConveyor;
        DiagramView.getInstance().remove(nodeToRemove);
        if (conveyorToRemove != null) {
            conveyorToRemove.aExit.aConveyor = null;
            DiagramView.getInstance().remove(conveyorToRemove);
        }
        aEntryNodes.remove(index);
        
        for (int i = index; i < aEntryNodes.size(); i++) {
            aEntryNodes.get(i).decrementIndex();
        }
        DiagramView.getInstance().repaint();
    }
    protected void removeExitNode(int index) {
        // Teardown and remove node and associated conveyor; update indexes of
        // the nodes that come after
        NodeView nodeToRemove = aExitNodes.get(index);
        ConveyorView conveyorToRemove = nodeToRemove.aConveyor;
        DiagramView.getInstance().remove(nodeToRemove);
        if (conveyorToRemove != null) {
            conveyorToRemove.aEntry.aConveyor = null;
            DiagramView.getInstance().remove(conveyorToRemove);
        }
        aExitNodes.remove(index);
        
        for (int i = index; i < aExitNodes.size(); i++) {
            aExitNodes.get(i).decrementIndex();
        }
        DiagramView.getInstance().repaint();
    }
    
    protected void addEntryNodeView() {
        int newIndex = aEntryNodes.size();
        NodeView node = new NodeView(this, newIndex, NodeView.ENTRY_NODE, Controller.getInstance().getEntryNodeProperties(aId, newIndex));
        aEntryNodes.add(node);
        DiagramView.getInstance().add(node);
        node.updatePosition();
    }
    
    protected void addExitNodeView() {
        int newIndex = aExitNodes.size();
        NodeView node = new NodeView(this, newIndex, NodeView.EXIT_NODE, Controller.getInstance().getExitNodeProperties(aId, newIndex));
        aExitNodes.add(node);
        DiagramView.getInstance().add(node);
        node.updatePosition();
    }
    
    protected NodeView getEntryNodeView(int index) {
        return aEntryNodes.get(index);
    }
    
    protected NodeView getExitNodeView(int index) {
        return aExitNodes.get(index);
    }
}

class ElementCornerTab extends JPanel implements MouseMotionListener, MouseListener {
    private static final int SIZE = 12; // Size in px of the draggable tab
    
    private final ElementView aParent;
    
    private Point aStartingPosition;
    private Dimension aStartingSize;
    
    public ElementCornerTab(ElementView parent) {
        aParent = parent;
        
        setOpaque(false);
        
        setSize(SIZE, SIZE);
        
        setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Dimension d = getSize();
        g.setColor(Color.BLACK);
        g.drawLine(0, d.height, d.width, 0);
        g.drawLine(d.width/2, d.height, d.width, d.height/2);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPosition = e.getLocationOnScreen();
        Point offset = new Point();
        
        offset.x = aStartingSize.width + newPosition.x - aStartingPosition.x;
        offset.y = aStartingSize.height + newPosition.y - aStartingPosition.y;
        
        aParent.resize(DiagramView.getInstance().pointToCoords(offset));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        aStartingSize = getParent().getSize();
        aStartingPosition = e.getLocationOnScreen();
        addMouseMotionListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        aStartingPosition = null;
        aStartingSize = null;
        aParent.saveResize();
        removeMouseMotionListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
