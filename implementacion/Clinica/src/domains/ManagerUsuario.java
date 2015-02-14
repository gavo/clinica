/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.Usuario;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pack.Main;
import utils.ManagerArchivo;
import utils.SQL;

/**
 *
 * @author CKN
 */
public abstract class ManagerUsuario {

    public static Usuario buscarUsuario(int id_us) {
        Usuario u = null;
        ResultSet rs = Main.con.consultar(SQL.BuscarUsuario(id_us));
        String ci = "-1";
        try {
            while (rs.next()) {
                u = new Usuario(rs.getInt("id_us"), rs.getString("user"), rs.getString("pass"), rs.getInt("tipo"), rs.getInt("estado"));
                ci = rs.getString("ci");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (u != null) {
            u.setPersona(ManagerPersona.buscarPersona(ci));
        }
        return u;
    }

    public static Usuario buscarUsuario(String user) {
        Usuario u = null;
        ResultSet rs = Main.con.consultar(SQL.BuscarUsuario(user));
        String ci = "-1";
        try {
            while (rs.next()) {
                u = new Usuario(rs.getInt("id_us"), rs.getString("user"), rs.getString("pass"), rs.getInt("tipo"), rs.getInt("estado"));
                ci = rs.getString("ci");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (u != null) {
            u.setPersona(ManagerPersona.buscarPersona(ci));
        }
        return u;
    }

    public static void registrarUsuario(Usuario u) {
        if (buscarUsuario(u.getUser()) == null) {
            if (SQL.pregunta("Desea agregar el usuario " + u + " a la Base de Datos")) {
                if (Main.con.ejecutar(SQL.registrarUsuario(u.getUser(), u.getPass(), u.getTipo(), u.getPersona().getCi()))) {
                    ManagerArchivo.escribirLog("Usuario Registrado en la Base de datos ->" + u);
                    JOptionPane.showMessageDialog(null, "El Usuario " + u.getUser() + " Fue agregado Correctamente a la DB", "Usuario Registrado Correctamente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ManagerArchivo.escribirLog("Error: No se pudo registrar el usuario " + u.getUser() + " En la Base de datos");
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario \n" + u.getUser() + "\nEn la Base de datos", "Error al Registrar Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            ManagerArchivo.escribirLog("No se puede registrar los datos del usuario ->" + u + " porque el nombre de usuario esta siendo utilizado");
            JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario \n" + u.getUser() + "\nEn la Base de datos porque el nombre de usuario esta siendo utilizado", "Error al registrar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Usuario autentificar(String user, String pass) {
        Usuario u = null;
        ResultSet rs = Main.con.consultar(SQL.autenticarUsuario(user, pass));
        String ci = "-1";
        try {
            while (rs.next()) {
                u = new Usuario(rs.getInt("id_us"), rs.getString("user"), rs.getString("pass"), rs.getInt("tipo"), rs.getInt("estado"));
                ci = rs.getString("ci");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (u != null) {
            u.setPersona(ManagerPersona.buscarPersona(ci));
        }
        return u;
    }

    public static void actualizarUsuario(Usuario u) {
        if (buscarUsuario(u.getUser()) == null) {
            if (SQL.pregunta("Desea actualizar los datos del usuario " + u.getUser())) {
                if (Main.con.ejecutar(SQL.actualizarUsuario(u.getId(), u.getUser(), u.getPass(), u.getTipo(), u.getPersona().getCi(), u.getEstado()))) {
                    ManagerArchivo.escribirLog("Usuario Modificado en la Base de datos ->" + u);
                    JOptionPane.showMessageDialog(null, "El Usuario " + u.getUser() + " Fue modificado Correctamente a la DB", "Usuario Actualizado Correctamente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ManagerArchivo.escribirLog("Error: No se pudo modificar el usuario " + u.getUser() + " En la Base de datos");
                    JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario \n" + u.getUser() + "\nEn la Base de datos", "Error al Modificar Usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            ManagerArchivo.escribirLog("No se puede actualizar los datos del usuario ->" + u + " porque los datos ya estan siendo utilizados");
            JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario \n" + u.getUser() + "\nEn la Base de datos", "Error al Modificar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }
}
