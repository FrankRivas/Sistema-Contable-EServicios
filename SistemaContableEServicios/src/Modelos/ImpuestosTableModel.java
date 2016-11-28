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
 * @author Merii
 */
public class ImpuestosTableModel extends AbstractTableModel{

    public List<Techo> retenciones = new ArrayList<Techo>();
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return retenciones.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Techo reten = retenciones.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           case 0: valor = reten.getIdtecho();
           break;
           case 1: valor = reten.getIdretimp().getNomretimpt();
           break;
           case 2: valor = reten.getPorcenaplicar();
           break;
       }
       return valor;
    }
    
}
