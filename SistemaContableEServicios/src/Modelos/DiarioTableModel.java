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
public class DiarioTableModel extends AbstractTableModel {
    public List<Diario> listaDiario=new ArrayList<Diario>();
    @Override
    public int getRowCount() {
        return listaDiario.size();
    }
    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Diario diario = listaDiario.get(rowIndex);
        Object valor = null;
        switch(columnIndex){
            case 0: valor = diario.getIdregistro();
            break;
            case 1: valor = diario.getConcepto();       
        }
    return valor;
    }
}
