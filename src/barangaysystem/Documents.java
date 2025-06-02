/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangaysystem;

import static barangaysystem.DocumentsSetting.lstRequestReason;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Admin
 */
public class Documents extends javax.swing.JFrame {
Connection conn = null;
PreparedStatement pst = null;
ResultSet rs = null;
Object[] cols = null;
String brgyCertificate = "";
String brgyPermit = "";
String businessPermit = "";
String getSelectedDoc = "";
String processStatus = "";
String businessPermitTime = "";
String getBusinessDatePrice = "";
String getORNo = "";
String reqCode = "";
    /**
     * Creates new form Archives
     */
    public Documents() {
        initComponents();
        pnlBusinessDetails.setVisible(false);
        lblUsername.setVisible(false);
        lblUserID.setVisible(false);
        lblUserType.setVisible(false);
        cmdPrintPreview.setEnabled(false);
        cmdPrint.setEnabled(false);
        lblConfirmCode.setVisible(false);
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(false);
        showDate();
        showTime();
        RequestReason();
        
        
        lblBusinessName.setHorizontalAlignment(SwingConstants.CENTER);
        lblBusinessPermitName.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrgyCertChairmanNameSig2.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrgyCertChairmanNameSig.setHorizontalAlignment(SwingConstants.CENTER);
        lblBrgyPermitChairmanNameSig.setHorizontalAlignment(SwingConstants.CENTER);
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
    void RequestReason(){
        conn=MySqlConnect.ConnectDB();
        String sql = "select * from docreqreason";
        try{
            pst=conn.prepareStatement(sql); 
            rs=pst.executeQuery();
            while(rs.next()){
      cboReqReason.addItem(rs.getString(1));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void printBrgyCertificate(){
  PrinterJob pj = PrinterJob.getPrinterJob();
  pj.setJobName(" Print Component ");
  pj.setPrintable (new Printable() {    
    public int print(Graphics pg, PageFormat pf, int pageNum){
      if (pageNum > 0){
      return Printable.NO_SUCH_PAGE;
      }
      Graphics2D g2 = (Graphics2D) pg;
      g2.translate(pf.getImageableX(), pf.getImageableY());
      pnlBrgyCert.paint(g2);
      //JOptionPane.showMessageDialog(null, "Reciept Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
      return Printable.PAGE_EXISTS;
    }
  });
  if (pj.printDialog() == false)
  return;
  try {
        pj.print();
        conn=MySqlConnect.ConnectDB();
            String reqCode = lblConfirmCode.getText();
            try{
        String updateBrgyCert = "";
        String sql = "UPDATE `documents` SET `brgyCertificate`='"+updateBrgyCert+"' WHERE `confirmationCode`='"+reqCode+"'";
        pst=conn.prepareStatement(sql);
        pst.executeUpdate();
            String sql2 = "SELECT * FROM `documents` WHERE `confirmationCode`='"+reqCode+"'";
            DefaultListModel model = new DefaultListModel();
            try{
        pst=conn.prepareStatement(sql2);
        rs = pst.executeQuery();
            if(rs.next()){
                brgyCertificate = rs.getString("brgyCertificate");
                brgyPermit = rs.getString("brgyPermit");
                businessPermit = rs.getString("businessPermit");
                 if(brgyCertificate.equals("") & brgyPermit.equals("") & businessPermit.equals("")){
                     String brgyCert = "UPDATE `documents` SET `processStatus`='Claimed' WHERE `confirmationCode`='"+reqCode+"'";
                     pst=conn.prepareStatement(brgyCert);
                       pst.executeUpdate();
                     cmdConfirm.setVisible(true);
        cmdCancel.setVisible(false);
        txtReqCode.setEnabled(true);
        txtReqCode.setText(null);
        txtReqCode.requestFocus();
        lblSelectedFname.setText(":");
        lblSelectedMname.setText(":");
        lblSelectedLname.setText(":");
        lblSelectedAddressBlock.setText(":");
        lblSelectedAddressLot.setText(":");
        cboReqReason.setSelectedItem("          ");
        getSelectedDoc = "";
        lblConfirmCode.setText(null);
        cmdPrintPreview.setEnabled(false);
        cmdPrint.setEnabled(false);
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(false);
        model.removeAllElements();
        lstDocRequested.setModel(model);
        pnlBusinessDetails.setVisible(false);
        txtBusinessName.setText(null);
        txtBusinessAddBlock.setText(null);
        txtBusinessAddLot.setText(null);
                  //  JOptionPane.showMessageDialog(null, "All Documents has beed Processed");
                  
                }else{
                if(brgyCertificate.equals("Yes")){
                    model.addElement("Barangay Certificate");
                    lstDocRequested.setModel(model);
                }if(brgyPermit.equals("Yes")){
                    model.addElement("Barangay Permit");
                    lstDocRequested.setModel(model);
                }if(businessPermit.equals("Yes")){
                    model.addElement("Business Permit");
                    lstDocRequested.setModel(model);
                }
            }
            }cboReqReason.setSelectedItem("          ");
                }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                        }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            try{
        Date d = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat tm = new SimpleDateFormat("hh:mm:ss");
        String date = dt.format(d);
        String time = tm.format(d);
             String sql = "INSERT INTO `documenttransaction` SET `User Name`='"+lblLoginAs.getText()+"',`Client Name`='"+lblBrgyCertName.getText()+"',`Date`='"+date+"',`Type`='Barangay Certificate',`Purpose`='"+lblBrgyCertReqReason.getText()+"',`Time`='"+time+"'";
             pst = conn.prepareStatement(sql);
             pst.executeUpdate();
   }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
         }
            cmdPrintPreview.setEnabled(false);
  JOptionPane.showMessageDialog(null, "Requested Document Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
  } catch (PrinterException ex) {
        // handle exception
  }
}
    public void printBrgyPermit(){
  PrinterJob pj = PrinterJob.getPrinterJob();
  pj.setJobName(" Print Component ");
  pj.setPrintable (new Printable() {    
    public int print(Graphics pg, PageFormat pf, int pageNum){
      if (pageNum > 0){
      return Printable.NO_SUCH_PAGE;
      }
      Graphics2D g2 = (Graphics2D) pg;
      g2.translate(pf.getImageableX(), pf.getImageableY());
      pnlBrgyPermit.paint(g2);
      return Printable.PAGE_EXISTS;
    }
  });
  if (pj.printDialog() == false)
  return;
  try {
        pj.print();
        conn=MySqlConnect.ConnectDB();
            String reqCode = lblConfirmCode.getText();
            try{
        String updateBrgyPermit = "";
        String sql = "UPDATE `documents` SET `brgyPermit`='"+updateBrgyPermit+"' WHERE `confirmationCode`='"+reqCode+"'";
        pst=conn.prepareStatement(sql);
        pst.executeUpdate();
            String sql2 = "SELECT * FROM `documents` WHERE `confirmationCode`='"+reqCode+"'";
            DefaultListModel model = new DefaultListModel();
            try{
        pst=conn.prepareStatement(sql2);
        rs = pst.executeQuery();
            if(rs.next()){
                brgyCertificate = rs.getString("brgyCertificate");
                brgyPermit = rs.getString("brgyPermit");
                businessPermit = rs.getString("businessPermit");
                 if(brgyCertificate.equals("") & brgyPermit.equals("") & businessPermit.equals("")){
                     String brgyPermit = "UPDATE `documents` SET `processStatus`='Claimed' WHERE `confirmationCode`='"+reqCode+"'";
                     pst=conn.prepareStatement(brgyPermit);
                       pst.executeUpdate();
        cmdConfirm.setVisible(true);
        cmdCancel.setVisible(false);
        txtReqCode.setEnabled(true);
        txtReqCode.setText(null);
        txtReqCode.requestFocus();
        lblSelectedFname.setText(":");
        lblSelectedMname.setText(":");
        lblSelectedLname.setText(":");
        lblSelectedAddressBlock.setText(":");
        lblSelectedAddressLot.setText(":");
        cboReqReason.setSelectedItem("          ");
        getSelectedDoc = "";
        lblConfirmCode.setText(null);
        cmdPrintPreview.setEnabled(false);
        cmdPrint.setEnabled(false);
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(false);
        model.removeAllElements();
        lstDocRequested.setModel(model);
        pnlBusinessDetails.setVisible(false);
        txtBusinessName.setText(null);
        txtBusinessAddBlock.setText(null);
        txtBusinessAddLot.setText(null);
                  //  JOptionPane.showMessageDialog(null, "All Documents has beed Processed");
                }else{
                if(brgyCertificate.equals("Yes")){
                    model.addElement("Certificate of Indigency");
                    lstDocRequested.setModel(model);
                }if(brgyPermit.equals("Yes")){
                    model.addElement("Barangay Clearance");
                    lstDocRequested.setModel(model);
                }if(businessPermit.equals("Yes")){
                    model.addElement("Business Permit");
                    lstDocRequested.setModel(model);
                }
            }
            }cboReqReason.setSelectedItem("          ");
                }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                        }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            try{
        Date d = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat tm = new SimpleDateFormat("hh:mm:ss");
        String date = dt.format(d);
        String time = tm.format(d);
             String sql = "INSERT INTO `documenttransaction` SET `User Name`='"+lblLoginAs.getText()+"',`Client Name`='"+lblBrgyPermitName.getText()+"',`Date`='"+date+"',`Type`='Barangay Permit',`Purpose`='"+lblBrgyPermitReqReason.getText()+"',`Time`='"+time+"'";
             pst = conn.prepareStatement(sql);
             pst.executeUpdate();
   }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
         }
             cmdPrintPreview.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Requested Document Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
  } catch (PrinterException ex) {
        // handle exception
  }
}
    public void printBusinessPermit(){
  PrinterJob pj = PrinterJob.getPrinterJob();
  pj.setJobName(" Print Component ");
  pj.setPrintable (new Printable() {    
    public int print(Graphics pg, PageFormat pf, int pageNum){
      if (pageNum > 0){
      return Printable.NO_SUCH_PAGE;
      }
      Graphics2D g2 = (Graphics2D) pg;
      g2.translate(pf.getImageableX(), pf.getImageableY());
      pnlBusinessPermit.paint(g2);
      //JOptionPane.showMessageDialog(null, "Reciept Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
      return Printable.PAGE_EXISTS;
    }
  });
  if (pj.printDialog() == false)
  return;
  try {
        conn=MySqlConnect.ConnectDB();
            String reqCode = lblConfirmCode.getText();
            try{
        String updateBusinessPermit = "";
        String sql = "UPDATE `documents` SET `businessPermit`='"+updateBusinessPermit+"' WHERE `confirmationCode`='"+reqCode+"'";
        pst=conn.prepareStatement(sql);
        pst.executeUpdate();
            String sql2 = "SELECT * FROM `documents` WHERE `confirmationCode`='"+reqCode+"'";
            DefaultListModel model = new DefaultListModel();
            try{
        pst=conn.prepareStatement(sql2);
        rs = pst.executeQuery();
            if(rs.next()){
                brgyCertificate = rs.getString("brgyCertificate");
                brgyPermit = rs.getString("brgyPermit");
                businessPermit = rs.getString("businessPermit");
                 if(brgyCertificate.equals("") & brgyPermit.equals("") & businessPermit.equals("")){
                     String businessPermit = "UPDATE `documents` SET `processStatus`='Claimed' WHERE `confirmationCode`='"+reqCode+"'";
                     pst=conn.prepareStatement(businessPermit);
                       pst.executeUpdate();
                     cmdConfirm.setVisible(true);
        cmdCancel.setVisible(false);
        txtReqCode.setEnabled(true);
        txtReqCode.setText(null);
        txtReqCode.requestFocus();
        lblSelectedFname.setText(":");
        lblSelectedMname.setText(":");
        lblSelectedLname.setText(":");
        lblSelectedAddressBlock.setText(":");
        lblSelectedAddressLot.setText(":");
        cboReqReason.setSelectedItem("          ");
        getSelectedDoc = "";
        lblConfirmCode.setText(null);
        cmdPrintPreview.setEnabled(false);
        cmdPrint.setEnabled(false);
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(false);
        model.removeAllElements();
        lstDocRequested.setModel(model);
        pnlBusinessDetails.setVisible(false);
        txtBusinessName.setText(null);
        txtBusinessAddBlock.setText(null);
        txtBusinessAddLot.setText(null);
                  //  JOptionPane.showMessageDialog(null, "All Documents has beed Processed");
                }else{
                if(brgyCertificate.equals("Yes")){
                    model.addElement("Certificate of Indigency");
                    lstDocRequested.setModel(model);
                }if(brgyPermit.equals("Yes")){
                    model.addElement("Barangay Clearance");
                    lstDocRequested.setModel(model);
                }if(businessPermit.equals("Yes")){
                    model.addElement("Business Permit");
                    lstDocRequested.setModel(model);
                }
            }
            }cboReqReason.setSelectedItem("          ");
                }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                        }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            try{
        Date d = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat tm = new SimpleDateFormat("hh:mm:ss");
        String date = dt.format(d);
        businessPermitTime = tm.format(d);
             String sql = "INSERT INTO `documenttransaction` SET `User Name`='"+lblLoginAs.getText()+"',`Client Name`='"+lblBusinessPermitName.getText()+"',`Date`='"+date+"',`Type`='Business Permit',`Purpose`='"+lblBrgyCertReqReason3.getText()+"', `Business Name`='"+lblBusinessName.getText()+"', `BusinessBlock`='"+lblBusinessPermitAddressBlock.getText()+"', `BusinessLot`='"+lblBusinessPermitAddressLot.getText()+"' , `Time`='"+businessPermitTime+"'";
             pst = conn.prepareStatement(sql);
             pst.executeUpdate();
   }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
         }
             cmdPrintPreview.setEnabled(false);
             
           //  try{
           //      Date d = new Date();
           // SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
           // String date = dt.format(d);
           //  String sql = "SELECT `TransactionID` FROM `documenttransaction` WHERE `Client Name`='"+lblBusinessPermitName.getText()+"' AND `Date`='"+date+"' AND `Business Name`='"+lblBusinessName.getText()+"' AND `time`='"+businessPermitTime+"'";
           //  pst=conn.prepareStatement(sql);
           //  rs = pst.executeQuery();
           //  while(rs.next()){
           //  lblControlNo.setText(rs.getString(1));
           //  }
           //  }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
           //  businessPermitTime = "";
        pj.print();
        JOptionPane.showMessageDialog(null, "Requested Document Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
  } catch (PrinterException ex) {
        // handle exception
  }
}
    
    void brgyCertGetCouncil(){
    conn=MySqlConnect.ConnectDB();
        try{
            String brgyCouncil = "SELECT * FROM `brgycouncil`";
            pst=conn.prepareStatement(brgyCouncil); 
            rs=pst.executeQuery();
            while(rs.next()){
            lblBrgyCertChairmanNameSig.setText(rs.getString("brgyChairman"));
            txtBrgyChairman.setText(rs.getString("brgyChairman"));
            txtCommiteeAppropiation.setText(rs.getString("commiteeAppropiation"));
            txtCommiteeEducation.setText(rs.getString("commiteeEducation"));
            txtCommiteeHumanRights.setText(rs.getString("commiteeHumanRights"));
            txtCommiteeEnvironment.setText(rs.getString("commiteeEnvironment"));
            txtCommiteeRules.setText(rs.getString("commiteeRules"));
            txtCommiteeInfrastructure.setText(rs.getString("commiteeInfrastructure"));
            txtCommiteeCooperatives.setText(rs.getString("commiteeCooperatives"));
            txtBrgySec.setText(rs.getString("brgySecretary"));
            txtBrgyTreasurer.setText(rs.getString("brgyTreasurer"));
            }//conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void brgyPermitGetCouncil(){
    conn=MySqlConnect.ConnectDB();
        try{
            String brgyCouncil = "SELECT * FROM `brgycouncil`";
            pst=conn.prepareStatement(brgyCouncil); 
            rs=pst.executeQuery();
            while(rs.next()){
            lblBrgyPermitChairmanNameSig.setText(rs.getString("brgyChairman"));
            txtBrgyChairman1.setText(rs.getString("brgyChairman"));
            txtCommiteeAppropiation1.setText(rs.getString("commiteeAppropiation"));
            txtCommiteeEducation1.setText(rs.getString("commiteeEducation"));
            txtCommiteeHumanRights1.setText(rs.getString("commiteeHumanRights"));
            txtCommiteeEnvironment1.setText(rs.getString("commiteeEnvironment"));
            txtCommiteeRules1.setText(rs.getString("commiteeRules"));
            txtCommiteeInfrastructure1.setText(rs.getString("commiteeInfrastructure"));
            txtCommiteeCooperatives1.setText(rs.getString("commiteeCooperatives"));
            txtBrgySec1.setText(rs.getString("brgySecretary"));
            txtBrgyTreasurer1.setText(rs.getString("brgyTreasurer"));
            }//conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void businessPermitGetCouncil(){            

    conn=MySqlConnect.ConnectDB();
        try{
            String brgyCouncil = "SELECT * FROM `brgycouncil`";
            pst=conn.prepareStatement(brgyCouncil); 
            rs=pst.executeQuery();
            while(rs.next()){
            lblBrgyCertChairmanNameSig2.setText(rs.getString("brgyChairman"));
            txtBrgyChairman3.setText(rs.getString("brgyChairman"));
            txtCommiteeAppropiation3.setText(rs.getString("commiteeAppropiation"));
            txtCommiteeEducation3.setText(rs.getString("commiteeEducation"));
            txtCommiteeHumanRights3.setText(rs.getString("commiteeHumanRights"));
            txtCommiteeEnvironment3.setText(rs.getString("commiteeEnvironment"));
            txtCommiteeRules3.setText(rs.getString("commiteeRules"));
            txtCommiteeInfrastructure3.setText(rs.getString("commiteeInfrastructure"));
            txtCommiteeCooperatives3.setText(rs.getString("commiteeCooperatives"));
            txtBrgySec3.setText(rs.getString("brgySecretary"));
            txtBrgyTreasurer3.setText(rs.getString("brgyTreasurer"));
            }//conn.close();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtReqCode = new javax.swing.JTextField();
        lblBack = new javax.swing.JLabel();
        lblBackHover = new javax.swing.JLabel();
        lblConfirmCode = new javax.swing.JLabel();
        lblUserID = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUserType = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        cmdPrintPreview = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        pnlCancelHover1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlPesonalInformation = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblSelectedLname = new javax.swing.JLabel();
        lblSelectedMname = new javax.swing.JLabel();
        lblSelectedFname = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSelectedAddressBlock = new javax.swing.JLabel();
        lblSelectedAddressLot = new javax.swing.JLabel();
        cboReqReason = new javax.swing.JComboBox<>();
        cmdPrint = new javax.swing.JButton();
        lstDocRequested = new javax.swing.JList<>();
        lblLoginAs1 = new javax.swing.JLabel();
        lblLoginAs = new javax.swing.JLabel();
        cmdConfirm = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        scrlBusinessPermit = new javax.swing.JScrollPane();
        pnlBusinessPermit = new javax.swing.JPanel();
        txtBrgyTreasurer3 = new javax.swing.JLabel();
        txtBrgySec3 = new javax.swing.JLabel();
        txtCommiteeCooperatives3 = new javax.swing.JLabel();
        txtCommiteeInfrastructure3 = new javax.swing.JLabel();
        txtCommiteeRules3 = new javax.swing.JLabel();
        txtCommiteeEnvironment3 = new javax.swing.JLabel();
        txtCommiteeHumanRights3 = new javax.swing.JLabel();
        txtCommiteeEducation3 = new javax.swing.JLabel();
        txtCommiteeAppropiation3 = new javax.swing.JLabel();
        txtBrgyChairman3 = new javax.swing.JLabel();
        lblBrgyCertChairmanNameSig2 = new javax.swing.JLabel();
        lblBusinessPermitName = new javax.swing.JLabel();
        lblBrgyCertAddressLot2 = new javax.swing.JLabel();
        lblBrgyCertAddressBlock2 = new javax.swing.JLabel();
        lblBusinessName = new javax.swing.JLabel();
        lblBusinessPermitAddressLot = new javax.swing.JLabel();
        lblBusinessPermitAddressLot2 = new javax.swing.JLabel();
        lblBusinessPermitAddressBlock = new javax.swing.JLabel();
        lblBusinessPermitAddressBlock1 = new javax.swing.JLabel();
        lblControlNo = new javax.swing.JLabel();
        lblORNo = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblBusinessPermitDate = new javax.swing.JLabel();
        lblBrgyCert2 = new javax.swing.JLabel();
        lblBrgyCertReqReason3 = new javax.swing.JLabel();
        scrlBarangayCert = new javax.swing.JScrollPane();
        pnlBrgyCert = new javax.swing.JPanel();
        txtBrgyTreasurer = new javax.swing.JLabel();
        txtBrgySec = new javax.swing.JLabel();
        txtCommiteeCooperatives = new javax.swing.JLabel();
        txtCommiteeInfrastructure = new javax.swing.JLabel();
        txtCommiteeRules = new javax.swing.JLabel();
        txtCommiteeEnvironment = new javax.swing.JLabel();
        txtCommiteeHumanRights = new javax.swing.JLabel();
        txtCommiteeEducation = new javax.swing.JLabel();
        txtCommiteeAppropiation = new javax.swing.JLabel();
        txtBrgyChairman = new javax.swing.JLabel();
        lblBrgyCertDateIssue = new javax.swing.JLabel();
        lblBrgyCertChairmanNameSig = new javax.swing.JLabel();
        lblBrgyCertName = new javax.swing.JLabel();
        lblBrgyCertReqReason = new javax.swing.JLabel();
        lblBrgyCertAddressLot = new javax.swing.JLabel();
        lblBrgyCertAddressBlock = new javax.swing.JLabel();
        lblBrgyCert = new javax.swing.JLabel();
        scrlBarangayPermit = new javax.swing.JScrollPane();
        pnlBrgyPermit = new javax.swing.JPanel();
        txtBrgyTreasurer1 = new javax.swing.JLabel();
        txtBrgySec1 = new javax.swing.JLabel();
        txtCommiteeCooperatives1 = new javax.swing.JLabel();
        txtCommiteeInfrastructure1 = new javax.swing.JLabel();
        txtCommiteeRules1 = new javax.swing.JLabel();
        txtCommiteeEnvironment1 = new javax.swing.JLabel();
        txtCommiteeHumanRights1 = new javax.swing.JLabel();
        txtCommiteeEducation1 = new javax.swing.JLabel();
        txtCommiteeAppropiation1 = new javax.swing.JLabel();
        txtBrgyChairman1 = new javax.swing.JLabel();
        lblBrgyPermitDateIssue = new javax.swing.JLabel();
        lblBrgyPermitChairmanNameSig = new javax.swing.JLabel();
        lblBrgyPermitName = new javax.swing.JLabel();
        lblBrgyPermitReqReason = new javax.swing.JLabel();
        lblBrgyPermitAddressLot = new javax.swing.JLabel();
        lblBrgyPermitAddressBlock = new javax.swing.JLabel();
        lblBrgyCert3 = new javax.swing.JLabel();
        pnlDocumentOutput = new javax.swing.JPanel();
        lblDate1 = new javax.swing.JLabel();
        lblTime1 = new javax.swing.JLabel();
        pnlBusinessDetails = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBusinessName = new javax.swing.JTextField();
        txtBusinessAddBlock = new javax.swing.JTextField();
        txtBusinessAddLot = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Barangay Record System: Documents");
        setResizable(false);

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 140, 0));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ENTER REQUEST CODE:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 10, 220, 30);

        txtReqCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtReqCode.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.add(txtReqCode);
        txtReqCode.setBounds(230, 10, 130, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 70, 370, 50);

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/BackButtonSmall.png"))); // NOI18N
        lblBack.setToolTipText("Back to Main Menu");
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackMouseEntered(evt);
            }
        });
        jPanel1.add(lblBack);
        lblBack.setBounds(20, 0, 50, 50);

        lblBackHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/BackButtonHoverSmall.png"))); // NOI18N
        lblBackHover.setToolTipText("Back");
        lblBackHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackHoverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackHoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBackHoverMouseExited(evt);
            }
        });
        jPanel1.add(lblBackHover);
        lblBackHover.setBounds(20, 0, 40, 50);

        lblConfirmCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblConfirmCode.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirmCode.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblConfirmCodePropertyChange(evt);
            }
        });
        jPanel1.add(lblConfirmCode);
        lblConfirmCode.setBounds(260, 30, 130, 20);

        lblUserID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUserID.setForeground(new java.awt.Color(255, 255, 255));
        lblUserID.setText("UserID");
        jPanel1.add(lblUserID);
        lblUserID.setBounds(260, 20, 70, 15);

        lblUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Username");
        jPanel1.add(lblUsername);
        lblUsername.setBounds(320, 20, 70, 15);

        lblUserType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUserType.setForeground(new java.awt.Color(255, 255, 255));
        lblUserType.setText("UserType");
        lblUserType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblUserTypePropertyChange(evt);
            }
        });
        jPanel1.add(lblUserType);
        lblUserType.setBounds(390, 20, 70, 15);

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date");
        jPanel1.add(lblDate);
        lblDate.setBounds(740, 30, 110, 20);

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("Time");
        jPanel1.add(lblTime);
        lblTime.setBounds(890, 30, 110, 20);

        cmdPrintPreview.setBackground(new java.awt.Color(0, 102, 255));
        cmdPrintPreview.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdPrintPreview.setForeground(new java.awt.Color(255, 255, 255));
        cmdPrintPreview.setText("PRINT PREVIEW");
        cmdPrintPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrintPreviewActionPerformed(evt);
            }
        });
        jPanel1.add(cmdPrintPreview);
        cmdPrintPreview.setBounds(40, 600, 310, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("REASON FOR REQUEST:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(20, 370, 150, 30);

        pnlCancelHover1.setBackground(new java.awt.Color(153, 153, 255));
        pnlCancelHover1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCancelHover1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCancelHover1MouseExited(evt);
            }
        });
        pnlCancelHover1.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DOCUMENT REQUESTED");
        pnlCancelHover1.add(jLabel7);
        jLabel7.setBounds(50, 0, 220, 20);

        jPanel1.add(pnlCancelHover1);
        pnlCancelHover1.setBounds(40, 410, 310, 20);

        pnlPesonalInformation.setBackground(new java.awt.Color(255, 255, 255));
        pnlPesonalInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   PERSONAL INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        pnlPesonalInformation.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("First Name");
        pnlPesonalInformation.add(jLabel4);
        jLabel4.setBounds(20, 20, 100, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Middle Name");
        pnlPesonalInformation.add(jLabel5);
        jLabel5.setBounds(20, 40, 100, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Last Name");
        pnlPesonalInformation.add(jLabel6);
        jLabel6.setBounds(20, 60, 100, 20);

        lblSelectedLname.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSelectedLname.setText(":");
        pnlPesonalInformation.add(lblSelectedLname);
        lblSelectedLname.setBounds(120, 60, 150, 20);

        lblSelectedMname.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSelectedMname.setText(":");
        pnlPesonalInformation.add(lblSelectedMname);
        lblSelectedMname.setBounds(120, 40, 150, 20);

        lblSelectedFname.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSelectedFname.setText(":");
        pnlPesonalInformation.add(lblSelectedFname);
        lblSelectedFname.setBounds(120, 20, 150, 20);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ADDRESS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel3.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("House #");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 30, 70, 16);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Street");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 50, 70, 16);

        lblSelectedAddressBlock.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSelectedAddressBlock.setText(":");
        jPanel3.add(lblSelectedAddressBlock);
        lblSelectedAddressBlock.setBounds(110, 30, 110, 16);

        lblSelectedAddressLot.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSelectedAddressLot.setText(":");
        jPanel3.add(lblSelectedAddressLot);
        lblSelectedAddressLot.setBounds(110, 50, 110, 16);

        pnlPesonalInformation.add(jPanel3);
        jPanel3.setBounds(10, 90, 350, 80);

        jPanel1.add(pnlPesonalInformation);
        pnlPesonalInformation.setBounds(10, 180, 370, 180);

        cboReqReason.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboReqReason.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "          " }));
        cboReqReason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboReqReasonActionPerformed(evt);
            }
        });
        jPanel1.add(cboReqReason);
        cboReqReason.setBounds(170, 370, 200, 30);

        cmdPrint.setBackground(new java.awt.Color(0, 102, 255));
        cmdPrint.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdPrint.setForeground(new java.awt.Color(255, 255, 255));
        cmdPrint.setText("PRINT DOCUMENT");
        cmdPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrintActionPerformed(evt);
            }
        });
        jPanel1.add(cmdPrint);
        cmdPrint.setBounds(390, 600, 600, 40);

        lstDocRequested.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lstDocRequested.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDocRequestedMouseClicked(evt);
            }
        });
        jPanel1.add(lstDocRequested);
        lstDocRequested.setBounds(40, 430, 310, 70);

        lblLoginAs1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLoginAs1.setForeground(new java.awt.Color(255, 255, 255));
        lblLoginAs1.setText("Login:");
        jPanel1.add(lblLoginAs1);
        lblLoginAs1.setBounds(700, 10, 50, 20);

        lblLoginAs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLoginAs.setForeground(new java.awt.Color(255, 255, 255));
        lblLoginAs.setText("Name");
        jPanel1.add(lblLoginAs);
        lblLoginAs.setBounds(750, 10, 250, 20);

        cmdConfirm.setBackground(new java.awt.Color(51, 204, 0));
        cmdConfirm.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdConfirm.setForeground(new java.awt.Color(255, 255, 255));
        cmdConfirm.setText("CONFIRM");
        cmdConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConfirmActionPerformed(evt);
            }
        });
        jPanel1.add(cmdConfirm);
        cmdConfirm.setBounds(10, 130, 370, 40);

        cmdCancel.setBackground(new java.awt.Color(102, 102, 102));
        cmdCancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmdCancel.setForeground(new java.awt.Color(255, 255, 255));
        cmdCancel.setText("CANCEL");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });
        jPanel1.add(cmdCancel);
        cmdCancel.setBounds(10, 130, 370, 40);

        scrlBusinessPermit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlBusinessPermit.setBackground(new java.awt.Color(102, 102, 102));
        pnlBusinessPermit.setPreferredSize(new java.awt.Dimension(612, 792));
        pnlBusinessPermit.setLayout(null);

        txtBrgyTreasurer3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgyTreasurer3.setForeground(new java.awt.Color(255, 255, 255));
        pnlBusinessPermit.add(txtBrgyTreasurer3);
        txtBrgyTreasurer3.setBounds(10, 740, 160, 30);

        txtBrgySec3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgySec3.setForeground(new java.awt.Color(255, 255, 255));
        pnlBusinessPermit.add(txtBrgySec3);
        txtBrgySec3.setBounds(10, 700, 160, 20);

        txtCommiteeCooperatives3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeCooperatives3.setForeground(java.awt.Color.white);
        pnlBusinessPermit.add(txtCommiteeCooperatives3);
        txtCommiteeCooperatives3.setBounds(270, 660, 160, 30);

        txtCommiteeInfrastructure3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeInfrastructure3.setForeground(java.awt.Color.white);
        pnlBusinessPermit.add(txtCommiteeInfrastructure3);
        txtCommiteeInfrastructure3.setBounds(270, 640, 160, 20);

        txtCommiteeRules3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeRules3.setForeground(java.awt.Color.white);
        pnlBusinessPermit.add(txtCommiteeRules3);
        txtCommiteeRules3.setBounds(30, 670, 160, 20);

        txtCommiteeEnvironment3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeEnvironment3.setForeground(java.awt.Color.white);
        pnlBusinessPermit.add(txtCommiteeEnvironment3);
        txtCommiteeEnvironment3.setBounds(10, 450, 160, 30);

        txtCommiteeHumanRights3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeHumanRights3.setForeground(new java.awt.Color(255, 255, 255));
        pnlBusinessPermit.add(txtCommiteeHumanRights3);
        txtCommiteeHumanRights3.setBounds(10, 370, 160, 20);

        txtCommiteeEducation3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeEducation3.setForeground(new java.awt.Color(255, 255, 255));
        pnlBusinessPermit.add(txtCommiteeEducation3);
        txtCommiteeEducation3.setBounds(160, 640, 160, 30);

        txtCommiteeAppropiation3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeAppropiation3.setForeground(new java.awt.Color(255, 255, 255));
        pnlBusinessPermit.add(txtCommiteeAppropiation3);
        txtCommiteeAppropiation3.setBounds(10, 280, 160, 20);

        txtBrgyChairman3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgyChairman3.setForeground(new java.awt.Color(255, 255, 255));
        pnlBusinessPermit.add(txtBrgyChairman3);
        txtBrgyChairman3.setBounds(430, 290, 160, 20);

        lblBrgyCertChairmanNameSig2.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        lblBrgyCertChairmanNameSig2.setForeground(new java.awt.Color(255, 255, 255));
        lblBrgyCertChairmanNameSig2.setText("Hon.");
        pnlBusinessPermit.add(lblBrgyCertChairmanNameSig2);
        lblBrgyCertChairmanNameSig2.setBounds(180, 690, 220, 30);

        lblBusinessPermitName.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblBusinessPermitName.setText("Owner Name");
        pnlBusinessPermit.add(lblBusinessPermitName);
        lblBusinessPermitName.setBounds(40, 400, 530, 30);

        lblBrgyCertAddressLot2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        pnlBusinessPermit.add(lblBrgyCertAddressLot2);
        lblBrgyCertAddressLot2.setBounds(540, 240, 70, 20);

        lblBrgyCertAddressBlock2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        pnlBusinessPermit.add(lblBrgyCertAddressBlock2);
        lblBrgyCertAddressBlock2.setBounds(470, 240, 70, 20);

        lblBusinessName.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        lblBusinessName.setText("Business Name");
        pnlBusinessPermit.add(lblBusinessName);
        lblBusinessName.setBounds(40, 330, 530, 40);

        lblBusinessPermitAddressLot.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblBusinessPermitAddressLot.setText("Lot");
        pnlBusinessPermit.add(lblBusinessPermitAddressLot);
        lblBusinessPermitAddressLot.setBounds(200, 470, 80, 30);

        lblBusinessPermitAddressLot2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblBusinessPermitAddressLot2.setText("Street");
        pnlBusinessPermit.add(lblBusinessPermitAddressLot2);
        lblBusinessPermitAddressLot2.setBounds(280, 470, 70, 30);

        lblBusinessPermitAddressBlock.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblBusinessPermitAddressBlock.setText("Block");
        pnlBusinessPermit.add(lblBusinessPermitAddressBlock);
        lblBusinessPermitAddressBlock.setBounds(120, 470, 70, 30);

        lblBusinessPermitAddressBlock1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblBusinessPermitAddressBlock1.setForeground(new java.awt.Color(255, 255, 255));
        lblBusinessPermitAddressBlock1.setText("Block");
        pnlBusinessPermit.add(lblBusinessPermitAddressBlock1);
        lblBusinessPermitAddressBlock1.setBounds(110, 470, 50, 30);

        lblControlNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblControlNo.setText("_ _ _ _ _ _");
        pnlBusinessPermit.add(lblControlNo);
        lblControlNo.setBounds(510, 230, 90, 30);

        lblORNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblORNo.setText("orno.");
        pnlBusinessPermit.add(lblORNo);
        lblORNo.setBounds(140, 230, 170, 30);

        lblPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPrice.setText("date");
        pnlBusinessPermit.add(lblPrice);
        lblPrice.setBounds(140, 260, 160, 30);

        lblBusinessPermitDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBusinessPermitDate.setText("date");
        pnlBusinessPermit.add(lblBusinessPermitDate);
        lblBusinessPermitDate.setBounds(140, 240, 160, 40);

        lblBrgyCert2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblBrgyCert2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/businessPermit.jpg"))); // NOI18N
        pnlBusinessPermit.add(lblBrgyCert2);
        lblBrgyCert2.setBounds(0, 0, 612, 792);

        lblBrgyCertReqReason3.setText("jLabel8");
        pnlBusinessPermit.add(lblBrgyCertReqReason3);
        lblBrgyCertReqReason3.setBounds(80, 50, 38, 16);

        scrlBusinessPermit.setViewportView(pnlBusinessPermit);

        jPanel1.add(scrlBusinessPermit);
        scrlBusinessPermit.setBounds(390, 70, 600, 520);

        scrlBarangayCert.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlBrgyCert.setBackground(new java.awt.Color(102, 102, 102));
        pnlBrgyCert.setPreferredSize(new java.awt.Dimension(612, 792));
        pnlBrgyCert.setLayout(null);

        txtBrgyTreasurer.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgyTreasurer.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtBrgyTreasurer);
        txtBrgyTreasurer.setBounds(10, 720, 160, 30);

        txtBrgySec.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgySec.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtBrgySec);
        txtBrgySec.setBounds(10, 700, 160, 20);

        txtCommiteeCooperatives.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeCooperatives.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeCooperatives);
        txtCommiteeCooperatives.setBounds(10, 590, 160, 30);

        txtCommiteeInfrastructure.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeInfrastructure.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeInfrastructure);
        txtCommiteeInfrastructure.setBounds(0, 590, 160, 20);

        txtCommiteeRules.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeRules.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeRules);
        txtCommiteeRules.setBounds(0, 520, 160, 20);

        txtCommiteeEnvironment.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeEnvironment.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeEnvironment);
        txtCommiteeEnvironment.setBounds(0, 490, 160, 30);

        txtCommiteeHumanRights.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeHumanRights.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeHumanRights);
        txtCommiteeHumanRights.setBounds(0, 280, 160, 20);

        txtCommiteeEducation.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeEducation.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeEducation);
        txtCommiteeEducation.setBounds(0, 280, 110, 20);

        txtCommiteeAppropiation.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeAppropiation.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtCommiteeAppropiation);
        txtCommiteeAppropiation.setBounds(0, 280, 160, 20);

        txtBrgyChairman.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgyChairman.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(txtBrgyChairman);
        txtBrgyChairman.setBounds(10, 230, 160, 20);

        lblBrgyCertDateIssue.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        pnlBrgyCert.add(lblBrgyCertDateIssue);
        lblBrgyCertDateIssue.setBounds(150, 630, 150, 30);

        lblBrgyCertChairmanNameSig.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        lblBrgyCertChairmanNameSig.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyCert.add(lblBrgyCertChairmanNameSig);
        lblBrgyCertChairmanNameSig.setBounds(370, 500, 220, 20);

        lblBrgyCertName.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        pnlBrgyCert.add(lblBrgyCertName);
        lblBrgyCertName.setBounds(300, 300, 220, 20);

        lblBrgyCertReqReason.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        pnlBrgyCert.add(lblBrgyCertReqReason);
        lblBrgyCertReqReason.setBounds(240, 450, 210, 20);

        lblBrgyCertAddressLot.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        pnlBrgyCert.add(lblBrgyCertAddressLot);
        lblBrgyCertAddressLot.setBounds(450, 320, 80, 20);

        lblBrgyCertAddressBlock.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        pnlBrgyCert.add(lblBrgyCertAddressBlock);
        lblBrgyCertAddressBlock.setBounds(410, 320, 90, 20);

        lblBrgyCert.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblBrgyCert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/barangayCertiicate.jpg"))); // NOI18N
        pnlBrgyCert.add(lblBrgyCert);
        lblBrgyCert.setBounds(0, 0, 610, 792);

        scrlBarangayCert.setViewportView(pnlBrgyCert);

        jPanel1.add(scrlBarangayCert);
        scrlBarangayCert.setBounds(390, 70, 600, 520);

        scrlBarangayPermit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlBrgyPermit.setBackground(new java.awt.Color(102, 102, 102));
        pnlBrgyPermit.setPreferredSize(new java.awt.Dimension(612, 792));
        pnlBrgyPermit.setLayout(null);

        txtBrgyTreasurer1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgyTreasurer1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtBrgyTreasurer1);
        txtBrgyTreasurer1.setBounds(300, 720, 160, 30);

        txtBrgySec1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgySec1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtBrgySec1);
        txtBrgySec1.setBounds(20, 720, 160, 20);

        txtCommiteeCooperatives1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeCooperatives1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeCooperatives1);
        txtCommiteeCooperatives1.setBounds(10, 600, 160, 30);

        txtCommiteeInfrastructure1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeInfrastructure1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeInfrastructure1);
        txtCommiteeInfrastructure1.setBounds(10, 590, 160, 20);

        txtCommiteeRules1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeRules1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeRules1);
        txtCommiteeRules1.setBounds(10, 520, 160, 20);

        txtCommiteeEnvironment1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeEnvironment1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeEnvironment1);
        txtCommiteeEnvironment1.setBounds(20, 520, 160, 30);

        txtCommiteeHumanRights1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeHumanRights1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeHumanRights1);
        txtCommiteeHumanRights1.setBounds(290, 360, 160, 20);

        txtCommiteeEducation1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeEducation1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeEducation1);
        txtCommiteeEducation1.setBounds(330, 230, 160, 30);

        txtCommiteeAppropiation1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtCommiteeAppropiation1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtCommiteeAppropiation1);
        txtCommiteeAppropiation1.setBounds(10, 280, 160, 20);

        txtBrgyChairman1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtBrgyChairman1.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(txtBrgyChairman1);
        txtBrgyChairman1.setBounds(10, 230, 160, 20);

        lblBrgyPermitDateIssue.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        pnlBrgyPermit.add(lblBrgyPermitDateIssue);
        lblBrgyPermitDateIssue.setBounds(150, 620, 150, 40);

        lblBrgyPermitChairmanNameSig.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        lblBrgyPermitChairmanNameSig.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrgyPermit.add(lblBrgyPermitChairmanNameSig);
        lblBrgyPermitChairmanNameSig.setBounds(370, 490, 220, 20);

        lblBrgyPermitName.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        pnlBrgyPermit.add(lblBrgyPermitName);
        lblBrgyPermitName.setBounds(290, 300, 230, 20);

        lblBrgyPermitReqReason.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pnlBrgyPermit.add(lblBrgyPermitReqReason);
        lblBrgyPermitReqReason.setBounds(240, 460, 230, 20);

        lblBrgyPermitAddressLot.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        pnlBrgyPermit.add(lblBrgyPermitAddressLot);
        lblBrgyPermitAddressLot.setBounds(460, 320, 120, 20);

        lblBrgyPermitAddressBlock.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        pnlBrgyPermit.add(lblBrgyPermitAddressBlock);
        lblBrgyPermitAddressBlock.setBounds(400, 320, 120, 20);

        lblBrgyCert3.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        lblBrgyCert3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/barangayPermit.jpg"))); // NOI18N
        pnlBrgyPermit.add(lblBrgyCert3);
        lblBrgyCert3.setBounds(0, 0, 612, 792);

        scrlBarangayPermit.setViewportView(pnlBrgyPermit);

        jPanel1.add(scrlBarangayPermit);
        scrlBarangayPermit.setBounds(390, 70, 600, 520);

        pnlDocumentOutput.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED)));
        pnlDocumentOutput.setLayout(null);
        jPanel1.add(pnlDocumentOutput);
        pnlDocumentOutput.setBounds(390, 70, 600, 520);

        lblDate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate1.setForeground(new java.awt.Color(255, 255, 255));
        lblDate1.setText("Date:");
        jPanel1.add(lblDate1);
        lblDate1.setBounds(700, 30, 40, 20);

        lblTime1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime1.setForeground(new java.awt.Color(255, 255, 255));
        lblTime1.setText("Time:");
        jPanel1.add(lblTime1);
        lblTime1.setBounds(840, 30, 50, 20);

        pnlBusinessDetails.setBackground(new java.awt.Color(255, 255, 255));
        pnlBusinessDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Business Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        pnlBusinessDetails.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Business Name: ");
        pnlBusinessDetails.add(jLabel2);
        jLabel2.setBounds(20, 20, 98, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("House ");
        pnlBusinessDetails.add(jLabel3);
        jLabel3.setBounds(40, 60, 60, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Street:");
        pnlBusinessDetails.add(jLabel12);
        jLabel12.setBounds(200, 60, 50, 30);

        txtBusinessName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlBusinessDetails.add(txtBusinessName);
        txtBusinessName.setBounds(120, 20, 180, 30);

        txtBusinessAddBlock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlBusinessDetails.add(txtBusinessAddBlock);
        txtBusinessAddBlock.setBounds(120, 60, 70, 30);

        txtBusinessAddLot.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlBusinessDetails.add(txtBusinessAddLot);
        txtBusinessAddLot.setBounds(240, 60, 60, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("#:");
        pnlBusinessDetails.add(jLabel13);
        jLabel13.setBounds(80, 60, 40, 30);

        jPanel1.add(pnlBusinessDetails);
        pnlBusinessDetails.setBounds(40, 500, 310, 100);

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));
        jPanel4.setMinimumSize(new java.awt.Dimension(860, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Documents");
        jLabel8.setMaximumSize(new java.awt.Dimension(327, 22));
        jLabel8.setMinimumSize(new java.awt.Dimension(327, 22));
        jLabel8.setPreferredSize(new java.awt.Dimension(327, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(577, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 1000, 50);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barangaysystem/image/bg1k.png"))); // NOI18N
        jLabel14.setText("jLabel14");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(0, 0, 1000, 650);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblUserTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblUserTypePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUserTypePropertyChange

    private void lblBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseEntered
        // TODO add your handling code here:
        lblBack.setVisible(false);
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblBackHoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackHoverMouseClicked
        // TODO add your handling code here:
        String LoginAs = lblLoginAs.getText();
        String UserID = lblUserID.getText();
        String Username = lblUsername.getText();
        String userType = lblUserType.getText();
        Main ob = new  Main();
        ob.lblLoginAs.setText(LoginAs);
        ob.lblUserID.setText(UserID);
        ob.lblUsername.setText(Username);
        ob.lblUserType.setText(userType);
        ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblBackHoverMouseClicked

    private void lblBackHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackHoverMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblBackHoverMouseEntered

    private void lblBackHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackHoverMouseExited
        // TODO add your handling code here:
        lblBack.setVisible(true);
    }//GEN-LAST:event_lblBackHoverMouseExited

    private void pnlCancelHover1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCancelHover1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlCancelHover1MouseEntered

    private void pnlCancelHover1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCancelHover1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlCancelHover1MouseExited

    private void cmdPrintPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrintPreviewActionPerformed
        // TODO add your handling code here:
        if(cboReqReason.getSelectedItem().equals("          ")){
            JOptionPane.showMessageDialog(null, "Choose the Reason of the Request","Barangay System",JOptionPane.INFORMATION_MESSAGE);
            cboReqReason.requestFocus();
        }else{
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MMMMM dd, yyyy");
        String fullName = lblSelectedFname.getText() + " " + lblSelectedMname.getText() + " " + lblSelectedLname.getText();
            if(getSelectedDoc.equals("Certificate of Indigency")){
             brgyCertGetCouncil();
            lblBrgyCertName.setText(fullName);
            lblBrgyCertAddressBlock.setText(lblSelectedAddressBlock.getText());
            lblBrgyCertAddressLot.setText(lblSelectedAddressLot.getText()+" Street");
            lblBrgyCertReqReason.setText((String) cboReqReason.getSelectedItem());
            lblBrgyCertDateIssue.setText(s.format(d));
        scrlBarangayCert.setVisible(true);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(false);
        //lblBrgyCert.setVisible(true);
        cmdPrint.setEnabled(true);
        }if(getSelectedDoc.equals("Barangay Clearance")){
            brgyPermitGetCouncil();
            lblBrgyPermitName.setText(fullName);
            lblBrgyPermitAddressBlock.setText(""+lblSelectedAddressBlock.getText());
            lblBrgyPermitAddressLot.setText(lblSelectedAddressLot.getText()+" Street");
            lblBrgyPermitReqReason.setText((String) cboReqReason.getSelectedItem());
            lblBrgyPermitDateIssue.setText(s.format(d));
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(true);
        scrlBusinessPermit.setVisible(false);
        cmdPrint.setEnabled(true);
        }
        
        if(getSelectedDoc.equals("Business Permit")){
            if(txtBusinessName.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter Business Details","Barangay System",JOptionPane.INFORMATION_MESSAGE);
            }else{
                businessPermitGetCouncil();
            lblBusinessPermitName.setText(fullName);
            lblBusinessPermitAddressBlock.setText(txtBusinessAddBlock.getText());
            lblBusinessPermitAddressLot.setText(txtBusinessAddLot.getText());
            lblBusinessName.setText("<html>" + "<center>" + txtBusinessName.getText() + "</center>"+"</html>");
            lblBusinessPermitDate.setText(s.format(d));
            lblPrice.setText("PHP "+getBusinessDatePrice);
            lblBrgyCertReqReason3.setText((String) cboReqReason.getSelectedItem());
            //lblORNo.setText(getORNo);
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(true);
        cmdPrint.setEnabled(true);
        }
            }
        }
    }//GEN-LAST:event_cmdPrintPreviewActionPerformed

    private void cmdConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfirmActionPerformed
        // TODO add your handling code here:
                if(txtReqCode.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter Confirmation Code","Barangay System",JOptionPane.ERROR_MESSAGE);
        txtReqCode.requestFocus();
                }else{
            String reqCodeCheck = txtReqCode.getText();
            String sql = "SELECT * FROM `documents` WHERE `confirmationCode`='"+reqCodeCheck+"'";
            try{
        pst=conn.prepareStatement(sql);
        rs = pst.executeQuery();
            if(rs.next()){
                processStatus = rs.getString("processStatus");
                if(processStatus.equals("Waiting")){
                lblConfirmCode.setText(rs.getString("confirmationCode"));
                }else{
                    String ClaimedProcessID = rs.getString("processID");
                    JOptionPane.showMessageDialog(null, "Confirmation Code Already Used \nProcess ID: "+ClaimedProcessID);
                    processStatus="";
                    lblConfirmCode.setText("");
                    txtReqCode.setText("");
                    txtReqCode.requestFocus();
                }
                }else{
            JOptionPane.showMessageDialog(null, "Confirmation Code Not Found","Barangay System",JOptionPane.ERROR_MESSAGE);
            txtReqCode.setText("");
            txtReqCode.requestFocus();
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
                }
                
    }//GEN-LAST:event_cmdConfirmActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        // TODO add your handling code here:
        //String LoginAs = lblLoginAs.getText();
        //String UserID = lblUserID.getText();
        //String Username = lblUsername.getText();
        //String userType = lblUserType.getText();
        //Main ob = new  Main();
        //ob.lblLoginAs.setText(LoginAs);
        //ob.lblUserID.setText(UserID);
        //ob.lblUsername.setText(Username);
        //ob.lblUserType.setText(userType);
        //ob.setVisible(true);
        //this.dispose();
        pnlBusinessDetails.setVisible(false);
        txtBusinessName.setText(null);
        txtBusinessAddBlock.setText(null);
        txtBusinessAddLot.setText(null);
        cmdConfirm.setVisible(true);
        cmdCancel.setVisible(false);
        txtReqCode.setEnabled(true);
        txtReqCode.setText(null);
        txtReqCode.requestFocus();
        lblSelectedFname.setText(":");
        lblSelectedMname.setText(":");
        lblSelectedLname.setText(":");
        lblSelectedAddressBlock.setText(":");
        lblSelectedAddressLot.setText(":");
        cboReqReason.setSelectedItem("          ");
        getSelectedDoc = "";
        lblConfirmCode.setText(null);
        cmdPrintPreview.setEnabled(false);
        cmdPrint.setEnabled(false);
        scrlBarangayCert.setVisible(false);
        scrlBarangayPermit.setVisible(false);
        scrlBusinessPermit.setVisible(false);
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        lstDocRequested.setModel(model);
        //DefaultListModel model = new DefaultListModel();
        //model.addElement(null);
        //lstDocRequested.setModel(model);
        //cmdPrintPreview.setEnabled(false);
        //cmdCancel.setVisible(false);
        //txtReqCode.setEnabled(true);
        //cmdPrint.setEnabled(false);
        //cmdConfirm.setVisible(true);
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void lblConfirmCodePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblConfirmCodePropertyChange
        // TODO add your handling code here:
        conn=MySqlConnect.ConnectDB();
            reqCode = lblConfirmCode.getText();
            String sql2 = "SELECT * FROM `documents` WHERE `confirmationCode`='"+reqCode+"'";
            DefaultListModel model = new DefaultListModel();
            try{
        pst=conn.prepareStatement(sql2);
        rs = pst.executeQuery();
            if(rs.next()){ 
                brgyCertificate = rs.getString("brgyCertificate");
                brgyPermit = rs.getString("brgyPermit");
                businessPermit = rs.getString("businessPermit");
                if(brgyCertificate.equals("Yes")){
                    model.addElement("Certificate of Indigency");
                    lstDocRequested.setModel(model);
                }if(brgyPermit.equals("Yes")){
                    model.addElement("Barangay Clearance");
                    lstDocRequested.setModel(model);
                }if(businessPermit.equals("Yes")){
                    model.addElement("Business Permit");
                    lstDocRequested.setModel(model);
                }
                lblSelectedFname.setText(rs.getString("fName"));
                lblSelectedMname.setText(rs.getString("mName"));
                lblSelectedLname.setText(rs.getString("lName"));
                lblSelectedAddressBlock.setText(rs.getString("addressBlock"));
                lblSelectedAddressLot.setText(rs.getString("addressLot"));
                try{
                String getBPPrice = "SELECT `Price` FROM `documentprice` WHERE `DocumentType`='Business Permit'";
                    pst=conn.prepareStatement(getBPPrice);
                    rs = pst.executeQuery();
                    while(rs.next()){
                    getBusinessDatePrice = rs.getString(1);
                    }
                    String getORNo = "SELECT `processID` FROM `documents` WHERE `confirmationCode`='"+reqCode+"'";
                    pst=conn.prepareStatement(getORNo);
                    rs = pst.executeQuery();
                    while(rs.next()){
                    getORNo = rs.getString(1);
                    lblORNo.setText(rs.getString(1));
                    }
                }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                }
       // cmdPrintPreview.setEnabled(true);
        cmdConfirm.setVisible(false);
        cmdCancel.setVisible(true);
        txtReqCode.setEnabled(false);
        //conn.close();
                }//conn.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        

    }//GEN-LAST:event_lblConfirmCodePropertyChange

    private void lstDocRequestedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDocRequestedMouseClicked
        // TODO add your handling code here:
       // cmdPrint.setEnabled(false);
        String value = lstDocRequested.getSelectedValue();
        getSelectedDoc = "";
        getSelectedDoc = value;
        if(value!=null){
            cmdPrintPreview.setEnabled(true);
            if(value=="Business Permit"){
            pnlBusinessDetails.setVisible(true);
            }else{pnlBusinessDetails.setVisible(false);}
        }else{
        cmdPrintPreview.setEnabled(false);
        }
    }//GEN-LAST:event_lstDocRequestedMouseClicked

    private void cmdPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrintActionPerformed
        if(getSelectedDoc.equals("Certificate of Indigency")){
            printBrgyCertificate();
        }if(getSelectedDoc.equals("Barangay Clearance")){
            printBrgyPermit();
        }if(getSelectedDoc.equals("Business Permit")){
            printBusinessPermit();
        }
    }//GEN-LAST:event_cmdPrintActionPerformed

    private void cboReqReasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboReqReasonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboReqReasonActionPerformed

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
            java.util.logging.Logger.getLogger(Documents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Documents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Documents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Documents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Documents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboReqReason;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdConfirm;
    private javax.swing.JButton cmdPrint;
    private javax.swing.JButton cmdPrintPreview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBackHover;
    private javax.swing.JLabel lblBrgyCert;
    private javax.swing.JLabel lblBrgyCert2;
    private javax.swing.JLabel lblBrgyCert3;
    private javax.swing.JLabel lblBrgyCertAddressBlock;
    private javax.swing.JLabel lblBrgyCertAddressBlock2;
    private javax.swing.JLabel lblBrgyCertAddressLot;
    private javax.swing.JLabel lblBrgyCertAddressLot2;
    private javax.swing.JLabel lblBrgyCertChairmanNameSig;
    private javax.swing.JLabel lblBrgyCertChairmanNameSig2;
    private javax.swing.JLabel lblBrgyCertDateIssue;
    private javax.swing.JLabel lblBrgyCertName;
    private javax.swing.JLabel lblBrgyCertReqReason;
    private javax.swing.JLabel lblBrgyCertReqReason3;
    private javax.swing.JLabel lblBrgyPermitAddressBlock;
    private javax.swing.JLabel lblBrgyPermitAddressLot;
    private javax.swing.JLabel lblBrgyPermitChairmanNameSig;
    private javax.swing.JLabel lblBrgyPermitDateIssue;
    private javax.swing.JLabel lblBrgyPermitName;
    private javax.swing.JLabel lblBrgyPermitReqReason;
    private javax.swing.JLabel lblBusinessName;
    private javax.swing.JLabel lblBusinessPermitAddressBlock;
    private javax.swing.JLabel lblBusinessPermitAddressBlock1;
    private javax.swing.JLabel lblBusinessPermitAddressLot;
    private javax.swing.JLabel lblBusinessPermitAddressLot2;
    private javax.swing.JLabel lblBusinessPermitDate;
    private javax.swing.JLabel lblBusinessPermitName;
    public static javax.swing.JLabel lblConfirmCode;
    private javax.swing.JLabel lblControlNo;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    public static javax.swing.JLabel lblLoginAs;
    public static javax.swing.JLabel lblLoginAs1;
    private javax.swing.JLabel lblORNo;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblSelectedAddressBlock;
    private javax.swing.JLabel lblSelectedAddressLot;
    private javax.swing.JLabel lblSelectedFname;
    private javax.swing.JLabel lblSelectedLname;
    private javax.swing.JLabel lblSelectedMname;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTime1;
    public static javax.swing.JLabel lblUserID;
    public static javax.swing.JLabel lblUserType;
    public static javax.swing.JLabel lblUsername;
    private javax.swing.JList<String> lstDocRequested;
    private javax.swing.JPanel pnlBrgyCert;
    private javax.swing.JPanel pnlBrgyPermit;
    private javax.swing.JPanel pnlBusinessDetails;
    private javax.swing.JPanel pnlBusinessPermit;
    private javax.swing.JPanel pnlCancelHover1;
    private javax.swing.JPanel pnlDocumentOutput;
    private javax.swing.JPanel pnlPesonalInformation;
    private javax.swing.JScrollPane scrlBarangayCert;
    private javax.swing.JScrollPane scrlBarangayPermit;
    private javax.swing.JScrollPane scrlBusinessPermit;
    private javax.swing.JLabel txtBrgyChairman;
    private javax.swing.JLabel txtBrgyChairman1;
    private javax.swing.JLabel txtBrgyChairman3;
    private javax.swing.JLabel txtBrgySec;
    private javax.swing.JLabel txtBrgySec1;
    private javax.swing.JLabel txtBrgySec3;
    private javax.swing.JLabel txtBrgyTreasurer;
    private javax.swing.JLabel txtBrgyTreasurer1;
    private javax.swing.JLabel txtBrgyTreasurer3;
    private javax.swing.JTextField txtBusinessAddBlock;
    private javax.swing.JTextField txtBusinessAddLot;
    private javax.swing.JTextField txtBusinessName;
    private javax.swing.JLabel txtCommiteeAppropiation;
    private javax.swing.JLabel txtCommiteeAppropiation1;
    private javax.swing.JLabel txtCommiteeAppropiation3;
    private javax.swing.JLabel txtCommiteeCooperatives;
    private javax.swing.JLabel txtCommiteeCooperatives1;
    private javax.swing.JLabel txtCommiteeCooperatives3;
    private javax.swing.JLabel txtCommiteeEducation;
    private javax.swing.JLabel txtCommiteeEducation1;
    private javax.swing.JLabel txtCommiteeEducation3;
    private javax.swing.JLabel txtCommiteeEnvironment;
    private javax.swing.JLabel txtCommiteeEnvironment1;
    private javax.swing.JLabel txtCommiteeEnvironment3;
    private javax.swing.JLabel txtCommiteeHumanRights;
    private javax.swing.JLabel txtCommiteeHumanRights1;
    private javax.swing.JLabel txtCommiteeHumanRights3;
    private javax.swing.JLabel txtCommiteeInfrastructure;
    private javax.swing.JLabel txtCommiteeInfrastructure1;
    private javax.swing.JLabel txtCommiteeInfrastructure3;
    private javax.swing.JLabel txtCommiteeRules;
    private javax.swing.JLabel txtCommiteeRules1;
    private javax.swing.JLabel txtCommiteeRules3;
    private javax.swing.JTextField txtReqCode;
    // End of variables declaration//GEN-END:variables
}
