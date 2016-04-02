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

public class DottedHalfNote extends MusicNote {

	public DottedHalfNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		//3rd oval
		g.setColor(getColor());
		g.fillOval(getNthOffset(2) + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
		//border
		g.setColor(Color.BLACK);
		g.drawOval(getNthOffset(2) + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

		//2nd oval
		g.setColor(getColor());
		g.fillOval(getNthOffset(1) + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
		//border
		g.setColor(Color.BLACK);
		g.drawOval(getNthOffset(1) + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

		//1st oval
		g.setColor(getColor());
		g.fillOval(0 + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
		//border
		g.setColor(Color.BLACK);
		g.drawOval(0 + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10) + MARGIN_LEFT;
		int y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10) + MARGIN_TOP;

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + offsetX, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(x + offsetX + getNthOffset(1), y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.OPEN));

		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return 1.5 / 2;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(QUARTER_NOTE_WIDTH + offsetX + getNthOffset(1), QUARTER_NOTE_HEIGHT);
	}

}
