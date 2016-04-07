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
import hu.szaniszlaid.ulwila.notes.whole.DottedQuarterNote;

public class DottedQuarterSemiNote extends DottedQuarterNote {

	public DottedQuarterSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawNote(Graphics2D g) {
		// eight part
		g.setColor(getRightColor());
		Arc2D left = new Arc2D.Double(offsetX, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.CHORD);
		g.fill(left);
		g.setColor(getLeftColor());
		Arc2D right = new Arc2D.Double(offsetX, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 110, 140, Arc2D.CHORD);
		g.fill(right);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(offsetX, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.CHORD);
		g.draw(border);

		// quarter
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(0, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(0, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);

		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(0, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(0, 0, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_HEIGHT / 10;
		int y = QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_HEIGHT / 10;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
		return octaveShapes;

	}

}
