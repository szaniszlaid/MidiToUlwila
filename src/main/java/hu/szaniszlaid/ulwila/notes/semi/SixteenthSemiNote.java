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
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;

public class SixteenthSemiNote extends SixteenthNote {

	int width = QUARTER_NOTE_WIDTH / 4;
	int height = QUARTER_NOTE_HEIGHT;

	public SixteenthSemiNote(Octave octave, Tone tone) {
		super(octave, tone);

	}

	@Override
	public void drawNote(Graphics2D g) {
		g.setColor(getLeftColor());
		g.fillRect(MARGIN_LEFT, MARGIN_TOP, width / 2, height);
		g.setColor(Color.BLACK);
		g.drawRect(MARGIN_LEFT, MARGIN_TOP, width / 2, height);

		g.setColor(getRightColor());
		g.fillRect(width / 2 + MARGIN_LEFT, MARGIN_TOP, width / 2, height);
		g.setColor(Color.BLACK);
		g.drawRect(width / 2 + MARGIN_LEFT, MARGIN_TOP, width / 2, height);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		double x = ((double) width / 5 - (double) width / 10) + MARGIN_LEFT;
		double y = ((double) height / 2 - (double) height / 10) + MARGIN_TOP;

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;
	}
}
