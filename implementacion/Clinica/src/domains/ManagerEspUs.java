/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.EspUs;
import data.Especialidad;
import data.Usuario;
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
public abstract class ManagerEspUs {

    public static void registrarEspUs(EspUs eu) {
        int estado = buscarEspecialidad(eu);
        switch (estado) {
            case -1:
                if (estado == EspUs.NOEXISTE) {
                    if (SQL.pregunta("Desea agregar la Especialidad " + eu.getEsp().getNombre() + " al Usuario " + eu.getUs())) {
                        if (Main.con.ejecutar(SQL.registrarEsUs(eu.getEsp().getId_es(), eu.getUs().getId()))) {
                            ManagerArchivo.escribirLog("Especialidad " + eu.getEsp() + " Registrada para el usuario" + eu.getUs() + " en la Base de datos");
                            JOptionPane.showMessageDialog(null, "La Especialidad " + eu.getEsp().getNombre() + " Fue agregada Correctamente a la DB para el usuario " + eu.getUs().getUser(), "Especialidad Registrada Correctamente", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            ManagerArchivo.escribirLog("Error: No se pudo registrar la Especialidad " + eu.getEsp().getNombre() + " En la Base de datos para el usuario " + eu.getUs());
                            JOptionPane.showMessageDialog(null, "No se pudo registrar la Especialidad \n" + eu.getEsp().getNombre() + "\nEn la Base de datos para el usuario " + eu.getUs().getUser(), "Error al Registrar Especialidad", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                break;
            case 0:
                cambiarEstadoEspUs(eu);
                break;
            case 1:
                ManagerArchivo.escribirLog("Error: No es necesario registrar la Especialidad " + eu.getEsp().getNombre() + " En la Base de datos para el usuario " + eu.getUs() + " Porque ya esta Registrada y Activa dicha especialidad para el usuario");
                JOptionPane.showMessageDialog(null, "No es necesario registrar la Especialidad \n" + eu.getEsp().getNombre() + "\nEn la Base de datos para el usuario " + eu.getUs().getUser() + "\nEsta Especialidad ya esta activa para este usuario", "Error al Registrar Especialidad", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static int buscarEspecialidad(EspUs eu) {
        int r = EspUs.NOEXISTE;
        ResultSet rs = Main.con.consultar(SQL.BuscarEspUs(eu.getEsp().getId_es(), eu.getUs().getId()));
        try {
            while (rs.next()) {
                r = rs.getInt("activa");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }

    public static EspUs instanciarEspUs(Especialidad e, Usuario u) {
        ResultSet rs = Main.con.consultar(SQL.BuscarEspUs(e.getId_es(), u.getId()));
        EspUs eu = null;
        try {
            while (rs.next()) {
                eu = new EspUs(e, u, rs.getInt("activa"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return eu;
    }

    public static void cambiarEstadoEspUs(EspUs eu) {
        if (eu.getActiva() == EspUs.INACTIVA) {
            if (SQL.pregunta("Desea activar la especialidad " + eu.getEsp().getNombre() + " para el usuario " + eu.getUs())) {
                if (Main.con.ejecutar(SQL.ActivarEspUs(eu.getEsp().getId_es(), eu.getUs().getId()))) {
                    ManagerArchivo.escribirLog("Especialidad " + eu.getEsp() + " Activada para el usuario" + eu.getUs() + " en la Base de datos");
                    JOptionPane.showMessageDialog(null, "La Especialidad " + eu.getEsp().getNombre() + " Fue activada Correctamente a la DB para el usuario " + eu.getUs().getUser(), "Especialidad Activada Correctamente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ManagerArchivo.escribirLog("Error: No se pudo activar la Especialidad " + eu.getEsp().getNombre() + " En la Base de datos para el usuario " + eu.getUs());
                    JOptionPane.showMessageDialog(null, "No se pudo activar la Especialidad \n" + eu.getEsp().getNombre() + "\nEn la Base de datos para el usuario " + eu.getUs().getUser(), "Error al activar Especialidad", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            if (SQL.pregunta("Desea desactivar la especialidad " + eu.getEsp().getNombre() + " para el usuario " + eu.getUs())) {
                if (Main.con.ejecutar(SQL.DesctivarEspUs(eu.getEsp().getId_es(), eu.getUs().getId()))) {
                    ManagerArchivo.escribirLog("Especialidad " + eu.getEsp() + " Desactivada para el usuario" + eu.getUs() + " en la Base de datos");
                    JOptionPane.showMessageDialog(null, "La Especialidad " + eu.getEsp().getNombre() + " Fue desactivada Correctamente a la DB para el usuario " + eu.getUs().getUser(), "Especialidad desactivada Correctamente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ManagerArchivo.escribirLog("Error: No se pudo desactivar la Especialidad " + eu.getEsp().getNombre() + " En la Base de datos para el usuario " + eu.getUs());
                    JOptionPane.showMessageDialog(null, "No se pudo desactivar la Especialidad \n" + eu.getEsp().getNombre() + "\nEn la Base de datos para el usuario " + eu.getUs().getUser(), "Error al desactivar Especialidad", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static ArrayList<EspUs> listarEspUs() {
        ArrayList<EspUs> lista = new ArrayList();
        ArrayList<int[]> laux = new ArrayList();
        ResultSet rs = Main.con.consultar(SQL.listarEspUs());
        try {
            while (rs.next()) {
                int[] n = {rs.getInt("id_es"), rs.getInt("id_us"), rs.getInt("activa")};
                laux.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int[] laux1 : laux) {
            lista.add(new EspUs(ManagerEspecialidad.buscarEspecialidad(laux1[0]), ManagerUsuario.buscarUsuario(laux1[1]), laux1[2]));
        }
        return lista;
    }

    public static ArrayList<EspUs> listarEspUs(Especialidad e) {
        ArrayList<EspUs> lista = new ArrayList();
        ArrayList<Integer[]> laux = new ArrayList();
        ResultSet rs = Main.con.consultar(SQL.listarUsuariosEspecialidad(e.getId_es()));
        try {
            while (rs.next()) {
                Integer[]aux = {rs.getInt("id_us"),rs.getInt("activa")};
                laux.add(aux);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Integer[] aux1: laux) {
            lista.add(new EspUs(e, ManagerUsuario.buscarUsuario(aux1[0]),aux1[1]));
        }
        return lista;
    }

    public static ArrayList<EspUs> listarEspUs(Usuario u) {
        ArrayList<EspUs> lista = new ArrayList();
        ArrayList<Integer[]> laux = new ArrayList();
        ResultSet rs = Main.con.consultar(SQL.listarEspecialidadUsuarios(u.getId()));
        try {
            while (rs.next()) {
                Integer[]aux = {rs.getInt("id_es"),rs.getInt("activa")};
                laux.add(aux);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Integer i[] : laux) {
            lista.add(new EspUs(ManagerEspecialidad.buscarEspecialidad(i[0]), u,i[1]));
        }
        return lista;
    }

    public static Object[] listarEspUsUsuario(EspUs eu) {
        String activa = "";
        if (eu.getActiva() == EspUs.ACTIVA) {
            activa = "Activa";
        } else {
            activa = "Inactiva";
        }
        Object[] o = {eu.getUs().getUser(), ManagerPersona.NombreCompleto(eu.getUs().getPersona()), activa};
        return o;
    }
    
    public static ArrayList<Usuario> listarUsuarios(Especialidad e){
        ArrayList<Usuario> usuarios = new ArrayList();
        ArrayList<String> ltemp = new ArrayList();
        ResultSet rs = Main.con.consultar(SQL.listarUsuarios(e.getId_es()));
        try {
            while(rs.next()){
                usuarios.add(new Usuario(rs.getInt("id_us"),rs.getString("user"),rs.getInt("tipo"),rs.getInt("estado")));
                ltemp.add(rs.getString("ci"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for(int i=0;i<ltemp.size();i++){
            usuarios.get(i).setPersona(ManagerPersona.buscarPersona(ltemp.get(i)));
        }
        return usuarios;
    }
}
