/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.View.notes;


import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import hu.szaniszlaid.ulwila.notesGraphics.NotesGraphics;

/**
 *
 * @author Franklin
 */
public class TestJpanel extends javax.swing.JPanel {
	public TestJpanel() {
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        doDrawing(g);
        
    }

    private void doDrawing(Graphics g) {
        NotesGraphics notesGraphics = new NotesGraphics(g);
        notesGraphics.drawEightNote(0);
        notesGraphics.drawQuarterNote(1);
        notesGraphics.drawHalfNote(2);
        notesGraphics.drawQuarterNote(3);
        notesGraphics.drawHalfNote(4);
        notesGraphics.drawQuarterNote(5);
        notesGraphics.drawQuarterNote(6);
    }

//    private void initComponents() {
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 400, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 300, Short.MAX_VALUE)
//        );
//    }
}
