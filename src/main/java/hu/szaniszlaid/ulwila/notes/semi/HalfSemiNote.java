package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;

public class HalfSemiNote extends HalfNote {

	int width = QUARTER_NOTE_WIDTH;
	int height = QUARTER_NOTE_HEIGHT;

	public HalfSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {

		// Second quarter
		int x = 0;
		int y = 0;
		
		int width = QUARTER_NOTE_WIDTH;
		int height = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(x + getNthOffset(1), y, width, height);
			g.setColor(Color.BLACK);
			g.drawOval(x + getNthOffset(1), y, width, height);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			width -= FOURTH_OCTAVE_WHITE_WIDTH;
			height -= FOURTH_OCTAVE_WHITE_WIDTH;

		}	
		g.setColor(getLeftColor());
		Arc2D secQuarterLeft = new Arc2D.Double(x + getNthOffset(1), y, width, height, 90, 180, Arc2D.OPEN);
		g.fill(secQuarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double secBorderLeft = new Arc2D.Double(x + getNthOffset(1), y, width, height, 90, 180, Arc2D.CHORD);
		g.draw(secBorderLeft);
		g.setColor(getRightColor());
		Arc2D secQuarterRight = new Arc2D.Double(x + getNthOffset(1), y, width, height, 90, -180, Arc2D.OPEN);
		g.fill(secQuarterRight);
		g.setColor(Color.BLACK);
		Arc2D secQuarterRightBorder = new Arc2D.Double(x + getNthOffset(1), y, width, height, 90, -180, Arc2D.CHORD);
		g.draw(secQuarterRightBorder);

		// First quarter
		x = 0;
		y = 0;
		
		width = QUARTER_NOTE_WIDTH;
		height = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(x + getNthOffset(0), y, width, height);
			g.setColor(Color.BLACK);
			g.drawOval(x + getNthOffset(0), y, width, height);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			width -= FOURTH_OCTAVE_WHITE_WIDTH;
			height -= FOURTH_OCTAVE_WHITE_WIDTH;

		}	
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(x, y, width, height, 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(x, y, width, height, 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);
		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(x, y, width, height, 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(x, y, width, height, 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);

	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = width / 2 - width / 10;
		int y = height / 2 - height / 10;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(1), y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;

	}

}
