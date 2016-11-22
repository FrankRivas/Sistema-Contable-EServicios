/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Area;
import Modelos.Puesto;
import Modelos.PuestoTableModel;
import static Vistas.GestionarPuestos.puestoTModel;
import static Vistas.GestionarPuestos.tablaPuestos;
import Vistas.login;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Frank
 */
public class PuestosControl {
    
    
    
    public static void inicializarColumnas(){
        
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
                    col.setHeaderValue("Area");
                break;
                case 3:
                    col.setHeaderValue("Descripcion");
                break;
                case 4:
                    col.setHeaderValue("Salario");
                break;
            }
            tColumnModel.addColumn(col);
        }
        tablaPuestos.setColumnModel(tColumnModel);  
    }
    
    public static void consultaInicial(){
        try{
            puestoTModel.puestos.clear();
            PuestoJpaController puestoControl=new PuestoJpaController(login.conexion);
            List <Puesto> listaPuestos=new ArrayList<Puesto>(); 
            
            listaPuestos=puestoControl.findPuestoEntities();
            for (Puesto puesto:listaPuestos){
                puestoTModel.puestos.add(puesto);
            }
            tablaPuestos.repaint();

        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public static boolean Crear(String nombre,String area, double salario, String desc){
    boolean resultado = true;
    Puesto pues = new Puesto();
    PuestoJpaController puestoControl = new PuestoJpaController(login.conexion);
    AreaJpaController areaControl = new AreaJpaController(login.conexion);
    
    Area area1 = areaControl.findArea(parseInt(area.split(", ")[0]));
    int numPuestos = puestoControl.getPuestoCount();
    
    pues.setIdpuesto(numPuestos+1);
    pues.setNompuesto(nombre);
    pues.setIdarea(area1);
    pues.setSalario(BigDecimal.valueOf(salario));
    pues.setDescripcionpuesto(desc);
    
    try{
    puestoControl.create(pues);
    }catch(Exception e){
    System.out.print(e);
    resultado = false;
    return resultado;
    }
    
    return resultado;
    }
    
}
