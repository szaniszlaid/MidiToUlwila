package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;

public class SixteenthSemiNote extends SixteenthNote {

	public SixteenthSemiNote(Octave octave, Tone tone) {
		super(octave, tone);

	}

	@Override
	public void drawNote(Graphics2D g) {
		g.setColor(getLeftColor());
		g.fillRect(MARGIN_LEFT, MARGIN_TOP, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(MARGIN_LEFT, MARGIN_TOP, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);

		g.setColor(getRightColor());
		g.fillRect(SIXTEENTH_WIDTH / 2 + MARGIN_LEFT, MARGIN_TOP, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(SIXTEENTH_WIDTH / 2 + MARGIN_LEFT, MARGIN_TOP, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		double x = ((double) SIXTEENTH_WIDTH / 2 - (double) QUARTER_NOTE_WIDTH / 10) + MARGIN_LEFT;
		double y = ((double) QUARTER_NOTE_HEIGHT / 2 - (double) QUARTER_NOTE_HEIGHT / 10) + MARGIN_TOP;

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;
	}
}
