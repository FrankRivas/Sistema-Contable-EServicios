/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Cuenta;
import Modelos.Rol;
import Modelos.RolTableModel;
//import static Vistas.GestionarRoles.rolTModel;
import static Vistas.GestionarRoles.tablaRoles;
import Vistas.login;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Frank
 */
public class RolControl {
    
    public static RolTableModel rolTModel=new RolTableModel();
    
    public static boolean agregar(String nombre, String desc){
    boolean resultado = true;
    Rol rol = new Rol();
    RolJpaController rolControl=new RolJpaController(login.conexion);
    int numeroRoles = rolControl.getRolCount();
    rol.setIdrol(numeroRoles+1);
    rol.setNomrol(nombre);
    rol.setDescripcionrol(desc);
    
    try{
    rolControl.create(rol);
    }catch(Exception ex){
    System.out.print(ex);
    resultado = false;
    return resultado;
    }
    return resultado;
    }
    
    public static void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<3;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Nombre de Rol");
                break;
                case 2:
                    col.setHeaderValue("Descripcion");
                break;
            }
            tColumnModel.addColumn(col);
        }
        tablaRoles.setColumnModel(tColumnModel);
        
    }
    
    public static void consultaInicial(){
        try{
            rolTModel.roles.clear();
            RolJpaController rolControl=new RolJpaController(login.conexion);
            List <Rol> listaRol=new ArrayList<Rol>(); 
            
            listaRol=rolControl.findRolEntities();
            for (Rol rol:listaRol){
                rolTModel.roles.add(rol);
            }
            tablaRoles.repaint();

        }catch(Exception e){
            
        }
    }
    
    public static boolean modificar(int id, String nombre, String desc){
    boolean resultado = true;
    
    RolJpaController rolControl = new RolJpaController(login.conexion);
    Rol rol = rolControl.findRol(id);
    rol.setNomrol(nombre);
    rol.setDescripcionrol(desc);
    try {
    rolControl.edit(rol);
    }catch(Exception w){
    System.out.print(w);
    resultado = false;
    return resultado;
    }
    return resultado;
    }
    
    
}
