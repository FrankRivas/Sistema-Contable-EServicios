/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.CuentaJpaController;
import Controladores.DiarioControl;
import Modelos.Cuenta;
import Modelos.DetalleDiarioTableModel;
import Modelos.Detallediario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author kevin
 */
public class PrincipalContabilidad extends javax.swing.JFrame {
    public DetalleDiarioTableModel detalleDTModel=new DetalleDiarioTableModel();
    public static String codCuentaSeleccionada;
    

    /**
     * Creates new form PrincipalContabilidad
     */
    public PrincipalContabilidad() throws SQLException {
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ImageIcon img =new ImageIcon("../icon.png");
        
        this.btnAceptar.setIcon(img);
        this.btnAceptar.setSize(img.getIconWidth(),img.getIconHeight());
        this.btnAceptar.setBorderPainted(false);
        paintComponents(getGraphics());
        
        inicializarColumnas();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        txtFecha.setText(sdfDate.format(now));
        llenarComboBox();
        
    }
    
    public void llenarComboBox() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String sql="SELECT distinct * FROM periodocontable";
            Statement stmnt=conexion.createStatement();
            ResultSet rs = stmnt.executeQuery(sql);
            while(rs.next()){
            int x=  Integer.parseInt(rs.getObject(1).toString());
                System.out.println(x);
            jComboBox1.addItem(rs.getObject(3));
            jComboBox1.setSelectedIndex(x-1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PrincipalContabilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    private void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        for(int i=0; i<4;i++){
            TableColumn col=new TableColumn(i);
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Nombre");
                break;
                case 2:
                    col.setHeaderValue("Debe");  
                break;
                case 3:
                    col.setHeaderValue("Haber");  
            }
            tColumnModel.addColumn(col);
        }
        tablaDetalleDiario.setColumnModel(tColumnModel);    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleDiario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConcepto = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnAgregarTransac = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        catalogoCuentas = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        Consultas = new javax.swing.JMenu();
        cuentasContables = new javax.swing.JMenuItem();
        partidasContables = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenu();
        balanzaMensual = new javax.swing.JMenuItem();
        balanzaAcumulada = new javax.swing.JMenuItem();
        balanceGeneral = new javax.swing.JMenuItem();
        estadoResultados = new javax.swing.JMenuItem();
        libroDiario = new javax.swing.JMenuItem();
        libroDiarioMayor = new javax.swing.JMenuItem();
        libroMayor = new javax.swing.JMenuItem();
        rrhh = new javax.swing.JMenu();
        planillaSueldos = new javax.swing.JMenuItem();
        issss = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        Opciones = new javax.swing.JMenu();
        manual = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        tablaDetalleDiario.setModel(detalleDTModel);
        tablaDetalleDiario.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tablaDetalleDiarioInputMethodTextChanged(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDetalleDiario);

        jLabel2.setText("Fecha:");

        jLabel1.setText("Concepto:");

        txtConcepto.setColumns(20);
        txtConcepto.setRows(5);
        jScrollPane1.setViewportView(txtConcepto);

        btnAceptar.setText("Guardar Diario");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnAgregarTransac.setText("Agregar Transacción");
        btnAgregarTransac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTransacActionPerformed(evt);
            }
        });

        Archivo.setText("Archivo");

        catalogoCuentas.setText("Catálogo de Cuentas");
        catalogoCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalogoCuentasActionPerformed(evt);
            }
        });
        Archivo.add(catalogoCuentas);

        jMenuItem1.setText("Crear Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Archivo.add(jMenuItem1);

        jMenuItem10.setText("Gestionar Roles");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        Archivo.add(jMenuItem10);

        jMenuItem11.setText("Tipos de Cuentas");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        Archivo.add(jMenuItem11);

        jMenuBar1.add(Archivo);

        Consultas.setText("Consultas");

        cuentasContables.setText("Cuentas Contables");
        cuentasContables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentasContablesActionPerformed(evt);
            }
        });
        Consultas.add(cuentasContables);

        partidasContables.setText("Partidas Contables");
        partidasContables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partidasContablesActionPerformed(evt);
            }
        });
        Consultas.add(partidasContables);

        jMenuBar1.add(Consultas);

        Reportes.setText("Reportes");

        balanzaMensual.setText("Balanza de Comprobación mensual");
        balanzaMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanzaMensualActionPerformed(evt);
            }
        });
        Reportes.add(balanzaMensual);

        balanzaAcumulada.setText("Balanza de Comprobación acumulada");
        balanzaAcumulada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanzaAcumuladaActionPerformed(evt);
            }
        });
        Reportes.add(balanzaAcumulada);

        balanceGeneral.setText("Balance General");
        balanceGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceGeneralActionPerformed(evt);
            }
        });
        Reportes.add(balanceGeneral);

        estadoResultados.setText("Estados de Resultados");
        estadoResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoResultadosActionPerformed(evt);
            }
        });
        Reportes.add(estadoResultados);

        libroDiario.setText("Libro Diario");
        libroDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libroDiarioActionPerformed(evt);
            }
        });
        Reportes.add(libroDiario);

        libroDiarioMayor.setText("Libro Diario Mayor");
        libroDiarioMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libroDiarioMayorActionPerformed(evt);
            }
        });
        Reportes.add(libroDiarioMayor);

        libroMayor.setText("Libro Mayor");
        libroMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libroMayorActionPerformed(evt);
            }
        });
        Reportes.add(libroMayor);

        jMenuBar1.add(Reportes);

        rrhh.setText("RR.HH.");

        planillaSueldos.setText("Planilla de Sueldos");
        planillaSueldos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planillaSueldosActionPerformed(evt);
            }
        });
        rrhh.add(planillaSueldos);

        issss.setText("Prestaciones e Impuestos");
        issss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issssActionPerformed(evt);
            }
        });
        rrhh.add(issss);

        jMenuItem5.setText("Gestionar Empleados");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        rrhh.add(jMenuItem5);

        jMenuBar1.add(rrhh);

        jMenu1.setText("Contabilidad de Costos");

        jMenuItem7.setText("Bases y Centros");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("Prorratear");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Manejar Empresa");

        jMenuItem4.setText("Gestionar Areas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem3.setText("Gestionar Puestos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        Opciones.setText("Opciones");

        manual.setText("Manual de Usuario");
        Opciones.add(manual);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        Opciones.add(salir);

        jMenuBar1.add(Opciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarTransac))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(39, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarTransac)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(btnAceptar)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void libroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libroDiarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_libroDiarioActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if(detalleDTModel.listaDetalleDiario.size()!=0){
            if(DiarioControl.validarDetallesDiarios(detalleDTModel.listaDetalleDiario)){
                if(DiarioControl.validarPartidaDoble(detalleDTModel.listaDetalleDiario)){
                    if(!txtConcepto.getText().isEmpty()){
                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try{
                            Date fecha = format.parse(txtFecha.getText());
                            if(DiarioControl.nuevoDiario(detalleDTModel.listaDetalleDiario, fecha, txtConcepto.getText())){
                                JOptionPane.showMessageDialog(this, "Creación del Diario con éxito");
                                txtConcepto.setText("");
                                detalleDTModel.listaDetalleDiario.clear();
                                tablaDetalleDiario.updateUI();
                            }else{
                                JOptionPane.showMessageDialog(this, "Error en la creación del diario!");
                            }
                        }catch(Exception e){
                        JOptionPane.showMessageDialog(this, "Error en los datos de la fecha, recordar que el formato de la fecha es dd/mm/aaaa");
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Por favor especifique el Concepto del Diario, antes de Guardarlo.");
                    }
                
                }else{
                    JOptionPane.showMessageDialog(this, "No se cumple Partida Doble, revisar datos en transacciones.");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Error en las transacciones. Recuerde que no dejar el debe y el haber de alguna transaccion a 0.0");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "No hay transacciones a guardar en el Diario.");    
        }  
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void catalogoCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalogoCuentasActionPerformed
        // TODO add your handling code here:
        CatalogoCuentas catalogo = new CatalogoCuentas();
        catalogo.setVisible(true);
        catalogo.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_catalogoCuentasActionPerformed

    private void issssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issssActionPerformed
        // TODO add your handling code here:
        GestionarRetencionImpuesto imp = new GestionarRetencionImpuesto();
        imp.setVisible(true);
        imp.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_issssActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        PrincipalEmpleados emp = new PrincipalEmpleados();
        emp.setVisible(true);
        emp.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        GestionarAreas areas = new GestionarAreas();
        areas.setVisible(true);
        areas.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CrearUsuarios usuarios = new CrearUsuarios();
        usuarios.setVisible(true);
        usuarios.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        
        GestionarPuestos puesto = new GestionarPuestos();
        puesto.setVisible(true);
        puesto.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        Prorrateo pro = new Prorrateo();
        pro.setVisible(true);
        pro.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        BasesyCentros bc = new BasesyCentros();
        bc.setVisible(true);
        bc.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        GestionarRoles roles = new GestionarRoles();
        roles.setVisible(true);
        roles.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        GestionarTiposCuentas tipos = new GestionarTiposCuentas();
        tipos.setVisible(true);
        tipos.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void planillaSueldosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planillaSueldosActionPerformed
        // TODO add your handling code here:
        Planilla plan = new Planilla();
        plan.setVisible(true);
        plan.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_planillaSueldosActionPerformed

    private void btnAgregarTransacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTransacActionPerformed
        //String codCuenta=JOptionPane.showInputDialog(this, "Ingrese el Código de la Cuenta");
        codCuentaSeleccionada="";
        SeleccionarCuenta sel=new SeleccionarCuenta(this);
        sel.setVisible(true);
        sel.setLocationRelativeTo(null); 
    }//GEN-LAST:event_btnAgregarTransacActionPerformed

    private void tablaDetalleDiarioInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tablaDetalleDiarioInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDetalleDiarioInputMethodTextChanged

    private void partidasContablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partidasContablesActionPerformed
        AprobarPartida aprobarP=new AprobarPartida();
        aprobarP.setVisible(true);
        aprobarP.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_partidasContablesActionPerformed

    private void cuentasContablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentasContablesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuentasContablesActionPerformed

    private void balanzaMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanzaMensualActionPerformed
        // TODO add your handling code here:}
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String archivo="src\\BalanceCompMensual.jasper"; 
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
             Map parametro = new HashMap();
            parametro.put("PeriodoContable",jComboBox1.getSelectedIndex()+1);
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametro, conexion);
            JasperViewer viewReport= new JasperViewer(jprint, false);
            viewReport.setTitle("Balance de comprobacion "+ jComboBox1.getSelectedItem());
            viewReport.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_balanzaMensualActionPerformed

    private void balanzaAcumuladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanzaAcumuladaActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String archivo="src\\BalanceCompAcum.jasper"; 
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
            JasperPrint jprint= JasperFillManager.fillReport(reporte, null, conexion);
            JasperViewer viewReport= new JasperViewer(jprint, false);
            viewReport.setTitle("Balance de comprobacion ");
            viewReport.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_balanzaAcumuladaActionPerformed

    private void libroDiarioMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libroDiarioMayorActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String archivo="src\\BalanceCuentasSubcuentas.jasper"; 
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
            JasperPrint jprint= JasperFillManager.fillReport(reporte, null, conexion);
            JasperViewer viewReport= new JasperViewer(jprint, false);
            viewReport.setTitle("Balance de comprobacion ");
            viewReport.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_libroDiarioMayorActionPerformed

    private void libroMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libroMayorActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String archivo="src\\Libro Mayor.jasper"; 
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
            JasperPrint jprint= JasperFillManager.fillReport(reporte, null, conexion);
            JasperViewer viewReport= new JasperViewer(jprint, false);
            viewReport.setTitle("Balance de comprobacion ");
            viewReport.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_libroMayorActionPerformed

    private void balanceGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceGeneralActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String archivo="src\\BalanceGeneral.jasper"; 
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
            JasperPrint jprint= JasperFillManager.fillReport(reporte, null, conexion);
            JasperViewer viewReport= new JasperViewer(jprint, false);
            viewReport.setTitle("Balance de comprobacion ");
            viewReport.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_balanceGeneralActionPerformed

    private void estadoResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoResultadosActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contables","postgres","administrador");
            String archivo="src\\EstadoResultado.jasper"; 
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
            JasperPrint jprint= JasperFillManager.fillReport(reporte, null, conexion);
            JasperViewer viewReport= new JasperViewer(jprint, false);
            viewReport.setTitle("Balance de comprobacion ");
            viewReport.setVisible(true);
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_estadoResultadosActionPerformed
    
    public void agregarDetalle(){
        if(codCuentaSeleccionada!=""){
            try{
            CuentaJpaController cuentaControl=new CuentaJpaController(login.conexion);
            Cuenta cuenta=cuentaControl.findCuenta(codCuentaSeleccionada);
            cuenta.getCodcuenta();
            Detallediario detalleD=new Detallediario();
            detalleD.setCodcuenta(cuenta);
            detalleD.setDebe(BigDecimal.valueOf(0.0));
            detalleD.setHaber(BigDecimal.valueOf(0.0));
            //detalleDTModel.listaDetalleDiario.add(detalleD);
            detalleDTModel.addRow(detalleD);
            //tablaDetalleDiario.updateUI();       
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error en el codigo de la cuenta");
            }   
        }else{
            JOptionPane.showMessageDialog(this, "Operación Cancelada!");
        }
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
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalContabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalContabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalContabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalContabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PrincipalContabilidad().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PrincipalContabilidad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Archivo;
    private javax.swing.JMenu Consultas;
    private javax.swing.JMenu Opciones;
    private javax.swing.JMenu Reportes;
    private javax.swing.JMenuItem balanceGeneral;
    private javax.swing.JMenuItem balanzaAcumulada;
    private javax.swing.JMenuItem balanzaMensual;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregarTransac;
    private javax.swing.JMenuItem catalogoCuentas;
    private javax.swing.JMenuItem cuentasContables;
    private javax.swing.JMenuItem estadoResultados;
    private javax.swing.JMenuItem issss;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem libroDiario;
    private javax.swing.JMenuItem libroDiarioMayor;
    private javax.swing.JMenuItem libroMayor;
    private javax.swing.JMenuItem manual;
    private javax.swing.JMenuItem partidasContables;
    private javax.swing.JMenuItem planillaSueldos;
    private javax.swing.JMenu rrhh;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTable tablaDetalleDiario;
    private javax.swing.JTextArea txtConcepto;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
