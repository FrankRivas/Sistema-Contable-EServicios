/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Retencionimpuesto;
import Modelos.Techo;
import static Vistas.GestionarRetencionImpuesto.impTModel;
import static Vistas.GestionarRetencionImpuesto.rentaTModel;
import static Vistas.GestionarRetencionImpuesto.tablaImpuestos;
import static Vistas.GestionarRetencionImpuesto.tablaRentas;
import Vistas.login;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;

/**
 *
 * @author Merii
 */
public class ImpuestosControl {
    
     public static void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<3;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Identificador");
                break;
                case 1:
                    col.setHeaderValue("Retencion");
                break;
                case 2:
                    col.setHeaderValue("Porcentaje");
                break;
            }
            tColumnModel.addColumn(col);
            }
            tablaImpuestos.setColumnModel(tColumnModel);
        }
     
     
       public static void consultaInicial(){
        try{
            impTModel.retenciones.clear();
            TechoJpaController tControl=new TechoJpaController(login.conexion);
            List <Techo> listaTecho=new ArrayList<Techo>(); 
            
            listaTecho=tControl.findTechoEntities();
            for (Techo techo:listaTecho){
                //String n = (techo.getIdretimp()).toString();
                //int nw = parseInt(n);
                //if(nw != 3){
                impTModel.retenciones.add(techo);
           // }
            }
            tablaImpuestos.repaint();

        }catch(Exception e){
            System.out.print(e);
        }
    }
       
       
       public static boolean Guardar(String nombre, double porcentaje){
       boolean resultado = true;
       Techo t = new Techo();
       Retencionimpuesto ri = new Retencionimpuesto();
       TechoJpaController tControl = new TechoJpaController(login.conexion);
       RetencionimpuestoJpaController riControl = new RetencionimpuestoJpaController(login.conexion);
       
       ri.setNomretimpt(nombre);
       try{
       riControl.create(ri);
       t.setCuotafija(BigDecimal.valueOf(0.0));
       t.setDesde(BigDecimal.valueOf(0.0));
       t.setHasta(BigDecimal.valueOf(900000));
       t.setIdretimp(ri);
       t.setPorcenaplicar(BigDecimal.valueOf(porcentaje));
       t.setSobreexceso(BigDecimal.valueOf(0));
       
       try{
       tControl.create(t);
       }catch(Exception ex){
       System.out.print(ex);
       resultado = false;
       return resultado;
       }
       }catch(Exception e){
       System.out.print(e);
       resultado = false;
       return resultado;
       }
       return resultado;
       }
    
       public static boolean Modificar(int id,String nombre, double porcen){
       boolean resultado = true;
       Techo t = new Techo();
       Retencionimpuesto ri = new Retencionimpuesto();
       TechoJpaController tControl = new TechoJpaController(login.conexion);
       RetencionimpuestoJpaController riControl = new RetencionimpuestoJpaController(login.conexion);
       t = tControl.findTecho(id);
       //ri = riControl.findRetencionimpuesto(id);
       t.setPorcenaplicar(BigDecimal.valueOf(porcen));
       ri = t.getIdretimp();
       ri.setNomretimpt(nombre);
       
       try{
       riControl.edit(ri);
       try{
       tControl.edit(t);
       }catch(Exception e){
       System.out.print(e);
       resultado = false;
       return resultado;
       }
       }catch(Exception ex){
       System.out.print(ex);
       resultado = false;
       return resultado;
       }
       
       return resultado;
       }
       
       public static boolean Eliminar(int id){
       boolean resultado = true;
       RetencionimpuestoJpaController riControl = new RetencionimpuestoJpaController(login.conexion);
       TechoJpaController tControl = new TechoJpaController(login.conexion);
       try{
           Techo tech = tControl.findTecho(id);
           Retencionimpuesto retim = tech.getIdretimp();
           int ret = (retim.getIdretimp());
       tControl.destroy(id);
            try{
            riControl.destroy(ret);
            }catch(Exception e){
            System.out.print(e);
            resultado = false;
            return resultado;
            }
       }catch(Exception ex){
       System.out.print(ex);
       resultado = false;
       return resultado;
       }
       
       return resultado;
       }
}
