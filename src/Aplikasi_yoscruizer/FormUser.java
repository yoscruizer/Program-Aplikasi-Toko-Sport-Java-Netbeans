/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aplikasi_yoscruizer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yosep alfatah
 */
public class FormUser extends javax.swing.JFrame {
    FormDataUser formDataUser;
    String status, id;
    
    /**
     * Creates new form FormJenis
     */
    public FormUser() {
        initComponents();
    }
    
    public FormUser(FormDataUser formDataUser) {
        initComponents();
        this.formDataUser=formDataUser;
        status = "SIMPAN";
        tombolSimpan.setText("Simpan");
        textUser.enable();
        this.setTitle("Tambah Data User");
    }
    
    public FormUser(FormDataUser formDataUser, String id) {
        initComponents();
        this.formDataUser=formDataUser;
        this.id=id;
        status = "UPDATE";
        tombolSimpan.setText("Update");
        textUser.disable();
        this.setTitle("Edit Data User"); 
        isiData(id);
    }
    
    public void isiData(String cari) {
        AksiDB aksidb = new AksiDB();
        String query ="SELECT * FROM t_user WHERE user_name = '" + cari + "'";
        int indek;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex){
            System.out.println("Driver tidak ditemukan... " + ex);
        }
        try {
            aksidb.dbCon = DriverManager.getConnection(aksidb.dbURL, aksidb.username, 
                    aksidb.password);
            aksidb.stmt = aksidb.dbCon.createStatement();
            aksidb.rs = aksidb.stmt.executeQuery(query);
            while(aksidb.rs.next()){
                textUser.setText(aksidb.rs.getString("user_name"));
                textPass.setText(aksidb.rs.getString("user_pass"));
                textPass2.setText(aksidb.rs.getString("user_pass"));
                indek = Integer.parseInt(aksidb.rs.getString("user_tipe"));
                comboStatus.setSelectedIndex(indek);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
           try {
                 aksidb.rs.close();
                 aksidb.stmt.close();
                 aksidb.dbCon.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
    public void simpanData() {
        AksiDB aksidb = new AksiDB();
        String query ="INSERT INTO t_user(user_name, user_pass, user_tipe) " 
                + "VALUES('" + textUser.getText() + "', '" 
                + textPass.getText() + "', " 
                + comboStatus.getSelectedIndex() + ")";
        System.out.println(query);
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex){
            System.out.println("Driver tidak ditemukan... " + ex);
        }   
        try {
            aksidb.dbCon = DriverManager.getConnection(aksidb.dbURL, aksidb.username, 
                    aksidb.password);
            aksidb.stmt = aksidb.dbCon.createStatement();
            aksidb.stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
            try {
                 aksidb.stmt.close();
                 aksidb.dbCon.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
             }
            dispose();
            formDataUser.tampilData("");
            JOptionPane.showMessageDialog(null,"Data Sudah di-Simpan.", 
                    "Konfirmasi",JOptionPane.INFORMATION_MESSAGE );
        }
    }  
    
    public void updateData() {
        AksiDB aksidb = new AksiDB();
        String query ="UPDATE t_user SET "
                + "user_tipe = " + comboStatus.getSelectedIndex() + ", " 
                + "user_pass = '" + textPass.getText() + "'"  
                + " WHERE user_name = '" + textUser.getText() + "'";
        System.out.println(query);
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex){
            System.out.println("Driver tidak ditemukan... " + ex);
        }   
        try {
            aksidb.dbCon = DriverManager.getConnection(aksidb.dbURL, aksidb.username, 
                    aksidb.password);
            aksidb.stmt = aksidb.dbCon.createStatement();
            aksidb.stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
            try {
                 aksidb.stmt.close();
                 aksidb.dbCon.close();
             } catch (SQLException ex) {
                 Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
             }
            dispose();
            formDataUser.tampilData("");
            JOptionPane.showMessageDialog(null,"Data Sudah di-Update.", 
                    "Konfirmasi",JOptionPane.INFORMATION_MESSAGE );
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textUser = new javax.swing.JTextField();
        tombolKeluar = new javax.swing.JButton();
        tombolSimpan = new javax.swing.JButton();
        comboStatus = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        textPass = new javax.swing.JPasswordField();
        textPass2 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input Data User");

        jLabel1.setText("Username :");

        jLabel2.setText("Password :");

        jLabel3.setText("Re-Password :");

        tombolKeluar.setText("Batal");
        tombolKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolKeluarActionPerformed(evt);
            }
        });

        tombolSimpan.setText("Simpan");
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "User" }));

        jLabel4.setText("Status :");

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textUser)
                            .addComponent(comboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textPass)
                            .addComponent(textPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tombolSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tombolKeluar)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolKeluar)
                    .addComponent(tombolSimpan))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        if(status.equals("UPDATE")){
            updateData();
        }else{
            simpanData();
        }
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void tombolKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_tombolKeluarActionPerformed

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
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField textPass;
    private javax.swing.JPasswordField textPass2;
    private javax.swing.JTextField textUser;
    private javax.swing.JButton tombolKeluar;
    private javax.swing.JButton tombolSimpan;
    // End of variables declaration//GEN-END:variables
}
