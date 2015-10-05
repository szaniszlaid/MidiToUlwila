/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.note.util.Octave;

/**
 *
 * @author Franklin
 */
public class EighthNotePanel extends MusicNote {

    /**
     * Creates new form QuarterNote
     *
     * @param color
     * @param pitch
     */
    public EighthNotePanel(Color color, Octave pitch) {
        super(new NoteBuilder().setColor(color),pitch);
        setContainerWidth(getNWidth()/2);
    }

    @Override
    public void drawNote(Graphics2D g) {
        g.setColor(getColor());
        Arc2D quarter = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.OPEN);
        g.fill(quarter);
        g.setColor(Color.BLACK);
        Arc2D.Double border = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.CHORD);
        g.draw(border);
    }

    @Override
    public void drawOctave(Graphics2D g) {
        int centerX = getNWidth() / 2 - getNWidth() / 10;
        int centerY = getNHeight() / 2 - getNHeight() / 10;        
        Arc2D quarter = new Arc2D.Double(centerX, centerY, getNWidth()/5, getNHeight()/5, 90, 180, Arc2D.OPEN);
        g.fill(quarter);
        g.setColor(Color.BLACK);
        g.draw(quarter);
    }
}
