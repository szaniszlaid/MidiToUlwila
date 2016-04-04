package hu.szaniszlaid.ulwila.notes.semi;

import static hu.szaniszlaid.ulwila.notes.whole.SixteenthNote.SIXTEENTH_WIDTH;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.DottedEighthNote;;

public class DottedEighthSemiNote extends DottedEighthNote {

	int width = QUARTER_NOTE_WIDTH;
	int height = QUARTER_NOTE_HEIGHT;

	public DottedEighthSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		//8th part
		g.setColor(getRightColor());
		Arc2D left = new Arc2D.Double(MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, height, 90, 180, Arc2D.CHORD);
		g.fill(left);
		g.setColor(getLeftColor());
		Arc2D right = new Arc2D.Double(MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, height, 110, 140, Arc2D.CHORD);
		g.fill(right);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(MARGIN_HORIZONTAL, MARGIN_VERTICAL, width, height, 90, 180, Arc2D.CHORD);
		g.draw(border);

		//16th part
		int sixteenthLeftX = QUARTER_NOTE_WIDTH / 2 + MARGIN_HORIZONTAL;
		g.setColor(getLeftColor());
		g.fillRect(sixteenthLeftX, MARGIN_VERTICAL, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(sixteenthLeftX, MARGIN_VERTICAL, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);

		int sixteenthRightX = QUARTER_NOTE_WIDTH / 2 + MARGIN_HORIZONTAL + SIXTEENTH_WIDTH / 2;
		g.setColor(getRightColor());
		g.fillRect(sixteenthRightX, MARGIN_VERTICAL, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(sixteenthRightX, MARGIN_VERTICAL, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int centerX = (width / 2 - width / 10) + MARGIN_HORIZONTAL;
		int centerY = (height / 2 - height / 10) + MARGIN_VERTICAL;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(centerX, centerY, width / 5, height / 5, 90, 180, Arc2D.OPEN));
		octaveShapes.add(new Arc2D.Double(centerX + SIXTEENTH_WIDTH / 2, centerY, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));

		return octaveShapes;

	}
}
