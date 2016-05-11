package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.MusicNote;
import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;

public class QuarterNote extends MusicNote {

	public QuarterNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {

		int noteWidth = QUARTER_NOTE_WIDTH;
		int noteHeight = QUARTER_NOTE_HEIGHT;

		int x = 0;
		int y = 0;

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			g.fillOval(x, y, noteWidth, noteHeight);

			g.setColor(Color.BLACK);
			g.drawOval(x, y, noteWidth, noteHeight);

			noteWidth = QUARTER_NOTE_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}

		g.setColor(getColor());
		g.fillOval(x, y, noteWidth, noteHeight);
		g.setColor(Color.BLACK);
		g.drawOval(y, x, noteWidth, noteHeight);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10);
		int y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10);

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 4;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
	}

}
