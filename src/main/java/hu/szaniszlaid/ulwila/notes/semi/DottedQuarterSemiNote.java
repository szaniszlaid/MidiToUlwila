package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.DottedQuarterNote;

public class DottedQuarterSemiNote extends DottedQuarterNote {

	public DottedQuarterSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		
		// eight part
		int noteWidth = QUARTER_NOTE_WIDTH;
		int noteHeight = QUARTER_NOTE_HEIGHT;
		int x = 0;
		int y = 0;
		

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			Arc2D whitePart = new Arc2D.Double(x + offsetX, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
			g.fill(whitePart);
			
			g.setColor(Color.BLACK);			
			g.draw(whitePart);
			
			noteWidth = QUARTER_NOTE_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = x + FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}
		
		g.setColor(getRightColor());
		Arc2D left = new Arc2D.Double(x + offsetX, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.fill(left);
		g.setColor(getLeftColor());
		Arc2D right = new Arc2D.Double(x + offsetX, y, noteWidth, noteHeight, 110, 140, Arc2D.CHORD);
		g.fill(right);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(x + offsetX, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(border);

		// quarter
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;

		x = 0;
		y = 0;

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
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);

		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(x, y, noteWidth, noteHeight, 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);
	}

}
