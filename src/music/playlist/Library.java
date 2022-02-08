/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package music.playlist;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author anushka
 */
public class Library extends javax.swing.JFrame {
    String user;

    /**
     * Creates new form Library
     */
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel model = null;

    public Library(String username) {
        user = username;
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };

        model.addColumn("#");
        model.addColumn("Title");
        model.addColumn("Artists");
        model.addColumn("Duration");
        model.addColumn("Plays");
        model.addColumn("Genre");


        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?user=root&password=Red@1989");
            
            String sql = "Select title, songs.song_id, duration ,plays, genre,\n" +
                         "Group_concat(artist_name)as \"Artists\"\n" +
                         "From artist,songs\n" +
                         "where artist.song_id = songs.song_id\n" +
                         "Group by title, song_id;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("song_id"), rs.getString("title"), rs.getString("Artists"), rs.getFloat("duration"), rs.getString("plays"),rs.getString("genre")});
            }

            rs.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        initComponents();
        
        libTable.getColumnModel().getColumn(0).setMaxWidth(40);
        libTable.getColumnModel().getColumn(3).setMaxWidth(40);
        libTable.getColumnModel().getColumn(4).setMaxWidth(40);
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
        backBtn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        libTable = new javax.swing.JTable();
        favBtn = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(600, 470));
        jPanel1.setPreferredSize(new java.awt.Dimension(666, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 80));

        jLabel1.setFont(new java.awt.Font("Silom", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LIBRARY");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 210, 52));

        libTable.setBackground(new java.awt.Color(238, 238, 238));
        libTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        libTable.setFont(new java.awt.Font("PT Sans", 0, 14)); // NOI18N
        libTable.setModel(model);
        libTable.setGridColor(new java.awt.Color(153, 153, 153));
        libTable.setSelectionBackground(new java.awt.Color(87, 64, 113));
        libTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        libTable.getTableHeader().setReorderingAllowed(false);
        libTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(libTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 570, 330));

        favBtn.setFont(new java.awt.Font("PT Sans", 0, 24)); // NOI18N
        favBtn.setForeground(new java.awt.Color(255, 255, 255));
        favBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fav.png"))); // NOI18N
        favBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                favBtnMouseClicked(evt);
            }
        });
        jPanel1.add(favBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 60, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpeg"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 660, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        // TODO add your handling code here:
        Home home = new Home(user);
        home.user = user;
        home.setVisible(true);
        home.pack();
        this.dispose();
    }//GEN-LAST:event_backBtnMouseClicked

    private void libTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libTableMouseClicked
        // TODO add your handling code here:

        
    }//GEN-LAST:event_libTableMouseClicked

    private void favBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_favBtnMouseClicked
        // TODO add your handling code here:

         try{
             
            String ID = null;
            int row = libTable.getSelectedRow();
            int column = 0;
            String id = null;
            String Title = null;
            String Plays = null;
            id = libTable.getValueAt(row, column).toString();
        
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?user=root&password=Red@1989");
            String sql = "select song_id, title, plays from songs where song_id = '" +id+"';";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ID = rs.getString("song_id");
                Title = rs.getString("Title");
                Plays = rs.getString("Plays");
            }

            ps = con.prepareStatement("insert into favourites (song_id, username, title, plays) values (?, ?, ?, ?)");  //can write select and where statements here
            ps.setString(1,ID);
            ps.setString(2,user);
            ps.setString(3,Title);
            ps.setString(4,Plays);
            ps.executeUpdate();
            
            rs.close();        
            Home home = new Home(user);           

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
         

        
    }//GEN-LAST:event_favBtnMouseClicked

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
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Library lb = new Library("");
                lb.setVisible(true);
                lb.pack();
            }
        });
  
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel favBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable libTable;
    // End of variables declaration//GEN-END:variables
}
