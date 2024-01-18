package Vistas;

import Clases.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Clientes extends javax.swing.JFrame {

    public Clientes() {
        initComponents();
        activar(true);
    }

    public Clientes(Session session) {
        initComponents();
        setLocationRelativeTo(null);
        activar(true);
        this.session = session;
        cargarDatos("");
    }

    private Session session;

    private void cargarDatos(String val) {
        String sentencia = "SELECT * FROM clientes WHERE razonSocial LIKE '%" + val + "%' OR documento='%" + val + "%'";
        ResultSet datos = session.getCon().listar(sentencia);
        String titulos[] = {"Id", "Documento", "Razon social"};
        DefaultTableModel m;
        m = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        String fila[] = new String[3];
        try {
            while (datos.next()) {
                fila[0] = datos.getString("idCliente");
                fila[1] = datos.getString("documento");
                fila[2] = datos.getString("razonSocial");
                m.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tblClientes.setModel(m);
    }

    private void activar(boolean a) {
        btnNuevo.setEnabled(a);
        btnModificar.setEnabled(a);
        btnGuardar.setEnabled(!a);
        btnCancelar.setEnabled(!a);
        btnEliminar.setEnabled(a);
        txtIdCliente.setEditable(!a);
        txtContacto.setEditable(!a);
        txtDireccion.setEditable(!a);
        txtDocumento.setEditable(!a);
        txtRazon.setEditable(!a);
        chkEstado.setEnabled(!a);
        txtBusqueda.setEditable(a);
    }

    private void limpiar() {
        txtContacto.setText("");
        txtIdCliente.setText("");
        txtBusqueda.setText("");
        txtDireccion.setText("");
        txtRazon.setText("");
        txtDocumento.setText("");
        chkEstado.setSelected(true);
        chkEstado.setText("ACTIVO");
        chkEstado.setForeground(new java.awt.Color(0, 255, 51));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        txtRazon = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        chkEstado = new javax.swing.JToggleButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LP1 - Clientes");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BUSCAR");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtContacto.setBackground(new java.awt.Color(0, 102, 102));
        txtContacto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtContacto.setForeground(new java.awt.Color(255, 255, 255));
        txtContacto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 320, 30));

        txtIdCliente.setBackground(new java.awt.Color(0, 102, 102));
        txtIdCliente.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtIdCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtIdCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 320, 30));

        btnGuardar.setBackground(new java.awt.Color(51, 51, 51));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 90, 30));

        btnNuevo.setBackground(new java.awt.Color(51, 51, 51));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 90, 30));

        btnCancelar.setBackground(new java.awt.Color(51, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 90, 30));

        btnModificar.setBackground(new java.awt.Color(51, 51, 51));
        btnModificar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 90, 30));

        btnEliminar.setBackground(new java.awt.Color(51, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 90, 30));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblClientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 530, 280));

        txtBusqueda.setBackground(new java.awt.Color(0, 102, 102));
        txtBusqueda.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 200, 30));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DOCUMENTO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("RAZON SOCIAL");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DIRECCION");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ESTADO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        txtDocumento.setBackground(new java.awt.Color(0, 102, 102));
        txtDocumento.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtDocumento.setForeground(new java.awt.Color(255, 255, 255));
        txtDocumento.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 320, 30));

        txtRazon.setBackground(new java.awt.Color(0, 102, 102));
        txtRazon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtRazon.setForeground(new java.awt.Color(255, 255, 255));
        txtRazon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtRazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 320, 30));

        txtDireccion.setBackground(new java.awt.Color(0, 102, 102));
        txtDireccion.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 320, 30));

        chkEstado.setForeground(new java.awt.Color(0, 255, 51));
        chkEstado.setSelected(true);
        chkEstado.setText("ACTIVO");
        chkEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 90, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("CONTACTO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String idCliente = txtIdCliente.getText().trim();
        String documento = txtDocumento.getText().trim();
        String razon = txtRazon.getText().trim();
        if (razon.length() > 0 && documento.length() > 0) {
            int activo = chkEstado.isSelected() ? 1 : 0;
            if (idCliente.length() > 0) {
                session.getCon().ejecutar("UPDATE clientes SET documento='" + documento + "',razonSocial='" + razon + "',direccion='" + txtDireccion.getText() + "',contacto='" + txtContacto.getText() + "',activo=" + activo + " WHERE idCliente=" + idCliente);
            } else {
                session.getCon().ejecutar("INSERT INTO clientes (documento,razonSocial,direccion,contacto,activo) VALUES ('" + documento + "','" + razon + "','" + txtDireccion.getText() + "','" + txtContacto.getText() + "'," + activo + ")");
            }
            cargarDatos("");
            limpiar();
            activar(true);

        } else {
            JOptionPane.showMessageDialog(this, "Ingrese documento y razon social del cliente");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        activar(false);
        limpiar();
        txtIdCliente.setEditable(false);
        txtDocumento.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        activar(true);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int row = tblClientes.getSelectedRow();
        if (row >= 0) {
            activar(false);
            String idCliente = tblClientes.getValueAt(row, 0).toString();
            ResultSet cli = session.listar("SELECT * FROM clientes WHERE idCliente=" + idCliente);
            try {
                if (cli.next()) {
                    txtIdCliente.setText(idCliente);
                    txtDocumento.setText(cli.getString("documento"));
                    txtRazon.setText(cli.getString("razonSocial"));
                    txtDireccion.setText(cli.getString("direccion"));
                    txtContacto.setText(cli.getString("contacto"));
                    if (cli.getInt("activo") == 1) {
                        chkEstado.setSelected(true);
                        chkEstado.setText("ACTIVO");
                        chkEstado.setForeground(new java.awt.Color(0, 255, 51));
                    } else {
                        chkEstado.setSelected(false);
                        chkEstado.setText("INACTIVO");
                        chkEstado.setForeground(new java.awt.Color(255, 0, 0));
                    }
                    txtIdCliente.setEditable(false);
                    txtDocumento.requestFocus();
                    txtDocumento.selectAll();
                }
            } catch (SQLException ex) {

            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para editar");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int row = tblClientes.getSelectedRow();
        if (row >= 0) {
            String idCliente = tblClientes.getValueAt(row, 0).toString();
            int op = JOptionPane.showConfirmDialog(this, "Eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                ResultSet uso = session.getCon().listar("SELECT * FROM ventas WHERE idCliente=" + idCliente);
                try {
                    if (!uso.next()) {
                        session.getCon().ejecutar("DELETE FROM clientes WHERE idCliente=" + idCliente);
                    } else {
                        JOptionPane.showMessageDialog(this, "El registro esta en uso, no es posible eliminar", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (SQLException ex) {

                }
                cargarDatos("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        cargarDatos(txtBusqueda.getText());
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void chkEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEstadoActionPerformed
        if (chkEstado.isSelected()) {
            chkEstado.setText("ACTIVO");
            chkEstado.setForeground(new java.awt.Color(0, 255, 51));
        } else {
            chkEstado.setText("INACTIVO");
            chkEstado.setForeground(new java.awt.Color(255, 0, 0));
        }
    }//GEN-LAST:event_chkEstadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JToggleButton chkEstado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtRazon;
    // End of variables declaration//GEN-END:variables
}
