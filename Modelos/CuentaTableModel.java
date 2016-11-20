/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kevin
 */
public class CuentaTableModel extends AbstractTableModel{
    public List<Cuenta> cuentas=new ArrayList<Cuenta>();
    @Override
    public int getRowCount() {
        return cuentas.size();
    }
    @Override
    public int getColumnCount() {
        return 5;
    }  
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuenta cuenta = cuentas.get(rowIndex);
        Object valor = null;
        switch(columnIndex){
            case 0: valor = cuenta.getCodcuenta();
            break;
            case 1: valor = cuenta.getNomcuenta();
            break;
            case 2: valor = cuenta.getIdtipocuenta().getNomtipocuenta();
            break;
            case 3: valor="";
                    if(cuenta.getEstadocuenta().charValue() == 'A'){
                        valor="Activa";
                    }else{
                        valor="Inactiva";
                    }
            break;
            case 4: valor="";
                    try{
                        valor = cuenta.getCueCodcuenta().getNomcuenta();
                    }catch(Exception e){
                
                    }
                    
        }
    return valor;
    }
    
}
