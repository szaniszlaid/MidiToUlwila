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

public class EighthNote extends MusicNote {

    int width = QUARTER_NOTE_WIDTH;
    int heiht = QUARTER_NOTE_HEIGHT;

    public EighthNote(Octave octave, Tone tone) {
        super(octave, tone);
    }

    @Override
    public Dimension drawNote(Graphics2D g) {
        g.setColor(getColor());
        Arc2D quarter = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, width, heiht, 90, 180, Arc2D.OPEN);
        g.fill(quarter);
        g.setColor(Color.BLACK);
        Arc2D.Double border = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, width, heiht, 90, 180, Arc2D.CHORD);
        g.draw(border);

        return new Dimension(width / 2, heiht);
    }

    @Override
    public List<Shape> getOctaveShapes() {
        int x = width / 2 - width / 10 + MARGIN_LEFT;
        int y = heiht / 2 - heiht / 10 + MARGIN_TOP;
        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(x, y, width / 5, heiht / 5, 90, 180, Arc2D.OPEN));
        return octaveShapes;
    }
}
