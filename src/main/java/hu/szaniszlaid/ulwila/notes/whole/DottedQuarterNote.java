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

public class DottedQuarterNote extends QuarterNote {

	public DottedQuarterNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		super.drawNote(g);
		
		double noteWidth = QUARTER_NOTE_WIDTH;
		double noteHeight = QUARTER_NOTE_HEIGHT;
		double x = offsetX;
		double y = 0;
		

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			Arc2D whitePart = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
			g.fill(whitePart);
			
			g.setColor(Color.BLACK);			
			g.draw(whitePart);
			
			noteWidth = QUARTER_NOTE_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = x + FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}


		// Eight part
		g.setColor(getColor());
		Arc2D quarter = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(quarter);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(border);


	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10);
		int y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10);

		List<Shape> octaveShapes = super.getOctaveShapes();

		octaveShapes.add(new Arc2D.Double(x + offsetX, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 90, 180, Arc2D.OPEN));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return 1.5 / 4;
	}

	@Override
	public Dimension getSize() {
		// quarter eight
		int width = QUARTER_NOTE_WIDTH + QUARTER_NOTE_WIDTH / 2 - (QUARTER_NOTE_WIDTH - offsetX);
		return new Dimension(width, QUARTER_NOTE_HEIGHT);
	}
}
