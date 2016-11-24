/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Detallediario;
import Modelos.Diario;
import Modelos.Periodocontable;
import Vistas.login;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kevin
 */
public class DiarioControl {
    
    public static boolean nuevoDiario(List<Detallediario> listaDetalleDiario, Date fecha,String concepto){
        Diario diario=new Diario();
        PeriodocontableJpaController periodoControl=new PeriodocontableJpaController(login.conexion);
        DiarioJpaController diarioControl=new DiarioJpaController(login.conexion);
        DetallediarioJpaController detalleDiarioControl=new DetallediarioJpaController(login.conexion);
        Periodocontable periodo=periodoControl.findPeriodocontable(1); //Importar csv de periodo contable en la DB
        diario.setIdperiodo(periodo);
        //diario.setIdusuario(idusuario);
        diario.setFecha(fecha);
        diario.setConcepto(concepto);
        diario.setEstadodiario('N');//N=No aprobado, pendiente de aprobación
        try{
            diarioControl.create(diario);
            List<Diario> listaDiarios=new ArrayList<Diario>();
            listaDiarios=diarioControl.findDiarioEntities();
            Diario recentCreatedDiario=listaDiarios.get(listaDiarios.size()-1);
            for(Detallediario detalle:listaDetalleDiario){
                //Asignando valor a llave foránea
                detalle.setIdregistro(recentCreatedDiario);
                detalleDiarioControl.create(detalle);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static boolean validarPartidaDoble(List<Detallediario> listaDetalleDiario){
        double debeAcum=0.0;
        double haberAcum=0.0;
        for(Detallediario detalle : listaDetalleDiario){
            debeAcum+=detalle.getDebe().doubleValue();
            haberAcum+=detalle.getHaber().doubleValue();
        }
        if(debeAcum==haberAcum){
            return true;
        }
        return false;
    }
    public static boolean validarDetallesDiarios(List<Detallediario> listaDetalleDiario){
        for(Detallediario detalle:listaDetalleDiario){
            if(detalle.getDebe().doubleValue()==0.0)
                if(detalle.getHaber().doubleValue()==0.0)
                    return false;
        }
        return true;
    }
}
