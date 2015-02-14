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
public class Especialidad {
    private int id_es;
    private String nombre;
    private float costo;

    public Especialidad(int id_es, String nombre, float costo) {
        this.id_es = id_es;
        this.nombre = nombre.toUpperCase();
        this.costo = costo;
    }

    public Especialidad(String nombre, float costo) {
        this.nombre = nombre.toUpperCase();
        this.costo = costo;
    }

    public int getId_es() {
        return id_es;
    }

    public void setId_es(int id_es) {
        this.id_es = id_es;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Especialidad{" + "id_es=" + id_es + ", nombre=" + nombre + ", costo=" + costo + '}';
    }    
}
