/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicNote;

/**
 *
 * @author Franklin
 */
public class QuarterNotePanel extends MusicNote {

    /**
     * Creates new form QuarterNote
     *
     * @param color
     * @param octave
     */
    public QuarterNotePanel(Octave octave, Tone tone) {
        super(new NoteBuilder(), octave, tone);
        setContainerWidth(getNHeight());
    }

    @Override
    public void drawNote(Graphics2D g) {
    	double x = getNWidth() / 2 - getNWidth() / 3;
    	double y = getNHeight() / 2 - getNHeight() / 3;
    			
    	
        g.setColor(getColor());
        g.fillOval(0, 0, getNWidth(), getNHeight());
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, getNWidth(), getNHeight());
        
        if (getTone() == Tone.C && getOctave() == Octave.FIRST){
        	  g.setColor(Color.WHITE);
              Arc2D whiteCircle = new Arc2D.Double(x, y, getNWidth()/1.5, getNHeight()/1.5, 0, 360, Arc2D.CHORD);
              g.fill(whiteCircle);

        }
    }

    @Override
    public Shape getOctaveShape() {
        int x = getNWidth() / 2 - getNWidth() / 10;
        int y = getNHeight() / 2 - getNHeight() / 10;
        
        return new Arc2D.Double(x, y, getNWidth()/ 5, getNHeight()/ 5, 0, 360, Arc2D.CHORD);
    }
}
