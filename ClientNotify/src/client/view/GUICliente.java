package client.view;

import common.entities.NotifyDTO;
import javax.swing.DefaultListModel;
/**
 *
 * @author fabian
 */
public class GUICliente extends javax.swing.JFrame {
    private DefaultListModel model;
    
    public GUICliente() {
        initComponents();
        this.model = new DefaultListModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNuevaAlerta = new javax.swing.JPanel();
        lblNuevaAlerta = new javax.swing.JLabel();
        pnlMensajeAlerta = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNuevaAlerta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNuevaAlerta.setText("NUEVA ALERTA");

        javax.swing.GroupLayout pnlNuevaAlertaLayout = new javax.swing.GroupLayout(pnlNuevaAlerta);
        pnlNuevaAlerta.setLayout(pnlNuevaAlertaLayout);
        pnlNuevaAlertaLayout.setHorizontalGroup(
            pnlNuevaAlertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevaAlertaLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(lblNuevaAlerta)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        pnlNuevaAlertaLayout.setVerticalGroup(
            pnlNuevaAlertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevaAlertaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNuevaAlerta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("PEDIDO A REALIZAR");

        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        javax.swing.GroupLayout pnlMensajeAlertaLayout = new javax.swing.GroupLayout(pnlMensajeAlerta);
        pnlMensajeAlerta.setLayout(pnlMensajeAlertaLayout);
        pnlMensajeAlertaLayout.setHorizontalGroup(
            pnlMensajeAlertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMensajeAlertaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(88, 88, 88))
            .addGroup(pnlMensajeAlertaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMensajeAlertaLayout.setVerticalGroup(
            pnlMensajeAlertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensajeAlertaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNuevaAlerta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMensajeAlerta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNuevaAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMensajeAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void showNotification(NotifyDTO notify) {
        GUICliente gui = new GUICliente();
        for (int i = 0; i < notify.getPedidos().size(); i++) {
            gui.area.append("Nombre Hamburguesa: " +notify.getPedidos().get(i).getIdentificador());
            gui.area.append("\n");
            gui.area.append("Tipo Hamburguesa: " +notify.getPedidos().get(i).getTipo());
            gui.area.append("\n");
            gui.area.append("Cantidad de ingredientes extra: " +notify.getPedidos().get(i).getCantIngExtra());
            gui.area.append("\n");
            gui.area.append("--------------------------");
            gui.area.append("\n");
        gui.setVisible(true);
       }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNuevaAlerta;
    private javax.swing.JPanel pnlMensajeAlerta;
    private javax.swing.JPanel pnlNuevaAlerta;
    // End of variables declaration//GEN-END:variables
}