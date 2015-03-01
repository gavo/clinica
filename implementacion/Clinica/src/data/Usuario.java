/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author CKN
 */
public class Usuario {

    public static final int ADMIN_SIS = 2;
    public static final int DOCTOR = 1;
    public static final int SECRETARIA = 0;
    private int id_us;
    private String user;
    private String pass;
    private int tipo;
    private Persona persona;
    private int estado;

    public Usuario(int id, String user, String pass, int tipo, Persona persona, int estado) {
        this.id_us = id;
        this.user = user;
        this.pass = pass;
        this.estado = estado;
        this.persona = persona;
        this.tipo = tipo;
    }

    public Usuario(int id, String user, String pass, int tipo, int estado) {
        this.id_us = id;
        this.user = user;
        this.pass = pass;
        this.estado = estado;
        this.persona = null;
        this.tipo = tipo;
    }

    public Usuario(String user, String pass, int tipo, Persona persona) {
        this.user = user;
        this.pass = pass;
        this.persona = persona;
        this.tipo = tipo;
    }

    public Usuario(int id,String user, int tipo, int estado) {
        this.id_us = id;
        this.user = user;
        this.estado = estado;
        this.tipo = tipo;
    }

    public Usuario() {
        // constructor sin parametros
    }

    public int getId() {
        return id_us;
    }

    public void setId(int id) {
        this.id_us = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id_us + ", user=" + user + ", estado=" + estado + ", persona=" + persona + '}';
    }
}
