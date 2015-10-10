package hu.szaniszlaid.ulwila.notes.semitone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicNote;

public class EightSemiNote extends MusicNote{
	 /**
     * Creates new form QuarterNote
     *
     * @param color
     * @param octave
     */
    public EightSemiNote(Octave octave, Tone tone) {
        super(octave, tone);
        setContainerWidth(getNWidth()/2);
    }

    @Override
    public void drawNote(Graphics2D g) {
        g.setColor(getRightColor());
        Arc2D left = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.CHORD);
        g.fill(left);
        g.setColor(getLeftColor());
        Arc2D right = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 110, 140, Arc2D.CHORD);
        g.fill(right);
        g.setColor(Color.BLACK);
        Arc2D.Double border = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.CHORD);
        g.draw(border);
    }

    @Override
    public Shape getOctaveShape() {
        int centerX = getNWidth() / 2 - getNWidth() / 10;
        int centerY = getNHeight() / 2 - getNHeight() / 10;        
        return new Arc2D.Double(centerX, centerY, getNWidth()/5, getNHeight()/5, 90, 180, Arc2D.OPEN);

    }
}
