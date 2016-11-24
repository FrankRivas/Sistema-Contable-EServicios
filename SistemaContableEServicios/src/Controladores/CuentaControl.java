/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Cuenta;
import Vistas.login;
import java.math.BigDecimal;

/**
 *
 * @author kevin
 */
public class CuentaControl {
    
    public static void actualizarSaldoCuentasPadre(Cuenta cuenta, double saldoModificar){ //El parámetro saldoModificar es el resultado del debe-haber, y éste puede ser positivo o negativo según sea el caso.
        boolean b=true;
        while(b=true){
            try{
                CuentaJpaController cuentaControl=new CuentaJpaController(login.conexion);
                Cuenta cuentaPadre=cuentaControl.findCuenta(cuenta.getCueCodcuenta().getCodcuenta()); //Devolverá una excepción si la cuenta no está asociada a una cuenta padre.
                cuentaPadre.setSaldocuenta(BigDecimal.valueOf(cuentaPadre.getSaldocuenta().doubleValue()+saldoModificar));
                cuentaControl.edit(cuentaPadre);
                cuenta=cuentaPadre;
            }catch(Exception e){
                b=false;
                return;
            }
        }
        return;
    }
    
}
