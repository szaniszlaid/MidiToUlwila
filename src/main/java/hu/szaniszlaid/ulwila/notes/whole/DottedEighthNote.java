package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;

public class DottedEighthNote extends EighthNote {


	public DottedEighthNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		super.drawNote(g);
		
		int width = SixteenthNote.SIXTEENTH_WIDTH;
		int height = QUARTER_NOTE_HEIGHT;
		
		int x = QUARTER_NOTE_WIDTH / 2;
		int y = 0;

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			
			x = x + FOURTH_OCTAVE_WHITE_WIDTH / 2;
			y = y + FOURTH_OCTAVE_WHITE_WIDTH / 2;
			
			width = width - FOURTH_OCTAVE_WHITE_WIDTH;
			height = height - FOURTH_OCTAVE_WHITE_WIDTH;
		}
		
		// 16th part
		g.setColor(getColor());
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10;
		int y = QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10;
		List<Shape> octaveShapes = super.getOctaveShapes();

		octaveShapes.add(new Arc2D.Double(x + SixteenthNote.SIXTEENTH_WIDTH / 2, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return 1.5 / 8;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(QUARTER_NOTE_WIDTH / 2 + SixteenthNote.SIXTEENTH_WIDTH, QUARTER_NOTE_HEIGHT);
	}
}
