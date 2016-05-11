package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNote;

public class QuarterSemiNote extends QuarterNote {



	public QuarterSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		
		int width = QUARTER_NOTE_WIDTH;
		int height = QUARTER_NOTE_HEIGHT;

		int x = 0;
		int y = 0;

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			g.fillOval(x, y, width, height);

			g.setColor(Color.BLACK);
			g.drawOval(x, y, width, height);

			width = QUARTER_NOTE_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			height = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}
		
		//Left part
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(x, y, width, height, 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		//Border
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(x, y, width, height, 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);
		
		//Right part
		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(x, y, width, height, 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		//Border
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(x, y, width, height, 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);
	}
}
