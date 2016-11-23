/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author kevin
 */
public class DetalleDiarioTableModel extends AbstractTableModel{
    public List<Detallediario> listaDetalleDiario=new ArrayList<Detallediario>();
    @Override
    public int getRowCount() {
        return listaDetalleDiario.size();
    }
    @Override
    public int getColumnCount() {
        return 4;
    }  
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Detallediario detalleD = listaDetalleDiario.get(rowIndex);
        Object valor = null;
        switch(columnIndex){
            case 0: valor = detalleD.getCodcuenta().getCodcuenta();
            break;
            case 1: valor = detalleD.getCodcuenta().getNomcuenta();
            break;
            case 2: valor = detalleD.getDebe();
            break;
            case 3: valor=detalleD.getHaber();        
        }
    return valor;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        if(column==0 || column==1){
            return false;
            
        }else{
            return true;
        }   
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        Detallediario detalleD=listaDetalleDiario.get(row);
        try{
            switch(col){
            case 2: detalleD.setDebe(BigDecimal.valueOf(Double.parseDouble(value.toString())));
            break;
            case 3: detalleD.setHaber(BigDecimal.valueOf(Double.parseDouble(value.toString())));
            break;
            } 
            
        }catch(Exception e){
        
        }
        listaDetalleDiario.set(row, detalleD);
        fireTableCellUpdated(row, col);
    }
    public void addRow(Detallediario detalleD){
        listaDetalleDiario.add(detalleD);
        fireTableRowsInserted(listaDetalleDiario.size()-1, listaDetalleDiario.size()-1);
    }
}
