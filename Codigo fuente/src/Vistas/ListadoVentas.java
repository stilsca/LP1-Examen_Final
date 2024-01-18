package Vistas;

import Clases.Session;
import Clases.Tools;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ListadoVentas extends javax.swing.JFrame {
    
    public ListadoVentas() {
        initComponents();
    }
    
    public ListadoVentas(Session session) {
        initComponents();
        this.session = session;
        setLocationRelativeTo(null);
        formatoTabla();
    }
    
    private Session session;
    
    private void formatoTabla() {
        String titulos[] = {"Id", "Fecha", "Documento", "Razon social", "Monto", "Estado"};
        DefaultTableModel m;
        m = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tblVentas.setModel(m);
    }
    
    private void comprobante(String idVenta) {
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtHasta = new com.toedter.calendar.JDateChooser();
        txtDesde = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnListar = new javax.swing.JButton();
        btnComprobante = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LP1 - Ventas");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DESDE");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HASTA");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));
        jPanel1.add(txtHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 160, 30));
        jPanel1.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 30));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblVentas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 540, 390));

        btnListar.setBackground(new java.awt.Color(51, 51, 51));
        btnListar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnListar.setForeground(new java.awt.Color(255, 255, 255));
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel1.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 130, 30));

        btnComprobante.setBackground(new java.awt.Color(51, 51, 51));
        btnComprobante.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnComprobante.setForeground(new java.awt.Color(255, 255, 255));
        btnComprobante.setText("Comprobante");
        btnComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobanteActionPerformed(evt);
            }
        });
        jPanel1.add(btnComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 130, 30));

        btnAnular.setBackground(new java.awt.Color(51, 51, 51));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnAnular.setForeground(new java.awt.Color(255, 255, 255));
        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnular, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        if (txtDesde.getCalendar() == null || txtHasta.getCalendar() == null) {
            JOptionPane.showMessageDialog(this, "Ingrese un rango de fecha", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String desde = Tools.toEUDate(txtDesde.getCalendar());
            String hasta = Tools.toEUDate(txtHasta.getCalendar());
            ResultSet ven = session.listar("SELECT ventas.idVenta,fecha,documento,razonSocial,anulado,SUM(precioVenta*cantidad) sub "
                    + "FROM ventas INNER JOIN clientes ON clientes.idCliente=ventas.idCliente INNER JOIN ventas_det ON ventas_det.idVenta=ventas.idVenta "
                    + "WHERE fecha BETWEEN '" + desde + "' AND '" + hasta + "' GROUP BY 1,2,3,4,5");
            String titulos[] = {"Id", "Fecha", "Documento", "Razon social", "Monto", "Estado"};
            DefaultTableModel m;
            m = new DefaultTableModel(null, titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            try {
                String fila[] = new String[6];
                while (ven.next()) {
                    fila[0] = ven.getString("idVenta");
                    fila[1] = ven.getString("fecha");
                    fila[2] = ven.getString("documento");
                    fila[3] = ven.getString("razonSocial");
                    fila[4] = ven.getString("sub");
                    fila[5] = (ven.getString("anulado").equals("1") ? "Anulado" : "Vigente");
                    m.addRow(fila);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            tblVentas.setModel(m);
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobanteActionPerformed
        int row = tblVentas.getSelectedRow();
        if (row >= 0) {
            String idVenta = tblVentas.getValueAt(row, 0).toString();
            comprobante(idVenta);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        }
    }//GEN-LAST:event_btnComprobanteActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        int row = tblVentas.getSelectedRow();
        if (row >= 0) {
            int op = JOptionPane.showConfirmDialog(this, "Desea anular la venta?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                String idVenta = tblVentas.getValueAt(row, 0).toString();
                session.ejecutar("UPDATE ventas SET anulado=1 WHERE idVenta=" + idVenta);
                ResultSet pro = session.listar("SELECT * FROM ventas_det WHERE idVenta=" + idVenta);
                try {
                    while (pro.next()) {
                        session.ejecutar("UPDATE productos SET existencia=existencia+" + pro.getString("cantidad") + " WHERE idProducto=" + pro.getString("idProducto"));
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(ListadoVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(this, "Venta anulada");
                formatoTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        }
    }//GEN-LAST:event_btnAnularActionPerformed

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
            java.util.logging.Logger.getLogger(ListadoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListadoVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnComprobante;
    private javax.swing.JButton btnListar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVentas;
    private com.toedter.calendar.JDateChooser txtDesde;
    private com.toedter.calendar.JDateChooser txtHasta;
    // End of variables declaration//GEN-END:variables
}
