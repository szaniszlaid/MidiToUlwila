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
import hu.szaniszlaid.ulwila.notes.whole.DottedHalfNote;

public class DottedSemiHalfNote extends DottedHalfNote {

	int width = QUARTER_NOTE_WIDTH;
	int height = QUARTER_NOTE_HEIGHT;

	public DottedSemiHalfNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {

		// Third quarter
		g.setColor(getLeftColor());
		Arc2D thirdQuarterLeft = new Arc2D.Double(getNthOffset(2), 0, width, height, 90, 180, Arc2D.OPEN);
		g.fill(thirdQuarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double thirdBorderLeft = new Arc2D.Double(getNthOffset(2), 0, width, height, 90, 180, Arc2D.CHORD);
		g.draw(thirdBorderLeft);
		g.setColor(getRightColor());
		Arc2D thirdQuarterRight = new Arc2D.Double(getNthOffset(2), 0, width, height, 90, -180, Arc2D.OPEN);
		g.fill(thirdQuarterRight);
		g.setColor(Color.BLACK);
		Arc2D thirdQuarterRightBorder = new Arc2D.Double(getNthOffset(2), 0, width, height, 90, -180, Arc2D.CHORD);
		g.draw(thirdQuarterRightBorder);

		// Second quarter
		g.setColor(getLeftColor());
		Arc2D secQuarterLeft = new Arc2D.Double(getNthOffset(1), 0, width, height, 90, 180, Arc2D.OPEN);
		g.fill(secQuarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double secBorderLeft = new Arc2D.Double(getNthOffset(1), 0, width, height, 90, 180, Arc2D.CHORD);
		g.draw(secBorderLeft);
		g.setColor(getRightColor());
		Arc2D secQuarterRight = new Arc2D.Double(getNthOffset(1), 0, width, height, 90, -180, Arc2D.OPEN);
		g.fill(secQuarterRight);
		g.setColor(Color.BLACK);
		Arc2D secQuarterRightBorder = new Arc2D.Double(getNthOffset(1), 0, width, height, 90, -180, Arc2D.CHORD);
		g.draw(secQuarterRightBorder);

		// First quarter
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);
		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(0, 0, width, height, 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(0, 0, width, height, 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);

	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = width / 2 - width / 10;
		int y = height / 2 - height / 10;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(1), y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
		octaveShapes.add(new Arc2D.Double(x + getNthOffset(2), y, width / 5, height / 5, 0, 360, Arc2D.CHORD));

		return octaveShapes;

	}
}
