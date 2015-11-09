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
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;

public class QuarterSemiNote extends QuarterNote {

    int width = QUARTER_NOTE_WIDTH;
    int height = QUARTER_NOTE_HEIGHT;

    public QuarterSemiNote(Octave octave, Tone tone) {
        super(octave, tone);
    }

    @Override
    public Dimension drawNote(Graphics2D g) {

        g.setColor(getLeftColor());
        Arc2D quarterLeft = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.OPEN);
        g.fill(quarterLeft);
        g.setColor(Color.BLACK);
        Arc2D.Double borderLeft = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.CHORD);
        g.draw(borderLeft);
        g.setColor(getRightColor());
        Arc2D quarterRight = new Arc2D.Double(0, 0, width, height, 90, -180, Arc2D.OPEN);
        g.fill(quarterRight);
        g.setColor(Color.BLACK);
        Arc2D quarterRightBorder = new Arc2D.Double(0, 0, width, height, 90, -180, Arc2D.CHORD);
        g.draw(quarterRightBorder);

        return new Dimension(width, height);
    }

    @Override
    public List<Shape> getOctaveShapes() {
        int x = width / 2 - width / 10;
        int y = height / 2 - height / 10;
        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
        return octaveShapes;

    }

    @Override
    protected void drawOctave(Graphics2D g) {
        super.drawOctave(g);
        if (getTone().equals(Tone.CIS) && getOctave().equals(Octave.FIRST)) {
            int x = width / 2 - width / 10;
            int y = height / 2 - height / 10;
            g.setColor(Color.WHITE);
            Arc2D.Double borderLeft = new Arc2D.Double(x, y, width / 5, height / 5, 90, 180, Arc2D.OPEN);
            g.draw(borderLeft);
        }
    }
}
