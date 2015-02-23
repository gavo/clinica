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
public class Consulta {

    public static final int PENDIENTE = 0;
    public static final int ATENDIDO = 1;

    private int id_con;
    private String registrado;
    private String atencion;
    private float costo;
    private String detalle;
    private Persona ci;
    private Usuario id_us_r;
    private Usuario id_us_a;
    private int estado;

    public Consulta() {
        //Constructor Sin Parametros
    }

    public Consulta(int id_con, String registrado, String atencion, float costo, String detalle, Persona ci, Usuario id_us_r, Usuario id_us_a, int estado) {
        this.id_con = id_con;
        this.registrado = registrado;
        this.atencion = atencion;
        this.costo = costo;
        this.detalle = detalle;
        this.ci = ci;
        this.id_us_r = id_us_r;
        this.id_us_a = id_us_a;
        this.estado = estado;
    }

    public Consulta(String atencion, float costo, Persona ci, Usuario id_us_r, Usuario id_us_a) {
        this.atencion = atencion;
        this.costo = costo;
        this.ci = ci;
        this.id_us_r = id_us_r;
        this.id_us_a = id_us_a;
    }

    public int getId_con() {
        return id_con;
    }

    public void setId_con(int id_con) {
        this.id_con = id_con;
    }

    public String getRegistrado() {
        return registrado;
    }

    public void setRegistrado(String registrado) {
        this.registrado = registrado;
    }

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Persona getCi() {
        return ci;
    }

    public void setCi(Persona ci) {
        this.ci = ci;
    }

    public Usuario getId_us_r() {
        return id_us_r;
    }

    public void setId_us_r(Usuario id_us_r) {
        this.id_us_r = id_us_r;
    }

    public Usuario getId_us_a() {
        return id_us_a;
    }

    public void setId_us_a(Usuario id_us_a) {
        this.id_us_a = id_us_a;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String est = "PENDIENTE";
        if (estado == ATENDIDO) {
            est = "ATENDIDO";
        }
        return "Consulta{" + "atencion=" + atencion + ", costo=" + costo + ", ci=" + ci.getNombres() + " " + ci.getApellidos() + ", id_us_a=" + id_us_a.getUser() + ", estado=" + est + '}';
    }
}
