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
    //À Enlever quand la matrice de récup va avoir de l'allure
    private void initListeSorties(){
        listeSorties.add(Sortie1Panel);
        listeSorties.add(Sortie2Panel);
        listeSorties.add(Sortie3Panel);
        listeSorties.add(Sortie4Panel);
        listeSorties.add(Sortie5Panel);
    }
    
    private void updateMatrixVisibility(){
        for (int i = 0; i < listeSorties.size(); i++){
            if (i < aExitNodeList.size()){
                listeSorties.get(i).setVisible(true);
            }
            else
                listeSorties.get(i).setVisible(false);
        }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Sortie2Panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        Sortie3Panel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        Sortie1Panel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        Sortie4Panel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        Sortie5Panel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Matrice de récupération (champs en %)");

        jLabel8.setText("P1");

        jLabel9.setText("P2");

        jLabel11.setText("Sortie2");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("0");

        javax.swing.GroupLayout Sortie2PanelLayout = new javax.swing.GroupLayout(Sortie2Panel);
        Sortie2Panel.setLayout(Sortie2PanelLayout);
        Sortie2PanelLayout.setHorizontalGroup(
            Sortie2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Sortie2PanelLayout.createSequentialGroup()
                .addGroup(Sortie2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Sortie2PanelLayout.setVerticalGroup(
            Sortie2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie2PanelLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel12.setText("Sortie3");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("0");

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("0");

        javax.swing.GroupLayout Sortie3PanelLayout = new javax.swing.GroupLayout(Sortie3Panel);
        Sortie3Panel.setLayout(Sortie3PanelLayout);
        Sortie3PanelLayout.setHorizontalGroup(
            Sortie3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Sortie3PanelLayout.setVerticalGroup(
            Sortie3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie3PanelLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setText("Sortie1");

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("0");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("0");

        javax.swing.GroupLayout Sortie1PanelLayout = new javax.swing.GroupLayout(Sortie1Panel);
        Sortie1Panel.setLayout(Sortie1PanelLayout);
        Sortie1PanelLayout.setHorizontalGroup(
            Sortie1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Sortie1PanelLayout.setVerticalGroup(
            Sortie1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie1PanelLayout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel14.setText("Sortie4");

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("0");

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("0");

        javax.swing.GroupLayout Sortie4PanelLayout = new javax.swing.GroupLayout(Sortie4Panel);
        Sortie4Panel.setLayout(Sortie4PanelLayout);
        Sortie4PanelLayout.setHorizontalGroup(
            Sortie4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Sortie4PanelLayout.createSequentialGroup()
                .addGroup(Sortie4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Sortie4PanelLayout.setVerticalGroup(
            Sortie4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie4PanelLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel15.setText("Sortie5");

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("0");

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setText("0");

        javax.swing.GroupLayout Sortie5PanelLayout = new javax.swing.GroupLayout(Sortie5Panel);
        Sortie5Panel.setLayout(Sortie5PanelLayout);
        Sortie5PanelLayout.setHorizontalGroup(
            Sortie5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Sortie5PanelLayout.setVerticalGroup(
            Sortie5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sortie5PanelLayout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JTextField InputTxtBox;
    private javax.swing.JTextField NameTxtBox;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
