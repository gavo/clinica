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
public class Rango {
    private int id_ra;
    private String nombre;

    public Rango() {
        //constructor sin parametros        
    }

    public Rango(int id_ra, String nombre) {
        this.id_ra = id_ra;
        this.nombre = nombre.toUpperCase();
    }

    public Rango(String nombre) {
        this.nombre = nombre.toUpperCase();
    }
    
    public int getId_ra() {
        return id_ra;
    }

    public void setId_ra(int id_ra) {
        this.id_ra = id_ra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    @Override
    public String toString() {
        return "Rango{" + "id_ra:" + id_ra + ", nombre:" + nombre + '}';
    }
    
    
}
