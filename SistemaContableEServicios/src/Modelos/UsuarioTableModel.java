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
public class UsuarioTableModel extends AbstractTableModel{
    
    public List<Usuario> usuarios = new ArrayList<Usuario>();
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Usuario user = usuarios.get(rowIndex);
        Object valor = null;
        
        switch(columnIndex){
           case 0: valor = user.getIdusuario();
           break;
           case 1: valor = user.getNomusuario();
           break;
           case 2: valor = user.getIdrol().getNomrol();
           break;
           case 3: valor = user.getPassword();
           break;
       }
        return valor;
    }
    
}
