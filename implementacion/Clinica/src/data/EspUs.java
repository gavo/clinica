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
public class EspUs {

    private Especialidad esp;
    private Usuario us;
    private int activa;
    public static final int ACTIVA = 1;
    public static final int INACTIVA = 0;
    public static final int NOEXISTE = -1;

    public EspUs(Especialidad esp, Usuario us) {
        this.esp = esp;
        this.us = us;
        activa = ACTIVA;
    }

    public EspUs(Especialidad esp, Usuario us, int activa) {
        this.esp = esp;
        this.us = us;
        this.activa = activa;
    }

    public Especialidad getEsp() {
        return esp;
    }

    public void setEsp(Especialidad esp) {
        this.esp = esp;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        if (activa == ACTIVA) {
            return "EspUs ACTIVA{" + "esp=" + esp.getNombre() + ", us=" + us.getUser() + "" + us.getPersona().getNombres() + " " + us.getPersona().getApellidos() + '}';
        } else {
            return "EspUs NoActiva{" + "esp=" + esp.getNombre() + ", us=" + us.getUser() + "" + us.getPersona().getNombres() + " " + us.getPersona().getApellidos() + '}';
        }

    }
}
