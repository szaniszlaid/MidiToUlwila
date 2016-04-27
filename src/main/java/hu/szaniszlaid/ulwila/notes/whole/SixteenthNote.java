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

public class SixteenthNote extends MusicNote {

	public static int SIXTEENTH_WIDTH = (int) Math.round(QUARTER_NOTE_WIDTH * 0.3);

	public SixteenthNote(Octave octave, Tone tone, PaintStyle paintStyle) {
		super(octave, tone, paintStyle);
	}

	@Override
	public void drawNote(Graphics2D g) {
		int width = SIXTEENTH_WIDTH;
		int height = QUARTER_NOTE_HEIGHT;
		
		int x = 0;
		int y = 0;

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, SIXTEENTH_WIDTH, QUARTER_NOTE_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIXTEENTH_WIDTH, QUARTER_NOTE_HEIGHT);
			
			width = SIXTEENTH_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			height = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}
		
		g.setColor(getColor());
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		double radius = QUARTER_NOTE_WIDTH / 5.0;
		double x = ((double) SIXTEENTH_WIDTH / 2 - radius/2);
		double y = ((double) QUARTER_NOTE_HEIGHT / 2 - radius/2);

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, radius, radius, 0, 360, Arc2D.CHORD));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 16;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(SIXTEENTH_WIDTH, QUARTER_NOTE_HEIGHT);
	}
}
