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

public class EighthNote extends MusicNote {

	public EighthNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {

		double noteWidth = QUARTER_NOTE_WIDTH;
		double noteHeight = QUARTER_NOTE_HEIGHT;
		double x = 0;
		double y = 0;
		

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			Arc2D whitePart = new Arc2D.Double(0, 0, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
			g.fill(whitePart);
			
			g.setColor(Color.BLACK);			
			g.draw(whitePart);
			
			noteWidth = QUARTER_NOTE_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}

		g.setColor(getColor());
		Arc2D quarter = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(quarter);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(border);

	}

	@Override
	public List<Shape> getOctaveShapes() {

		double radius = QUARTER_NOTE_WIDTH / 5.0;
		double x = ((double) QUARTER_NOTE_WIDTH / 2 - radius / 2);
		double y = ((double) QUARTER_NOTE_WIDTH / 2 - radius / 2);

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, radius, radius, 90, 180, Arc2D.OPEN));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 8;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(QUARTER_NOTE_WIDTH / 2, QUARTER_NOTE_HEIGHT);
	}
}
