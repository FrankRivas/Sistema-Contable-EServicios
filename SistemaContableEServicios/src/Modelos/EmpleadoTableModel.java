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
public class EmpleadoTableModel extends AbstractTableModel{

    public List<Empleado> empleados = new ArrayList<Empleado>();
    @Override
    public int getRowCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return empleados.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Empleado empleado = empleados.get(rowIndex);
       Object valor = null;
       
       switch(columnIndex){
           
           case 0: valor = empleado.getNomempleado();
           break;
           case 1: valor = empleado.getApellido();
           break;
           case 2: valor = empleado.getIdpuesto().getNompuesto();
           break;
           case 3: valor = empleado.getIdpuesto().getSalario();
           break;
           case 4: valor = empleado.getTelefono();
           break;
           case 5: valor = empleado.getEdad();
           break;
       }
       return valor;
    }
    
}
