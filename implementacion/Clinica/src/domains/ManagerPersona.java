/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.Persona;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pack.Main;
import utils.ManagerArchivo;
import utils.SQL;

/**
 *
 * @author CKN
 */
public abstract class ManagerPersona {

    public static ArrayList<Persona> listarPersonas(String palabra, boolean f) {
        ArrayList<Persona> p = new ArrayList();
        ResultSet rs;
        if (f) {
            rs = Main.con.consultar(SQL.listarPersonaCi(palabra));
        } else {
            rs = Main.con.consultar(SQL.listarPersonaNombre(palabra));
        }
        try {
            while (rs.next()) {
                p.add(new Persona(rs.getString("ci"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("direccion"), rs.getString("telefono")));
            }
        } catch (Exception ex) {
            ManagerArchivo.escribirLog("ERROR: No se pudo Registrar Persona ->" + p + " " + ex.getMessage());
        }
        return p;
    }

    public static Persona buscarPersona(String ci) {
        Persona persona = null;
        ResultSet rs = Main.con.consultar(SQL.buscarPersona(ci));
        try {
            while (rs.next()) {
                persona = new Persona(rs.getString("ci"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("direccion"), rs.getString("telefono"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }

    public static void insertarPersona(Persona p) {
        if (SQL.pregunta("Desea registrar a \"" + p.getNombres() + " " + p.getApellidos() + "\" En la Base de Datos")) {
            if (Main.con.ejecutar(SQL.registrarPersona(p.getCi(), p.getNombres(), p.getApellidos(), p.getDireccion(), p.getTelefono()))) {
                ManagerArchivo.escribirLog("Persona Registrada->" + p);
                JOptionPane.showMessageDialog(null, "Los Datos de la Persona \n" + p + "\nFueron Registrados Correctamente", "Persona Registrada Correctamente", JOptionPane.INFORMATION_MESSAGE);             
            } else {
                ManagerArchivo.escribirLog("ERROR: No se pudo Registrar Persona ->" + p);
                JOptionPane.showMessageDialog(null, "Los Datos de la Persona \n" + p + "\nNo pudieron ser Registrados", "Error al Registrar Persona", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void actualizarPersona(Persona p) {
        if (SQL.pregunta("Desea actualizar los datos de \"" + p.getNombres() + " " + p.getApellidos() + "\" En la Base de Datos")) {
            if (Main.con.ejecutar(SQL.actualizarPersona(p.getCi(), p.getNombres(), p.getApellidos(), p.getDireccion(), p.getTelefono()))) {
                ManagerArchivo.escribirLog("Datos de Persona Modificada->" + p);
                JOptionPane.showMessageDialog(null, "Los datos de la persona: \n" + p + "\nFueron actualizados Correctamente", "Datos Actualizados Correctamente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ManagerArchivo.escribirLog("ERROR: No se pudo actualizar los datos de la Persona");
                JOptionPane.showMessageDialog(null, "Los Datos de la Persona \n" + p + "\nNo pudieron ser Actualizados", "Error al Actualizar datos de Persona", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String[] parseVector(Persona p) {
        String[] r = {
            p.getCi(),
            p.getNombres(),
            p.getApellidos(),
            p.getDireccion(),
            p.getTelefono()
        };
        return r;
    }
}
