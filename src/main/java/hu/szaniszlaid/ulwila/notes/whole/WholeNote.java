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
		int x = QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10;
		int y = QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10;
		
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(1), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(2), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(3), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		
		return octaveShapes;
	}
}
