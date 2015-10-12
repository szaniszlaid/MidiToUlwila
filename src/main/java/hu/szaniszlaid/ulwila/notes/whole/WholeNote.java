package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicNote;

public class WholeNote extends MusicNote {

    public WholeNote(Octave octave, Tone tone) {
        super(octave, tone);
    }

    @Override
    public Dimension drawNote(Graphics2D g) {
        g.setColor(getColor());
        g.fillOval(getNthOffset(3), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(getNthOffset(3), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

        g.setColor(getColor());
        g.fillOval(getNthOffset(2), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(getNthOffset(2), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

        g.setColor(getColor());
        g.fillOval(getNthOffset(1), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(getNthOffset(1), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

        g.setColor(getColor());
        g.fillOval(getNthOffset(0), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(getNthOffset(0), 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

        return new Dimension(QUARTER_NOTE_WIDTH + getNthOffset(3), QUARTER_NOTE_HEIGHT);

    }

    @Override
    public List<Shape> getOctaveShapes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
