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
 * @author Frank
 */
public class TCuentaTableModel extends AbstractTableModel{

    public List<Tipocuenta> tCuentas = new ArrayList<Tipocuenta>();
    
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return tCuentas.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Tipocuenta tCuenta = tCuentas.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           case 0: valor = tCuenta.getIdtipocuenta();
           break;
           case 1: valor = tCuenta.getNomtipocuenta();
           break;
       }
       return valor;
    
    }
    
}
