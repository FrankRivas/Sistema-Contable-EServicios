/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Empleado;
import Modelos.Puesto;
import Modelos.Usuario;
import static Vistas.PrincipalEmpleados.empleadoTModel;
import static Vistas.PrincipalEmpleados.tablaEmpleados;
import Vistas.login;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Frank
 */
public class EmpleadoControl {
    
     public static void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<6;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                
                case 0:
                    col.setHeaderValue("Nombre");
                break;
                case 1:
                    col.setHeaderValue("Apellido");
                break;
                case 2:
                    col.setHeaderValue("Puesto");
                break;
                case 3:
                    col.setHeaderValue("Salario");
                break;
                case 4:
                    col.setHeaderValue("Telefono");
                break;
                case 5:
                    col.setHeaderValue("Edad");
                break;
            }
            tColumnModel.addColumn(col);
        }
        tablaEmpleados.setColumnModel(tColumnModel);
        
    }
     
     public static void consultaInicial(){
        try{
            empleadoTModel.empleados.clear();
            EmpleadoJpaController empleadoControl=new EmpleadoJpaController(login.conexion);
            List <Empleado> listaEmpleado=new ArrayList<Empleado>(); 
            
            listaEmpleado=empleadoControl.findEmpleadoEntities();
            for (Empleado empleado:listaEmpleado){
                empleadoTModel.empleados.add(empleado);
            }
            tablaEmpleados.repaint();

        }catch(Exception e){
            System.out.print(e);
        }
    }
     
     
    public static boolean guardar(String nom,String ap,String dui,String nit,String puest,Short ed,String tel,String dir,String ema,String is,String afp,String sex,String est){
    boolean resultado = true;
    
    Empleado empleado = new Empleado();
    EmpleadoJpaController empControl = new EmpleadoJpaController(login.conexion);
    PuestoJpaController puestoControl = new PuestoJpaController(login.conexion);
    
    Puesto puesto = puestoControl.findPuesto(parseInt(puest.split(", ")[0]));
    int cantEmp = empControl.getEmpleadoCount();
    
    empleado.setCodempleado(cantEmp+1);
    empleado.setNomempleado(nom);
    empleado.setApellido(ap);
    empleado.setDui(dui);
    empleado.setNitempleado(nit);
    empleado.setIdpuesto(puesto);
    empleado.setEdad(ed);
    empleado.setTelefono(tel);
    empleado.setDirempleado(dir);
    empleado.setEmail(ema);
    empleado.setIsss(is);
    empleado.setAfp(afp);
    empleado.setSexo(sex.charAt(0));
    empleado.setEstadocivil(est.charAt(0));
    
    try {
    empControl.create(empleado);
    }catch(Exception e){
        System.out.print(e);
        resultado = false;
        return resultado;
    }
    
    return resultado;
    }
    public static boolean borrar(int id){
    boolean resultado = true;
    EmpleadoJpaController empleadoControl = new EmpleadoJpaController(login.conexion);
    UsuarioJpaController usuarioControl=new UsuarioJpaController(login.conexion);
    List <Usuario> listaUsuarios=new ArrayList<Usuario>();    
    listaUsuarios=usuarioControl.findUsuarioEntities();
    boolean eliminarUsuario=false;
    int idUsuario=-1;
    for (Usuario user:listaUsuarios){
        if(user.getCodempleado().getCodempleado()==id){
            eliminarUsuario=true;
             idUsuario=user.getIdusuario();
        }
             
    }
    try{
        if(eliminarUsuario)
            usuarioControl.destroy(idUsuario);
        empleadoControl.destroy(id);   
    }catch(Exception e){
        System.out.print(e);
        resultado = false;
        return resultado;
        }
    return resultado;
    
    }
    
}
