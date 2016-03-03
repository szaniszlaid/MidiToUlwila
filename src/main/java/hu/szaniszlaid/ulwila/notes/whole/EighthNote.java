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

public class EighthNote extends MusicNote {

	private static int width = QUARTER_NOTE_WIDTH;
	private static int height = QUARTER_NOTE_HEIGHT;

	public EighthNote(Octave octave, Tone tone) {
		super(octave, tone);
	}

	@Override
	public void drawNote(Graphics2D g) {
		g.setColor(getColor());
		Arc2D quarter = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, width, height, 90, 180, Arc2D.OPEN);
		g.fill(quarter);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, width, height, 90, 180, Arc2D.CHORD);
		g.draw(border);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = width / 2 - width / 10 + MARGIN_LEFT;
		int y = height / 2 - height / 10 + MARGIN_TOP;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 90, 180, Arc2D.OPEN));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 8;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width / 2, height);
	}
}
