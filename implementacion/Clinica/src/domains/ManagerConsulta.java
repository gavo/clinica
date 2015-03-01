/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import data.Consulta;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import pack.Main;
import utils.ManagerArchivo;
import utils.SQL;

/**
 *
 * @author CKN
 */
public abstract class ManagerConsulta {

    public static int numeroFichaSiguiente(Date fecha) {
        int r = 1;
        ResultSet rs = Main.con.consultar(SQL.consultarNFicha(fecha));
        try {
            while (rs.next()) {
                r = rs.getInt("ficha");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }
    
    public static void registrarConsulta(Consulta c){
        if(SQL.pregunta("Desea registrar la Consulta?")){
            if(Main.con.ejecutar(SQL.registrarConsulta(c.getAtencion(),c.getCosto(),c.getCi().getCi(),Main.usuario.getId(),c.getId_us_a().getId()))){
                ManagerArchivo.escribirLog("Se registro correctamente la consulta "+c);
                JOptionPane.showMessageDialog(null, "La Consulta fue registrada Exitosamente\n"+c, "Consulta Registrada", JOptionPane.INFORMATION_MESSAGE);
            }else{
                ManagerArchivo.escribirLog("Error: No se pudo registrar la consulta "+c);
                JOptionPane.showMessageDialog(null, "No Se pudo registrar la consulta \n"+c, "Error al registrar Consulta", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
