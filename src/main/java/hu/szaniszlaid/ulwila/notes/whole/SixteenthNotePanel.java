///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hu.szaniszlaid.ulwila.notes.whole;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//
//import hu.szaniszlaid.ulwila.note.util.Octave;
//import hu.szaniszlaid.ulwila.notes.MusicNote;
//
///**
// *
// * @author Franklin
// */
//public class SixteenthNotePanel extends MusicNote {
//
//
//    /**
//     * Creates new form QuarterNote
//     *
//     * @param color
//     * @param octave
//     */
//    public SixteenthNotePanel(Color color,  Octave octave) {
//        super(new NoteBuilder().setColor(color), octave);
//        setContainerWidth(getNWidth()/4);
//        initComponents();
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//    }// </editor-fold>//GEN-END:initComponents
//
//
//    // Variables declaration - do not modify//GEN-BEGIN:variables
//    // End of variables declaration//GEN-END:variables
//
//
//    @Override
//    public void drawNote(Graphics2D g) {
//        g.setColor(getColor());
//        g.fillRect(0, 0, getNWidth()/4, getNHeight());
//        g.setColor(Color.BLACK);
//        g.drawRect(0, 0,  getNWidth()/4, getNHeight());    }
//
//    @Override
//    public void drawOctave(Graphics2D g) {
//        int centerX = getNWidth() / 8 - getNWidth() / 10;
//        int centerY = getNHeight() / 2 - getNHeight() / 10;
//        //belső körök
//        g.fillOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
//        //kontúr
//        g.setColor(Color.BLACK);
//        g.drawOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
//    }
//}
