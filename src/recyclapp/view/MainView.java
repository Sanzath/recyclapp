/*
 * Recyclapp - Projet de session GLO-2004, A2014
 * Equipe Engrenages
 * Alexandre Poli * Clement Sanquier * Gabriel Grenon * Martin Boisvert
 */

package recyclapp.view;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author Sanquer
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    
    boolean aGrille;
    
    public MainView() {
        initComponents();
        buildTree();
        aGrille = ButtonGrille.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OutilsBar = new javax.swing.JToolBar();
        BouttonOutilsOuvir = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        ButtonOutilsSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        ButtonOutilsUndo = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        ButtonOutilsRedo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        ButtonOutilsStart = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        ButtonOutilsNouveau = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        ButtonOutilsZoom = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        ButtonGrille = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        ButtonOutilsExport = new javax.swing.JButton();
        SlideZoom = new javax.swing.JSlider();
        PanelPosition = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelToolBox = new javax.swing.JPanel();
        jScrollPanelTree = new javax.swing.JScrollPane();
        Tree = new javax.swing.JTree();
        PanelEntreeSortie = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextEntreeSortie = new javax.swing.JTextArea();
        PanelGraph = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Grille = new recyclapp.view.DiagramView();
        MenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));

        OutilsBar.setRollover(true);

        BouttonOutilsOuvir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/folder31.png"))); // NOI18N
        BouttonOutilsOuvir.setBorder(null);
        BouttonOutilsOuvir.setContentAreaFilled(false);
        BouttonOutilsOuvir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BouttonOutilsOuvir.setFocusable(false);
        BouttonOutilsOuvir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BouttonOutilsOuvir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(BouttonOutilsOuvir);
        OutilsBar.add(jSeparator5);

        ButtonOutilsSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/floppy1.png"))); // NOI18N
        ButtonOutilsSave.setBorder(null);
        ButtonOutilsSave.setContentAreaFilled(false);
        ButtonOutilsSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsSave.setFocusable(false);
        ButtonOutilsSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(ButtonOutilsSave);
        OutilsBar.add(jSeparator1);

        ButtonOutilsUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/undo16.png"))); // NOI18N
        ButtonOutilsUndo.setBorder(null);
        ButtonOutilsUndo.setContentAreaFilled(false);
        ButtonOutilsUndo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsUndo.setFocusable(false);
        ButtonOutilsUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(ButtonOutilsUndo);
        OutilsBar.add(jSeparator6);

        ButtonOutilsRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/redo13.png"))); // NOI18N
        ButtonOutilsRedo.setBorder(null);
        ButtonOutilsRedo.setContentAreaFilled(false);
        ButtonOutilsRedo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsRedo.setFocusable(false);
        ButtonOutilsRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(ButtonOutilsRedo);
        OutilsBar.add(jSeparator2);

        ButtonOutilsStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/play43.png"))); // NOI18N
        ButtonOutilsStart.setBorder(null);
        ButtonOutilsStart.setContentAreaFilled(false);
        ButtonOutilsStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsStart.setFocusable(false);
        ButtonOutilsStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsStart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ButtonOutilsStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOutilsStartActionPerformed(evt);
            }
        });
        OutilsBar.add(ButtonOutilsStart);
        OutilsBar.add(jSeparator3);

        ButtonOutilsNouveau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/add11.png"))); // NOI18N
        ButtonOutilsNouveau.setBorder(null);
        ButtonOutilsNouveau.setContentAreaFilled(false);
        ButtonOutilsNouveau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsNouveau.setFocusable(false);
        ButtonOutilsNouveau.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsNouveau.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(ButtonOutilsNouveau);
        OutilsBar.add(jSeparator7);

        ButtonOutilsZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/magnifier13.png"))); // NOI18N
        ButtonOutilsZoom.setBorder(null);
        ButtonOutilsZoom.setContentAreaFilled(false);
        ButtonOutilsZoom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsZoom.setFocusable(false);
        ButtonOutilsZoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsZoom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(ButtonOutilsZoom);
        OutilsBar.add(jSeparator8);

        ButtonGrille.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/table41.png"))); // NOI18N
        ButtonGrille.setSelected(true);
        ButtonGrille.setBorder(null);
        ButtonGrille.setFocusable(false);
        ButtonGrille.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonGrille.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ButtonGrille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonGrilleActionPerformed(evt);
            }
        });
        OutilsBar.add(ButtonGrille);
        OutilsBar.add(jSeparator4);

        ButtonOutilsExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recyclapp/view/img/export2.png"))); // NOI18N
        ButtonOutilsExport.setBorder(null);
        ButtonOutilsExport.setContentAreaFilled(false);
        ButtonOutilsExport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonOutilsExport.setFocusable(false);
        ButtonOutilsExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonOutilsExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OutilsBar.add(ButtonOutilsExport);

        SlideZoom.setMajorTickSpacing(1);
        SlideZoom.setMinimum(10);
        SlideZoom.setMinorTickSpacing(1);
        SlideZoom.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                SlideZoomMouseDragged(evt);
            }
        });
        OutilsBar.add(SlideZoom);

        PanelPosition.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Position : 253,25m");
        PanelPosition.add(jLabel1);

        PanelToolBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPanelTree.setViewportView(Tree);

        javax.swing.GroupLayout PanelToolBoxLayout = new javax.swing.GroupLayout(PanelToolBox);
        PanelToolBox.setLayout(PanelToolBoxLayout);
        PanelToolBoxLayout.setHorizontalGroup(
            PanelToolBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );
        PanelToolBoxLayout.setVerticalGroup(
            PanelToolBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanelTree, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        PanelEntreeSortie.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TextEntreeSortie.setEditable(false);
        TextEntreeSortie.setColumns(20);
        TextEntreeSortie.setRows(5);
        jScrollPane1.setViewportView(TextEntreeSortie);

        javax.swing.GroupLayout PanelEntreeSortieLayout = new javax.swing.GroupLayout(PanelEntreeSortie);
        PanelEntreeSortie.setLayout(PanelEntreeSortieLayout);
        PanelEntreeSortieLayout.setHorizontalGroup(
            PanelEntreeSortieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEntreeSortieLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelEntreeSortieLayout.setVerticalGroup(
            PanelEntreeSortieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        PanelGraph.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Grille.setMaximumSize(new java.awt.Dimension(50000, 50000));

        javax.swing.GroupLayout GrilleLayout = new javax.swing.GroupLayout(Grille);
        Grille.setLayout(GrilleLayout);
        GrilleLayout.setHorizontalGroup(
            GrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        GrilleLayout.setVerticalGroup(
            GrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(Grille);

        javax.swing.GroupLayout PanelGraphLayout = new javax.swing.GroupLayout(PanelGraph);
        PanelGraph.setLayout(PanelGraphLayout);
        PanelGraphLayout.setHorizontalGroup(
            PanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        PanelGraphLayout.setVerticalGroup(
            PanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jMenu1.setText("File");
        MenuBar.add(jMenu1);

        jMenu2.setText("Edit");
        MenuBar.add(jMenu2);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OutilsBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelToolBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelEntreeSortie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(OutilsBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelToolBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelEntreeSortie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonOutilsStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOutilsStartActionPerformed
        // TODO add your handling code here:
        TextEntreeSortie.setText("Entree = fsoiefsefh");
    }//GEN-LAST:event_ButtonOutilsStartActionPerformed

    private void ButtonGrilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonGrilleActionPerformed
        aGrille = ButtonGrille.isSelected();
        TextEntreeSortie.setText(Integer.toString(SlideZoom.getValue()));
        Grille = new recyclapp.view.DiagramView(SlideZoom.getValue(),aGrille);
        
        Grille.setMaximumSize(new java.awt.Dimension(70*SlideZoom.getValue(), 50*SlideZoom.getValue()));

        javax.swing.GroupLayout GrilleLayout = new javax.swing.GroupLayout(Grille);
        Grille.setLayout(GrilleLayout);
        GrilleLayout.setHorizontalGroup(
            GrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70*SlideZoom.getValue(), Short.MAX_VALUE)
        );
        GrilleLayout.setVerticalGroup(
            GrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50*SlideZoom.getValue(), Short.MAX_VALUE)
        );
        
        
        jScrollPane2.setViewportView(Grille);
        
    }//GEN-LAST:event_ButtonGrilleActionPerformed

    private void SlideZoomMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SlideZoomMouseDragged
        // TODO add your handling code here:
        TextEntreeSortie.setText(Integer.toString(SlideZoom.getValue()));
        Grille = new recyclapp.view.DiagramView(SlideZoom.getValue(),aGrille);
        
        Grille.setMaximumSize(new java.awt.Dimension(70*SlideZoom.getValue(), 50*SlideZoom.getValue()));

        javax.swing.GroupLayout GrilleLayout = new javax.swing.GroupLayout(Grille);
        Grille.setLayout(GrilleLayout);
        GrilleLayout.setHorizontalGroup(
            GrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70*SlideZoom.getValue(), Short.MAX_VALUE)
        );
        GrilleLayout.setVerticalGroup(
            GrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50*SlideZoom.getValue(), Short.MAX_VALUE)
        );
        
        
        jScrollPane2.setViewportView(Grille);
    }//GEN-LAST:event_SlideZoomMouseDragged

    private void buildTree(){
        //Création d'une racine
        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Element");
        DefaultTreeModel x = new DefaultTreeModel(racine);
        //Nous allons ajouter des des feuilles à notre racine
        
         DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Station");
         racine.add(rep); 
         DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Jonction");
         racine.add(rep2); 
         DefaultMutableTreeNode rep3 = new DefaultMutableTreeNode("Convoyeur");
         racine.add(rep3);
         
         DefaultMutableTreeNode rep4 = new DefaultMutableTreeNode("Entrée Usine");
         racine.add(rep4);
         
         DefaultMutableTreeNode rep5 = new DefaultMutableTreeNode("Sortie Usine");
         racine.add(rep5);

         Tree.setModel(x);
         Tree.setDragEnabled(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);  
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BouttonOutilsOuvir;
    private javax.swing.JToggleButton ButtonGrille;
    private javax.swing.JButton ButtonOutilsExport;
    private javax.swing.JButton ButtonOutilsNouveau;
    private javax.swing.JButton ButtonOutilsRedo;
    private javax.swing.JButton ButtonOutilsSave;
    private javax.swing.JButton ButtonOutilsStart;
    private javax.swing.JButton ButtonOutilsUndo;
    private javax.swing.JButton ButtonOutilsZoom;
    private recyclapp.view.DiagramView Grille;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JToolBar OutilsBar;
    private javax.swing.JPanel PanelEntreeSortie;
    private javax.swing.JPanel PanelGraph;
    private javax.swing.JPanel PanelPosition;
    private javax.swing.JPanel PanelToolBox;
    private javax.swing.JSlider SlideZoom;
    private javax.swing.JTextArea TextEntreeSortie;
    private javax.swing.JTree Tree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPanelTree;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    // End of variables declaration//GEN-END:variables
}
