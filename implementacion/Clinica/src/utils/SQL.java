/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CKN
 */
public abstract class SQL {
    /////////////////////HERRAMIENTAS USADAS PARA LAS CONSULTAS/////////////////////

    public static String sha1(String string) {
        String hash = "";
        try {
            MessageDigest md;
            byte[] buffer, digest;
            buffer = string.getBytes();
            md = MessageDigest.getInstance("SHA1");
            md.update(buffer);
            digest = md.digest();
            for (byte aux : digest) {
                int b = aux & 0xff;
                if (Integer.toHexString(b).length() == 1) {
                    hash += "0";
                }
                hash += Integer.toHexString(b);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    public static final SimpleDateFormat formatoDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat formatDates = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean pregunta(String pregunta) {
        int seleccion = JOptionPane.showOptionDialog(
                null, pregunta,
                "Seleccione una opción", // Título
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Si", "No"}, // null para YES, NO y CANCEL
                "Si");
        if (seleccion != -1) {
            if ((seleccion + 1) == 1) {
                return true;
            }
        }
        return false;
    }

// REGISTRO DE SQL EJECUTADO EN LA DB
    public static String backup(String sql) {
        return "INSERT INTO `backup`(`sql`)VALUES(\"" + sql + "\");";
    }

// CONSULTAS Y MODIFICACIONES PARA LA TABLA DE PERSONAS
    public static String listarPersonaCi(String ci) {
        return "SELECT * FROM persona WHERE ci LIKE '%" + ci + "%';";
    }

    public static String listarPersonaNombre(String nombre) {
        return "SELECT * FROM persona WHERE CONCAT(persona.`nombres`,' ',persona.`apellidos`,' ',persona.`apellido_materno`) LIKE '%" + nombre + "%';";
    }

    public static String buscarPersona(String ci) {
        return "SELECT * FROM `persona` WHERE `ci` = '" + ci + "';";
    }

    public static String registrarPersona(String ci, String nombres, String apellidos, String direccion, String telefono) {
        return "INSERT INTO persona(ci,nombres,apellidos,direccion,telefono)VALUES('" + ci + "','" + nombres + "','" + apellidos + "','" + direccion + "','" + telefono + "');";
    }

    public static String actualizarPersona(String ci, String nombres, String apellidos, String direccion, String telefono) {
        return "UPDATE persona SET `nombres`='" + nombres + "', `apellidos`='" + apellidos + "',`direccion`='" + direccion + "',`telefono`='" + telefono + "' WHERE `ci`='" + ci + "';";
    }

    // CONSULTAS Y MODIFICACIONES PARA LA TABLA USUARIOS
    public static String BuscarUsuario(int id_us) {
        return "SELECT * FROM usuario WHERE id_us = '" + id_us + "'";
    }

    public static String registrarUsuario(String user, String pass, int tipo, String ci) {
        return "INSERT INTO usuario(`user`,`pass`,`tipo`,`ci`)VALUES('" + user + "','" + sha1(pass) + "','" + tipo + "','" + ci + "');";
    }

    public static String autenticarUsuario(String user, String pass) {
        return "SELECT * FROM usuario WHERE `user`='" + user + "' AND `pass`='" + sha1(pass) + "'";
    }

    public static String actualizarUsuario(int id, String user, String pass, int tipo, String ci, int estado) {
        return "UPDATE usuario SET `user`='" + user + "', `pass`='" + sha1(pass) + "',`tipo`='" + tipo + "',`ci`='" + ci + "',`estado`='" + estado + "' WHERE `id_us`='" + id + "'";
    }

    public static String BuscarUsuario(String user) {
        return "SELECT * FROM usuario WHERE `user`='" + user + "';";
    }

    public static String BuscarUsuarios(String user) {
        return "SELECT * FROM usuario WHERE `user` like '%" + user + "%';";
    }

// CONSULTAS Y MODIFICACIONES PARA LA TABLA ESPECIALIDADES
    public static String listarEspecialidades() {
        return "SELECT * FROM especialidad;";
    }

    public static String BuscarEspecialidad(String nombre) {
        return "SELECT * FROM especialidad WHERE nombre = '" + nombre + "'";
    }

    public static String BuscarEspecialidad(int id) {
        return "SELECT * FROM especialidad WHERE id_es ='" + id + "'";
    }

    public static String registrarEspecialidad(String nombre, float costo) {
        return "INSERT INTO especialidad(nombre,costo)values('" + nombre + "','" + costo + "')";
    }

    public static String actualizarEspecialidad(int id_es, String nombre, float costo) {
        return "UPDATE especialidad SET nombre='" + nombre + "', costo='" + costo + "' WHERE id_es = '" + id_es + "'";
    }

// CONSULTAS Y MODIFICACIONES A LA TABLA ESUS
    public static String registrarEsUs(int id_es, int id) {
        return "INSERT INTO esp_us(id_es,id_us)VALUES('" + id_es + "','" + id + "');";
    }

    public static String BuscarEspUs(int id_es, int id) {
        return "SELECT * FROM esp_us WHERE id_es='" + id_es + "' AND id_us='" + id + "'";
    }

    public static String ActivarEspUs(int id_es, int id) {
        return "UPDATE esp_us SET activa='1' WHERE id_es='" + id_es + "' AND id_us='" + id + "'";
    }

    public static String DesctivarEspUs(int id_es, int id) {
        return "UPDATE esp_us SET activa='0' WHERE id_es='" + id_es + "' AND id_us='" + id + "'";
    }

    public static String listarEspUs() {
        return "SELECT * FROM esp_us WHERE activa='1'";
    }

    public static String listarUsuariosEspecialidad(int id_es) {
        return "SELECT * FROM esp_us WHERE activa='1' AND id_es='" + id_es + "'";
    }

    public static String listarEspecialidadUsuarios(int id_us) {
        return "SELECT * FROM esp_us WHERE activa='1' AND id_us='" + id_us + "'";
    }
}
