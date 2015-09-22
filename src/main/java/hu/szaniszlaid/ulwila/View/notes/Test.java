/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.View.notes;

import java.awt.Color;

import hu.szaniszlaid.ulwila.note.util.Octave;

/**
 *
 * @author Franklin
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();
        initUI();
    }

    private void initUI() {

        //add(new TestJPanel());
        add(new HalfRestPanel());
        add(new EightRestPanel());
        add(new SixteenthRestPanel());
        add(new HalfNotePanel(Color.YELLOW, Octave.FIRST));
        add(new HalfNotePanel(Color.YELLOW, Octave.SECOND));
        add(new HalfNotePanel(Color.YELLOW, Octave.THIRD));
        add(new EighthNotePanel(Color.RED, Octave.FIRST));
        add(new EighthNotePanel(Color.RED, Octave.SECOND));
        add(new EighthNotePanel(Color.RED, Octave.THIRD));
        add(new WholeRestPanel());                
        add(new QuarterNotePanel(Color.BLUE, Octave.FIRST));
        add(new QuarterNotePanel(Color.BLUE, Octave.SECOND));
        add(new QuarterNotePanel(Color.BLUE, Octave.THIRD));
        add(new SixteenthNotePanel(Color.GREEN, Octave.FIRST));
        add(new SixteenthNotePanel(Color.GREEN, Octave.SECOND));
        add(new SixteenthNotePanel(Color.GREEN, Octave.THIRD));
        add(new WholeNotePanel(Color.MAGENTA, Octave.SECOND));


        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
