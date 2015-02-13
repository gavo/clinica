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
public class Persona {

    private String ci;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;

    public Persona(String ci, String nombres, String apellidos, String direccion, String telefono) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona() {
        // constructor sin parametros
        this.ci = null;
        this.nombres = null;
        this.apellidos = null;
        this.direccion = null;
        this.telefono = null;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "CI:" + ci + ", Nombre:" + nombres + " " + apellidos + '}';
    }

}
