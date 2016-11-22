/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Tipocuenta;
import static Vistas.GestionarTiposCuentas.cTuentaTModel;
import static Vistas.GestionarTiposCuentas.tablaTipos;
import Vistas.login;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Frank
 */
public class TipoCuentaControl {
    
        public static void inicializarColumnas(){
        
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<2;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Nombre");
                break;
            }
            tColumnModel.addColumn(col);
        }
        tablaTipos.setColumnModel(tColumnModel);
        
    }
        
        public static void consultaInicial(){
        try{
            cTuentaTModel.tCuentas.clear();
            TipocuentaJpaController tCuentaControl=new TipocuentaJpaController(login.conexion);
            List <Tipocuenta> listaTcuenta=new ArrayList<Tipocuenta>(); 
            
            listaTcuenta=tCuentaControl.findTipocuentaEntities();
            for (Tipocuenta tCuenta:listaTcuenta){
                cTuentaTModel.tCuentas.add(tCuenta);
            }
            tablaTipos.repaint();

        }catch(Exception e){
            
        }
    }
        
        public static boolean crear(String codigo, String nombre){
        boolean resultado = true;
        
        Tipocuenta tCuenta = new Tipocuenta();
        TipocuentaJpaController tCuentaControl = new TipocuentaJpaController(login.conexion);
        
        tCuenta.setIdtipocuenta(codigo);
        tCuenta.setNomtipocuenta(nombre);
        
        try{
        tCuentaControl.create(tCuenta);
        }catch(Exception e){
            System.out.print(e);
            resultado = false;
            return resultado;
        }
            return resultado;
        }
        
        public static boolean editar(String codigo, String nombre){
        boolean resultado = true;
        
        TipocuentaJpaController tCuentaControl = new TipocuentaJpaController(login.conexion);
        Tipocuenta tCuenta = tCuentaControl.findTipocuenta(codigo);
        tCuenta.setNomtipocuenta(nombre);
        try{
        tCuentaControl.edit(tCuenta);
        }catch(Exception w){
        System.out.print(w);
        resultado = false;
        return resultado;
        }
        return resultado;
        }

}
