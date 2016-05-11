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
import hu.szaniszlaid.ulwila.notes.whole.WholeNote;

public class WholeSemiNote extends WholeNote {

	public WholeSemiNote(Octave octave, Tone tone, PaintStyle paintStyle) {
		super(octave, tone, paintStyle);
	}

	@Override
	public void drawNote(Graphics2D g) {

		// Fourth quearter
		int x = 0;
		int y = 0;
		
		int noteWidth = QUARTER_NOTE_WIDTH;
		int noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(x + getNthOffset(3), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(x + getNthOffset(3), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getLeftColor());
		Arc2D frthQuarterLeft = new Arc2D.Double(x + getNthOffset(3), y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(frthQuarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double frthBorderLeft = new Arc2D.Double(x + getNthOffset(3), y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(frthBorderLeft);
		g.setColor(getRightColor());
		Arc2D frthQuarterRight = new Arc2D.Double(x + getNthOffset(3), y, noteWidth, noteHeight, 90, -180, Arc2D.OPEN);
		g.fill(frthQuarterRight);
		g.setColor(Color.BLACK);
		Arc2D frthQuarterRightBorder = new Arc2D.Double(x + getNthOffset(3), y, noteWidth, noteHeight, 90, -180, Arc2D.CHORD);
		g.draw(frthQuarterRightBorder);

		// Third quearter
		x = 0;
		y = 0;
		
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(x + getNthOffset(2), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(x + getNthOffset(2), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getLeftColor());
		Arc2D thrdQuarterLeft = new Arc2D.Double(x + getNthOffset(2), y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(thrdQuarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double thrdBorderLeft = new Arc2D.Double(x + getNthOffset(2), y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(thrdBorderLeft);
		g.setColor(getRightColor());
		Arc2D thrdQuarterRight = new Arc2D.Double(x + getNthOffset(2), y, noteWidth, noteHeight, 90, -180, Arc2D.OPEN);
		g.fill(thrdQuarterRight);
		g.setColor(Color.BLACK);
		Arc2D thrdQuarterRightBorder = new Arc2D.Double(x + getNthOffset(2), y, noteWidth, noteHeight, 90, -180, Arc2D.CHORD);
		g.draw(thrdQuarterRightBorder);

		// Second quarter
		x = 0;
		y = 0;
		
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(x + getNthOffset(1), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(x + getNthOffset(1), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getLeftColor());
		Arc2D secQuarterLeft = new Arc2D.Double(x + getNthOffset(1), y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(secQuarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double secBorderLeft = new Arc2D.Double(x + getNthOffset(1), y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(secBorderLeft);
		g.setColor(getRightColor());
		Arc2D secQuarterRight = new Arc2D.Double(x + getNthOffset(1), y, noteWidth, noteHeight, 90, -180, Arc2D.OPEN);
		g.fill(secQuarterRight);
		g.setColor(Color.BLACK);
		Arc2D secQuarterRightBorder = new Arc2D.Double(x + getNthOffset(1), y, noteWidth, noteHeight, 90, -180, Arc2D.CHORD);
		g.draw(secQuarterRightBorder);

		// First quarter
		x = 0;
		y = 0;
		
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(x + getNthOffset(0), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(x + getNthOffset(0), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(y, y, noteWidth, noteHeight, 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(y, y, noteWidth, noteHeight, 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);
		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(y, y, noteWidth, noteHeight, 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(y, y, noteWidth, noteHeight, 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int width = QUARTER_NOTE_WIDTH;
		int height = QUARTER_NOTE_HEIGHT;
		
		int x = width / 2 - width / 10;
		
		int y = height / 2 - height / 10;
		
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, y, 360, Arc2D.CHORD));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(1), y, width / 5, height / 5, y, 360, Arc2D.CHORD));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(2), y, width / 5, height / 5, y, 360, Arc2D.CHORD));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(3), y, width / 5, height / 5, y, 360, Arc2D.CHORD));
		return octaveShapes;

	}

}
