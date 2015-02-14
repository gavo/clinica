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
        return "SELECT * FROM usuarios WHERE id_us = '" + id_us + "'";
    }
}
