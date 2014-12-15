/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */
package recyclapp.view;
import java.awt.Color;
import java.awt.Component;
import recyclapp.transport.ElementProperties;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private final Controller controller = Controller.getInstance();
    private DefaultTableModel aDefTabMod;
    private JTable aTable;

    
    public ElementPropertiesView(ElementProperties properties, ElementView elemView){
        aElemView = elemView;
        aPropInitial = properties;
        aEntryNodeList = controller.getEntryNodeNames(aPropInitial.aId);
        aExitNodeList = controller.getExitNodeNames(aPropInitial.aId);
        initNodes();
        initComponents();
        initColorMap();
        aPropFinal = properties;
        NameTxtBox.setText(aPropInitial.aName);
        DescriptionTxtArea.setText(aPropInitial.aDescription);
        InputTxtBox.setText(Float.toString(aPropInitial.aMaxInput));
        ColorComboBox.setSelectedItem(aColorStringMap.get(aPropInitial.aColor));
        
    }
    
    private ParameterGroup createNewParamGroup(){
        if (aPropInitial.aParameters instanceof SortingStationParameterGroup){
            MaterialFlowMatrix sortingMatrix = new MaterialFlowMatrix();
            if (aDefTabMod.getRowCount() > 0){
                for(int i = 2; i < aDefTabMod.getColumnCount(); i++){
                    MaterialFlowTable tempTable = new MaterialFlowTable();
                    for (int j = 0; j < aDefTabMod.getRowCount(); j++){
                        MaterialFlow tempFlow = 
                                new MaterialFlow(aDefTabMod.getValueAt(j, 0).toString(),   Float.parseFloat(aDefTabMod.getValueAt(j, i).toString()));
                        tempTable.add(tempFlow);
                    }
                    sortingMatrix.add(tempTable);
                }
            }
            else{
                while (sortingMatrix.size() < aDefTabMod.getColumnCount()-2){
                    sortingMatrix.add(new MaterialFlowTable());
                }
            }
            SortingStationParameterGroup sortingParamGroup = new SortingStationParameterGroup(sortingMatrix, StationType.AUTOMATIC);
            return sortingParamGroup;
        }
        else if (aPropInitial.aParameters instanceof EntryPointParameterGroup){
            MaterialFlowTable flowTable = new MaterialFlowTable();
            if (aDefTabMod.getRowCount() > 0){
                for (int i = 0; i < aDefTabMod.getRowCount(); i++){
                    MaterialFlow tempFlow = 
                            new MaterialFlow(aDefTabMod.getValueAt(i, 0).toString(), Float.parseFloat(aDefTabMod.getValueAt(i, 1).toString()));
                    flowTable.add(tempFlow);
                }
            }
            return new EntryPointParameterGroup(flowTable);
            
        }
        else if (aPropInitial.aParameters instanceof TransformStationParameterGroup){
            MaterialFlowTable flowTable = new MaterialFlowTable();
            if (aDefTabMod.getRowCount() > 0){
                for (int i = 0; i < aDefTabMod.getRowCount(); i++){
                    MaterialFlow tempFlow =
                            new MaterialFlow(aDefTabMod.getValueAt(i, 0).toString(), Float.parseFloat(aDefTabMod.getValueAt(i, 1).toString()));
                    flowTable.add(tempFlow);
                }
            }
            return new TransformStationParameterGroup(((TransformStationParameterGroup)
                    aPropInitial.aParameters).aInputMaterial, flowTable, StationType.AUTOMATIC);
        }
        return null;
    }
    
    private JTable initTable(){
        
        if (aPropInitial.aParameters instanceof SortingStationParameterGroup){
            MaterialFlowTable tableFlow = controller.getEntryNodeThroughput(aPropInitial.aId, 0);
            MaterialFlowMatrix matrixFlow = ((SortingStationParameterGroup) aPropInitial.aParameters).aSortingMatrix;
            String[][] sortingRowData = new String[tableFlow.size()][aExitNodeList.size()+2];
            String[] sortingColumnNames = new String[aExitNodeList.size()+2];
            for (int i = 0; i < tableFlow.size(); i++){
                sortingRowData[i][0] = tableFlow.get(i).aName;
                sortingRowData[i][1] = Float.toString(tableFlow.get(i).aFlow);
                for (int j = 0; j < aExitNodeList.size(); j++){
                    sortingRowData[i][j+2] = Float.toString(0.0f);
                    for (int k = 0; k < matrixFlow.get(j).size(); k++){
                        if (matrixFlow.get(j).get(k).aName.equals(sortingRowData[i][0])){
                            sortingRowData[i][j+2] = Float.toString(matrixFlow.get(j).get(k).aFlow);
                            break;
                        }
                    }
                }
            }
            sortingColumnNames[0] = "Matériaux";
            sortingColumnNames[1] = "Débit (Kg/h)";
            for (int i = 2; i < sortingColumnNames.length; i++){
                sortingColumnNames[i] = controller.getExitNodeProperties(aPropInitial.aId, i-2).aName;
            }
            aDefTabMod = new DefaultTableModel(sortingRowData, sortingColumnNames);
        }
        
        else if (aPropInitial.aParameters instanceof TransformStationParameterGroup){
            MaterialFlowTable transTable = ((TransformStationParameterGroup) aPropInitial.aParameters).aTransformTable;
            String[][] transRowData = new String[transTable.size()][2];
            String[] transColumnNames = new String[2];
            for (int i = 0; i < transTable.size(); i++){
                transRowData[i][0] = transTable.get(i).aName;
                transRowData[i][1] = Float.toString(transTable.get(i).aFlow);
            }
            transColumnNames[0] = "Matière sortante";
            transColumnNames[1] = "% de transformation";
            aDefTabMod = new DefaultTableModel(transRowData, transColumnNames);
        }
        else if (aPropInitial.aParameters instanceof EntryPointParameterGroup){
            MaterialFlowTable flowTable = ((EntryPointParameterGroup) aPropInitial.aParameters).aEntryMaterials;
            String[][] entryRowData = new String[flowTable.size()][2];
            String[] entryColumnNames = new String[2];
            for (int i = 0; i < flowTable.size(); i++){
                entryRowData[i][0] = flowTable.get(i).aName;
                entryRowData[i][1] = Float.toString(flowTable.get(i).aFlow);
            }
            entryColumnNames[0] = "Matériaux";
            entryColumnNames[1] = "Débit (Kg/h)";
            aDefTabMod = new DefaultTableModel(entryRowData, entryColumnNames);
        }
       /* String[][] rowData = new String[1][2];
        rowData[0][0] = "Hi";
        rowData[0][1] = "allo";
        String[] columnNames = new String[2];
        columnNames[0] = "Hello";
        columnNames[1] = "bonj";*/
        
        aTable = new JTable(aDefTabMod){
            @Override
            public boolean isCellEditable(int row, int column){
                boolean editable = true;
                if (aPropInitial.aParameters instanceof SortingStationParameterGroup && (column == 0 || column == 1)){
                    editable = false;
                }
                return editable;
            }
        };
        return aTable;
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
        for(int i = 0; i < controller.getEntryNodeCount(aPropInitial.aId); i++){
            aEntryNodeListModel.addElement(aEntryNodeList.get(i));
        }
        for(int i = 0; i < controller.getExitNodeCount(aPropInitial.aId); i++){
            aExitNodeListModel.addElement(aExitNodeList.get(i));
        }    
    }
    private String[][] removeTableColumn(DefaultTableModel oldDtm, int selectedIndex){
        if (oldDtm.getRowCount() > 0){
        String[][] newRowVec = new String[oldDtm.getRowCount()][oldDtm.getColumnCount()-1];
        for (int i = 0; i < oldDtm.getRowCount(); i++){
            int k = 0;
            for (int j = 0; j < oldDtm.getColumnCount(); j++){
                if (j != selectedIndex + 2){
                    newRowVec[i][k] = oldDtm.getValueAt(i, j).toString();
                    k++;
                }
            }
        }
        return newRowVec;
        }
        else {
            return new String[0][oldDtm.getColumnCount()-1];
        }
        
    }
    
    private String[] removeTableColumnHeader(DefaultTableModel oldDtm, int selectedIndex){
        String[] newHeader = new String[oldDtm.getColumnCount()-1];
        int k = 0;
        for (int i = 0; i < oldDtm.getColumnCount(); i++)
            if (i != selectedIndex+2){
                newHeader[k] = oldDtm.getColumnName(i);
                k++;
            }
        return newHeader;
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
        sortingMatrixScrollPane = new javax.swing.JScrollPane(initTable());
        ajoutMatButton = new javax.swing.JButton();
        ajoutMatTextField = new javax.swing.JTextField();
        supMatButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

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

        if (controller.canAddEntryNode(aPropInitial.aId)){
            EntryNodeAddTxtBox.setEnabled(true);
        }
        else EntryNodeAddTxtBox.setEnabled(false);

        jLabel5.setText("Noeuds d'entrée:");

        EntryNodeAddBtn.setText("Ajout");
        EntryNodeAddBtn.setMaximumSize(new java.awt.Dimension(65, 23));
        EntryNodeAddBtn.setMinimumSize(new java.awt.Dimension(65, 23));
        EntryNodeAddBtn.setPreferredSize(new java.awt.Dimension(65, 23));
        if (controller.canAddEntryNode(aPropInitial.aId)){
            EntryNodeAddBtn.setEnabled(true);
        }
        else EntryNodeAddBtn.setEnabled(false);
        EntryNodeAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntryNodeAddBtnMouseClicked(evt);
            }
        });

        EntryNodeDelBtn.setText("Suppr.");
        if (controller.canRemoveEntryNode(aPropInitial.aId) && 
            controller.getEntryNodeCount(aPropInitial.aId) > 0){
            EntryNodeDelBtn.setEnabled(true);
        }
        else EntryNodeDelBtn.setEnabled(false);
        EntryNodeDelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntryNodeDelBtnMouseClicked(evt);
            }
        });

        if (controller.canAddExitNode(aPropInitial.aId)){
            ExitNodeAddTxtBox.setEnabled(true);
        }
        else ExitNodeAddTxtBox.setEnabled(false);

        jScrollPane3.setViewportView(ExitNodeList);

        jLabel6.setText("Noeuds de sortie:");

        ExitNodeAddBtn.setText("Ajout");
        ExitNodeAddBtn.setMaximumSize(new java.awt.Dimension(65, 23));
        ExitNodeAddBtn.setMinimumSize(new java.awt.Dimension(65, 23));
        ExitNodeAddBtn.setPreferredSize(new java.awt.Dimension(65, 23));
        if (controller.canAddExitNode(aPropInitial.aId)){
            ExitNodeAddBtn.setEnabled(true);
        }
        else ExitNodeAddBtn.setEnabled(false);
        ExitNodeAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitNodeAddBtnMouseClicked(evt);
            }
        });

        ExitNodeDelBtn.setText("Suppr.");
        if (controller.canRemoveExitNode(aPropInitial.aId) && 
            controller.getExitNodeCount(aPropInitial.aId) > 0){
            ExitNodeDelBtn.setEnabled(true);
        }
        else ExitNodeDelBtn.setEnabled(false);
        ExitNodeDelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitNodeDelBtnMouseClicked(evt);
            }
        });

        if (aPropInitial.aParameters == null){   
            sortingMatrixScrollPane.setVisible(false);
        }
        else {
            sortingMatrixScrollPane.setVisible(true);
        }

        ajoutMatButton.setText("Ajouter matériel");
        if (aPropInitial.aParameters instanceof EntryPointParameterGroup ||
            aPropInitial.aParameters instanceof TransformStationParameterGroup){
            ajoutMatButton.setVisible(true);
        }
        else {
            ajoutMatButton.setVisible(false);
        }
        ajoutMatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajoutMatButtonMouseClicked(evt);
            }
        });

        if (aPropInitial.aParameters instanceof EntryPointParameterGroup ||
            aPropInitial.aParameters instanceof TransformStationParameterGroup){
            ajoutMatTextField.setVisible(true);
        }
        else {
            ajoutMatTextField.setVisible(false);
        }

        supMatButton.setText("Supprimer matériel");
        if (aPropInitial.aParameters instanceof EntryPointParameterGroup ||
            aPropInitial.aParameters instanceof TransformStationParameterGroup){
            supMatButton.setVisible(true);
        }  
        else {
            supMatButton.setVisible(false);  
        }
        supMatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supMatButtonMouseClicked(evt);
            }
        });

        jLabel7.setText("jLabel7");
        if(aPropInitial.aParameters instanceof TransformStationParameterGroup){
            jLabel7.setVisible(true);
            if (((TransformStationParameterGroup) aPropInitial.aParameters).aInputMaterial.equals("")){
                jLabel7.setText("Matériel d'entrée : Aucun");
            }
            else{
                jLabel7.setText(String.format("Matériel d'entrée : %s", ((TransformStationParameterGroup) aPropInitial.aParameters).aInputMaterial));
            }
        }
        else{
            jLabel7.setVisible(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(sortingMatrixScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ajoutMatTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supMatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ajoutMatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(AcceptButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelButton))
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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(EntryNodeDelBtn)
                    .addComponent(ExitNodeDelBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sortingMatrixScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supMatButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcceptButton)
                    .addComponent(CancelButton)
                    .addComponent(ajoutMatButton)
                    .addComponent(ajoutMatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        aPropFinal.aParameters = createNewParamGroup();
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
            controller.addEntryNode(aPropInitial.aId, newNode);
            aElemView.addEntryNodeView();
            if (!controller.canAddEntryNode(aPropInitial.aId)){
                EntryNodeAddBtn.setEnabled(false);
                EntryNodeAddTxtBox.setEnabled(false);
            }
            if (controller.canRemoveEntryNode(aPropInitial.aId) && 
                    controller.getEntryNodeCount(aPropInitial.aId) > 0){
                EntryNodeDelBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_EntryNodeAddBtnMouseClicked

    private void ExitNodeAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitNodeAddBtnMouseClicked
        String newNode = ExitNodeAddTxtBox.getText();
        if (!newNode.isEmpty()){
            aExitNodeList.add(newNode);
            aExitNodeListModel.addElement(newNode);
            if (aPropInitial.aParameters instanceof SortingStationParameterGroup){
                aDefTabMod.addColumn(newNode);
                for (int i = 0; i < aDefTabMod.getRowCount(); i++){
                    aDefTabMod.setValueAt("0", i, aDefTabMod.getColumnCount() - 1);
                }
            }
            ExitNodeAddTxtBox.setText("");
            controller.addExitNode(aPropInitial.aId, newNode);
            aElemView.addExitNodeView();
            if (!controller.canAddExitNode(aPropInitial.aId)) {
                ExitNodeAddBtn.setEnabled(false);
                ExitNodeAddTxtBox.setEnabled(false);
            }
            if (controller.canRemoveExitNode(aPropInitial.aId) && 
                    controller.getExitNodeCount(aPropInitial.aId) > 0){
                ExitNodeDelBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_ExitNodeAddBtnMouseClicked

    private void EntryNodeDelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntryNodeDelBtnMouseClicked
        int selectedIndex = EntryNodeList.getSelectedIndex();
        if (selectedIndex != -1 && EntryNodeDelBtn.isEnabled()){
            aEntryNodeList.remove(selectedIndex);
            aEntryNodeListModel.remove(selectedIndex);
            controller.removeEntryNode(aPropInitial.aId, selectedIndex);
            aElemView.removeEntryNode(selectedIndex);
            if (controller.canAddEntryNode(aPropInitial.aId)){
                EntryNodeAddBtn.setEnabled(true);
                EntryNodeAddTxtBox.setEnabled(true);
            }
            if (controller.getEntryNodeCount(aPropInitial.aId) < 1 ||
                    !controller.canRemoveEntryNode(aPropInitial.aId)){
                EntryNodeDelBtn.setEnabled(false);          
            }
        }
    }//GEN-LAST:event_EntryNodeDelBtnMouseClicked

    private void ExitNodeDelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitNodeDelBtnMouseClicked
        int selectedIndex = ExitNodeList.getSelectedIndex();
        if (selectedIndex != -1 && ExitNodeDelBtn.isEnabled()){
            aExitNodeList.remove(selectedIndex);
            aExitNodeListModel.remove(selectedIndex);
            if (aPropInitial.aParameters instanceof SortingStationParameterGroup){               
                aDefTabMod.setDataVector(removeTableColumn(aDefTabMod, selectedIndex),removeTableColumnHeader(aDefTabMod, selectedIndex));
            }
            controller.removeExitNode(aPropInitial.aId, selectedIndex);
            aElemView.removeExitNode(selectedIndex);
            if (controller.canAddExitNode(aPropInitial.aId)){
                ExitNodeAddBtn.setEnabled(true);
                ExitNodeAddTxtBox.setEnabled(true);
            }
            if (controller.getExitNodeCount(aPropInitial.aId) < 1 || 
                    !controller.canRemoveExitNode(aPropInitial.aId)){
                ExitNodeDelBtn.setEnabled(false);
            
            }
        }
    }//GEN-LAST:event_ExitNodeDelBtnMouseClicked

    private void ajoutMatButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajoutMatButtonMouseClicked
        if (!ajoutMatTextField.getText().isEmpty()){
            if (aPropInitial.aParameters instanceof EntryPointParameterGroup ){
            ( (EntryPointParameterGroup) aPropInitial.aParameters).
                    aEntryMaterials.add(new MaterialFlow(ajoutMatTextField.getText(), 0));
            }
            if (aPropInitial.aParameters instanceof TransformStationParameterGroup){
                ((TransformStationParameterGroup) aPropInitial.aParameters).
                        aTransformTable.add(new MaterialFlow(ajoutMatTextField.getText(), 0));
            }
            String[] row = new String[2];
            row[0] = ajoutMatTextField.getText();
            row[1] = "0";
            aDefTabMod.addRow(row);
            ajoutMatTextField.setText("");
        }
    }//GEN-LAST:event_ajoutMatButtonMouseClicked

    private void supMatButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supMatButtonMouseClicked
       if (aTable.getSelectedRow() != -1){
           aDefTabMod.removeRow(aTable.getSelectedRow());
       }
    }//GEN-LAST:event_supMatButtonMouseClicked


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
    private javax.swing.JButton ajoutMatButton;
    private javax.swing.JTextField ajoutMatTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane sortingMatrixScrollPane;
    private javax.swing.JButton supMatButton;
    // End of variables declaration//GEN-END:variables
}
