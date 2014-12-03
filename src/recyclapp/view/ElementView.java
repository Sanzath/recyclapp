/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
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
public final class ElementView extends JPanel implements MouseListener, MouseMotionListener {
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 2);
    private static final Border UNSELECTED_BORDER = BorderFactory.createLineBorder(Color.black, 1);
    private static final float MINIMUM_SIZE = 0.5F;
    
    private static ElementView sSelected = null;
    
    private Point aStartingPosition;
    
    private long aClickTime1;
    private final int aId;
    
    private Coords aPosition;
    private Coords aRSize;
    private Coords aSize;
    private boolean aPositionUpdated = false;
    
    private final JLabel aName;
    private final ElementCornerTab aTab;
    
    private final List<NodeView> aNodes = new ArrayList<>();
    
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
            aNodes.add(node);
            DiagramView.getInstance().add(node);
        }
        for (int i = 0; i < Controller.getInstance().getExitNodeCount(aId); ++i) {
            NodeView node = new NodeView(this, i, NodeView.EXIT_NODE, Controller.getInstance().getExitNodeProperties(aId, i));
            aNodes.add(node);
            DiagramView.getInstance().add(node);
        }
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public int getID() {
        return aId;
    }
    
    public static void deselectSelected() {
        if (sSelected != null) {
            sSelected.deselect();
        }
    }
    
    public void deselect() {
        sSelected = null;
        setBorder(UNSELECTED_BORDER);
        aTab.setVisible(false);
    }
    
    public void select() {
        deselectSelected();
        sSelected = this;
        setBorder(SELECTED_BORDER);
        aTab.setVisible(true);
    }
    
    protected void updateProperties(ElementProperties properties) {
        setBackground(properties.aColor);
        aName.setText(properties.aName);
        Controller.getInstance().setElementProperties(properties);
    }
    
    public void updatePosition() {
        aPositionUpdated = true;
        setLocation(DiagramView.getInstance().coordsToPoint(aPosition));
        Point size = DiagramView.getInstance().coordsToPoint(aSize);
        setSize(size.x, size.y);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (!aPositionUpdated) {
            updatePosition();
        }
        
        super.paintComponent(g);
        doLayout();
        
        for (NodeView node : aNodes) {
            node.updatePosition();
        }
        aPositionUpdated = false;
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
        
        select();
    }
     
    @Override
    public void mouseReleased(MouseEvent e) {
        aStartingPosition = null;
        // Only set position model-side once done dragging
        Controller.getInstance().setElementPosition(aId, aPosition);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(SELECTED_BORDER);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (sSelected != this) {
            setBorder(UNSELECTED_BORDER);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        aStartingPosition = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPosition = e.getPoint();
        Point newLocation = getLocation();
        newLocation.x += newPosition.x - aStartingPosition.x;
        newLocation.y += newPosition.y - aStartingPosition.y;
        
        aPosition = DiagramView.getInstance().pointToCoords(newLocation);
        updatePosition();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    protected void relativeResize(Coords offset) {
        if (aRSize == null) {
            aRSize = new Coords(aSize);
        }
        aRSize.x += offset.x;
        aRSize.y += offset.y;
        
        aSize = new Coords(aRSize);
        
        if (aSize.x < MINIMUM_SIZE) {
            aSize.x = MINIMUM_SIZE;
        }
        if (aSize.y < MINIMUM_SIZE) {
            aSize.y = MINIMUM_SIZE;
        }
        
        updatePosition();
    }
    
    protected void saveResize() {
        aRSize = null;
        Controller.getInstance().setElementSize(aId, aSize);
    }
    
}

class ElementCornerTab extends JPanel implements MouseMotionListener, MouseListener {
    private static final int SIZE = 12; // Size in px of the draggable tab
    
    private final ElementView aParent;
    
    private Point aLastPosition;
    
    public ElementCornerTab(ElementView parent) {
        aParent = parent;
        
        setOpaque(false);
        
        setSize(SIZE, SIZE);
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
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
        
        offset.x = newPosition.x - aLastPosition.x;
        offset.y = newPosition.y - aLastPosition.y;
        
        aLastPosition = newPosition;
        
        aParent.relativeResize(DiagramView.getInstance().pointToCoords(offset));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        aLastPosition = e.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        aLastPosition = null;
        aParent.saveResize();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
