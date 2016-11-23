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
public class RolTableModel extends AbstractTableModel{

        public List<Rol>roles = new ArrayList<Rol>();
    @Override
    public int getRowCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return roles.size();
    }

    @Override
    public int getColumnCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Rol rol = roles.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           case 0: valor = rol.getIdrol();
           break;
           case 1: valor = rol.getNomrol();
           break;
           case 2: valor = rol.getDescripcionrol();
           break;
       }
       return valor;
    }
    
    
}
