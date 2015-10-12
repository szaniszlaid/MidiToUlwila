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

public class HalfNote extends MusicNote {

    int width = QUARTER_NOTE_WIDTH;
    int height = QUARTER_NOTE_HEIGHT;

    /**
     * Creates new form QuarterNote
     * @param color
     * @param octave
     */

    public HalfNote(Octave octave, Tone tone) {
        super(octave, tone);
    }

    @Override
    public Dimension drawNote(Graphics2D g) {
        g.setColor(getColor());
        g.fillOval(offsetX, 0, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(offsetX, 0, width, height);
        g.setColor(getColor());
        g.fillOval(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, width, height);

        //FIXME calculate based on offset
        return new Dimension(160, height);
    }

    @Override
    public void drawOctave(Graphics2D g) {
        int centerX = width / 2 - width / 10;
        int centerY = height / 2 - height / 10;

        //belső körök
        g.fillOval(centerX, centerY, width / 5, height / 5);
        g.fillOval(centerX + offsetX, centerY, width / 5, height / 5);

        //kontúr
        g.setColor(Color.BLACK);
        g.drawOval(centerX, centerY, width / 5, height / 5);
        g.drawOval(centerX + offsetX, centerY, width / 5, height / 5);

    }

    @Override
    public List<Shape> getOctaveShapes() {
        int x = width / 8 - height / 10;
        int y = width / 2 - height / 10;
        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 0, 360, Arc2D.OPEN));
        return octaveShapes;
    }
}
