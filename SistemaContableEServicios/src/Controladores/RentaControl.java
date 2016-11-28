/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Retencionimpuesto;
import Modelos.Techo;
import static Vistas.GestionarRetencionImpuesto.rentaTModel;
import static Vistas.GestionarRetencionImpuesto.tablaRentas;
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
public class RentaControl {
    
    public static void inicializarColumnas(){
        TableColumnModel tColumnModel=new DefaultTableColumnModel();
        
        for(int i=0; i<6;i++){
            TableColumn col=new TableColumn(i);
            
            switch(i){
                case 0:
                    col.setHeaderValue("Tramo");
                break;
                case 1:
                    col.setHeaderValue("Desde");
                break;
                case 2:
                    col.setHeaderValue("Hasta");
                break;
                case 3:
                    col.setHeaderValue("Porcentaje");
                break;
                case 4:
                    col.setHeaderValue("Sobre Exceso");
                break;
                case 5:
                    col.setHeaderValue("Cuota Fija");
                break;
            }
            tColumnModel.addColumn(col);
            }
            tablaRentas.setColumnModel(tColumnModel);
        }
    
    
             public static void consultaInicial(){
        try{
            rentaTModel.rentas.clear();
            TechoJpaController tControl=new TechoJpaController(login.conexion);
            
            List <Techo> listaTechos=new ArrayList<Techo>(); 
            RetencionimpuestoJpaController rControl = new RetencionimpuestoJpaController(login.conexion);
            
            Retencionimpuesto n = new Retencionimpuesto();
            n = rControl.findRetencionimpuesto(3);
            List t = n.getTechoList();
            int nT = t.size();
            
            for (int i = 0; i<nT;i++){
                Techo techo = (Techo) t.get(i);
                rentaTModel.rentas.add(techo);
                }
            
            tablaRentas.repaint();

        }catch(Exception e){
            System.out.print(e);
        }
    }
        
        
    }

