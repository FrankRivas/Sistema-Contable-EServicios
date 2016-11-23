/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Area;
import Modelos.Empresa;
import Vistas.login;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Merii
 */
public class AreaControlPrimario {
    
    public static boolean agregar(int empresa, String nombre, String descripcion){
        boolean resultado = true;
        Area area1 = new Area();
        AreaJpaController areaControl=new AreaJpaController(login.conexion);
        EmpresaJpaController empControl = new EmpresaJpaController(login.conexion);
        
        area1.setIdarea(5);
        Empresa emp = empControl.findEmpresa(empresa);
        area1.setIdempresa(emp);
        area1.setNomarea(nombre);
        area1.setDescripcionarea(descripcion);
        
        try{
        areaControl.create(area1);
            //JOptionPane.showMessageDialog(null, "Area creada con exito");
        }catch(Exception ex){
            resultado = false;
            return resultado;
        
        }
        return resultado;
    }
    
}
    