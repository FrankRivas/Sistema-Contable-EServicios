/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.CuentaJpaController;
import Modelos.Cuenta;
import Modelos.CuentaTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author kevin
 */
public class SeleccionarCuenta extends javax.swing.JFrame {
    private TableRowSorter trsFiltro;
    public CuentaTableModel cuentaTModel=new CuentaTableModel();
    private static PrincipalContabilidad principalC;
    

    /**
     * Creates new form SeleccionarCuenta
     */
    public SeleccionarCuenta(PrincipalContabilidad principalConta) {
        initComponents();
        consultaInicial();
        inicializarColumnas();
        this.principalC=principalConta;
    }
    private void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<2;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Nombre");
                
            }
            tColumnModel.addColumn(col);
        }
        tablaSeleccionarCuenta.setColumnModel(tColumnModel);
        
    }
    public void consultaInicial(){
        try{
            CuentaJpaController cuentaControl=new CuentaJpaController(login.conexion);
            List <Cuenta> listaCuenta=new ArrayList<Cuenta>(); 
            
            listaCuenta=cuentaControl.findCuentaEntities();
            for (Cuenta cuenta:listaCuenta){
                if(cuenta.getEstadocuenta()=='A') //Sólo cuentas activas
                    cuentaTModel.cuentas.add(cuenta);
            }
            tablaSeleccionarCuenta.repaint();

        }catch(Exception e){
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboxBuscar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSeleccionarCuenta = new javax.swing.JTable();
        txtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Buscar por:");

        cboxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código Cuenta", "Nombre Cuenta" }));
        cboxBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxBuscarActionPerformed(evt);
            }
        });

        txtBuscar.setBackground(java.awt.Color.white);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        tablaSeleccionarCuenta.setModel(cuentaTModel);
        tablaSeleccionarCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSeleccionarCuentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSeleccionarCuenta);

        txtCancelar.setText("Cancelar");
        txtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCancelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(txtCancelar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void filtro() {
        int columnaABuscar = 0;
        if (cboxBuscar.getSelectedItem() == "Código Cuenta") {
            columnaABuscar = 0;
        }
        if (cboxBuscar.getSelectedItem().toString() == "Nombre Cuenta") {
            columnaABuscar = 1;
        }
        
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), columnaABuscar));
    }
    private void cboxBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsFiltro = new TableRowSorter(tablaSeleccionarCuenta.getModel());
        tablaSeleccionarCuenta.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCancelarActionPerformed
        this.dispose();
        principalC.agregarDetalle();
    }//GEN-LAST:event_txtCancelarActionPerformed

    private void tablaSeleccionarCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSeleccionarCuentaMouseClicked
        int clics = evt.getClickCount();
        int row = tablaSeleccionarCuenta.rowAtPoint(evt.getPoint());
        if (clics == 2) {
            PrincipalContabilidad.codCuentaSeleccionada=tablaSeleccionarCuenta.getValueAt(row, 0).toString();
            this.dispose();
            principalC.agregarDetalle();
        }
        
    }//GEN-LAST:event_tablaSeleccionarCuentaMouseClicked

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
            java.util.logging.Logger.getLogger(SeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionarCuenta(principalC).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboxBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaSeleccionarCuenta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JButton txtCancelar;
    // End of variables declaration//GEN-END:variables
}
