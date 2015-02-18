/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import data.Usuario;
import gui.GuiAddUsuario;
import gui.GuiPersona;
import gui.Login;
import utils.Conexion;
import utils.ManagerArchivo;

/**
 *
 * @author CKN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        con = Conexion.getInstance();
        Login l = new Login();
    }
    
    public static void iniciarSecion(){
        //no implementado todavia
    }
    
    public static String user;
    public static String host;
    public static String db;
    public static String password;
    public static String dirDoc;
    public static ManagerArchivo managerArchivo = new ManagerArchivo();
    public static Conexion con;
    public static GuiPersona guiPersona;
    public static Usuario usuario;
    public static GuiAddUsuario guiAddUsuario;
}
