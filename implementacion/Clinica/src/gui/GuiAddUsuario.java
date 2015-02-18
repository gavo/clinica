/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Persona;
import data.Usuario;
import domains.ManagerPersona;
import domains.ManagerUsuario;
import javax.swing.JOptionPane;
import pack.Main;
import utils.ManagerArchivo;

/**
 *
 * @author CKN
 */
public class GuiAddUsuario extends javax.swing.JFrame {

    /**
     * Creates new form GuiAddUsuario
     */
    private static GuiAddUsuario myInstance;
    public static final int GUIADMINUSUARIO = 0;
    private static int tip;
    public static Persona p = null;
    private boolean newPass = true;
    Usuario u;

    private GuiAddUsuario() {
        super("Registrar Usuario");
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private GuiAddUsuario(Usuario u) {
        super("Actualizar Usuario");
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.u = u;
        p = u.getPersona();
        jTextField1.setText(u.getUser());
        jComboBox1.setSelectedIndex(u.getTipo());
        jLabel6.setText(u.getPersona().getNombres() + " " + u.getPersona().getApellidos());
        jComboBox2.setSelectedIndex(u.getEstado());
    }

    public static GuiAddUsuario get(int t) {
        tip = t;
        if (myInstance == null) {
            myInstance = new GuiAddUsuario();
        }
        return myInstance;
    }

    public static GuiAddUsuario get(Usuario u, int t) {
        tip = t;
        if (myInstance == null) {
            myInstance = new GuiAddUsuario(u);
        }
        return myInstance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setText("Usuario :");

        jPasswordField1.setText("jPasswordField1");

        jPasswordField2.setText("jPasswordField2");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Secretaria", "Doctor", "Admin Sis" }));

        jLabel2.setText("Contraseña : ");

        jLabel3.setText("Confirmacion :");

        jLabel4.setText("Tipo Usuario :");

        jLabel5.setText("Persona Asig :");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel6.setText("Ninguna Persona Seleccionada");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setText("Estado : ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inactivo", "Activo" }));

        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 12)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                .addComponent(jPasswordField1)
                                .addComponent(jTextField1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(32, 32, 32)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        myInstance = null;
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        p = ManagerPersona.buscarPersona(JOptionPane.showInputDialog("Ingrese El CI. De la Persona que sera Dueño de la Cuenta"));
        if (p == null) {
            Main.guiPersona = GuiPersona.get(p, GuiPersona.GuiAddUsuario);
        } else {
            jLabel6.setText(p.getNombres() + " " + p.getApellidos());
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private boolean verificacion() {
        if ("".equals(jTextField1.getText())) {
            JOptionPane.showMessageDialog(this, "Necesita asignar un nombre de usuario", "ERROR al Validar Datos", JOptionPane.ERROR_MESSAGE);
            ManagerArchivo.escribirLog("Error: El usuario " + Main.usuario + " intento registrar una cuenta sin nombre de usuario");
            return false;
        }
        if (!jPasswordField1.getText().equals(jPasswordField2.getText())) {
            if ("jPasswordField1".equals(jPasswordField1.getText()) && "jPasswordField2".equals(jPasswordField2.getText())) {
                newPass = false;
            } else {
                JOptionPane.showMessageDialog(this, "La Contraseña y la Validacion de contraseña no coinciden", "ERROR al Validar Datos", JOptionPane.ERROR_MESSAGE);
                ManagerArchivo.escribirLog("Error: El usuario " + Main.usuario + " escribio valores diferentes en contraseña y validacion de contraseña");
                return false;
            }
        }
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Necesita seleccionar una persona para asignarle una persona a la cuenta", "ERROR al Validar Datos", JOptionPane.ERROR_MESSAGE);
            ManagerArchivo.escribirLog("Error: El usuario " + Main.usuario + " intento registrar una cuenta sin asignarle una persona como dueña de la cuenta");
            return false;
        }
        return true;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificacion()) {
            boolean actualizar = true;
            if (u == null) {
                u = new Usuario();
                actualizar = false;
            }
            u.setUser(jTextField1.getText());
            if (newPass) {
                u.setPass(jPasswordField1.getText());
            }
            u.setTipo(jComboBox1.getSelectedIndex());
            u.setPersona(p);
            u.setEstado(jComboBox2.getSelectedIndex());
            if (actualizar) {
                ManagerUsuario.actualizarUsuario(u);
            } else {
                ManagerUsuario.registrarUsuario(u);
            }
            if (tip == GUIADMINUSUARIO) {
                GuiAdminUsuarios.jButton1.doClick();
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
