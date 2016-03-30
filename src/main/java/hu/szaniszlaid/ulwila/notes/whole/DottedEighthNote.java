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
import hu.szaniszlaid.ulwila.notes.util.Tone;

public class DottedEighthNote extends MusicNote {

	static final int rectWidth = QUARTER_NOTE_WIDTH / 3;
	public DottedEighthNote(Octave octave, Tone tone) {
		super(octave, tone);
	}

	@Override
	public void drawNote(Graphics2D g) {
		//8th part
		g.setColor(getColor());
		Arc2D quarter = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.OPEN);
		g.fill(quarter);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.CHORD);
		g.draw(border);

		//16th part
		g.setColor(getColor());
		g.fillRect(QUARTER_NOTE_WIDTH / 2 + MARGIN_LEFT, 0 + MARGIN_RIGHT, rectWidth, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(QUARTER_NOTE_WIDTH / 2 + MARGIN_LEFT, 0 + MARGIN_RIGHT, rectWidth, QUARTER_NOTE_HEIGHT);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10 + MARGIN_LEFT;
		int y = QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10 + MARGIN_TOP;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 90, 180, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + rectWidth / 2, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return 1.5 / 8;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(QUARTER_NOTE_WIDTH / 2 + rectWidth, QUARTER_NOTE_HEIGHT);
	}
}
