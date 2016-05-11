package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;

public class EighthSemiNote extends EighthNote {

	public EighthSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {

		double width = QUARTER_NOTE_WIDTH;
		double height = QUARTER_NOTE_HEIGHT;
		double x = 0;
		double y = 0;		

		if (getOctave().equals(Octave.FOURTH)) {
			g.setColor(Color.WHITE);
			Arc2D whitePart = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.CHORD);
			g.fill(whitePart);
			
			g.setColor(Color.BLACK);			
			g.draw(whitePart);
			
			width = QUARTER_NOTE_WIDTH - FOURTH_OCTAVE_WHITE_WIDTH;
			height = QUARTER_NOTE_HEIGHT - FOURTH_OCTAVE_WHITE_WIDTH;

			x = FOURTH_OCTAVE_WHITE_WIDTH/2;
			y = FOURTH_OCTAVE_WHITE_WIDTH/2;
		}

		g.setColor(getRightColor());
		Arc2D right = new Arc2D.Double(x, y, width, height, 90, 180, Arc2D.CHORD);
		g.fill(right);
		g.setColor(Color.BLACK);
		g.draw(right);
		g.setColor(getLeftColor());
		Arc2D left = new Arc2D.Double(x, y, width, height, 110, 140, Arc2D.CHORD);
		g.fill(left);
		g.setColor(Color.BLACK);
		g.draw(left);
	}

}
