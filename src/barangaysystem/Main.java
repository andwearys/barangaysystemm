/*
 * GREETINGS! WE ARE GROUP II FROM COMPUTER PROGRAMMING III
 * BARANGAY SYSTEM
 * WE ARE:
 * GARRATA (CODER)
 * SERATO  (CODER)
 * NUNEZ   (CODER)
 * ANDAYA  (CODER, DESIGNER)
 */

package barangaysystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class Main extends javax.swing.JFrame {
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        lblPopulation.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblUsername.setVisible(false);
        lblUserID.setVisible(false);
        lblUserType.setVisible(false);
        jMenuItem1.setEnabled(false);
        jMenuItem2.setEnabled(false);
        String Sql="SELECT count(ResidentID) FROM residents ";
        Connection conn=MySqlConnect.ConnectDB();
        try{
            pst=conn.prepareStatement(Sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String sum = rs.getString("count(ResidentID)");
                lblPopulation.setText("000"+sum);
                conn.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
          //  JOptionPane.showMessageDialog(null, e);
        }
        
        showDate();
        showTime();
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
        lblDate.setText(s.format(d));
    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
        lblTime.setText(s.format(d));
            }
            
        }
        ).start();
    }
    
    void report(){
        Connection con=null;
        con=SqlConnectReports.ConnectDB();
        String report = "INSERT INTO `audittrail`(`Date`, `Time`, `User Name`, `Action Performed`, `User ID`, `AuditTrailType`) VALUES (?,?,?,?,?,?)";
            Date d = new Date();        
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat c = new SimpleDateFormat("hh:mm:ss");
            String date = (s.format(d));
            String time = (c.format(d));
    try{
            pst=con.prepareStatement(report);
            pst.setString(1, date);
            pst.setString(2, time);
            pst.setString(3, lblLoginAs.getText());
            pst.setString(4, "LOGOUT");
            pst.setString(5, lblUserID.getText());
            //pst.setString(5, "");
            pst.setString(6, "Logs");
            pst.executeUpdate();
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
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

        jMenu2 = new javax.swing.JMenu();
        lblLoginAs1 = new javax.swing.JLabel();
        lblUserID = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCurrentPopulation = new javax.swing.JLabel();
        lblPopulation = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblLoginAs = new javax.swing.JLabel();
        lblLogoutHover = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        lblDocumentsHover = new javax.swing.JLabel();
        lblDocuments = new javax.swing.JLabel();
        lblUsersHover = new javax.swing.JLabel();
        lblUsers = new javax.swing.JLabel();
        lblResidentsHover = new javax.swing.JLabel();
        lblResidents = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBackround = new javax.swing.JLabel();
        lblCurrentPopulation1 = new javax.swing.JLabel();
        lblCurrentPopulation2 = new javax.swing.JLabel();
        lblCurrentPopulation3 = new javax.swing.JLabel();
        lblCurrentPopulation4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        lblLoginAs1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLoginAs1.setForeground(new java.awt.Color(255, 255, 255));
        lblLoginAs1.setText("Welcome, ");

        lblUserID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUserID.setForeground(new java.awt.Color(255, 255, 255));
        lblUserID.setText("UserID");

        lblUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Username");

        lblUserType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUserType.setForeground(new java.awt.Color(255, 255, 255));
        lblUserType.setText("UserType");
        lblUserType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblUserTypePropertyChange(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Barangay Records System");
        setMaximumSize(new java.awt.Dimension(860, 500));
        setResizable(false);

        jPanel1.setLayout(null);

        lblCurrentPopulation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurrentPopulation.setText("Current Population:");
        jPanel1.add(lblCurrentPopulation);
        lblCurrentPopulation.setBounds(230, 190, 210, 29);

        lblPopulation.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblPopulation.setText("00000");
        lblPopulation.setToolTipText("Total Population of Barangay");
        lblPopulation.setAutoscrolls(true);
        jPanel1.add(lblPopulation);
        lblPopulation.setBounds(50, 220, 470, 30);

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date");
        jPanel1.add(lblDate);
        lblDate.setBounds(630, 200, 170, 20);

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("Time");
        jPanel1.add(lblTime);
        lblTime.setBounds(630, 230, 180, 20);

        lblLoginAs.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblLoginAs.setForeground(new java.awt.Color(255, 255, 255));
        lblLoginAs.setText("Name");
        jPanel1.add(lblLoginAs);
        lblLoginAs.setBounds(690, 10, 210, 20);

        lblLogoutHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/logout.png"))); // NOI18N
        lblLogoutHover.setToolTipText("Logout");
        lblLogoutHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogoutHoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogoutHoverMouseExited(evt);
            }
        });
        jPanel1.add(lblLogoutHover);
        lblLogoutHover.setBounds(690, 320, 110, 110);

        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/logouthover.png"))); // NOI18N
        lblLogout.setToolTipText("Logout");
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogoutMouseExited(evt);
            }
        });
        jPanel1.add(lblLogout);
        lblLogout.setBounds(690, 330, 110, 110);

        lblDocumentsHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/documents.png"))); // NOI18N
        lblDocumentsHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDocumentsHoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDocumentsHoverMouseExited(evt);
            }
        });
        jPanel1.add(lblDocumentsHover);
        lblDocumentsHover.setBounds(480, 320, 110, 110);

        lblDocuments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/documentshover.png"))); // NOI18N
        lblDocuments.setToolTipText("Documents");
        lblDocuments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDocumentsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDocumentsMouseExited(evt);
            }
        });
        jPanel1.add(lblDocuments);
        lblDocuments.setBounds(480, 330, 110, 110);

        lblUsersHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/profile.png"))); // NOI18N
        lblUsersHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUsersHoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUsersHoverMouseExited(evt);
            }
        });
        jPanel1.add(lblUsersHover);
        lblUsersHover.setBounds(260, 310, 160, 150);

        lblUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/profilehover.png"))); // NOI18N
        lblUsers.setToolTipText("View Users");
        lblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUsersMouseExited(evt);
            }
        });
        jPanel1.add(lblUsers);
        lblUsers.setBounds(260, 320, 130, 140);

        lblResidentsHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/group.png"))); // NOI18N
        lblResidentsHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblResidentsHoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblResidentsHoverMouseExited(evt);
            }
        });
        jPanel1.add(lblResidentsHover);
        lblResidentsHover.setBounds(50, 320, 110, 120);

        lblResidents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/grouphover.png"))); // NOI18N
        lblResidents.setToolTipText("View Residents");
        lblResidents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblResidentsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblResidentsMouseExited(evt);
            }
        });
        jPanel1.add(lblResidents);
        lblResidents.setBounds(50, 330, 110, 110);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BARANGAY CERTIFICATE SYSTEM");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(140, 60, 610, 50);

        lblBackround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/MainUI.jpg"))); // NOI18N
        jPanel1.add(lblBackround);
        lblBackround.setBounds(0, 0, 860, 500);

        lblCurrentPopulation1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurrentPopulation1.setText("Current Population:");
        jPanel1.add(lblCurrentPopulation1);
        lblCurrentPopulation1.setBounds(230, 190, 210, 29);

        lblCurrentPopulation2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurrentPopulation2.setText("Current Population:");
        jPanel1.add(lblCurrentPopulation2);
        lblCurrentPopulation2.setBounds(230, 190, 210, 29);

        lblCurrentPopulation3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurrentPopulation3.setText("Current Population:");
        jPanel1.add(lblCurrentPopulation3);
        lblCurrentPopulation3.setBounds(230, 190, 210, 29);

        lblCurrentPopulation4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurrentPopulation4.setText("Current Population:");
        jPanel1.add(lblCurrentPopulation4);
        lblCurrentPopulation4.setBounds(230, 190, 210, 29);

        jMenu1.setText("File");

        jMenuItem1.setText("Document Settings");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Reports");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblUserTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblUserTypePropertyChange
        // TODO add your handling code here:
        if(lblUserType.getText().equals("Admin")){
            lblResidents.setEnabled(true);
            lblDocuments.setEnabled(true);
            jMenuItem1.setEnabled(true);
            jMenuItem2.setEnabled(true);
            lblResidentsHover.setEnabled(true);
            lblDocumentsHover.setEnabled(true);
        }else if(lblUserType.getText().equals("Admin") || lblUserType.getText().equals("User")){
            lblResidents.setEnabled(true);
            lblDocuments.setEnabled(true);
            lblResidentsHover.setEnabled(true);
            lblDocumentsHover.setEnabled(true);
        }else{
            lblResidents.setEnabled(false);
            lblDocuments.setEnabled(false);
            lblResidentsHover.setEnabled(false);
            lblDocumentsHover.setEnabled(false);
        }
    }//GEN-LAST:event_lblUserTypePropertyChange

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        String LoginAs = lblLoginAs.getText();
        String UserID = lblUserID.getText();
        String Username = lblUsername.getText();
        String userType = lblUserType.getText();
        DocumentsSetting ob = new DocumentsSetting();
        ob.lblLoginAs.setText(LoginAs);
        ob.lblUserID.setText(UserID);
        ob.lblUsername.setText(Username);
        ob.lblUserType.setText(userType);
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String LoginAs = lblLoginAs.getText();
        String UserID = lblUserID.getText();
        String Username = lblUsername.getText();
        String userType = lblUserType.getText();
        Reports ob = new Reports();
        ob.lblLoginAs.setText(LoginAs);
        ob.lblUserID.setText(UserID);
        ob.lblUsername.setText(Username);
        ob.lblUserType.setText(userType);
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void lblResidentsHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResidentsHoverMouseEntered
        // TODO add your handling code here:
        lblResidentsHover.setVisible(false);
    }//GEN-LAST:event_lblResidentsHoverMouseEntered

    private void lblResidentsHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResidentsHoverMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblResidentsHoverMouseExited

    private void lblResidentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResidentsMouseExited
        // TODO add your handling code here:
        lblResidentsHover.setVisible(true);
    }//GEN-LAST:event_lblResidentsMouseExited

    private void lblResidentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResidentsMouseClicked
        // TODO add your handling code here:
        String LoginAs = lblLoginAs.getText();
        String UserID = lblUserID.getText();
        String Username = lblUsername.getText();
        String userType = lblUserType.getText();
        Residents ob = new Residents();
        ob.lblLoginAs.setText(LoginAs);
        ob.lblUserID.setText(UserID);
        ob.lblUsername.setText(Username);
        ob.lblUserType.setText(userType);
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblResidentsMouseClicked

    private void lblUsersHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersHoverMouseEntered
        // TODO add your handling code here:
        lblUsersHover.setVisible(false);
    }//GEN-LAST:event_lblUsersHoverMouseEntered

    private void lblUsersHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersHoverMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsersHoverMouseExited

    private void lblUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsersMouseEntered

    private void lblUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseExited
        // TODO add your handling code here:
        lblUsersHover.setVisible(true);
    }//GEN-LAST:event_lblUsersMouseExited

    private void lblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseClicked
        // TODO add your handling code here:
        String LoginAs = lblLoginAs.getText();
        String UserID = lblUserID.getText();
        String Username = lblUsername.getText();
        String userType = lblUserType.getText();
        Users ob = new Users();
        ob.lblLoginAs.setText(LoginAs);
        ob.lblUserID.setText(UserID);
        ob.lblUsername.setText(Username);
        ob.lblUserType.setText(userType);
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblUsersMouseClicked

    private void lblDocumentsHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDocumentsHoverMouseEntered
        // TODO add your handling code here:
        lblDocumentsHover.setVisible(false);
    }//GEN-LAST:event_lblDocumentsHoverMouseEntered

    private void lblDocumentsHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDocumentsHoverMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDocumentsHoverMouseExited

    private void lblDocumentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDocumentsMouseClicked
        // TODO add your handling code here:
          String LoginAs = lblLoginAs.getText();
        String UserID = lblUserID.getText();
        String Username = lblUsername.getText();
        String userType = lblUserType.getText();
        Documents ob = new Documents();
        ob.lblLoginAs.setText(LoginAs);
        ob.lblUserID.setText(UserID);
        ob.lblUsername.setText(Username);
        ob.lblUserType.setText(userType);
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblDocumentsMouseClicked

    private void lblDocumentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDocumentsMouseExited
        // TODO add your handling code here:
        lblDocumentsHover.setVisible(true);
    }//GEN-LAST:event_lblDocumentsMouseExited

    private void lblLogoutHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutHoverMouseEntered
        // TODO add your handling code here:
        lblLogoutHover.setVisible(false);
    }//GEN-LAST:event_lblLogoutHoverMouseEntered

    private void lblLogoutHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutHoverMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblLogoutHoverMouseExited

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        // TODO add your handling code here:
         int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Logout",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (selectedOption==JOptionPane.YES_OPTION){
            report();
        new Login().setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void lblLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseExited
        // TODO add your handling code here:
        lblLogoutHover.setVisible(true);
    }//GEN-LAST:event_lblLogoutMouseExited

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackround;
    private javax.swing.JLabel lblCurrentPopulation;
    private javax.swing.JLabel lblCurrentPopulation1;
    private javax.swing.JLabel lblCurrentPopulation2;
    private javax.swing.JLabel lblCurrentPopulation3;
    private javax.swing.JLabel lblCurrentPopulation4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDocuments;
    private javax.swing.JLabel lblDocumentsHover;
    public static javax.swing.JLabel lblLoginAs;
    public static javax.swing.JLabel lblLoginAs1;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblLogoutHover;
    private javax.swing.JLabel lblPopulation;
    private javax.swing.JLabel lblResidents;
    private javax.swing.JLabel lblResidentsHover;
    private javax.swing.JLabel lblTime;
    public static javax.swing.JLabel lblUserID;
    public static javax.swing.JLabel lblUserType;
    public static javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsers;
    private javax.swing.JLabel lblUsersHover;
    // End of variables declaration//GEN-END:variables
}
