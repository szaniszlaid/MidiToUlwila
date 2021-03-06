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

public class WholeNote extends MusicNote {

	public WholeNote(Octave octave, Tone tone, PaintStyle paintStyle) {
		super(octave, tone, paintStyle);
	}

	@Override
	public void drawNote(Graphics2D g) {
		//4th quarter
		int x = 0;
		int y = 0;
		
		int noteWidth = QUARTER_NOTE_WIDTH;
		int noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(getNthOffset(3), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(getNthOffset(3), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getColor());
		g.fillOval(x + getNthOffset(3), y, noteWidth, noteHeight);
		g.setColor(Color.BLACK);
		g.drawOval(x + getNthOffset(3), y, noteWidth, noteHeight);

		//3th quarter
		x = 0;
		y = 0;
		
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(getNthOffset(2), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(getNthOffset(2), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getColor());
		g.fillOval(x + getNthOffset(2), y, noteWidth, noteHeight);
		g.setColor(Color.BLACK);
		g.drawOval(x + getNthOffset(2), y, noteWidth, noteHeight);

		//2th quarter
		x = 0;
		y = 0;
		
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(getNthOffset(1), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(getNthOffset(1), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getColor());
		g.fillOval(x + getNthOffset(1), y, noteWidth, noteHeight);
		g.setColor(Color.BLACK);
		g.drawOval(x + getNthOffset(1), y, noteWidth, noteHeight);

		//1th quarter
		x = 0;
		y = 0;
		
		noteWidth = QUARTER_NOTE_WIDTH;
		noteHeight = QUARTER_NOTE_HEIGHT;
		if (getOctave().equals(Octave.FOURTH)) {
			
			g.setColor(Color.WHITE);
			g.fillOval(getNthOffset(0), y, noteWidth, noteHeight);
			g.setColor(Color.BLACK);
			g.drawOval(getNthOffset(0), y, noteWidth, noteHeight);	
			
			x += FOURTH_OCTAVE_WHITE_WIDTH/2;
			y += FOURTH_OCTAVE_WHITE_WIDTH/2;
			
			noteWidth -= FOURTH_OCTAVE_WHITE_WIDTH;
			noteHeight -= FOURTH_OCTAVE_WHITE_WIDTH;

		}
		g.setColor(getColor());
		g.fillOval(x + getNthOffset(0), y, noteWidth, noteHeight);
		g.setColor(Color.BLACK);
		g.drawOval(x + getNthOffset(0), y, noteWidth, noteHeight);

	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10);
		int y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10);

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(1), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(2), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(3), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));

		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return 1;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(QUARTER_NOTE_WIDTH + getNthOffset(3), QUARTER_NOTE_HEIGHT);
	}
}
