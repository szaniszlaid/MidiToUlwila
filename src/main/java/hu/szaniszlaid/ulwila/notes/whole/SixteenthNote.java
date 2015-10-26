package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicNote;

public class SixteenthNote extends MusicNote {

    int width = QUARTER_NOTE_WIDTH / 4;
    int height = QUARTER_NOTE_HEIGHT;

    public SixteenthNote(Octave octave, Tone tone) {
        super(octave, tone);

    }

    @Override
    public Dimension drawNote(Graphics2D g) {
        g.setColor(getColor());
        g.fillRect(0 + MARGIN_LEFT, 0 + MARGIN_RIGHT, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(0 + MARGIN_LEFT, 0 + MARGIN_RIGHT, width, height);

        return new Dimension(width, height);
    }

    @Override
    public List<Shape> getOctaveShapes() {
        double x = (double)  width / 5 -  (double) width/ 10;
        double y = (double)  height / 2 - (double)  height / 10;

        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(x + MARGIN_LEFT, y + MARGIN_RIGHT, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
        return octaveShapes;
    }
}
