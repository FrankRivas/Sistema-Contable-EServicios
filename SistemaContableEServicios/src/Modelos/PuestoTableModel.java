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
public class PuestoTableModel extends AbstractTableModel{
    
    public List<Puesto> puestos = new ArrayList<Puesto>();
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return puestos.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Puesto puesto = puestos.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           case 0: valor = puesto.getIdpuesto();
           break;
           case 1: valor = puesto.getNompuesto();
           break;
           case 2: valor = puesto.getIdarea().getNomarea();
           break;
           case 3: valor = puesto.getDescripcionpuesto();
           break;
           case 4: valor = puesto.getSalario();
           break;
       }
       return valor;
    }
    
}
