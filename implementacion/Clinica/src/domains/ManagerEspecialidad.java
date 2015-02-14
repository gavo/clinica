/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.Especialidad;
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
public abstract class ManagerEspecialidad {

    public static ArrayList<Especialidad> listarEspecialidad() {
        ArrayList<Especialidad> lista = new ArrayList();
        ResultSet rs = Main.con.consultar(SQL.listarEspecialidades());
        try {
            while (rs.next()) {
                lista.add(new Especialidad(rs.getInt("id_es"), rs.getString("nombre"), rs.getFloat("costo")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static Especialidad buscarEspecialidad(String nombre) {
        Especialidad e = null;
        ResultSet rs = Main.con.consultar(SQL.BuscarEspecialidad(nombre));
        try {
            while (rs.next()) {
                e = new Especialidad(rs.getInt("id_es"), rs.getString("nombre"), rs.getFloat("costo"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public static Especialidad buscarEspecialidad(int id) {
        Especialidad e = null;
        ResultSet rs = Main.con.consultar(SQL.BuscarEspecialidad(id));
        try {
            while (rs.next()) {
                e = new Especialidad(rs.getInt("id_es"), rs.getString("nombre"), rs.getFloat("costo"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public static void registrarEspecialidad(Especialidad e) {
        if (buscarEspecialidad(e.getNombre()) == null) {
            if (SQL.pregunta("Desea Registrar la Especialidad " + e.getNombre())) {
                if (Main.con.ejecutar(SQL.registrarEspecialidad(e.getNombre(), e.getCosto()))) {
                    ManagerArchivo.escribirLog("Especialidad Registrada en la Base de datos ->" + e);
                    JOptionPane.showMessageDialog(null, "La Especialidad " + e.getNombre() + " Fue agregada Correctamente a la DB", "Especialidad Registrada Correctamente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ManagerArchivo.escribirLog("Error: No se pudo registrar la Especialidad " + e.getNombre() + " En la Base de datos");
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la Especialidad \n" + e.getNombre() + "\nEn la Base de datos", "Error al Registrar Especialidad", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            ManagerArchivo.escribirLog("No se puede registrar la especialidad ->" + e + " porque el nombre de especialidad esta siendo utilizada");
            JOptionPane.showMessageDialog(null, "No se pudo registrar la especialidad \n" + e.getNombre() + "\nEn la Base de datos porque el nombre de especialidad esta siendo utilizado", "Error al registrar Especialidad", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void actualizarEspecialidad(Especialidad e) {
        if (SQL.pregunta("Desea Actualizar la Especialidad " + e.getNombre())) {
            if (Main.con.ejecutar(SQL.actualizarEspecialidad(e.getId_es(), e.getNombre(), e.getCosto()))) {
                ManagerArchivo.escribirLog("Especialidad Actualidada en la Base de datos ->" + e);
                JOptionPane.showMessageDialog(null, "La Especialidad " + e.getNombre() + " Fue actualizada Correctamente a la DB", "Especialidad Actualizada Correctamente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ManagerArchivo.escribirLog("Error: No se pudo actualizar la Especialidad " + e.getNombre() + " En la Base de datos");
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la Especialidad \n" + e.getNombre() + "\nEn la Base de datos", "Error al actualizar Especialidad", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
