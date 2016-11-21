/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Empleado;
import Modelos.Rol;
import Modelos.Usuario;
import static Vistas.CrearUsuarios.tablaUsuarios;
import static Vistas.CrearUsuarios.usuarioTModel;
import Vistas.login;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Merii
 */
public class UsuariosControl {
    
    public static void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<4;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Nombre de Usuario");
                break;
                case 2:
                    col.setHeaderValue("Rol");
                break;
                case 3:
                    col.setHeaderValue("Contraseña");
                break;
            }
            tColumnModel.addColumn(col);
        }
        tablaUsuarios.setColumnModel(tColumnModel);
        
    }
    
      public static void consultaInicial(){
        try{
            usuarioTModel.usuarios.clear();
            UsuarioJpaController usuarioControl=new UsuarioJpaController(login.conexion);
            List <Usuario> listaUsuarios=new ArrayList<Usuario>(); 
            
            listaUsuarios=usuarioControl.findUsuarioEntities();
            for (Usuario user:listaUsuarios){
                usuarioTModel.usuarios.add(user);
            }
            tablaUsuarios.repaint();

        }catch(Exception e){
            
        }
    }
      
      public static boolean crear(String nombre, String empleado, String rol, String contra){
      boolean resultado = true;
      
      Usuario user = new Usuario();
      UsuarioJpaController usuarioControl = new UsuarioJpaController(login.conexion);
      EmpleadoJpaController empControl = new EmpleadoJpaController(login.conexion);
      RolJpaController rolControl = new RolJpaController(login.conexion);
      
      Empleado emp = empControl.findEmpleado(parseInt(empleado.split(", ")[0]));
      Rol roles = rolControl.findRol(parseInt(rol.split(", ")[0]));
      int cantidad = usuarioControl.getUsuarioCount();
   
      user.setIdusuario(cantidad+1);
      user.setCodempleado(emp);
      user.setIdrol(roles);
      user.setNomusuario(nombre);
      user.setPassword(contra);
      
      try{
      usuarioControl.create(user);
      }catch(Exception ex){
      System.out.print(ex);
      resultado = false;
      return resultado;
      }
      
      return resultado;
      
      }
      
      public static boolean validar(String contr1, String contr2){
          String c1 = contr1;
          String c2 = contr2;
      boolean res = true;
          if (c1 == c2){
      }else{
              res = false;
          return res;
          }
          return res;
      }
      
      public static String empleados(int id){
      
          String empleado = null;
          try{
          EmpleadoJpaController empControl = new EmpleadoJpaController(login.conexion);
          empleado = empControl.findEmpleado(id).getNomempleado();
          }catch(Exception e){
              System.out.print(e);
          }
          return empleado;
      }
      
       public static String roles(int id){
            
            String rool= null;
            try{
            RolJpaController rolControl=new RolJpaController(login.conexion);
            rool = rolControl.findRol(id).getNomrol();
          
            }
        catch(Exception e){
            System.out.print(e);
        }
               
                return rool;
        }
       
       public static void limpiar(){
       int tamano = tablaUsuarios.getRowCount();
       for(int i= 0; i<tamano; i++){
       tablaUsuarios.removeAll();
       }
       }
    
}
