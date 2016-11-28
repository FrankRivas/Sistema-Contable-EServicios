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
public class RentaTableModel extends AbstractTableModel{
public List<Techo> rentas = new ArrayList<Techo>();
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return rentas.size();
    }

    @Override
    public int getColumnCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Techo renta = rentas.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           case 0: valor = renta.getIdtecho();
           break;
           case 1: valor = renta.getDesde();
           break;
           case 2: valor = renta.getHasta();
           break;
           case 3: valor = renta.getPorcenaplicar();
           break;
           case 4: valor = renta.getSobreexceso();
           break;
           case 5: valor = renta.getCuotafija();
           break;
       }
       return valor;
    }
    
}
