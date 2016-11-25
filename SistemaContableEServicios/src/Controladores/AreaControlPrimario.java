/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Area;
import Modelos.AreaTableModel;
import Modelos.Empresa;
import static Vistas.GestionarAreas.tablaAreas;
import Vistas.login;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import static Vistas.GestionarAreas.areaTModel;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Merii
 */
public class AreaControlPrimario { 
    
    
    public static boolean agregar(String empresa, String nombre, String descripcion){
        boolean resultado = true;
        Area area1 = new Area();
        AreaJpaController areaControl=new AreaJpaController(login.conexion);
        EmpresaJpaController empControl = new EmpresaJpaController(login.conexion);
        
        Empresa emp = empControl.findEmpresa(parseInt(empresa.split(", ")[0]));
        int contArea = areaControl.getAreaCount();
        area1.setIdarea(contArea+1);
        area1.setIdempresa(emp);
        area1.setNomarea(nombre);
        area1.setDescripcionarea(descripcion);
        
        try{
        areaControl.create(area1);
            
        }catch(Exception ex){
            System.out.print(ex);
            resultado = false;
            return resultado;
        
        }
        return resultado;
    }
    
    public static boolean modificar(int id, String empresa, String nombre, String descripcion){
        boolean resultado = true;
        
        AreaJpaController areaControl=new AreaJpaController(login.conexion);
        EmpresaJpaController empControl = new EmpresaJpaController(login.conexion);
        
        Empresa emp = empControl.findEmpresa(parseInt(empresa.split(", ")[0]));
        Area area1 = areaControl.findArea(id);
        area1.setIdempresa(emp);
        area1.setNomarea(nombre);
        area1.setDescripcionarea(descripcion);
        try{
        areaControl.edit(area1);
        }catch(Exception e){
        System.out.print(e);
        resultado = false;
        return resultado;
        }
        return resultado;
    }
    
    
     
      
      
        public static void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<4;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Código");
                break;
                case 1:
                    col.setHeaderValue("Empresa");
                break;
                case 2:
                    col.setHeaderValue("Nombre de Area");
                break;
                case 3:
                    col.setHeaderValue("Descripcion");
                break;
            }
            tColumnModel.addColumn(col);
        }
        tablaAreas.setColumnModel(tColumnModel);
        
    }
        
         public static void consultaInicial(){
        try{
            areaTModel.areas.clear();
            AreaJpaController areaControl=new AreaJpaController(login.conexion);
            List <Area> listaArea=new ArrayList<Area>(); 
            
            listaArea=areaControl.findAreaEntities();
            for (Area area:listaArea){
                areaTModel.areas.add(area);
            }
            tablaAreas.repaint();

        }catch(Exception e){
            
        }
    }
        
        public static String empresas(int id){
            
            String miEmpresa= null;
            try{
            EmpresaJpaController empresaControl=new EmpresaJpaController(login.conexion);
            miEmpresa = empresaControl.findEmpresa(id).getNomempresa();
          
            }
        catch(Exception e){
            System.out.print(e);
        }
               
                return miEmpresa;
        }
    
}
    