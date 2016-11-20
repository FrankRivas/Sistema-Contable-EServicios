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
public class AreaTableModel extends AbstractTableModel{
    
    public List<Area> areas = new ArrayList<Area>();

    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return areas.size();
    }

    @Override
    public int getColumnCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Area area = areas.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           case 0: valor = area.getIdarea();
           break;
           case 1: valor = area.getIdempresa().getNomempresa();
           break;
           case 2: valor = area.getNomarea();
           break;
           case 3: valor = area.getDescripcionarea();
           break;
       }
       return valor;
    }
    
}
