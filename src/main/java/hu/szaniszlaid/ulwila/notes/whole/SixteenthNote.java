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
		g.setColor(getColor());
		g.fillRect(0 + MARGIN_HORIZONTAL, 0 + MARGIN_VERTICAL, SIXTEENTH_WIDTH, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(0 + MARGIN_HORIZONTAL, 0 + MARGIN_VERTICAL, SIXTEENTH_WIDTH, QUARTER_NOTE_HEIGHT);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		double x = ((double) SIXTEENTH_WIDTH / 2 - (double) QUARTER_NOTE_WIDTH / 10) + MARGIN_HORIZONTAL;
		double y = ((double) QUARTER_NOTE_HEIGHT / 2 - (double) QUARTER_NOTE_HEIGHT / 10) + MARGIN_VERTICAL;

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
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
