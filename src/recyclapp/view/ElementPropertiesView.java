/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */
package recyclapp.view;
import java.awt.Color;
import recyclapp.transport.ElementProperties;
import java.util.*;
import javax.swing.*;
import recyclapp.model.Controller;
import recyclapp.transport.*;
/**
 *
 * @author Gabriel Grenon <your.name at your.org>
 */
public class ElementPropertiesView extends javax.swing.JFrame {
    private final Map<String, Color> aColorMap = new HashMap<>();
    private final Map<Color, String> aColorStringMap = new HashMap<>();
    private final ElementProperties aPropInitial;
    private final ElementProperties aPropFinal;
    private final ElementView aElemView;
    private final DefaultListModel aEntryNodeListModel = new DefaultListModel();
    private final DefaultListModel aExitNodeListModel = new DefaultListModel();
    private List<String> aEntryNodeList;
    private List<String> aExitNodeList;
    //À enlever
    private List<JPanel> listeSorties = new ArrayList<>();

    
    public ElementPropertiesView(ElementProperties properties, ElementView elemView){
        aElemView = elemView;
        aPropInitial = properties;
        aEntryNodeList = Controller.getInstance().getEntryNodeNames(aPropInitial.aId);
        aExitNodeList = Controller.getInstance().getExitNodeNames(aPropInitial.aId);
        initComponents();
        initColorMap();
        initNodes();
        initListeSorties();
        updateMatrixVisibility();
        aPropFinal = properties;
        NameTxtBox.setText(aPropInitial.aName);
        DescriptionTxtArea.setText(aPropInitial.aDescription);
        InputTxtBox.setText(Float.toString(aPropInitial.aMaxInput));
        ColorComboBox.setSelectedItem(aColorStringMap.get(aPropInitial.aColor));
        
    }
    
    private void initColorMap(){
        aColorMap.put("Noir", Color.BLACK);
        aColorMap.put("Bleu", Color.BLUE);
        aColorMap.put("Rouge", Color.RED);
        aColorMap.put("Orange", Color.ORANGE);
        aColorMap.put("Vert", Color.GREEN);
        aColorMap.put("Rose", Color.PINK);
        aColorMap.put("Magenta", Color.MAGENTA);
        aColorStringMap.put(Color.BLACK, "Noir");
        aColorStringMap.put(Color.BLUE, "Bleu");
        aColorStringMap.put(Color.RED, "Rouge");
        aColorStringMap.put(Color.ORANGE, "Orange");
        aColorStringMap.put(Color.GREEN, "Vert");
        aColorStringMap.put(Color.PINK, "Rose");
        aColorStringMap.put(Color.MAGENTA, "Magenta");    
    }
    
    private void initNodes(){
        for(int i = 0; i < Controller.getInstance().getEntryNodeCount(aPropInitial.aId); i++){
            aEntryNodeListModel.addElement(aEntryNodeList.get(i));
        }
        for(int i = 0; i < Controller.getInstance().getExitNodeCount(aPropInitial.aId); i++){
            aExitNodeListModel.addElement(aExitNodeList.get(i));
        }    
    }
    private void initListeSorties(){
        if (aPropInitial.aParameters instanceof SortingStationParameterGroup){
            listeSorties.add(Sortie1Panel);
            listeSorties.add(Sortie2Panel);
            listeSorties.add(Sortie3Panel);
            listeSorties.add(Sortie4Panel);
            listeSorties.add(Sortie5Panel);
        }
        else RecuMatrixPanel.setVisible(false);
    }
    
    private void updateMatrixVisibility(){
        if (aPropInitial.aParameters instanceof SortingStationParameterGroup){
            for (int i = 0; i < listeSorties.size(); i++){
                if (i < aExitNodeList.size()){
                    listeSorties.get(i).setVisible(true);
                }
                else
                    listeSorties.get(i).setVisible(false);
            }
        }
    }
    
    private List<Float> getFlow(){
        List<Float> listeFlow = new ArrayList<>();
        listeFlow.add(Float.parseFloat(FlowMatrix00.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix01.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix10.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix11.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix20.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix21.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix30.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix31.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix40.getText()));
        listeFlow.add(Float.parseFloat(FlowMatrix41.getText()));
        return listeFlow;
    }
    private MaterialFlowMatrix getMatrix(){
        List<Float> listeFlow = getFlow();
        MaterialFlowMatrix flowMatrix = new MaterialFlowMatrix();
        for (int i = 0; i < aExitNodeList.size(); i++){
            MaterialFlowTable table = new MaterialFlowTable();
            for (int j = 0; j < 2; j++){
                table.add(new MaterialFlow(j, listeFlow.get(i+j)));
            }
            flowMatrix.add(table);
        }
        return flowMatrix;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NameTxtBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescriptionTxtArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        ColorComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        InputTxtBox = new javax.swing.JTextField();
        AcceptButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        EntryNodeList = new JList(aEntryNodeListModel);
        EntryNodeAddTxtBox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EntryNodeAddBtn = new javax.swing.JButton();
        EntryNodeDelBtn = new javax.swing.JButton();
        ExitNodeAddTxtBox = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        ExitNodeList = new JList(aExitNodeListModel);
        jLabel6 = new javax.swing.JLabel();
        ExitNodeAddBtn = new javax.swing.JButton();
        ExitNodeDelBtn = new javax.swing.JButton();
        RecuMatrixPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Sortie2Panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        FlowMatrix10 = new javax.swing.JTextField();
        FlowMatrix11 = new javax.swing.JTextField();
        Sortie3Panel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        FlowMatrix20 = new javax.swing.JTextField();
        FlowMatrix21 = new javax.swing.JTextField();
        Sortie1Panel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        FlowMatrix00 = new javax.swing.JTextField();
        FlowMatrix01 = new javax.swing.JTextField();
        Sortie4Panel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        FlowMatrix30 = new javax.swing.JTextField();
        FlowMatrix31 = new javax.swing.JTextField();
        Sortie5Panel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        FlowMatrix40 = new javax.swing.JTextField();
        FlowMatrix41 = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Édition de paramêtres");

        jLabel1.setText("Nom de l'élément:");

        NameTxtBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NameTxtBoxFocusLost(evt);
            }
        });

        jLabel2.setText("Description de l'élément:");

        DescriptionTxtArea.setColumns(20);
        DescriptionTxtArea.setRows(5);
        DescriptionTxtArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DescriptionTxtAreaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(DescriptionTxtArea);
        DescriptionTxtArea.getAccessibleContext().setAccessibleName("DescriptionTxtArea");

        jLabel3.setText("Couleur de l'élément:");

        ColorComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Noir", "Rouge", "Bleu", "Vert", "Magenta", "Violet", "Orange" }));
        ColorComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ColorComboBoxFocusLost(evt);
            }
        });

        jLabel4.setText("Débit d'entrée max. (kg/h):");

        InputTxtBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                InputTxtBoxFocusLost(evt);
            }
        });

        AcceptButton.setText("Accepter");
        AcceptButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AcceptButtonMouseClicked(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelButtonMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(EntryNodeList);

        jLabel5.setText("Noeuds d'entrée:");

        EntryNodeAddBtn.setText("Ajout");
        EntryNodeAddBtn.setMaximumSize(new java.awt.Dimension(65, 23));
        EntryNodeAddBtn.setMinimumSize(new java.awt.Dimension(65, 23));
        EntryNodeAddBtn.setPreferredSize(new java.awt.Dimension(65, 23));
        EntryNodeAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntryNodeAddBtnMouseClicked(evt);
            }
        });

        EntryNodeDelBtn.setText("Suppr.");
        EntryNodeDelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntryNodeDelBtnMouseClicked(evt);
            }
        });

        jScrollPane3.setViewportView(ExitNodeList);

        jLabel6.setText("Noeuds de sortie:");

        ExitNodeAddBtn.setText("Ajout");
        ExitNodeAddBtn.setMaximumSize(new java.awt.Dimension(65, 23));
        ExitNodeAddBtn.setMinimumSize(new java.awt.Dimension(65, 23));
        ExitNodeAddBtn.setPreferredSize(new java.awt.Dimension(65, 23));
        ExitNodeAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitNodeAddBtnMouseClicked(evt);
            }
        });

        ExitNodeDelBtn.setText("Suppr.");
        ExitNodeDelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitNodeDelBtnMouseClicked(evt);
            }
        });

        RecuMatrixPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Matrice de récupération (champs en %)");

        jLabel8.setText("P1");

        jLabel9.setText("P2");

        jLabel11.setText("Sortie2");

        FlowMatrix10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix10.setText("0");

        FlowMatrix11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix11.setText("0");

        javax.swing.GroupLayout Sortie2PanelLayout = new javax.swing.GroupLayout(Sortie2Panel);
        Sortie2Panel.setLayout(Sortie2PanelLayout);
        Sortie2PanelLayout.setHorizontalGroup(
            Sortie2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Sortie2PanelLayout.createSequentialGroup()
                .addGroup(Sortie2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FlowMatrix10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FlowMatrix11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Sortie2PanelLayout.setVerticalGroup(
            Sortie2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie2PanelLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(FlowMatrix11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel12.setText("Sortie3");

        FlowMatrix20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix20.setText("0");

        FlowMatrix21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix21.setText("0");

        javax.swing.GroupLayout Sortie3PanelLayout = new javax.swing.GroupLayout(Sortie3Panel);
        Sortie3Panel.setLayout(Sortie3PanelLayout);
        Sortie3PanelLayout.setHorizontalGroup(
            Sortie3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FlowMatrix20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(FlowMatrix21, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Sortie3PanelLayout.setVerticalGroup(
            Sortie3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie3PanelLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setText("Sortie1");

        FlowMatrix00.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix00.setText("0");

        FlowMatrix01.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix01.setText("0");

        javax.swing.GroupLayout Sortie1PanelLayout = new javax.swing.GroupLayout(Sortie1Panel);
        Sortie1Panel.setLayout(Sortie1PanelLayout);
        Sortie1PanelLayout.setHorizontalGroup(
            Sortie1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FlowMatrix00, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(FlowMatrix01, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Sortie1PanelLayout.setVerticalGroup(
            Sortie1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie1PanelLayout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel14.setText("Sortie4");

        FlowMatrix30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix30.setText("0");

        FlowMatrix31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix31.setText("0");

        javax.swing.GroupLayout Sortie4PanelLayout = new javax.swing.GroupLayout(Sortie4Panel);
        Sortie4Panel.setLayout(Sortie4PanelLayout);
        Sortie4PanelLayout.setHorizontalGroup(
            Sortie4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Sortie4PanelLayout.createSequentialGroup()
                .addGroup(Sortie4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FlowMatrix30, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FlowMatrix31, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Sortie4PanelLayout.setVerticalGroup(
            Sortie4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie4PanelLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(FlowMatrix31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel15.setText("Sortie5");

        FlowMatrix40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix40.setText("0");

        FlowMatrix41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FlowMatrix41.setText("0");

        javax.swing.GroupLayout Sortie5PanelLayout = new javax.swing.GroupLayout(Sortie5Panel);
        Sortie5Panel.setLayout(Sortie5PanelLayout);
        Sortie5PanelLayout.setHorizontalGroup(
            Sortie5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FlowMatrix40, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(FlowMatrix41, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Sortie5PanelLayout.setVerticalGroup(
            Sortie5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie5PanelLayout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlowMatrix41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout RecuMatrixPanelLayout = new javax.swing.GroupLayout(RecuMatrixPanel);
        RecuMatrixPanel.setLayout(RecuMatrixPanelLayout);
        RecuMatrixPanelLayout.setHorizontalGroup(
            RecuMatrixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecuMatrixPanelLayout.createSequentialGroup()
                .addGroup(RecuMatrixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(RecuMatrixPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(RecuMatrixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sortie1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sortie2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sortie3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sortie4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sortie5Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RecuMatrixPanelLayout.setVerticalGroup(
            RecuMatrixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RecuMatrixPanelLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RecuMatrixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RecuMatrixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RecuMatrixPanelLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9)
                            .addGap(5, 5, 5))
                        .addComponent(Sortie1Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Sortie2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sortie3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sortie4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sortie5Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RecuMatrixPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NameTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(ColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(InputTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(EntryNodeDelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ExitNodeDelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(EntryNodeAddTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(EntryNodeAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel5))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ExitNodeAddTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ExitNodeAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AcceptButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EntryNodeAddTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EntryNodeAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitNodeAddTxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitNodeAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(EntryNodeDelBtn))
                        .addGap(18, 18, 18)
                        .addComponent(RecuMatrixPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AcceptButton)
                            .addComponent(CancelButton)))
                    .addComponent(ExitNodeDelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("JLabel1");
        NameTxtBox.getAccessibleContext().setAccessibleName("NameTxtBox");
        ColorComboBox.getAccessibleContext().setAccessibleName("ColorComboBox");
        InputTxtBox.getAccessibleContext().setAccessibleName("QtyInputTxtBox");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameTxtBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameTxtBoxFocusLost
        aPropFinal.aName = NameTxtBox.getText();
    }//GEN-LAST:event_NameTxtBoxFocusLost

    private void ColorComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ColorComboBoxFocusLost
        aPropFinal.aColor = aColorMap.get(ColorComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_ColorComboBoxFocusLost

    private void InputTxtBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InputTxtBoxFocusLost
        aPropFinal.aMaxInput = Float.parseFloat(InputTxtBox.getText());
    }//GEN-LAST:event_InputTxtBoxFocusLost

    private void DescriptionTxtAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DescriptionTxtAreaFocusLost
        aPropFinal.aDescription = DescriptionTxtArea.getText();
    }//GEN-LAST:event_DescriptionTxtAreaFocusLost

    private void AcceptButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AcceptButtonMouseClicked
        AcceptButton.requestFocusInWindow();
        if (aPropInitial.aParameters instanceof SortingStationParameterGroup){
            SortingStationParameterGroup params = (SortingStationParameterGroup) aPropInitial.aParameters;
            aPropFinal.aParameters = new SortingStationParameterGroup(getMatrix(), params.aType);
        }
        aElemView.updateProperties(aPropFinal);
        this.dispose();
        
    }//GEN-LAST:event_AcceptButtonMouseClicked

    private void CancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_CancelButtonMouseClicked

    private void EntryNodeAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntryNodeAddBtnMouseClicked
        String newNode = EntryNodeAddTxtBox.getText();
        if (!newNode.isEmpty()){
            aEntryNodeList.add(newNode);
            aEntryNodeListModel.addElement(newNode);
            EntryNodeAddTxtBox.setText("");
        }
    }//GEN-LAST:event_EntryNodeAddBtnMouseClicked

    private void ExitNodeAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitNodeAddBtnMouseClicked
        String newNode = ExitNodeAddTxtBox.getText();
        if (!newNode.isEmpty()){
            aExitNodeList.add(newNode);
            aExitNodeListModel.addElement(newNode);
            ExitNodeAddTxtBox.setText("");
            updateMatrixVisibility();
        }
    }//GEN-LAST:event_ExitNodeAddBtnMouseClicked

    private void EntryNodeDelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntryNodeDelBtnMouseClicked
        int selectedIndex = EntryNodeList.getSelectedIndex();
        if (selectedIndex != -1){
            aEntryNodeList.remove(selectedIndex);
            aEntryNodeListModel.remove(selectedIndex);
        }
    }//GEN-LAST:event_EntryNodeDelBtnMouseClicked

    private void ExitNodeDelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitNodeDelBtnMouseClicked
        int selectedIndex = ExitNodeList.getSelectedIndex();
        if (selectedIndex != -1){
            aExitNodeList.remove(selectedIndex);
            aExitNodeListModel.remove(selectedIndex);
            updateMatrixVisibility();
        }
    }//GEN-LAST:event_ExitNodeDelBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AcceptButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JComboBox ColorComboBox;
    private javax.swing.JTextArea DescriptionTxtArea;
    private javax.swing.JButton EntryNodeAddBtn;
    private javax.swing.JTextField EntryNodeAddTxtBox;
    private javax.swing.JButton EntryNodeDelBtn;
    private javax.swing.JList EntryNodeList;
    private javax.swing.JButton ExitNodeAddBtn;
    private javax.swing.JTextField ExitNodeAddTxtBox;
    private javax.swing.JButton ExitNodeDelBtn;
    private javax.swing.JList ExitNodeList;
    private javax.swing.JTextField FlowMatrix00;
    private javax.swing.JTextField FlowMatrix01;
    private javax.swing.JTextField FlowMatrix10;
    private javax.swing.JTextField FlowMatrix11;
    private javax.swing.JTextField FlowMatrix20;
    private javax.swing.JTextField FlowMatrix21;
    private javax.swing.JTextField FlowMatrix30;
    private javax.swing.JTextField FlowMatrix31;
    private javax.swing.JTextField FlowMatrix40;
    private javax.swing.JTextField FlowMatrix41;
    private javax.swing.JTextField InputTxtBox;
    private javax.swing.JTextField NameTxtBox;
    private javax.swing.JPanel RecuMatrixPanel;
    private javax.swing.JPanel Sortie1Panel;
    private javax.swing.JPanel Sortie2Panel;
    private javax.swing.JPanel Sortie3Panel;
    private javax.swing.JPanel Sortie4Panel;
    private javax.swing.JPanel Sortie5Panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
