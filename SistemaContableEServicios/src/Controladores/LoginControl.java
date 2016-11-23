/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Usuario;
import Vistas.PrincipalContabilidad;
import static Vistas.login.conexion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Merii
 */
public class LoginControl {
    
    public static boolean validar(String nombre, String contra){
    
        boolean resultado = false;
        UsuarioJpaController controler = new UsuarioJpaController(conexion);
        List<Usuario> users = new ArrayList<Usuario>();
        users = controler.findUsuarioEntities();
        String usr = nombre;
        String pas = contra;
        for (Usuario user1 : users) {
            try {
                if (usr.equals(user1.getNomusuario()) ){
                    if (pas.equals(user1.getPassword())) {
                       return resultado = true;
                    }
                }
            } catch (Exception ex) {
                return resultado;
            }
        }
        return resultado;
    }
    
}
