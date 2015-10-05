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
//public class WholeNotePanel extends MusicNote {
//
//    /**
//     * Creates new form QuarterNote
//     *
//     * @param color
//     * @param octave
//     */
//    public WholeNotePanel(Color color, Octave octave) {
//        super(new NoteBuilder().setColor(color), octave);
//        setContainerWidth(800);
//    }
//
//
//    @Override
//    public void drawNote(Graphics2D g) {
//        int width = getNWidth();
//        int height = getNHeight();
//        g.setColor(getColor());
//        g.fillOval(getNthOffset(3), 0, width, height);
//        g.setColor(Color.BLACK);
//        g.drawOval(getNthOffset(3), 0, width, height);       
//        
//        g.setColor(getColor());
//        g.fillOval(getNthOffset(2), 0, width, height);
//        g.setColor(Color.BLACK);
//        g.drawOval(getNthOffset(2), 0, width, height);
//        
//        g.setColor(getColor());
//        g.fillOval(getNthOffset(1), 0, width, height);
//        g.setColor(Color.BLACK);
//        g.drawOval(getNthOffset(1), 0, width, height);     
//      
//        g.setColor(getColor());
//        g.fillOval(getNthOffset(0), 0, width, height);
//        g.setColor(Color.BLACK);
//        g.drawOval(getNthOffset(0), 0, width, height);
//    }
//
//    @Override
//    public void drawOctave(Graphics2D g) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
