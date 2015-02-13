/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.Rango;
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
public abstract class ManagerRango {
    
    public static Rango buscarRango(String nombreRango){
        Rango rango = null;
        ResultSet rs = Main.con.consultar(SQL.buscarRango(nombreRango));
        try {
            while(rs.next()){
                rango = new Rango(rs.getInt("id_ra"),rs.getString("nombre"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rango;
    }
    
    public static void insertarRango(Rango rango){
        if(buscarRango(rango.getNombre())==null){
            if (SQL.pregunta("Desea registrar el rango \"" + rango.getNombre() + "\" En la Base de Datos")) {
                Main.con.ejecutar(SQL.registrarRango(rango.getNombre()));
            }
        }else{
            JOptionPane.showMessageDialog(null, "Existe otro rango con el mismo nombre registrado en la DB", "Error al Registrar Rango", JOptionPane.ERROR_MESSAGE);
            ManagerArchivo.escribirLog("No se pudo registrar el Rango ->"+rango);
        }
    }
    
    public static void modificarRango(Rango rango){
        if(buscarRango(rango.getNombre())==null){
            if (SQL.pregunta("Desea Modificar el rango \"" + rango.getNombre() + "\" En la Base de Datos")) {
                Main.con.ejecutar(SQL.modificarRango(rango.getId_ra(),rango.getNombre()));
                ManagerArchivo.escribirLog("Rango modificado ->"+rango);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Existe otro rango con el mismo nombre registrado en la DB", "Error al Modificar Rango", JOptionPane.ERROR_MESSAGE);
            ManagerArchivo.escribirLog("Se intento modificar rango sin exito ->"+rango);
        }
    }
    
    public static ArrayList<Rango> listarRangos(){
        ArrayList<Rango> rangos = new ArrayList();
        ResultSet rs = Main.con.consultar(SQL.listarRangos());
        try {
            while(rs.next()){
                rangos.add(new Rango(rs.getInt("id_ra"),rs.getString("nombre")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rangos;
    }
}
