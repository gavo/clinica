/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.Persona;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import pack.Main;
import utils.ManagerArchivo;
import utils.SQL;

/**
 *
 * @author CKN
 */
public class ManagerPersona {

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
            ManagerArchivo.escribirLog("[" + new Date() + "] ERROR AL LISTAR PERSONAS: " + ex.getMessage());
        }
        return p;
    }

    public static Persona buscarPersona(String ci) {
        Persona persona = null;
        int id_tit = 0;
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
            Main.con.ejecutar(SQL.registrarPersona(p.getCi(), p.getNombres(), p.getApellidos(), p.getDireccion(), p.getTelefono()));
        }
    }

    public static void actualizarPersona(Persona p) {
        if (SQL.pregunta("Desea actualizar los datos de \"" + p.getNombres() + " " + p.getApellidos()+ "\" En la Base de Datos")) {
            Main.con.ejecutar(SQL.actualizarPersona(p.getCi(), p.getNombres(), p.getApellidos(), p.getDireccion(), p.getTelefono()));
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