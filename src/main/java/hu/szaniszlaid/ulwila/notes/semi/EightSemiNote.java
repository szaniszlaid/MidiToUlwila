package hu.szaniszlaid.ulwila.notes.semi;

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

public class EightSemiNote extends MusicNote {

    int width = QUARTER_NOTE_WIDTH;
    int height = QUARTER_NOTE_HEIGHT;

    public EightSemiNote(Octave octave, Tone tone) {
        super(octave, tone);
    }

    @Override
    public Dimension drawNote(Graphics2D g) {

        g.setColor(getRightColor());
        Arc2D left = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.CHORD);
        g.fill(left);
        g.setColor(getLeftColor());
        Arc2D right = new Arc2D.Double(0, 0, width, height, 110, 140, Arc2D.CHORD);
        g.fill(right);
        g.setColor(Color.BLACK);
        Arc2D.Double border = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.CHORD);
        g.draw(border);

        return new Dimension(width / 2, height);
    }

    @Override
    public List<Shape> getOctaveShapes() {
        int centerX = width / 2 - width / 10;
        int centerY = height / 2 - height / 10;
        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(centerX, centerY, width / 5, height / 5, 90, 180, Arc2D.OPEN));
        return octaveShapes;

    }
}
