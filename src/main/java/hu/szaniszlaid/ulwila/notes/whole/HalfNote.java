package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicNote;

public class HalfNote extends MusicNote {

	int width = QUARTER_NOTE_WIDTH;
	int height = QUARTER_NOTE_HEIGHT;

	public HalfNote(Octave octave, Tone tone) {
		super(octave, tone);
	}

	@Override
	public void drawNote(Graphics2D g) {
		g.setColor(getColor());
		g.fillOval(offsetX + MARGIN_LEFT, 0 + MARGIN_TOP, width, height);
		g.setColor(Color.BLACK);
		g.drawOval(offsetX + MARGIN_LEFT, 0 + MARGIN_TOP, width, height);
		g.setColor(getColor());
		g.fillOval(0 + MARGIN_LEFT, 0 + MARGIN_TOP, width, height);
		g.setColor(Color.BLACK);
		g.drawOval(0 + MARGIN_LEFT, 0 + MARGIN_TOP, width, height);

	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = (width / 2 - width / 10) + MARGIN_LEFT;
		int y = (height / 2 - height / 10) + MARGIN_TOP;
		
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + offsetX, y, width / 5, height / 5, 0, 360, Arc2D.OPEN));
		
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return (double) 1/2;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width + offsetX, height);
	}
}
