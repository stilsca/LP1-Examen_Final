package Vistas;

import Clases.Session;
import Clases.Tools;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Ventas extends javax.swing.JFrame {
    
    public Ventas() {
        initComponents();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        txtFecha.setText(dtf.format(localDate));
        txtUsuario.setText("-");
    }
    
    public Ventas(Session session) {
        initComponents();
        this.session = session;
        setLocationRelativeTo(null);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        txtFecha.setText(dtf.format(localDate));
        txtUsuario.setText(session.getUsuario());
        formatoTabla();
        cargarClientes();
        cargarCondiciones();
        txtCodigo.requestFocus();
    }
    
    private Session session;
    
    private ArrayList<Integer> idClientes;
    private ArrayList<Integer> idCondiciones;
    
    public void cargarClientes() {
        idClientes = new ArrayList();
        ResultSet esta = session.getCon().listar("SELECT * FROM clientes ORDER BY razonSocial");
        Vector<String> cus = new Vector();
        try {
            while (esta.next()) {
                cus.add(esta.getString("documento") + " - " + esta.getString("razonSocial"));
                idClientes.add(esta.getInt("idCliente"));
            }
        } catch (SQLException ex) {
            //Log.log(ex, this);
        }
        DefaultComboBoxModel mdl = new DefaultComboBoxModel(cus);
        cmbCliente.setModel(mdl);
    }
    
    public void cargarCondiciones() {
        idCondiciones = new ArrayList();
        ResultSet esta = session.getCon().listar("SELECT * FROM condiciones");
        Vector<String> cus = new Vector();
        try {
            while (esta.next()) {
                cus.add(esta.getString("nombreCondicion"));
                idCondiciones.add(esta.getInt("idCondicion"));
            }
        } catch (SQLException ex) {
            //Log.log(ex, this);
        }
        DefaultComboBoxModel mdl = new DefaultComboBoxModel(cus);
        cmbCondicion.setModel(mdl);
    }
    
    private void limpiar() {
        txtPrecio.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtCodigo.setText("");
    }
    
    private int total = 0;
    private int contador = 0;
    
    private void agregar() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad > 0) {
                if (idProducto > 0) {
                    DefaultTableModel m = (DefaultTableModel) tblItems.getModel();
                    String fila[] = new String[5];
                    fila[0] = idProducto + "";
                    fila[1] = cantidad + "";
                    fila[2] = txtProducto.getText();
                    fila[3] = txtPrecio.getText();
                    int sub = Integer.parseInt(txtPrecio.getText()) * cantidad;
                    fila[4] = sub + "";
                    total += sub;
                    contador++;
                    lblTotal.setText("TOTAL: " + total + " Gs.");
                    lblContador.setText("TOTAL PRODUCTOS: " + contador);
                    m.addRow(fila);
                    limpiar();
                    txtCodigo.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado");
                    txtCodigo.requestFocus();
                    txtCodigo.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cantidad no valida");
                txtCantidad.requestFocus();
                txtCantidad.selectAll();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad no valida");
            txtCantidad.requestFocus();
            txtCantidad.selectAll();
        }
    }
    
    public void setCodigo(String cod) {
        txtCodigo.setText(cod);
    }
    
    private void formatoTabla() {
        String titulos[] = {"Id", "Cantidad", "Producto", "Precio Unit", "Sub total"};
        DefaultTableModel m;
        m = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tblItems.setModel(m);
    }
    
    private int idProducto = 0;
    
    private void comprobante(int idVenta) {
        try {
            String ruta = System.getProperty("user.dir");
            JasperPrint jasperPrint;
            Map para = new HashMap();
            
            para.put("p_idVenta", idVenta + "");
            
            String reportDest = ruta + "/src/Reportes/Comprobante.jasper";
            jasperPrint = JasperFillManager.fillReport(reportDest, para, session.getCon().getConexion());
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Comprobante");
            jv.setVisible(true);
        } catch (JRException ex) {
            //Logger.getLogger(MyHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void buscarCodigo(String cod) {
        ResultSet bus = session.listar("SELECT * FROM productos WHERE codigo='" + cod + "' OR idProducto='" + cod + "'");
        try {
            limpiar();
            if (bus.next()) {
                txtProducto.setText(bus.getString("nombreProducto"));
                txtPrecio.setText(bus.getInt("precio") + "");
                txtCantidad.setText("1");
                txtCantidad.requestFocus();
                txtCantidad.selectAll();
                idProducto = bus.getInt("idProducto");
                txtCodigo.setText(cod);
            } else {
                idProducto = 0;
                JOptionPane.showMessageDialog(this, "Codigo no existe");
                txtCodigo.requestFocus();
                txtCodigo.selectAll();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmbCondicion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        txtFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnVender = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lblContador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LP1 - Vender");

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("USUARIO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(0, 102, 102));
        txtUsuario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 130, 30));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CONDICION");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        txtCantidad.setBackground(new java.awt.Color(0, 102, 102));
        txtCantidad.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(255, 255, 255));
        txtCantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 100, 30));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CLIENTE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 710, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 8)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("F6 - Buscador");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        cmbCondicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 190, 40));

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblItems);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 820, 250));

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(0, 102, 102));
        txtFecha.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, 30));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("FECHA");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CANT");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        txtCodigo.setBackground(new java.awt.Color(0, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, 30));

        txtProducto.setEditable(false);
        txtProducto.setBackground(new java.awt.Color(0, 102, 102));
        txtProducto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtProducto.setForeground(new java.awt.Color(255, 255, 255));
        txtProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 310, 30));

        lblTotal.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("TOTAL: - Gs.");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 200, 50));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("PRODUCTO");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("PRE. UNIT.");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        txtPrecio.setEditable(false);
        txtPrecio.setBackground(new java.awt.Color(0, 102, 102));
        txtPrecio.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 100, 30));

        btnVender.setBackground(new java.awt.Color(51, 51, 51));
        btnVender.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnVender.setForeground(new java.awt.Color(255, 255, 255));
        btnVender.setText("VENDER");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        jPanel1.add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, 90, 30));

        btnAgregar.setBackground(new java.awt.Color(51, 51, 51));
        btnAgregar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 90, 30));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CODIGO");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        lblContador.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblContador.setForeground(new java.awt.Color(255, 255, 255));
        lblContador.setText("TOTAL PRODUCTOS: -");
        jPanel1.add(lblContador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 200, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        if (tblItems.getRowCount() > 0) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            String fecha = dtf.format(localDate);
            session.ejecutar("INSERT INTO ventas (idCliente,fecha,anulado,idUsuario,idCondicion) VALUES (" + idClientes.get(cmbCliente.getSelectedIndex()) + ",'" + fecha + "',0," + session.getIdUsuario() + "," + idCondiciones.get(cmbCondicion.getSelectedIndex()) + ")");
            ResultSet venta = session.listar("SELECT MAX(idVenta) id FROM ventas");
            try {
                if (venta.next()) {
                    int idVenta = venta.getInt("id");
                    for (int i = 0; i < tblItems.getRowCount(); i++) {
                        String idProducto = tblItems.getValueAt(i, 0).toString();
                        String cant = tblItems.getValueAt(i, 1).toString();
                        String precio = tblItems.getValueAt(i, 3).toString();
                        ResultSet pro = session.listar("SELECT * FROM productos INNER JOIN impuestos ON impuestos.idImpuesto=productos.idImpuesto WHERE idProducto=" + idProducto);
                        if (pro.next()) {
                            String imp = pro.getString("porcentaje");
                            session.ejecutar("INSERT INTO ventas_det (idVenta,idProducto,cantidad,precioVenta,impuesto) VALUES (" + idVenta + "," + idProducto + "," + cant + "," + precio + "," + imp + ")");
                            session.ejecutar("UPDATE productos SET existencia=existencia-" + cant + " WHERE idProducto=" + idProducto);
                        }
                        comprobante(idVenta);
                        new Ventas(session).setVisible(true);
                        dispose();
                    }
                }
            } catch (SQLException ex) {
                //Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Venta vacia");
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && txtCodigo.getText().length() > 0) {
            buscarCodigo(txtCodigo.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_F6) {
            new Buscador(session, this).setVisible(true);
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            agregar();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

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
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbCondicion;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblContador;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblItems;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
