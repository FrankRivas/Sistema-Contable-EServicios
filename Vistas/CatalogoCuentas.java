/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.BaseprorrateoJpaController;
import Controladores.CatalogocuentaJpaController;
import Controladores.CuentaJpaController;
import javax.swing.JFrame;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Controladores.TipocuentaJpaController;
import Modelos.Baseprorrateo;
import Modelos.Catalogocuenta;
import Modelos.Cuenta;
import Modelos.CuentaTableModel;
import Modelos.Tipocuenta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author kevin
 */
public class CatalogoCuentas extends javax.swing.JFrame {
    public CuentaTableModel cuentaTModel=new CuentaTableModel();
    /**
     * Creates new form CatalogoCuentas
     */
    public CatalogoCuentas() {
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        inicializarColumnas();
        cboxEstado.addItem("Activa");
        cboxEstado.addItem("Inactiva");
        //EntityManagerFactory conexion=Persistence.createEntityManagerFactory("EmpresaMultiServiciosContabilidadPU");
        TipocuentaJpaController tipoCuentaControl=new TipocuentaJpaController(login.conexion);
        List <Tipocuenta> listaTipoCuenta=new List<Tipocuenta>() {
            @Override
            public boolean removeIf(Predicate<? super Tipocuenta> filter) {
                return List.super.removeIf(filter); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Stream<Tipocuenta> stream() {
                return List.super.stream(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Stream<Tipocuenta> parallelStream() {
                return List.super.parallelStream(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Spliterator<Tipocuenta> spliterator() {
                return List.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void forEach(Consumer<? super Tipocuenta> action) {
                List.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEmpty() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Iterator<Tipocuenta> iterator() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object[] toArray() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <T> T[] toArray(T[] a) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean add(Tipocuenta e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean addAll(Collection<? extends Tipocuenta> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean addAll(int index, Collection<? extends Tipocuenta> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void replaceAll(UnaryOperator<Tipocuenta> operator) {
                List.super.replaceAll(operator); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void sort(Comparator<? super Tipocuenta> c) {
                List.super.sort(c); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Tipocuenta get(int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Tipocuenta set(int index, Tipocuenta element) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(int index, Tipocuenta element) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Tipocuenta remove(int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int indexOf(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int lastIndexOf(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ListIterator<Tipocuenta> listIterator() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ListIterator<Tipocuenta> listIterator(int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Tipocuenta> subList(int fromIndex, int toIndex) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        BaseprorrateoJpaController baseProrrateoControl=new BaseprorrateoJpaController(login.conexion);
        List <Baseprorrateo> listaBaseprorrateo=new List<Baseprorrateo>() {
            @Override
            public boolean removeIf(Predicate<? super Baseprorrateo> filter) {
                return List.super.removeIf(filter); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Stream<Baseprorrateo> stream() {
                return List.super.stream(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Stream<Baseprorrateo> parallelStream() {
                return List.super.parallelStream(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Spliterator<Baseprorrateo> spliterator() {
                return List.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void forEach(Consumer<? super Baseprorrateo> action) {
                List.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEmpty() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Iterator<Baseprorrateo> iterator() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object[] toArray() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <T> T[] toArray(T[] a) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean add(Baseprorrateo e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean addAll(Collection<? extends Baseprorrateo> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean addAll(int index, Collection<? extends Baseprorrateo> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void replaceAll(UnaryOperator<Baseprorrateo> operator) {
                List.super.replaceAll(operator); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void sort(Comparator<? super Baseprorrateo> c) {
                List.super.sort(c); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Baseprorrateo get(int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Baseprorrateo set(int index, Baseprorrateo element) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(int index, Baseprorrateo element) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Baseprorrateo remove(int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int indexOf(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int lastIndexOf(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ListIterator<Baseprorrateo> listIterator() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ListIterator<Baseprorrateo> listIterator(int index) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Baseprorrateo> subList(int fromIndex, int toIndex) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }; 
        
        listaTipoCuenta=tipoCuentaControl.findTipocuentaEntities();
        for (Tipocuenta tipo:listaTipoCuenta){
            cboxTipoCuenta.addItem(tipo.getIdtipocuenta() + ", "+tipo.getNomtipocuenta());  
        }
        
        listaBaseprorrateo=baseProrrateoControl.findBaseprorrateoEntities();
        for (Baseprorrateo base:listaBaseprorrateo){
            cboxBaseProrrateo.addItem(base.getIdbase() + ", " + base.getNombase());
        }
        consultaInicial();
    }
    
    private void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<5;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Nombre");
                break;
                case 2:
                    col.setHeaderValue("Tipo");
                break;
                case 3:
                    col.setHeaderValue("Estado");
                break;
                case 4:
                    col.setHeaderValue("Cuenta Padre");
            }
            tColumnModel.addColumn(col);
        }
        tablaCuentas.setColumnModel(tColumnModel);
        
    }
    
    private void consultaInicial(){
        try{
            CuentaJpaController cuentaControl=new CuentaJpaController(login.conexion);
            List <Cuenta> listaCuenta=new List<Cuenta>() {
                @Override
                public boolean removeIf(Predicate<? super Cuenta> filter) {
                    return List.super.removeIf(filter); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Stream<Cuenta> stream() {
                    return List.super.stream(); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Stream<Cuenta> parallelStream() {
                    return List.super.parallelStream(); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Spliterator<Cuenta> spliterator() {
                    return List.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void forEach(Consumer<? super Cuenta> action) {
                    List.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public int size() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean isEmpty() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean contains(Object o) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Iterator<Cuenta> iterator() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Object[] toArray() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean add(Cuenta e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean remove(Object o) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean addAll(Collection<? extends Cuenta> c) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean addAll(int index, Collection<? extends Cuenta> c) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void replaceAll(UnaryOperator<Cuenta> operator) {
                    List.super.replaceAll(operator); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void sort(Comparator<? super Cuenta> c) {
                    List.super.sort(c); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void clear() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Cuenta get(int index) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Cuenta set(int index, Cuenta element) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void add(int index, Cuenta element) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Cuenta remove(int index) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public int indexOf(Object o) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public int lastIndexOf(Object o) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public ListIterator<Cuenta> listIterator() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public ListIterator<Cuenta> listIterator(int index) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public List<Cuenta> subList(int fromIndex, int toIndex) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            
            listaCuenta=cuentaControl.findCuentaEntities();
            for (Cuenta cuenta:listaCuenta){
                cuentaTModel.cuentas.add(cuenta);
            }
            tablaCuentas.repaint();

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
        jLabel2 = new javax.swing.JLabel();
        txtBuscarCodCuenta = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNomCuenta = new javax.swing.JTextField();
        txtCodCuenta = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cboxEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtCuentaPadre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboxBaseProrrateo = new javax.swing.JComboBox<>();
        cboxTipoCuenta = new javax.swing.JComboBox<>();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Buscar Cuenta:");

        jLabel2.setText("Código de la Cuenta:");

        txtBuscarCodCuenta.setBackground(java.awt.Color.white);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(19, 19, 19)
                .addComponent(txtBuscarCodCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarCodCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Crear Cuenta");

        jLabel5.setText("Código de la Cuenta:");

        jLabel6.setText("Nombre de la Cuenta:");

        jLabel7.setText("Tipo de Cuenta: ");

        txtNomCuenta.setBackground(java.awt.Color.white);
        txtNomCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomCuentaActionPerformed(evt);
            }
        });

        txtCodCuenta.setBackground(java.awt.Color.white);
        txtCodCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodCuentaActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel9.setText("Estado:");

        cboxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cboxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxEstadoActionPerformed(evt);
            }
        });

        jLabel8.setText("Cuenta Padre:");

        txtCuentaPadre.setBackground(java.awt.Color.white);

        jLabel10.setText("Base Prorrateo");

        cboxBaseProrrateo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        cboxTipoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cboxTipoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxTipoCuentaActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(btnGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegresar))
                            .addComponent(txtNomCuenta)
                            .addComponent(cboxBaseProrrateo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCuentaPadre)
                            .addComponent(cboxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodCuenta)
                            .addComponent(cboxTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCodCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNomCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboxTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCuentaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboxBaseProrrateo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaCuentas.setModel(cuentaTModel);
        tablaCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        PrincipalContabilidad conta = new PrincipalContabilidad();
        conta.setVisible(true);
        conta.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void cboxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxEstadoActionPerformed
        
    }//GEN-LAST:event_cboxEstadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            String codCuenta=txtCodCuenta.getText();
            String nomCuenta=txtNomCuenta.getText();
            String StringtipoCuenta=(String) cboxTipoCuenta.getSelectedItem();
            String estado=(String) cboxEstado.getSelectedItem();
            String stringCuentaPadre=txtCuentaPadre.getText();
            String stringBaseProrrateo=(String) cboxBaseProrrateo.getSelectedItem();
            //EntityManagerFactory conexion=Persistence.createEntityManagerFactory("EmpresaMultiServiciosContabilidadPU");
            CuentaJpaController cuentaControl=new CuentaJpaController(login.conexion);
            Cuenta cuenta=new Cuenta();
            
            cuenta.setCodcuenta(codCuenta);
            cuenta.setNomcuenta(nomCuenta);
            CatalogocuentaJpaController catalogoControl=new CatalogocuentaJpaController(login.conexion);
            Catalogocuenta catalogo=catalogoControl.findCatalogocuenta(1); //idCatalogo=1, catalogo de cuentas actual de la empresa
            
            cuenta.setIdcatalogo(catalogo);
            
            
            TipocuentaJpaController tipoCuentaControl=new TipocuentaJpaController(login.conexion);
            Tipocuenta idtipocuenta=tipoCuentaControl.findTipocuenta(StringtipoCuenta.split(", ")[0]);
            cuenta.setIdtipocuenta(idtipocuenta);

            cuenta.setEstadocuenta(estado.charAt(0));
            
            if(stringCuentaPadre != null && stringCuentaPadre != ""){
                Cuenta cuentaPadre=cuentaControl.findCuenta(stringCuentaPadre);
                cuenta.setCueCodcuenta(cuentaPadre);   
            } 
            if(stringBaseProrrateo != null && stringBaseProrrateo != ""){
                BaseprorrateoJpaController baseProrrateoControl=new BaseprorrateoJpaController(login.conexion);
                Baseprorrateo base=baseProrrateoControl.findBaseprorrateo(Integer.parseInt(stringBaseProrrateo.split(", ")[0]));
                cuenta.setIdbase(base);
            }
          
        try {
            cuentaControl.create(cuenta);
            JOptionPane.showMessageDialog(this, "La cuenta ha sido guardada con éxito!");

        } catch (Exception ex) {
            Logger.getLogger(CatalogoCuentas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al guardar la cuenta!");

        } 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error en los datos de la cuenta!");

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCodCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodCuentaActionPerformed

    private void txtNomCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomCuentaActionPerformed

    private void cboxTipoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxTipoCuentaActionPerformed

    }//GEN-LAST:event_cboxTipoCuentaActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tablaCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuentasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaCuentasMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cuentaTModel.cuentas.clear();
        try{
            String codCuenta = txtBuscarCodCuenta.getText().toString();
            if(!codCuenta.isEmpty()){
                CuentaJpaController cuentaControl=new CuentaJpaController(login.conexion);
                Cuenta cuenta=cuentaControl.findCuenta(codCuenta);
                cuentaTModel.cuentas.add(cuenta);
            }
            else{
                consultaInicial();
            }
            tablaCuentas.repaint(); 
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(CatalogoCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatalogoCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatalogoCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalogoCuentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatalogoCuentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboxBaseProrrateo;
    private javax.swing.JComboBox<String> cboxEstado;
    private javax.swing.JComboBox<String> cboxTipoCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCuentas;
    private javax.swing.JTextField txtBuscarCodCuenta;
    private javax.swing.JTextField txtCodCuenta;
    private javax.swing.JTextField txtCuentaPadre;
    private javax.swing.JTextField txtNomCuenta;
    // End of variables declaration//GEN-END:variables
}
